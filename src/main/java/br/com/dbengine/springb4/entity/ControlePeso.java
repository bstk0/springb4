package br.com.dbengine.springb4.entity;

import lombok.*;
import org.json.simple.*;

@Getter
@Setter
public class ControlePeso {
    private String id; //  SERIAL PRIMARY KEY,
    private String data; //           date      NOT NULL,
    private String valor; //           INT       NOT NULL,
    private String comentario; // varchar(50)


    public JSONObject toJSON() {
        JSONObject result = new JSONObject();
        JSONObject snuttgly = new JSONObject();
        if (getId() != null) snuttgly.put("id", getId());
        snuttgly.put("data", getData() );
        snuttgly.put("valor", getValor());
        snuttgly.put("comentario", getComentario() );

        result.put("object", snuttgly);
        return result;
    }

}

