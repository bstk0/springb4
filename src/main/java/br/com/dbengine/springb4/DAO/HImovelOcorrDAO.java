package br.com.dbengine.springb4.DAO;

import br.com.dbengine.springb4.dbUtil.*;
import br.com.dbengine.springb4.entity.*;
import br.com.dbengine.springb4.form.*;
import br.com.dbengine.springb4.interfaces.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.time.format.*;
import java.util.*;

@SuppressWarnings("unchecked")
@Component
public class HImovelOcorrDAO implements DAOInterface<ImovelOcorrencia> {

    private static final String COLLECTION = "ImovelOcorr"; //"imovelOcorr";
    private static final String TABLE = "ImovelOcorr";
    private int NCOUNT = 0;
    //private static restClient rest = new restClient();
    private String ImovelDescri;


    private static final RestClient rest = new RestClient(
            "https://ukygdppeibetcsiohtzm.hasura.sa-east-1.nhost.run/api/rest/",
            "text/plain",
            new ArrayList<String>(
                    Arrays.asList("x-hasura-admin-secret", Sysout.HASURA_KEY2)
            ));


    public List<ImovelOcorrencia> getList() {
        JSONParser parser = new JSONParser();
        Object obj = null;
        String resultGetAll;
//        try {
        resultGetAll = this.getRequest();
        //obj = parser.parse(resultGetAll);
        //JSONArray results = (JSONArray) (obj);

        JSONArray results = rest.HasuraJSONList(resultGetAll, COLLECTION);

        Sysout.s(" >> HImovelFinancDAO.getList - results : " + results.size());
        this.NCOUNT = results.size();

        //List<HPeople> hPeopleList = this.getListFromJSON(results); //resultGetAll);
        return UtilsJSON.getListFromJSON(results, ImovelOcorrencia.class);
    }

    public void add(ImovelOcorrencia imovelOcorr) {
        //JSONObject snuttgly = this.convertIFtoJSON(imovelOcorr);
        JSONObject snuttgly = this.convertIOtoJSON(imovelOcorr);
        Sysout.s(" >> DAO.add : " + snuttgly.toJSONString());
        String resultWoobly = rest.post(COLLECTION, snuttgly.toJSONString());
    }


    public void update(ImovelOcorrencia imovel) {
        String hImovelId = imovel.getId();
        JSONObject snuttgly = this.convertIOtoJSON(imovel);
        Sysout.s("DAO.UPDATE >> snuttgly.toJSONString():" + snuttgly.toJSONString());

        //rest.setCONTENT_TYPE("application/json");
        //String reString = rest.put(COLLECTION + "/" + hPeopleId, snuttgly.toJSONString());
        String reString = rest.post(COLLECTION + "/" + hImovelId, snuttgly.toJSONString());
    }


    public ImovelOcorrencia getItem(String id) {
        final String PREFIX = "ImovelOcorr_by_pk";
        ObjectMapper objectMapper = new ObjectMapper();
        JSONParser parser = new JSONParser();
        //2025.11.19 - teste
        String test = this.getRequest();

        String hpeopleItem = rest.get(COLLECTION + "/" + id);
        Sysout.s("getItem:" + hpeopleItem);
        ImovelOcorrencia imov = null;
        try {
            JSONObject jobj = (JSONObject) parser.parse(hpeopleItem);
            JSONObject obj = (JSONObject) jobj.get(PREFIX);

            imov = objectMapper.readValue(obj.toString(), ImovelOcorrencia.class);
            Sysout.s(">>> id + nome : " + imov.getId() + " / " + imov.getImovelId());
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
        Sysout.s(" >> HImovelDAO - result getRequest : " + result);
        return result;
    }

    private String getRequest(String path) {
        rest.setAUTH_KEY(new ArrayList<String>(
                Arrays.asList("x-hasura-admin-secret", Sysout.HASURA_KEY2)
        ));
        String result = rest.get(path);
        Sysout.s(" 24.11 >> HImovelOcorrDAO - result getRequest : " + result);
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


    public List<ImovelOcorrForm> getListForm(int imovelId) {
        JSONParser parser = new JSONParser();
        Object obj = null;
        String resultGetAll;
//        try {
        String path = "imovelocorr-by-imovelid/imovelid/" + imovelId;
        resultGetAll = this.getRequest(path);
        //obj = parser.parse(resultGetAll);
        //JSONArray results = (JSONArray) (obj);

        JSONArray results = rest.HasuraJSONList(resultGetAll, TABLE);

        JSONObject imovel_by_pk = rest.HasuraJSONObject(resultGetAll, "imovel_by_pk");
        this.setImovelDescri((String) imovel_by_pk.get("apelido"));

        Sysout.s(" >> HImovelFinancDAO.getList - results : " + results.size());
        this.NCOUNT = results.size();

        List<ImovelOcorrForm> iOccListForm = new ArrayList<ImovelOcorrForm>();
        for (Object o : results) {
            JSONObject iocc = (JSONObject) o;
            ImovelOcorrForm ioccFom = getImovelOcorrForm(iocc);
            iOccListForm.add(ioccFom);
        }
        return iOccListForm;
    }

    public List<ImovelOcorrEmAberto> getListEmAberto() {
        List<ImovelOcorrEmAberto> imovelOcorrEmAbertos = new ArrayList<ImovelOcorrEmAberto>();
        return imovelOcorrEmAbertos;
    }

    public ImovelOcorrForm getItemForm(String imovelOccId) {
//            String resultGetAll = rest.get();
//            JSONObject iocc = canDb.CanonicJSONItem(resultGetAll);
//            ImovelOcorrForm ioccFom = getImovelOcorrForm(iocc);
//            return ioccFom;

        ImovelOcorrencia imovOcorr = this.getItem(imovelOccId);
        ImovelOcorrForm ioccFom = getImovelOcorrForm(imovOcorr);
        return ioccFom;
        //return new ImovelOcorrForm();
    }

    private static ImovelOcorrForm getImovelOcorrForm(ImovelOcorrencia iocc) {
        ImovelOcorrForm ioccFom = new ImovelOcorrForm(
                iocc.getId(),
                iocc.getImovelId(),
                iocc.getDescricao(),
                iocc.getNr_ref(),
                iocc.getStatusFinal(),
                iocc.getDtInicioOcorr(),
                iocc.getDtInicioOcorr());
        return ioccFom;
    }

    private static ImovelOcorrForm getImovelOcorrForm(JSONObject iocc) {
        //String formattedDate = JSONValidations.parseAttrToDateTimeBR(iocc.get("__createdtime__"));
        //String dataUpdate = JSONValidations.parseAttrToDateTimeBR(iocc.get("__updatedtime__"));
        String createdAt = UtilsJSON.cvtUTCDateToBr(iocc.get("createdAt"));
        String updatedAt = UtilsJSON.cvtUTCDateToBr(iocc.get("updatedAt"));

        //Sysout.s(" DAO - getImovelOcorrForm : " + iocc.toJSONString());

        String ioId = UtilsJSON.validaAtributo(iocc.get("id"));
        if (ioId.equals("")) {
            ioId = UtilsJSON.validaAtributo(iocc.get("_id"));
        }

        ImovelOcorrForm ioccFom = new ImovelOcorrForm(
                ioId,
                UtilsJSON.parseAttrToInteger(iocc.get("imovelId")),
                UtilsJSON.validaAtributo(iocc.get("descricao")),
                UtilsJSON.validaAtributo(iocc.get("nr_ref")),
                UtilsJSON.validaAtributo(iocc.get("statusFinal")),
                createdAt,   //JSONValidations.validaAtributo(iocc.get("createdAt")), //formattedDate,
                updatedAt);  //JSONValidations.validaAtributo(iocc.get("updatedAt"))); //dataUpdate);
        return ioccFom;
    }

    public String getImovelDescri() {
        return ImovelDescri;
    }

    public void setImovelDescri(String imovelDescri) {
        ImovelDescri = imovelDescri;
    }

    private JSONObject convertIOtoJSON(ImovelOcorrencia imovelOcorrencia) {

        JSONObject result = new JSONObject();
        JSONObject jo = new JSONObject();
        //jo.put("id", imovelOcorrencia.getId());
        jo.put("imovelId", imovelOcorrencia.getImovelId());
        jo.put("descricao", imovelOcorrencia.getDescricao());
        jo.put("nr_ref", imovelOcorrencia.getNr_ref());
        jo.put("statusFinal", imovelOcorrencia.getStatusFinal());

        // Get the current date
        LocalDate currentDate = LocalDate.now();
        // Define the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // Format the date
        String formattedDate = currentDate.format(formatter);

        jo.put("dtInicioOcorr" , formattedDate);
        //jo.put("updatedBy" , imovelOcorrencia.getUpdatedBy());
        //return jo;
        result.put("object", jo);
        return result; //jo;
    }
}
