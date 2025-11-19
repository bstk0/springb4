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

import java.util.*;

@SuppressWarnings("unchecked")
@Component
public class HImovelOcorrDAO implements DAOInterface<ImovelOcorrencia>

    {

    private static final String COLLECTION = "imovelOcorr";
    private int NCOUNT = 0;
    //private static restClient rest = new restClient();


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

            JSONArray results = rest.HasuraJSONList(resultGetAll,COLLECTION);

            Sysout.s( " >> HImovelFinancDAO.getList - results : " + results.size());
            this.NCOUNT = results.size();

        //List<HPeople> hPeopleList = this.getListFromJSON(results); //resultGetAll);
        return UtilsJSON.getListFromJSON(results,ImovelOcorrencia.class);
    }

    public void add(ImovelOcorrencia imovelFinanc) {
        JSONObject snuttgly = this.convertIFtoJSON(imovelFinanc);
        Sysout.s(" >> DAO.add : " + snuttgly.toJSONString());
        String resultWoobly = rest.post(COLLECTION, snuttgly.toJSONString());
    }


    public void update(ImovelOcorrencia imovel) {
        String hImovelId = imovel.getId();
        JSONObject snuttgly = this.upd_toJSON(imovel);
        Sysout.s("DAO.UPDATE >> snuttgly.toJSONString():" + snuttgly.toJSONString());

        //rest.setCONTENT_TYPE("application/json");
        //String reString = rest.put(COLLECTION + "/" + hPeopleId, snuttgly.toJSONString());
        String reString = rest.post(COLLECTION + "/" + hImovelId, snuttgly.toJSONString());
    }


    public ImovelOcorrencia getItem(String id) {
        final String PREFIX = "ImovelOcorr_by_pk";
        ObjectMapper objectMapper=new ObjectMapper();
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

        private JSONObject convertIFtoJSON(ImovelFinanceiro imovelFinanceiro) {
            JSONObject jo = new JSONObject();
            jo.put("id", imovelFinanceiro.getId());
            jo.put("imovel_id", imovelFinanceiro.getImovelId());
            jo.put("dadosGerais", imovelFinanceiro.getDadosGerais() );
            jo.put("vl_aluguel", imovelFinanceiro.getVl_aluguel() );
            jo.put("vl_condom", imovelFinanceiro.getVl_condom() );
            jo.put("vl_iptu", imovelFinanceiro.getVl_iptu() );
            jo.put("vl_iptu_desc", imovelFinanceiro.getVl_iptu_desc() );
            jo.put("cd_luz", imovelFinanceiro.getCd_luz());
            jo.put("cd_daem", imovelFinanceiro.getCd_daem());
            jo.put("diaPagtoAluguel", imovelFinanceiro.getDiaPagtoAluguel());
            jo.put("diaPagtoCond", imovelFinanceiro.getDiaPagtoCond());
            jo.put("cpfCadastrado", imovelFinanceiro.getCpfCadastrado());
            jo.put("nr_contrato", imovelFinanceiro.getNr_contrato());
            jo.put("nr_inscr", imovelFinanceiro.getNr_inscr());

            if(imovelFinanceiro.getDtInicioContr() != null && !("".equals(imovelFinanceiro.getDtFimContr()))) {
                //jo.put("dtInicioContrato", Sysout.dateStringtoUnix(imovelFinanceiro.getDtInicioContrato()));
                jo.put("dtInicioContr", UtilsJSON.cvtBRDateToUTC(imovelFinanceiro.getDtInicioContr()));
            }
            if(imovelFinanceiro.getDtFimContr() != null && !("".equals(imovelFinanceiro.getDtFimContr()))) {
                //jo.put("dtFimContrato", Sysout.dateStringtoUnix(imovelFinanceiro.getDtFimContrato()));
                jo.put("dtFimContr", UtilsJSON.cvtBRDateToUTC(imovelFinanceiro.getDtFimContr()));
            }

//        jo.put("dtInicioContr", imovelFinanceiro.getDtInicioContr());
//        jo.put("dtFimContr", imovelFinanceiro.getDtFimContr());

            jo.put("sindico", imovelFinanceiro.getSindico());
            jo.put("administradora", imovelFinanceiro.getAdministradora());

            return jo;
        }

        // upd_toJSON()
        private JSONObject upd_toJSON(ImovelFinanceiro imovelFinanceiro) {

//            JSONObject result = new JSONObject();
//            JSONObject snuttgly = new JSONObject();
//            //snuttgly.put("product_id", getProduct_id());
//            snuttgly.put("nome", getNome() );
//            result.put("object", snuttgly);
//            return result;

            JSONObject result = new JSONObject();
            JSONObject jo = new JSONObject();
            //jo.put("id", imovelFinanceiro.getId());
            //jo.put("imovel_id", imovelFinanceiro.getImovelId());
            jo.put("dadosGerais", imovelFinanceiro.getDadosGerais() );
            jo.put("vl_aluguel", imovelFinanceiro.getVl_aluguel() );
            jo.put("vl_condom", imovelFinanceiro.getVl_condom() );
            jo.put("vl_iptu", imovelFinanceiro.getVl_iptu() );
            jo.put("vl_iptu_desc", imovelFinanceiro.getVl_iptu_desc() );
            jo.put("cd_luz", imovelFinanceiro.getCd_luz());
            jo.put("cd_daem", imovelFinanceiro.getCd_daem());
            jo.put("diaPagtoAluguel", imovelFinanceiro.getDiaPagtoAluguel());
            jo.put("diaPagtoCond", imovelFinanceiro.getDiaPagtoCond());
            jo.put("cpfCadastrado", imovelFinanceiro.getCpfCadastrado());
            jo.put("nr_contrato", imovelFinanceiro.getNr_contrato());
            jo.put("nr_inscr", imovelFinanceiro.getNr_inscr());

            if(imovelFinanceiro.getDtInicioContr() != null && !("".equals(imovelFinanceiro.getDtFimContr()))) {
                //jo.put("dtInicioContrato", Sysout.dateStringtoUnix(imovelFinanceiro.getDtInicioContrato()));
                jo.put("dtInicioContr", UtilsJSON.cvtBRDateToUTC(imovelFinanceiro.getDtInicioContr()));
            }
            if(imovelFinanceiro.getDtFimContr() != null && !("".equals(imovelFinanceiro.getDtFimContr()))) {
                //jo.put("dtFimContrato", Sysout.dateStringtoUnix(imovelFinanceiro.getDtFimContrato()));
                jo.put("dtFimContr", UtilsJSON.cvtBRDateToUTC(imovelFinanceiro.getDtFimContr()));
            }

//        jo.put("dtInicioContr", imovelFinanceiro.getDtInicioContr());
//        jo.put("dtFimContr", imovelFinanceiro.getDtFimContr());

            jo.put("sindico", imovelFinanceiro.getSindico());
            jo.put("administradora", imovelFinanceiro.getAdministradora());

            result.put("object", jo);
            return result; //jo;
        }

        public List<ImovelOcorrForm> getListForm(int imovelId) {
        }

        public List<ImovelOcorrEmAberto> getListEmAberto() {
        }

        public ImovelOcorrForm getItemForm(String imovelOccId) {
        }
    }
