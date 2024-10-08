package br.com.dbengine.springb4.DAO;

import br.com.dbengine.springb4.dbUtil.*;
import br.com.dbengine.springb4.entity.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
public class ControleGlicemiaDAO {

    private static final String COLLECTION = "controleglicemia";
    private static final String ROOT_COLLECTION = "rep1_controleglicemia";
    //private int NCOUNT = 0;

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

        resultGetAll = this.getRequest();
        JSONArray results = rest.HasuraJSONList(resultGetAll,ROOT_COLLECTION);
        //Sysout.s( " >> getList - results : " + results.size());
        //this.NCOUNT = results.size();
        return UtilsJSON.getListFromJSON(results,ControleGlicemia.class);
    }

    private String getRequest() {
        rest.setAUTH_KEY(new ArrayList<String>(
                Arrays.asList("x-hasura-admin-secret", Sysout.HASURA_KEY)
        ));
        String result = rest.get(COLLECTION);
        Sysout.s("ControlePressaoDAO - result getRequest : " + result);
        return result;
    }

    public void add(ControleGlicemia hglic) {
        JSONObject snuttgly =  hglic.toJSON();
        Sysout.s(" >> add : " + snuttgly.toJSONString());
        String resultWoobly = rest.post(COLLECTION, snuttgly.toJSONString());
    }

}
