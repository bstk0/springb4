package br.com.dbengine.springb4.form;

public class ImovelOcorrForm {
    private String id;
    private Integer imovel_id;
    private String descricao;
    private String numero_ref;
    private String status_final;
    private String dataCriacao;

    private String dataAtualizacao;

    public ImovelOcorrForm() {
    }

    public ImovelOcorrForm(String id, Integer imovel_id,
                           String descricao,
                           String numero_ref,
                           String status_final,
                           String dataCriacao,
                           String dataAtualizacao) {
        this.id = id;
        this.imovel_id = imovel_id;
        this.descricao = descricao;
        this.numero_ref = numero_ref;
        this.status_final = status_final;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
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

    public String getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(String dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
