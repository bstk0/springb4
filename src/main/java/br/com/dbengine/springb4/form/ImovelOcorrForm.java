package br.com.dbengine.springb4.form;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImovelOcorrForm {
    private String id;
    private Integer imovelId;
    private String descricao;
    private String nr_ref;
    private String statusFinal;
    private String dataCriacao;

    private String dataAtualizacao;

    public ImovelOcorrForm() {
    }

    public ImovelOcorrForm(String id, Integer imovelId, String descricao,
                           String nr_ref, String statusFinal,
                           String dataCriacao,
                           String dataAtualizacao) {
        this.id = id;
        this.imovelId = imovelId;
        this.descricao = descricao;
        this.nr_ref = nr_ref;
        this.statusFinal = statusFinal;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }
}
