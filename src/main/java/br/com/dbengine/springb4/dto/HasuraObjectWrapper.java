package br.com.dbengine.springb4.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Encapsula um objeto de dados dentro de uma chave "object",
 * que é a estrutura esperada pelos endpoints REST padrão do Hasura
 * para operações de escrita (POST e PUT).
 * @param <T> O tipo do objeto de dados.
 */
public class HasuraObjectWrapper<T> {

    @JsonProperty("object")
    private T object;

    // Construtor
    public HasuraObjectWrapper(T object) {
        this.object = object;
    }

    // Getter (necessário para o Jackson serializar o objeto)
    public T getObject() {
        return this.object;
    }
}

