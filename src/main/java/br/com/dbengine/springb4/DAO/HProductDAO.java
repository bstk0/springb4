package br.com.dbengine.springb4.DAO;

import br.com.dbengine.springb4.dbUtil.*;
import br.com.dbengine.springb4.entity.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import org.springframework.stereotype.*;

import java.util.*;

@SuppressWarnings("unchecked")
@Component
public class HProductDAO { //implements DAOInterface<Cultura> {

    private static final String COLLECTION = "product";
    private int NCOUNT = 0;
    //private static restClient rest = new restClient();


    private static final RestClient rest = new RestClient(
            "https://ukygdppeibetcsiohtzm.hasura.sa-east-1.nhost.run/api/rest/",
            "text/plain",
            new ArrayList<String>(
                    Arrays.asList("x-hasura-admin-secret", Sysout.HASURA_KEY2)
            ));


    public List<HProduct> getList() {
        JSONParser parser = new JSONParser();
        Object obj = null;
        String resultGetAll;
//        try {
            resultGetAll = this.getRequest();
            //obj = parser.parse(resultGetAll);
            //JSONArray results = (JSONArray) (obj);

            JSONArray results = rest.HasuraJSONList(resultGetAll,COLLECTION);

            Sysout.s( " >> HProductDAO.getList - results : " + results.size());
            this.NCOUNT = results.size();

        //List<HPeople> hPeopleList = this.getListFromJSON(results); //resultGetAll);
        List<HProduct> hPeopleList = UtilsJSON.getListFromJSON(results,HProduct.class); //resultGetAll);
        return hPeopleList;
    }

    public void add(HProduct hproduct) {
        JSONObject snuttgly = hproduct.ins_toJSON();
        Sysout.s(" >> DAO.add : " + snuttgly.toJSONString());
        String resultWoobly = rest.post(COLLECTION, snuttgly.toJSONString());
    }


    public void update(HProduct hProduct) {
        String hProductId = hProduct.getProduct_id();
        JSONObject snuttgly = hProduct.upd_toJSON();
        Sysout.s("DAO.UPDATE >> snuttgly.toJSONString():" + snuttgly.toJSONString());

        //rest.setCONTENT_TYPE("application/json");
        //String reString = rest.put(COLLECTION + "/" + hPeopleId, snuttgly.toJSONString());
        String reString = rest.post(COLLECTION + "/" + hProductId, snuttgly.toJSONString());
    }


    public HProduct getItem(String id) {
        final String PREFIX = "product_by_pk";
        ObjectMapper objectMapper=new ObjectMapper();
        JSONParser parser = new JSONParser();
        String hpeopleItem = rest.get(COLLECTION + "/" + id);
        Sysout.s("getItem:" + hpeopleItem);
        HProduct imov = null;
        try {
            JSONObject jobj = (JSONObject) parser.parse(hpeopleItem);
            JSONObject obj = (JSONObject) jobj.get(PREFIX);

            imov = objectMapper.readValue(obj.toString(), HProduct.class);
            Sysout.s(">>> id + nome : " + imov.getProduct_id() + " / " + imov.getNome());
        } catch (JsonProcessingException | ParseException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
        return imov;
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
        //String culturaDeleteItem = rest.delete(COLLECTION + "/" + id);
        String hpeopleDeleteItem = rest.delete(COLLECTION + "/" + id);
        return hpeopleDeleteItem;
    }


    private String getRequest() {
        rest.setAUTH_KEY(new ArrayList<String>(
                Arrays.asList("x-hasura-admin-secret", Sysout.HASURA_KEY2)
        ));
        String result = rest.get(COLLECTION);
        Sysout.s("HProductDAO - result getRequest : " + result);
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

    public int getNCOUNT() {
        return NCOUNT;
    }

}
