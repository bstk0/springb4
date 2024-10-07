package br.com.dbengine.springb4.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown=true)
public class Imobiliaria {
    private String id;
    private Integer imobid;
    private String nome;
    private String contato;

    public Imobiliaria(String id, Integer imobid, String nome, String contato) {
        this.id = id;
        this.imobid = imobid;
        this.nome = nome;
        this.contato = contato;
    }
}
