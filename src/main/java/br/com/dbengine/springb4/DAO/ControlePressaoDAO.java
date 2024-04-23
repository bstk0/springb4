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
public class ControlePressaoDAO {

    private static final String COLLECTION = "controlepressao";
    private int NCOUNT = 0;

    private static final RestClient rest = new RestClient(
            "https://funny-vervet-43.hasura.app/api/rest/",
            "text/plain",
            new ArrayList<String>(
                    Arrays.asList("x-hasura-admin-secret", Sysout.HASURA_KEY)
            ));

    public List<HPeople> getList() {
        JSONParser parser = new JSONParser();
        Object obj = null;
        String resultGetAll;
//        try {
        resultGetAll = this.getRequest();
        //obj = parser.parse(resultGetAll);
        //JSONArray results = (JSONArray) (obj);

        JSONArray results = rest.HasuraJSONList(resultGetAll,COLLECTION);

        Sysout.s( " >> getList - results : " + results.size());
        this.NCOUNT = results.size();

        //List<HPeople> hPeopleList = this.getListFromJSON(results); //resultGetAll);
        List<HPeople> hPeopleList = JSONValidations.getListFromJSON(results,HPeople.class); //resultGetAll);
        return hPeopleList;
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
