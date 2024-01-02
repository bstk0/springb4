package br.com.dbengine.springb4.entity;

import lombok.*;

@Getter
@Setter
public class CPeople {

    private String nome;
    private String observacao;
    private String email;
    private String nascimento;

    public CPeople() {
    }

    public CPeople(String nome, String observacao, String email, String nascimento) {
        this.nome = nome;
        this.observacao = observacao;
        this.email = email;
        this.nascimento = nascimento;
    }
}
