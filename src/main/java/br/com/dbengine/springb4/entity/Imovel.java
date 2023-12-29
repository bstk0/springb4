package br.com.dbengine.springb4.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Imovel {
    // id	inscricao	 apelido	tipo	imovel	imobiliaria	bairro observacoes
    private String id;

    public int getImovelId() {
        return imovelId;
    }

    public void setImovelId(int imovelId) {
        this.imovelId = imovelId;
    }

    private int imovelId;
    private String inscricao;
    private String apelido;
    private String tipo;
    private String descricao;
    private String imobiliaria;
    private String bairro;
    private String observacoes;
    private String status;
    private String createdAt;

    //private long __createdtime__;

    //private long __updatedtime__;

    // constructor


    public Imovel(String id, String inscricao, String apelido, String tipo, String descricao,
                  String imobiliaria, String bairro, String observacoes, int imovelId) {
                  //long __createdtime__, long __updatedtime__) {
        this.id = id;
        this.imovelId = imovelId;
        this.inscricao = inscricao;
        this.apelido = apelido;
        this.tipo = tipo;
        this.descricao = descricao;
        this.imobiliaria = imobiliaria;
        this.bairro = bairro;
        this.observacoes = observacoes;
        //this.__createdtime__ = __createdtime__;
        //this.__updatedtime__ = __updatedtime__;
    }

    public Imovel() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInscricao() {
        return inscricao;
    }

    public void setInscricao(String inscricao) {
        this.inscricao = inscricao;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImobiliaria() {
        return imobiliaria;
    }

    public void setImobiliaria(String imobiliaria) {
        this.imobiliaria = imobiliaria;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
