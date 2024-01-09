package br.com.dbengine.springb4.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImovelOcorrencia {
    private String id;
    private Integer imovelId;
    private String descricao;
    private String nr_ref;
    private String statusFinal;

    public ImovelOcorrencia() {
    }

    public ImovelOcorrencia(String id, Integer imovelId, String descricao, String nr_ref, String statusFinal) {
        this.id = id;
        this.imovelId = imovelId;
        this.descricao = descricao;
        this.nr_ref = nr_ref;
        this.statusFinal = statusFinal;
    }
}
