package br.com.dbengine.springb4.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO (Data Transfer Object) para representar os dados de um Saldo
 * da forma como eles serão exibidos na tela (View).
 * Este objeto é "enriquecido" com os nomes amigáveis do banco e da aplicação.
 */
//@Data // Gera Getters, Setters, toString(), equals() e hashCode()
//@Getter
//@Setter
//@NoArgsConstructor // Gera um construtor sem argumentos
//@AllArgsConstructor // Gera um construtor com todos os campos como argumentos
public class SaldoViewDTO {

    /**
     * O ID do saldo, vindo diretamente do Hasura (geralmente um UUID).
     */
    private String id;

    /**
     * O nome amigável do Banco, "traduzido" a partir do id_banco usando nosso BancoEnum.
     */
    private String nomeBanco;

    /**
     * A descrição amigável do Tipo de Aplicação, "traduzida" a partir do id_aplic usando nosso TipoAplicacaoEnum.
     */
    private String nomeAplicacao;

    /**
     * A data do saldo.
     */
    private LocalDate data;

    /**
     * O valor do saldo.
     */
    private BigDecimal valor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    public String getNomeAplicacao() {
        return nomeAplicacao;
    }

    public void setNomeAplicacao(String nomeAplicacao) {
        this.nomeAplicacao = nomeAplicacao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public SaldoViewDTO(String id, String nomeBanco, String nomeAplicacao, LocalDate data, BigDecimal valor) {
        this.id = id;
        this.nomeBanco = nomeBanco;
        this.nomeAplicacao = nomeAplicacao;
        this.data = data;
        this.valor = valor;
    }

    public SaldoViewDTO() {
    }
}
