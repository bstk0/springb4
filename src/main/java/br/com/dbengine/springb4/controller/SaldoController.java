package br.com.dbengine.springb4.controller;

import br.com.dbengine.springb4.Enum.BancoEnum;
import br.com.dbengine.springb4.Enum.TipoAplicacaoEnum;
import br.com.dbengine.springb4.dto.*;
import br.com.dbengine.springb4.service.BancoAplicacaoService;
import br.com.dbengine.springb4.service.HasuraClientService;
import com.fasterxml.jackson.databind.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/saldos" )
public class SaldoController {

    private final HasuraClientService hasuraClient;
    private final BancoAplicacaoService bancoAplicacaoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public SaldoController(HasuraClientService hasuraClient, BancoAplicacaoService bancoAplicacaoService) {
        this.hasuraClient = hasuraClient;
        this.bancoAplicacaoService = bancoAplicacaoService;
    }

    @GetMapping
    public Mono<String> listarSaldos(Model model) {
        return hasuraClient.execute(HttpMethod.GET, "/api/rest/Saldos", SaldosApiResponseDTO.class)
                .map(apiResponse -> {
                    if (apiResponse != null && apiResponse.getSaldos() != null) {
                        List<SaldoViewDTO> saldosParaView = apiResponse.getSaldos().stream()
                                .map(this::converterParaViewDTO)
                                .collect(Collectors.toList());
                        model.addAttribute("saldos", saldosParaView);
                    }
                    return "lista-saldos";
                });
    }

    @GetMapping("/novo")
    public String mostrarFormularioCriacao(Model model) {
        model.addAttribute("formularioSaldoDTO", new FormularioSaldoDTO());
        model.addAttribute("bancos", BancoEnum.values());
        model.addAttribute("isEdicao", false);
        return "form-saldos";
    }

    @GetMapping("/editar/{id}")
    public Mono<String> mostrarFormularioEdicao(@PathVariable String id, Model model) {
        // --- A PORRA DA CORREÇÃO ESTÁ AQUI ---
        // A URL É A QUE FUNCIONA NO POSTMAN. PONTO FINAL.
        String uri = "/api/rest/Saldos/" + id;

        // 1. A chamada agora espera receber um Map<String, Object> genérico.
        return hasuraClient.execute(HttpMethod.GET, uri, Map.class)
                .map(responseMap -> {
                    // 2. Extraímos o objeto que está dentro da chave "Saldos_by_pk".
                    Map<String, Object> saldoMap = (Map<String, Object>) responseMap.get("Saldos_by_pk");

                    // 3. Usamos o ObjectMapper para converter esse Map interno para o nosso SaldoHasuraDTO.
                    SaldoHasuraDTO saldoHasura = objectMapper.convertValue(saldoMap, SaldoHasuraDTO.class);

                    // 4. O resto do código continua igual.
                    model.addAttribute("formularioSaldoDTO", converterParaFormularioDTO(saldoHasura));
                    model.addAttribute("bancos", BancoEnum.values());
                    model.addAttribute("isEdicao", true);
                    model.addAttribute("id", id);
                    return "form-saldos";
                });
    }


    @PostMapping("/salvar")
    public Mono<String> salvarSaldo(@ModelAttribute FormularioSaldoDTO formularioDTO, @RequestParam(required = false) String id) {
        if (id != null && !id.isEmpty()) {
            // --- LÓGICA DE ATUALIZAÇÃO (UPDATE VIA POST) CORRIGIDA ---

            // 1. A URI é a de Saldos com o ID no final.
            String uri = "/api/rest/Saldos/" + id;

            // 2. Criamos o objeto interno com os novos dados.
            CreateSaldoRequestDTO saldoData = converterParaRequestDTO(formularioDTO);

            // 3. Criamos o corpo da requisição com a chave "object".
            Map<String, CreateSaldoRequestDTO> requestBody = new HashMap<>();
            requestBody.put("object", saldoData);

            // 4. A chamada é um POST para a URI de update, com o corpo correto.
            return hasuraClient.executeAndForget(HttpMethod.POST, uri, requestBody)
                    .then(Mono.just("redirect:/saldos"));

        } else {
            // --- LÓGICA DE CRIAÇÃO (POST) ---
            CreateSaldoRequestDTO saldoData = converterParaRequestDTO(formularioDTO);
            Map<String, CreateSaldoRequestDTO> requestBody = new HashMap<>();
            requestBody.put("object", saldoData);

            return hasuraClient.executeAndForget(HttpMethod.POST, "/api/rest/Saldos", requestBody)
                    .then(Mono.just("redirect:/saldos"));
        }
    }




    @PostMapping("/deletar/{id}")
    public Mono<String> deletarSaldo(@PathVariable String id) {
        String uri = "/api/rest/Saldos/" + id;
        return hasuraClient.executeAndForget(HttpMethod.DELETE, uri, null)
                .then(Mono.just("redirect:/saldos"));
    }

    @GetMapping("/api/tipos-por-banco")
    @ResponseBody
    public List<Map<String, String>> getTiposPorBanco(@RequestParam String banco) {
        BancoEnum bancoEnum = BancoEnum.fromName(banco);
        return bancoAplicacaoService.getTiposPorBanco(bancoEnum).stream()
                .map(tipo -> {
                    Map<String, String> tipoMap = new HashMap<>();
                    tipoMap.put("name", tipo.name());
                    tipoMap.put("descricao", tipo.getDescricao());
                    return tipoMap;
                })
                .collect(Collectors.toList());
    }

    // --- MÉTODOS PRIVADOS DE CONVERSÃO ---

    private SaldoViewDTO converterParaViewDTO(SaldoHasuraDTO saldoHasura) {
        BancoEnum banco = BancoEnum.fromCodigo(saldoHasura.getId_banco());
        TipoAplicacaoEnum tipo = TipoAplicacaoEnum.fromCodigo(saldoHasura.getId_aplic());

        SaldoViewDTO viewDTO = new SaldoViewDTO();
        viewDTO.setId(saldoHasura.getId());
        viewDTO.setNomeBanco((banco != null) ? banco.getNome() : "Inválido");
        viewDTO.setNomeAplicacao((tipo != null) ? tipo.getDescricao() : "Inválido");
        viewDTO.setData(saldoHasura.getDt_saldo());
        viewDTO.setValor(saldoHasura.getVl_saldo());
        return viewDTO;
    }

    private FormularioSaldoDTO converterParaFormularioDTO(SaldoHasuraDTO saldoHasura) {
        BancoEnum banco = BancoEnum.fromCodigo(saldoHasura.getId_banco());
        TipoAplicacaoEnum tipo = TipoAplicacaoEnum.fromCodigo(saldoHasura.getId_aplic());

        FormularioSaldoDTO formularioDTO = new FormularioSaldoDTO();
        formularioDTO.setBanco((banco != null) ? banco.name() : null);
        formularioDTO.setAplicacao((tipo != null) ? tipo.name() : null);
        formularioDTO.setData(saldoHasura.getDt_saldo());
        formularioDTO.setValor(saldoHasura.getVl_saldo());
        return formularioDTO;
    }

    private CreateSaldoRequestDTO converterParaRequestDTO(FormularioSaldoDTO formularioDTO) {
        BancoEnum banco = BancoEnum.fromName(formularioDTO.getBanco());
        TipoAplicacaoEnum tipo = TipoAplicacaoEnum.fromName(formularioDTO.getAplicacao());

        if (banco == null || tipo == null) {
            throw new IllegalArgumentException("Banco ou Tipo de Aplicação inválido.");
        }
        return new CreateSaldoRequestDTO(
                banco.getCodigo(),
                tipo.getCodigo(),
                formularioDTO.getData(),
                formularioDTO.getValor()
        );
    }
}
