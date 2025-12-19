package br.com.dbengine.springb4.dto;

// Não precisamos mais do @JsonProperty se os nomes dos campos forem idênticos
// import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

// Vamos usar getters/setters manuais para garantir que não haja mais problemas
public class SaldoHasuraDTO {

    // Nomes dos campos agora são idênticos aos do JSON da API
    private String id;
    private int id_banco;
    private int id_aplic;
    private LocalDate dt_saldo;
    private BigDecimal vl_saldo;

    // Getters e Setters manuais
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public int getId_banco() { return id_banco; }
    public void setId_banco(int id_banco) { this.id_banco = id_banco; }

    public int getId_aplic() { return id_aplic; }
    public void setId_aplic(int id_aplic) { this.id_aplic = id_aplic; }

    public LocalDate getDt_saldo() { return dt_saldo; }
    public void setDt_saldo(LocalDate dt_saldo) { this.dt_saldo = dt_saldo; }

    public BigDecimal getVl_saldo() { return vl_saldo; }
    public void setVl_saldo(BigDecimal vl_saldo) { this.vl_saldo = vl_saldo; }
}
