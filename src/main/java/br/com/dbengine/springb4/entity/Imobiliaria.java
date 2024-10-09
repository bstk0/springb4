package br.com.dbengine.springb4.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown=true)
public class Imobiliaria {
    private String id;
    private int imobid;
    private String nome;
    private String contato;
    private String createdAt;

    public Imobiliaria() {
    }

    public Imobiliaria(String id, int imobid, String nome, String contato, String createdAt) {
        this.id = id;
        this.imobid = imobid;
        this.nome = nome;
        this.contato = contato;
        this.createdAt = createdAt;
    }
}



