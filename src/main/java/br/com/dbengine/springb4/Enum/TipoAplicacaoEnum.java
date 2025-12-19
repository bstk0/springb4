package br.com.dbengine.springb4.Enum;

import com.fasterxml.jackson.annotation.*;

// Adicionamos o @JsonFormat para garantir que a API REST retorne o objeto completo
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoAplicacaoEnum {

    // 1. Defina as constantes com seus novos atributos
    SALDO(1, "SALDO CONTA"),
    CDB(2, "CDB"),
    PREVIDENCIA(3, "Previdência Privada"),
    ACOES(4, "Ações"),
    FUNDOS_IMOBILIARIOS(5, "Fundos Imobiliários");

    // 2. Adicione os campos
    private final int codigo;
    private final String descricao;
    private final String name; // Campo para o nome do enum, útil para o JSON

    // 3. Crie o construtor
    TipoAplicacaoEnum(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.name = this.name(); // Captura o nome da constante (ex: "CDB")
    }

    // 4. Crie os getters
    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getName() {
        return name;
    }

    // 5. (Opcional, mas recomendado) Método para buscar pelo código
    public static TipoAplicacaoEnum fromCodigo(int codigo) {
        for (TipoAplicacaoEnum tipo : TipoAplicacaoEnum.values()) {
            if (tipo.codigo == codigo) {
                return tipo;
            }
        }
        return null;
    }

    public static TipoAplicacaoEnum fromName(String name) {
        try {
            return TipoAplicacaoEnum.valueOf(name);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}