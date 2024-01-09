package br.com.dbengine.springb4.test;

import br.com.dbengine.springb4.dbUtil.*;
import org.json.simple.*;
import org.json.simple.parser.*;

import java.util.*;

public class TestJSON {

    public static void main(String[] args) {
        String sjson = "{\"success\":true,\"data\":{\"0\":{\"_id\":\"658dc1fe2f542ab36d0ce03f\",\"tipo\":\"CASA\",\"imobiliaria\":\"DIRETO\",\"observacoes\":\"Inquilino direto\",\"descricao\":\"Casa Esmeralda II – Condomínio\",\"apelido\":\"Esmeralda II\",\"bairro\":\"Esmeralda\",\"imovelId\":1,\"status\":\"ALUGADO\",\"createdAt\":\"2023-12-28T18:44:14.602Z\",\"updatedAt\":\"2023-12-28T19:00:09.939Z\",\"__v\":0,\"id\":\"658dc1fe2f542ab36d0ce03f\"},\"1\":{\"_id\":\"658dc722870a1e4c7ecf540c\",\"tipo\":\"BARRACAO\",\"imobiliaria\":\"TOCA\",\"observacoes\":\"VERIFICAR\",\"descricao\":\"Av. Sampaio Vidal, 1405-A – Corte e Dobra\",\"apelido\":\"barracão 1\",\"bairro\":\"Centro\",\"imovelId\":2,\"status\":\"ALUGADO\",\"createdAt\":\"2023-12-28T19:06:10.491Z\",\"updatedAt\":\"2023-12-28T19:46:22.019Z\",\"__v\":0,\"id\":\"658dc722870a1e4c7ecf540c\"}},\"error\":null}";

        JSONArray result = new JSONArray();
        JSONParser parser = new JSONParser();
        JSONObject jobj = null;
        try {
            jobj = (JSONObject) parser.parse(sjson);
            //JSONObject jobj = new JSONObject(sjson);
            //System.out.print(jobj.toJSONString());
            JSONObject jobj2 = (JSONObject) jobj.get("data");
            //System.out.print(jobj2.toJSONString());
            //Sysout.s(jobj2.size());

            //teste item 0
            Object key0 = "1";
            jobj = (JSONObject) jobj2.get("0"); //key0.toString());
            Sysout.s(">> " + jobj.toJSONString());
            //teste item 0

            Iterator<?> iterator = jobj2.keySet().iterator();
            while (iterator.hasNext()) {
                Object key = iterator.next();
                //Sysout.s(iterator.next());
                Sysout.s(key.toString());
                jobj = (JSONObject) jobj2.get(key.toString());
                result.add(jobj);
            }

            //JSONArray sportsArray = (JSONArray) jobj.get("data");
            Sysout.s("## " + result.toJSONString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
