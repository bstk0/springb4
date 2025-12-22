package br.com.dbengine.springb4.service;

import br.com.dbengine.springb4.dbUtil.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;

@Service
public class HasuraClientService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    @Autowired
    public HasuraClientService(WebClient.Builder webClientBuilder,
                               ObjectMapper objectMapper,
                               @Value("${hasura-key2}") String adminSecret) {

        this.objectMapper = objectMapper;
        String apiUrl = "https://ukygdppeibetcsiohtzm.hasura.sa-east-1.nhost.run";

        this.webClient = webClientBuilder.baseUrl(apiUrl )
                // HEADER DA CHAVE
                .defaultHeader("x-hasura-admin-secret", adminSecret)
                // HEADERS ADICIONAIS NA MARRA
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Accept", "application/json")
                .build();
    }

    public <R> Mono<R> execute(HttpMethod method, String uri, Class<R> responseType) {
        return this.execute(method, uri, null, responseType);
    }

    public <T, R> Mono<R> execute(HttpMethod method, String uri, T requestBody, Class<R> responseType) {
        WebClient.RequestBodySpec request = webClient
                .method(method)
                .uri(uri); // A URI para GraphQL é sempre a mesma: /v1/graphql

        WebClient.RequestHeadersSpec<?> spec;
        if (requestBody != null) {
            // Para GraphQL, o corpo é sempre um Map<String, Object>
            spec = request.body(Mono.just(requestBody), Map.class);
        } else {
            spec = request;
        }

        return spec.retrieve()
                .bodyToMono(String.class)
                .doOnNext(responseBodyString -> {
                    Sysout.s("--- RESPOSTA CRUA DA API HASURA ---");
                    Sysout.s(responseBodyString);
                    Sysout.s("---------------------------------");
                })
                .flatMap(responseBodyString -> {
                    try {
                        return Mono.just(objectMapper.readValue(responseBodyString, responseType));
                    } catch (JsonProcessingException e) {
                        Sysout.s("### ERRO DE DESSERIALIZAÇÃO JSON ###");
                        e.printStackTrace();
                        return Mono.empty();
                    }
                });
    }

    public <T> Mono<Void> executeAndForget(HttpMethod method, String uri, T requestBody) {
        WebClient.RequestBodySpec request = webClient.method(method).uri(uri);
        WebClient.RequestHeadersSpec<?> spec = (requestBody != null) ? request.body(Mono.just(requestBody), requestBody.getClass()) : request;
        return spec.retrieve().bodyToMono(Void.class);
    }
}
