package br.com.dbengine.springb4.Enum;

import com.fasterxml.jackson.annotation.*;

// Opcional, mas útil para APIs REST: Garante que o enum seja serializado como um objeto JSON completo
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum BancoEnum {
    // 1. Defina suas constantes, passando os valores para o construtor
    SANTANDER(1, "Santander"),
    MERCADO_PAGO(2, "Mercado Pago"),
    PIC_PAY(3, "PicPay"),
    BANCO_XP(4, "XP"),
    GERDAU_PREV(10, "GERDAU PREVIDENCIA"),
    ICATU(11, "ICATU");

    // 2. Adicione os campos (fields) que cada constante terá
    private final int codigo;
    private final String nome;

    // 3. Crie o construtor para inicializar os campos
    // O construtor de um enum é sempre privado (private)
    BancoEnum(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    // 4. Crie os getters para acessar os valores
    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    // --- MÉTODOS ÚTEIS ADICIONAIS (MUITO RECOMENDADO) ---

    /**
     * Permite encontrar um enum pelo seu código.
     * @param codigo O código do banco (ex: 1).
     * @return O BancoEnum correspondente, ou null se não for encontrado.
     */
    public static BancoEnum fromCodigo(int codigo) {
        for (BancoEnum banco : BancoEnum.values()) {
            if (banco.codigo == codigo) {
                return banco;
            }
        }
        return null; // Ou lançar uma exceção: throw new IllegalArgumentException("Código inválido: " + codigo);
    }

    /**
     * Busca uma constante do enum a partir de seu nome em formato String.
     * Este método é "case-sensitive" (diferencia maiúsculas de minúsculas).
     * Exemplo de uso: BancoEnum.fromName("BRADESCO") retorna BancoEnum.BRADESCO.
     *
     * @param name O nome da constante do enum (ex: "BRADESCO", "ITAU").
     * @return A constante do enum correspondente, ou null se não for encontrada.
     */
    public static BancoEnum fromName(String name) {
        // O método estático Enum.valueOf() faz exatamente isso,
        // mas ele lança uma exceção (IllegalArgumentException) se o nome não for encontrado.
        // Envolvê-lo em um try-catch torna nosso método mais seguro, retornando null em caso de erro.
        try {
            return BancoEnum.valueOf(name);
        } catch (IllegalArgumentException | NullPointerException e) {
            // Retorna null se o nome for inválido ou nulo, evitando que a aplicação quebre.
            return null;
        }
    }

}

