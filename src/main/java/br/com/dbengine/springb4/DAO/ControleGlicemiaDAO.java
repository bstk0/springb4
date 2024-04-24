package br.com.dbengine.springb4.DAO;

import br.com.dbengine.springb4.dbUtil.*;
import br.com.dbengine.springb4.entity.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
public class ControleGlicemiaDAO {

    private static final String COLLECTION = "controleglicemia";
    private static final String ROOT_COLLECTION = "rep1_controleglicemia";
    private int NCOUNT = 0;

    private static final RestClient rest = new RestClient(
            "https://funny-vervet-43.hasura.app/api/rest/",
            "text/plain",
            new ArrayList<String>(
                    Arrays.asList("x-hasura-admin-secret", Sysout.HASURA_KEY)
            ));

    public List<ControleGlicemia> getList() {
        JSONParser parser = new JSONParser();
        Object obj = null;
        String resultGetAll;
//        try {
        resultGetAll = this.getRequest();
        //JSONArray results = rest.HasuraJSONList(resultGetAll,COLLECTION);
        JSONArray results = rest.HasuraJSONList(resultGetAll,ROOT_COLLECTION);

        Sysout.s( " >> getList - results : " + results.size());
        this.NCOUNT = results.size();

        //List<HPeople> hPeopleList = this.getListFromJSON(results); //resultGetAll);
        //List<ControleGlicemia> hPeopleList = JSONValidations.getListFromJSON(results,ControleGlicemia.class); //resultGetAll);
        return JSONValidations.getListFromJSON(results,ControleGlicemia.class);
    }

    private String getRequest() {
        rest.setAUTH_KEY(new ArrayList<String>(
                Arrays.asList("x-hasura-admin-secret", Sysout.HASURA_KEY)
        ));
        String result = rest.get(COLLECTION);
        Sysout.s("ControlePressaoDAO - result getRequest : " + result);
        return result;
    }

    private List<HPeople> getListFromJSON(JSONArray results) {
        List<HPeople> retorno = new ArrayList<HPeople>();
        ObjectMapper objectMapper=new ObjectMapper();
        results.forEach(item -> {
            JSONObject obj = (JSONObject) item;
            HPeople imov = null;
            try {
                imov = objectMapper.readValue(obj.toString(), HPeople.class);
                Sysout.s(">>> id + nome : " + imov.getId() + " / " + imov.getNome());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                //throw new RuntimeException(e);
            }
            retorno.add(imov);
        });
        return retorno;
    }
}
