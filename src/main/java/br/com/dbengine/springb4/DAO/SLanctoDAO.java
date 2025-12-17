package br.com.dbengine.springb4.DAO;

import br.com.dbengine.springb4.dbUtil.*;
import br.com.dbengine.springb4.entity.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.type.TypeReference;
import org.json.simple.*;
import org.json.simple.parser.*;
import org.springframework.stereotype.*;

import java.util.*;

@SuppressWarnings("unchecked")
@Component
public class SLanctoDAO { //implements DAOInterface<Cultura> {

    private static final String COLLECTION = "lancto";
    private static final String SUPA_LIST_ALL = "lancto?select=*";
    private int NCOUNT = 0;
    //private static restClient rest = new restClient();


    private static final RestClient rest = new RestClient(
            "https://ambhjcnqaeqhvltvwktb.supabase.co/rest/v1/",
            "text/plain",
            new ArrayList<String>(
                    Arrays.asList("apikey", Sysout.SUPABASE_KEY)
            ));


    public List<SLancto> getList() {
        JSONParser parser = new JSONParser();
        Object obj = null;
        String resultGetAll;
//        try {
            resultGetAll = this.getRequest();
            //obj = parser.parse(resultGetAll);
            //JSONArray results = (JSONArray) (obj);

            JSONArray results = rest.SupabaseJSONList(resultGetAll);

            Sysout.s( " >> HProductDAO.getList - results : " + results.size());
            this.NCOUNT = results.size();

        //List<HPeople> hPeopleList = this.getListFromJSON(results); //resultGetAll);
        List<SLancto> hPeopleList = UtilsJSON.getListFromJSON(results,SLancto.class); //resultGetAll);
        return hPeopleList;
    }

    public void add(SLancto slancto) {
        JSONObject snuttgly = slancto.ins_toJSON();
        Sysout.s(" >> SlanctoDAO.add : " + snuttgly.toJSONString());
        String resultWoobly = rest.post(COLLECTION, snuttgly.toJSONString());
    }


    public void update(SLancto sLancto) {
        String sLanctoId = sLancto.getId();
        JSONObject snuttgly = sLancto.upd_toJSON();
        Sysout.s(" SLanctoDAO.UPDATE >> snuttgly.toJSONString():" + snuttgly.toJSONString());

        String uri_UPDATE = COLLECTION + "?id=eq." + sLanctoId;

        //String reString = rest.post(uri_UPDATE, snuttgly.toJSONString());
        // ** Não funcionou !!
        //String reString = rest.patch(uri_UPDATE, snuttgly.toJSONString());
        String reString = rest.put(uri_UPDATE, snuttgly.toJSONString());
    }


    public SLancto getItem(String id) {
        //final String PREFIX = "product_by_pk";
        ObjectMapper objectMapper=new ObjectMapper();
        JSONParser parser = new JSONParser();
        String uri_GETITEM = COLLECTION + "?id=eq." + id;
        String jsonItem = rest.get(uri_GETITEM);
        Sysout.s("getItem:" + jsonItem);
        SLancto imov = null;
        List<SLancto> lista = new ArrayList<SLancto>();
        try {
            //JSONObject jobj = (JSONObject) parser.parse(hpeopleItem);
//            JSONObject obj = (JSONObject) jobj.get(PREFIX);
//
//            imov = objectMapper.readValue(obj.toString(), HProduct.class);
//            Sysout.s(">>> id + nome : " + imov.getProduct_id() + " / " + imov.getNome());
//----------------
//ini
            //String jsonString = hpeopleItem.toJSONString();

            // Primeiro converte o array para uma lista
            lista = objectMapper.readValue(
                    jsonItem,
                    new TypeReference<List<SLancto>>() {}
            );
//fim

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
        //return imov;
        // Retorna o primeiro e único elemento
        return lista.isEmpty() ? null : lista.get(0);
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
        String uri_DEL = COLLECTION + "?id=eq." + id;
        String hpeopleDeleteItem = rest.delete(uri_DEL);
        return hpeopleDeleteItem;
    }


    private String getRequest() {
        rest.setAUTH_KEY(new ArrayList<String>(
                Arrays.asList("apikey", Sysout.SUPABASE_KEY)
        ));
        String result = rest.get(SUPA_LIST_ALL); //COLLECTION);
        Sysout.s("SLanctoDAO - result getRequest : " + result);
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
