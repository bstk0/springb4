package br.com.dbengine.springb4.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown = true)
public class ImovelForm {
    // id	inscricao	 apelido	tipo	imovel	imobiliaria	bairro observacoes
    private long id;
    private String inscricao;
    private String apelido;
    private String tipo;
    private String imovel;
    private String imobiliaria;
    private String bairro;
    private String observacoes;

    private long __createdtime__;

    private long __updatedtime__;

    public long get__createdtime__() {
        return __createdtime__;
    }

    public void set__createdtime__(long __createdtime__) {
        this.__createdtime__ = __createdtime__;
    }

    public long get__updatedtime__() {
        return __updatedtime__;
    }

    public void set__updatedtime__(long __updatedtime__) {
        this.__updatedtime__ = __updatedtime__;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getImovel() {
        return imovel;
    }

    public void setImovel(String imovel) {
        this.imovel = imovel;
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
}
