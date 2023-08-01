package br.com.dbengine.springb4.DAO;

import br.com.dbengine.springb4.dbUtil.HarperDBClient;
import br.com.dbengine.springb4.dbUtil.HarperDBOperation;
import br.com.dbengine.springb4.entity.Imovel;
import br.com.dbengine.springb4.entity.ImovelOcorrencia;
import br.com.dbengine.springb4.interfaces.DAOInterface;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ImovelOcorrenciaDAO implements DAOInterface<ImovelOcorrencia> {

    private static HarperDBClient harperDb = new HarperDBClient();

    public List<ImovelOcorrencia> getList(String imovelId) {
        String strQuery = "select * FROM rep1.imovelOcorrencia where imovel_id = " + imovelId;
        strQuery += " order by __createdtime__ desc";
        JSONParser parser = new JSONParser();
        Object obj = null;
        String resultGetAll;
        try {
            resultGetAll = harperDb.getList(strQuery);
            obj = parser.parse(resultGetAll);
            JSONArray results = (JSONArray) (obj);
            return (ArrayList<ImovelOcorrencia>) results;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new ArrayList<ImovelOcorrencia>();
    }

    @Override
    public List<ImovelOcorrencia> getList() {
        return null;
    }

    @Override
    public void add(ImovelOcorrencia imovelOcorrencia) {

//        System.out.println("ADD >>" + imovelOcorrencia.getDescricao());

        JSONObject obj = new JSONObject();
//        try {
            obj.put("operation", "insert");
            obj.put("schema", "rep1");
            obj.put("table", "imovelOcorrencia");

            JSONArray list = new JSONArray();

            JSONParser parser = new JSONParser();
            JSONObject innerObj = null;

            //System.out.println(" objToString >> " + imovelOcorrencia.toString());

            innerObj = convertIOtoJSON(imovelOcorrencia);
            list.add(innerObj);
            obj.put("records", list);
//        } catch (ParseException e) {
//            //throw new RuntimeException(e);
//            System.out.println("ERRO PARSE: " + e.toString());
//        }
//        System.out.println(">> " + obj.toJSONString());
        String opResult = harperDb.execOperation(obj.toJSONString());
//        System.out.println(" RESULT >> " + opResult);

    }

    @Override
    public void update(ImovelOcorrencia obj) {

    }

    @Override
    public ImovelOcorrencia getItem(String id) {
        return DAOInterface.super.getItem(id);
    }

    @Override
    public String getCount() {
        return null;
    }

    @Override
    public String delete(String id) {
        return null;
    }

    private JSONObject convertIOtoJSON(ImovelOcorrencia imovelOcorrencia) {
        JSONObject jo = new JSONObject();
        jo.put("id", imovelOcorrencia.getId());
        jo.put("imovel_id", imovelOcorrencia.getImovel_id());
        jo.put("descricao", imovelOcorrencia.getDescricao());
        jo.put("numero_ref", imovelOcorrencia.getNumero_ref());
        jo.put("status_final", imovelOcorrencia.getStatus_final());
        return jo;
    }
}
