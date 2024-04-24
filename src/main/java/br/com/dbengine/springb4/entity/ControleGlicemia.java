package br.com.dbengine.springb4.entity;

import lombok.*;
import org.json.simple.*;

@Getter
@Setter
public class ControleGlicemia {
    private String id; //  SERIAL PRIMARY KEY,
    private String data; //           date      NOT NULL,
    private String valor; //           INT       NOT NULL,
    private String aparelho; //    varchar(15),
    private String estado; //       varchar(50),
    private String comentario; // varchar(50)


    public JSONObject toJSON() {
        JSONObject result = new JSONObject();
        JSONObject snuttgly = new JSONObject();
        if (getId() != null) snuttgly.put("id", getId());
        snuttgly.put("data", getData() );
        snuttgly.put("valor", getValor());
        snuttgly.put("aparelho", getAparelho() );
        snuttgly.put("estado", getEstado() );
        snuttgly.put("comentario", getComentario() );

        result.put("object", snuttgly);
        return result;
    }

}

