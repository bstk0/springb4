package br.com.dbengine.springb4.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ImovelFinanceiro {
    private String __createdtime__;
    private String __updatedtime__;
    private Integer imovel_id;
    private String codLUZ;
    private Double vlAluguel;
    private Double vlCondominio;
    private Double vlIPTU;
    private Double vlIPTUDesc;
    private Integer diaPagtoAluguel;
    private String cpfCadastrado;
    private Integer diaPagtoCondominio;
    private String nroContrato;
    //private Date dtInicioContrato;
    private String dtInicioContrato;
    //private Date dtFimContrato;
    private String dtFimContrato;
    private String dadosGerais;
    private String codDAEM;
    private String nroInscrPrefeitura;
    private Integer imobiliaria_id;
    private String sindico;
    private String administradora;
    private String createdBy;
    private String updatedBy;

    public _ImovelFinanceiro() {
    }

    public _ImovelFinanceiro(String __createdtime__, String __updatedtime__,
                             Integer imovel_id, String codLUZ, Double vlAluguel, Double vlCondominio,
                             Double vlIPTU, Double vlIPTUDesc, Integer diaPagtoAluguel,
                             String cpfCadastrado, Integer diaPagtoCondominio,
                             String nroContrato, String dtInicioContrato, String dtFimContrato,
                             String dadosGerais, String codDAEM, String nroInscrPrefeitura,
                             Integer imobiliaria_id,
                             String sindico, String administradora,
                             String createdBy, String updatedBy) {
        this.__createdtime__ = __createdtime__;
        this.__updatedtime__ = __updatedtime__;
        this.imovel_id = imovel_id;
        this.codLUZ = codLUZ;
        this.vlAluguel = vlAluguel;
        this.vlCondominio = vlCondominio;
        this.vlIPTU = vlIPTU;
        this.vlIPTUDesc = vlIPTUDesc;
        this.diaPagtoAluguel = diaPagtoAluguel;
        this.cpfCadastrado = cpfCadastrado;
        this.diaPagtoCondominio = diaPagtoCondominio;
        this.nroContrato = nroContrato;
        this.dtInicioContrato = dtInicioContrato;
        this.dtFimContrato = dtFimContrato;
        this.dadosGerais = dadosGerais;
        this.codDAEM = codDAEM;
        this.nroInscrPrefeitura = nroInscrPrefeitura;
        this.imobiliaria_id = imobiliaria_id;
        this.sindico = sindico;
        this.administradora = administradora;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
