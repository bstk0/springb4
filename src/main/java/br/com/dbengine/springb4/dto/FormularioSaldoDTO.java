package br.com.dbengine.springb4.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO que representa os dados enviados pelo formulário HTML,
 * tanto para criação quanto para edição de um saldo.
 */
public class FormularioSaldoDTO {

    // Os nomes dos campos correspondem aos atributos 'name' do formulário HTML
    private String banco;       // Nome do enum do banco (ex: "BRADESCO")
    private String aplicacao;   // Nome do enum da aplicação (ex: "CDB")

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate data;

    private BigDecimal valor;

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getAplicacao() {
        return aplicacao;
    }

    public void setAplicacao(String aplicacao) {
        this.aplicacao = aplicacao;
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
}

