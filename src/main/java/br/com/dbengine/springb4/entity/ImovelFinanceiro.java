package br.com.dbengine.springb4.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImovelFinanceiro {
    private String id;                  //ok
    private int imovelId;               //ok
    private String cd_luz;              //ok
    private Double vl_aluguel;          //ok
    private Double vl_condom;           //ok
    private Double vl_iptu;             //ok
    private Double vl_iptu_desc;        //ok
    private Integer diaPagtoAluguel;    //ok
    private String cpfCadastrado;       //ok
    private Integer diaPagtoCond;       //ok
    private String nr_contrato;         //ok
    //private Date dtInicioContrato;
    private String dtInicioContr;       //ok
    //private Date dtFimContrato;
    private String dtFimContr;          //ok
    private String dadosGerais;         //ok
    private String cd_daem;             //ok
    private String nr_inscr;            //ok
    private Integer imobiliaria_id;
    private String sindico;             //ok
    private String administradora;      //ok

    public ImovelFinanceiro() {
    }

    public ImovelFinanceiro(String id, int imovelId, String cd_luz, Double vl_aluguel,
                            Double vl_condom, Double vl_iptu, Double vl_iptu_desc,
                            Integer diaPagtoAluguel, String cpfCadastrado, Integer diaPagtoCond,
                            String nr_contrato, String dtInicioContr, String dtFimContr,
                            String dadosGerais, String cd_daem, String nr_inscr,
                            Integer imobiliaria_id, String sindico, String administradora) {
        this.id = id;
        this.imovelId = imovelId;
        this.cd_luz = cd_luz;
        this.vl_aluguel = vl_aluguel;
        this.vl_condom = vl_condom;
        this.vl_iptu = vl_iptu;
        this.vl_iptu_desc = vl_iptu_desc;
        this.diaPagtoAluguel = diaPagtoAluguel;
        this.cpfCadastrado = cpfCadastrado;
        this.diaPagtoCond = diaPagtoCond;
        this.nr_contrato = nr_contrato;
        this.dtInicioContr = dtInicioContr;
        this.dtFimContr = dtFimContr;
        this.dadosGerais = dadosGerais;
        this.cd_daem = cd_daem;
        this.nr_inscr = nr_inscr;
        this.imobiliaria_id = imobiliaria_id;
        this.sindico = sindico;
        this.administradora = administradora;
    }
}
