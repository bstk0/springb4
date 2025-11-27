package br.com.dbengine.springb4.entity;

import lombok.*;
import org.json.simple.*;

import java.time.*;
import java.time.format.*;

@Getter
@Setter
public class SLancto {
    private String id;
    private String dt_ref;
    private String descr;
    private String created_at;

    public JSONObject toJSON() {
        //JSONObject result = new JSONObject();
        JSONObject snuttgly = new JSONObject();
        snuttgly.put("id", getId());
        snuttgly.put("descr", getDescr() );
        snuttgly.put("dt_ref", getDt_ref() );

        //result.put("object", snuttgly);
        return snuttgly; //result;
    }

    public JSONObject upd_toJSON() {
        //JSONObject result = new JSONObject();
        JSONObject snuttgly = new JSONObject();
        snuttgly.put("id", getId());
        snuttgly.put("descr", getDescr() );
        snuttgly.put("dt_ref", getDt_ref() );
        //result.put("object", snuttgly);
        return snuttgly; //result;
    }

    public JSONObject ins_toJSON() {
        //JSONObject result = new JSONObject();
        JSONObject snuttgly = new JSONObject();

        // Get the current date
        //LocalDate currentDate = LocalDate.now();

        // Define the desired format
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Format the date
        //String formattedDate = currentDate.format(formatter);

        //snuttgly.put("dt_criacao", formattedDate);
        snuttgly.put("descr", getDescr() );
        snuttgly.put("dt_ref", getDt_ref() );
        //result.put("object", snuttgly);
        return snuttgly; //result;
    }
}
