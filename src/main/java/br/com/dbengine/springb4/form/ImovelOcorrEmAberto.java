package br.com.dbengine.springb4.form;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImovelOcorrEmAberto {
    private String id;
    private Integer imovelId;
    private String ImovelDescricao;
    private String descricao;
    private String nr_ref;
    private String statusFinal;
    //private String dataCriacao;
    //private String dataAtualizacao;
    private String createdAt;
    private String updatedAt;

}
