package br.com.dbengine.springb4.DAO;

import br.com.dbengine.springb4.dbUtil.*;
import br.com.dbengine.springb4.entity.*;
import br.com.dbengine.springb4.interfaces.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import org.springframework.stereotype.*;

import java.util.*;

@SuppressWarnings("unchecked")
@Component
public class HPeopleDAO { //implements DAOInterface<Cultura> {
    /**
     * Collection name
     */
    private static final String COLLECTION = "people1";
    //private static restClient rest = new restClient();

//    private static final List<String> authKey = new ArrayList<String>(
//            Arrays.asList("x-hasura-admin-secret", Sysout.HASURA_KEY)
//    );
    private static final RestClient rest = new RestClient(
            "https://funny-vervet-43.hasura.app/api/rest/people1",
            "text/plain",
            new ArrayList<String>(
                    Arrays.asList("x-hasura-admin-secret", Sysout.HASURA_KEY)
            ));

    //public String getList() {
    //	return getRequest(rest);
    //}

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

        List<HPeople> hPeopleList = this.getListFromJSON(results); //resultGetAll);
        return hPeopleList;

//            return (ArrayList<HPeople>) results;
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return new ArrayList<HPeople>();
    }

    public void add(Cultura cultura) {
        //Sysout.s("CulturaCodigo :" + cultura.getCodigo());
        //Sysout.s("CulturaDescricao :" + cultura.getDescricao() );
        JSONObject snuttgly = culturaToJSON(cultura);
        String resultWoobly = rest.post(COLLECTION, snuttgly.toJSONString());

    }


    public void update(Cultura cultura) {
        String culturaId = cultura.get_id();
        //Sysout.s("CULTURA ID:" + culturaId);
        JSONObject snuttgly = culturaToJSON(cultura);
        //Sysout.s("snuttgly.toJSONString():" + snuttgly.toJSONString());
        String reString = rest.put(COLLECTION + "/" + culturaId, snuttgly.toJSONString());
        //Sysout.s(reString);
    }


    public Cultura getItem(String id) {
        JSONParser parser = new JSONParser();
        //JSONObject jsonQuery = new JSONObject();
        String culturaItem = rest.get(COLLECTION + "/" + id);
        //Sysout.s("getItem:" + culturaItem);
        JSONObject result = null;
        try {
            Object obj = parser.parse(culturaItem);
            result = (JSONObject) obj;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (result == null) {
            return new Cultura("", 0,"CULTURA NULL...");

        } else {
            return new Cultura(result.get("_id").toString(),
                    Integer.parseInt(result.get("CulturaCodigo").toString()),
                    result.get("CulturaDescricao").toString());

        }
    }

    public String getCount() {
        JSONParser parser = new JSONParser();
        String count = rest.getCount(COLLECTION);
        //Sysout.s("getCount:" + count);
        try {
            Object obj = parser.parse(count);
            JSONObject results = (JSONObject) (obj);
            return results.get("COUNT ").toString();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "0";
    }

    public String delete(String id) {
        String culturaDeleteItem = rest.delete(COLLECTION + "/" + id);
        return culturaDeleteItem;
    }


    private JSONObject culturaToJSON(Cultura cultura) {
        JSONObject snuttgly = new JSONObject();
        snuttgly.put("CulturaCodigo", cultura.getCodigo());
        snuttgly.put("CulturaDescricao", cultura.getDescricao() );
        //snuttgly.put("NovaColuna", "They are the best");
        return snuttgly;
    }


    /**
     * Create 2 JSON Objects and add them to the collection
     *
     * @param client rest client
     */
    @SuppressWarnings("unused")
    private static void postRequest(final RestClient client) {
        JSONObject woobly = new JSONObject();
        woobly.put("title", "Wobbly bubbles");
        woobly.put("description", "They are the best");
        woobly.put("count", 4);
        woobly.put("email", "wobble@wobble.com");

        JSONObject snuttgly = new JSONObject();
        snuttgly.put("title", "Snuggly snuggles");
        snuttgly.put("description", "They are the worst");
        snuttgly.put("count", 8);
        snuttgly.put("email", "snuggle@snuggle.com");

        String resultWoobly = client.post(COLLECTION, woobly.toJSONString());

        Sysout.s("result Woobly postRequest : " + resultWoobly);
        String resultSnuttgly = client.post(COLLECTION, snuttgly.toJSONString());
        Sysout.s("result Snuttgly postRequest : " + resultSnuttgly);

        // call Garbage collection
        snuttgly = null;
        woobly = null;
    }


    private String getRequest() {
        rest.setAUTH_KEY(new ArrayList<String>(
                Arrays.asList("x-hasura-admin-secret", Sysout.HASURA_KEY)
        ));
        String result = rest.get(COLLECTION);
        Sysout.s("HPeolpeDAO - result getRequest : " + result);
        return result;
    }

    /**
     * Get the list of items in the collection where count = 0 More info about the
     * parameters of the queries here : https://rest.io/docs/querying-with-the-api
     *
     * @param client restClient
     * @return list of items in the collection, String format
     */
    private static String getRequestWithFilter(final RestClient client) {
        JSONObject jsonQuery = new JSONObject();
        jsonQuery.put("count", 8);
        String query = "?q=" + jsonQuery.toJSONString();
        String result = client.get(COLLECTION + query);
        Sysout.s("result getRequestWithFilter : " + result);
        return result;
    }

    /**
     * Update an item from a collection given its ID
     *
     * @param client       restClient
     * @param resultGetAll list of items from the collection
     * @return ID of the updated item
     */
    private static String putRequest(final RestClient client, final String resultGetAll) {
        JSONParser parser = new JSONParser();
        Object obj = null;
        String id = null;
        try {
            obj = parser.parse(resultGetAll);
            JSONArray results = (JSONArray) (obj);
            if (!resultGetAll.isEmpty() && results.size() > 0) {
                JSONObject firstOne = (JSONObject) results.get(0); // take first item
                Sysout.s(firstOne.toJSONString());
                firstOne.put("title", "updated title");
                id = (String) firstOne.get("_id");
                String resultPut = client.put(COLLECTION + "/" + id, firstOne.toJSONString());
                Sysout.s(resultPut);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * Delete item from a collection by aa given ID
     *
     * @param client restCliet
     * @param id     ID of the item to delete from the collection
     */
    private static void deleteRequest(final RestClient client, final String id) {
        String resultDelete = client.delete(COLLECTION + "/" + id);
        Sysout.s(resultDelete);
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
