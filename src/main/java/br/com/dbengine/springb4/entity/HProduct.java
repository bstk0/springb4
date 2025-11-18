package br.com.dbengine.springb4.entity;

import lombok.*;
import org.json.simple.*;

//import java.time.format.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class HProduct {
    private String product_id;
    private String nome;
    private String descri;
    private String dt_criacao;

    public JSONObject toJSON() {
        JSONObject result = new JSONObject();
        JSONObject snuttgly = new JSONObject();
        snuttgly.put("product_id", getProduct_id());
        snuttgly.put("nome", getNome() );

        result.put("object", snuttgly);
        return result;
    }

    public JSONObject upd_toJSON() {
        JSONObject result = new JSONObject();
        JSONObject snuttgly = new JSONObject();
        //snuttgly.put("product_id", getProduct_id());
        snuttgly.put("nome", getNome() );
        result.put("object", snuttgly);
        return result;
    }

    public JSONObject ins_toJSON() {
        JSONObject result = new JSONObject();
        JSONObject snuttgly = new JSONObject();

        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Define the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Format the date
        String formattedDate = currentDate.format(formatter);

        snuttgly.put("dt_criacao", formattedDate);
        snuttgly.put("nome", getNome() );
        result.put("object", snuttgly);
        return result;
    }
}
