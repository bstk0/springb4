package br.com.dbengine.springb4.DAO;

import java.util.ArrayList;
import java.util.List;

import br.com.dbengine.springb4.dbUtil.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import br.com.dbengine.springb4.entity.Cultura;
import br.com.dbengine.springb4.interfaces.DAOInterface;
import org.springframework.stereotype.Component;

@SuppressWarnings("unchecked")
@Component
public class CulturaDAO implements DAOInterface<Cultura> {
    /**
     * Collection name
     */
    private static final String COLLECTION = "cultura";
    private static RestDBClient restDb = new RestDBClient();

    //public String getList() {
    //	return getRequest(restDb);
    //}

    public List<Cultura> getList() {
        JSONParser parser = new JSONParser();
        Object obj = null;
        String resultGetAll;
        try {
            resultGetAll = getRequest(restDb);
            obj = parser.parse(resultGetAll);
            JSONArray results = (JSONArray) (obj);
            return (ArrayList<Cultura>) results;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new ArrayList<Cultura>();
    }

    public void add(Cultura cultura) {
        //Sysout.s("CulturaCodigo :" + cultura.getCodigo());
        //Sysout.s("CulturaDescricao :" + cultura.getDescricao() );
        JSONObject snuttgly = culturaToJSON(cultura);
        String resultWoobly = restDb.post(COLLECTION, snuttgly.toJSONString());

    }


    public void update(Cultura cultura) {
        String culturaId = cultura.get_id();
        //Sysout.s("CULTURA ID:" + culturaId);
        JSONObject snuttgly = culturaToJSON(cultura);
        //Sysout.s("snuttgly.toJSONString():" + snuttgly.toJSONString());
        String reString = restDb.put(COLLECTION + "/" + culturaId, snuttgly.toJSONString());
        //Sysout.s(reString);
    }


    public Cultura getItem(String id) {
        JSONParser parser = new JSONParser();
        //JSONObject jsonQuery = new JSONObject();
        String culturaItem = restDb.get(COLLECTION + "/" + id);
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
        String count = restDb.getCount(COLLECTION);
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

    @Override
    public String delete(String id) {
        String culturaDeleteItem = restDb.delete(COLLECTION + "/" + id);
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
     * @param client restDB client
     */
    @SuppressWarnings("unused")
    private static void postRequest(final RestDBClient client) {
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

    /**
     * Get the list of items in the collection
     *
     * @param client RestDBClient
     * @return list of items in the collection, String format
     */
    private static String getRequest(final RestDBClient client) {
        String result = client.get(COLLECTION);
        //Sysout.s("CulturaDAO - result getRequest : " + result);
        return result;
    }

    /**
     * Get the list of items in the collection where count = 0 More info about the
     * parameters of the queries here : https://restdb.io/docs/querying-with-the-api
     *
     * @param client RestDBClient
     * @return list of items in the collection, String format
     */
    private static String getRequestWithFilter(final RestDBClient client) {
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
     * @param client       RestDBClient
     * @param resultGetAll list of items from the collection
     * @return ID of the updated item
     */
    private static String putRequest(final RestDBClient client, final String resultGetAll) {
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
     * @param client RestDBCliet
     * @param id     ID of the item to delete from the collection
     */
    private static void deleteRequest(final RestDBClient client, final String id) {
        String resultDelete = client.delete(COLLECTION + "/" + id);
        Sysout.s(resultDelete);
    }

    /**
     * entry point
     *
     * @param args arguments, there is currently no argument given to this app

    public static void main(String[] args) {

    RestDBClient client = new RestDBClient();

    postRequest(client);
    String resultGetAll = getRequest(client);
    getRequestWithFilter(client);
    String id = putRequest(client, resultGetAll);
    deleteRequest(client, id);

    // Garbage collection
    client = null;
    }
     */
}
