package br.com.dbengine.springb4.entity;

import lombok.*;
import org.json.simple.*;

@Getter
@Setter
public class HPeople {
    private String id;
    private String nome;
    private String observacao;
    private String datanascimento;

    public JSONObject toJSON() {
        JSONObject result = new JSONObject();
        JSONObject snuttgly = new JSONObject();
        snuttgly.put("id", getId());
        snuttgly.put("nome", getNome() );

        result.put("object", snuttgly);
        return result;
    }
}
