package br.com.dbengine.springb4.entity;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class ImovelOcorrencia {
    private String id;
    private Integer imovel_id;
    private String descricao;
    private String numero_ref;
    private String status_final;

    private long __createdtime__;

    private long __updatedtime__;

    private String createdBy;

    private String updatedBy;

    public ImovelOcorrencia() {
    }

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



    public ImovelOcorrencia(String id, Integer imovel_id, String descricao, String numero_ref,
                            String status_final,
                            long __createdtime__,
                            long  __updatedtime__) {
        this.id = id;
        this.imovel_id = imovel_id;
        this.descricao = descricao;
        this.numero_ref = numero_ref;
        this.status_final = status_final;
        this.__createdtime__ = __createdtime__;
        this.__updatedtime__ = __updatedtime__;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getImovel_id() {
        return imovel_id;
    }

    public void setImovel_id(Integer imovel_id) {
        this.imovel_id = imovel_id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNumero_ref() {
        return numero_ref;
    }

    public void setNumero_ref(String numero_ref) {
        this.numero_ref = numero_ref;
    }

    public String getStatus_final() {
        return status_final;
    }

    public void setStatus_final(String status_final) {
        this.status_final = status_final;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
