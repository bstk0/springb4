package br.com.dbengine.springb4.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
//import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

//@Data
//@Getter
//@Setter
//@AllArgsConstructor
public class CreateSaldoRequestDTO {
    @JsonProperty("id_banco")
    private int idBanco;

    @JsonProperty("id_aplic")
    private int idAplicacao;

    @JsonProperty("dt_saldo")
    private LocalDate dtSaldo;

    @JsonProperty("vl_saldo")
    private BigDecimal vlSaldo;

    public int getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(int idBanco) {
        this.idBanco = idBanco;
    }

    public int getIdAplicacao() {
        return idAplicacao;
    }

    public void setIdAplicacao(int idAplicacao) {
        this.idAplicacao = idAplicacao;
    }

    public LocalDate getDtSaldo() {
        return dtSaldo;
    }

    public void setDtSaldo(LocalDate dtSaldo) {
        this.dtSaldo = dtSaldo;
    }

    public BigDecimal getVlSaldo() {
        return vlSaldo;
    }

    public void setVlSaldo(BigDecimal vlSaldo) {
        this.vlSaldo = vlSaldo;
    }

    public CreateSaldoRequestDTO() {
    }

    public CreateSaldoRequestDTO(int idBanco, int idAplicacao, LocalDate dtSaldo, BigDecimal vlSaldo) {
        this.idBanco = idBanco;
        this.idAplicacao = idAplicacao;
        this.dtSaldo = dtSaldo;
        this.vlSaldo = vlSaldo;
    }
}
