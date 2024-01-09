package br.com.dbengine.springb4.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown=true)
public class Imovel {
    // id	inscricao	 apelido	tipo	imovel	imobiliaria	bairro observacoes
    private String id;
    private int imovelId;
    private String apelido;
    private String tipo;
    private String descricao;
    private String imobiliaria;
    private String bairro;
    private String observacoes;
    private String status;
    private String createdAt;

    public Imovel() {
    }

    public Imovel(String id, int imovelId, String apelido, String tipo, String descricao, String imobiliaria,
                  String bairro, String observacoes, String status, String createdAt) {
        this.id = id;
        this.imovelId = imovelId;
        this.apelido = apelido;
        this.tipo = tipo;
        this.descricao = descricao;
        this.imobiliaria = imobiliaria;
        this.bairro = bairro;
        this.observacoes = observacoes;
        this.status = status;
        this.createdAt = createdAt;
    }

}
