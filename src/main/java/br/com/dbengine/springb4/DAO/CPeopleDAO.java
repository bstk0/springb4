package br.com.dbengine.springb4.DAO;

import br.com.dbengine.springb4.dbUtil.*;
import br.com.dbengine.springb4.entity.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import org.springframework.stereotype.*;

import java.text.*;
import java.util.*;

@Component
public class CPeopleDAO {

    private static CanonicClient cndb = new CanonicClient();

    public void add(CPeople cpeople) {

        JSONObject obj = new JSONObject();
        JSONParser parser = new JSONParser();
        JSONObject innerObj = null;

        innerObj = this.convertToJSON(cpeople);
        obj.put("input", innerObj);

        //Sysout.s(">> " + obj.toJSONString());
        String opResult = cndb.add(obj.toJSONString());
        //Sysout.s(" RESULT >> " + opResult);
    }


    private JSONObject convertToJSON(CPeople cpeople) {

        //Sysout.s(cpeople.getNascimento().toString());
        //String pattern = "yyyy-dd-MM";
        //DateFormat df = new SimpleDateFormat(pattern);
        //String nascAsString = df.format(cpeople.getNascimento());

        JSONObject jo = new JSONObject();
        jo.put("nome", cpeople.getNome());
        jo.put("observacao", cpeople.getObservacao());
        jo.put("email", cpeople.getEmail());
        jo.put("nascimento", cpeople.getNascimento());
        return jo;
    }
}
