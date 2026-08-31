package br.com.dbengine.springb4.entity;

import lombok.*;
import org.json.simple.*;

@Getter
@Setter
public class Prestador {
    private String id;
    private String nome;
    //private String observacao;
    //private String datanascimento;
    private String endereco;
    private String telefone1;
    private String telefone2;
    private String email1;
    private String email2;
    private String cidade;
    private String especialidades;
    private String comentarios;
    private String ativo;

    public JSONObject toJSON() {
        JSONObject result = new JSONObject();
        JSONObject snuttgly = new JSONObject();
        snuttgly.put("id", getId());
        snuttgly.put("nome", getNome());
        snuttgly.put("comentarios;", getComentarios());


        result.put("object", snuttgly);
        return result;
    }
}
