package br.com.dbengine.springb4.DAO;

import br.com.dbengine.springb4.Singleton.ImovelListSingleton;
import br.com.dbengine.springb4.dbUtil.*;
import br.com.dbengine.springb4.entity.Imovel;
import br.com.dbengine.springb4.interfaces.DAOInterface;
//import org.json.simple.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import org.springframework.stereotype.Component;


import java.io.*;
import java.util.*;

@Component
public class ImovelDAO implements DAOInterface<Imovel> {

    //private static HarperDBClient harperDb = new HarperDBClient();
    private static CanonicClient canDb = new CanonicClient();

    private final String URL_UPD = "https://can.canonic.dev/rep1-180hdf/api/imovel/:_id";


    public List<Imovel> getList() { //throws ParseException {

        if ( ImovelListSingleton.getInstance() != null) {
            Sysout.s(" >> Usando singleton <<");
            return ImovelListSingleton.getInstance();
        }
        //JSONParser parser = new JSONParser();
        Object obj = null;
        String resultGetAll;
            //resultGetAll = harperDb.getList();
            resultGetAll = canDb.getList("imovel");
            //Sysout.s(resultGetAll);
            //obj = parser.parse(resultGetAll);
            JSONArray results = canDb.CanonicJSONList(resultGetAll);
            //JSONArray results = (JSONArray) (obj);
            //List<Imovel> imovelList = (ArrayList<Imovel>) results;
            List<Imovel> imovelList = this.getImovelList(results); //resultGetAll);
            //singleton
            //ImovelListSingleton.setInstance(imovelList);
            ImovelListSingleton.setInstaceJSON((JSONArray) results);
            return imovelList;      // (ArrayList<Imovel>) results;

    }

    @Override
    public void add(Imovel obj) {

    }

    @Override
    public void update(Imovel imovel) {
        System.out.println("ImovelDAO.update...");

        JSONObject obj = new JSONObject();
        JSONParser parser = new JSONParser();
        JSONObject innerObj = null;

        innerObj = convertItoJSON(imovel);
        obj.put("_id", imovel.getId());
        obj.put("input", innerObj);

        Sysout.s("UPDATE JSON >> " + obj.toJSONString());
        String opResult = canDb.update(URL_UPD, obj.toJSONString());
        Sysout.s(" UPDATE RESULT >> " + opResult);

        ImovelListSingleton.setInstance(null);

    }

    //@Override
    public Imovel getItem(int id) {
        //Sysout.s("getItem.param " + id);
        List<Imovel> imovelList = ImovelListSingleton.getInstance();
        for(Imovel imovel : imovelList) {
            //Sysout.s("imovel.getImovelId() : " + imovel.getImovelId());
            if(imovel.getImovelId() == id) {
                return imovel;
            }
        }
        return new Imovel(); //DAOInterface.super.getItem(id);
    }

    @Override
    public String getCount() {
        return null;
    }

    @Override
    public String delete(String id) {
        return null;
    }

    private JSONObject convertItoJSON(Imovel imovel) {
        JSONObject jo = new JSONObject();
        jo.put("id",imovel.getId());
        //jo.put("id", Integer.parseInt(imovel.getId()));
        //jo.put("imovel_id", imovel.getImovel_id());
        jo.put("imovelId", imovel.getImovelId());
        jo.put("apelido", imovel.getApelido());
        jo.put("descricao", imovel.getDescricao());
        jo.put("status", imovel.getStatus());
        jo.put("bairro",imovel.getBairro());
        jo.put("imobiliaria",imovel.getImobiliaria());
        jo.put("tipo",imovel.getTipo());
        jo.put("observacoes",imovel.getObservacoes());
        //jo.put("status_final", imovel.getStatus_final());
        return jo;
    }

    private List<Imovel> getImovelList(JSONArray results) {
  //private List<Imovel> getImovelList(String sjon) {
        //JSONArray results = canDb.CanonicJSONList(sjon);
        //Iterator<String> iterator = results.iterator();
        List<Imovel> retorno = new ArrayList<Imovel>();
        //Imovel imov = new Imovel();
        ObjectMapper objectMapper=new ObjectMapper();
        results.forEach(item -> {
            JSONObject obj = (JSONObject) item;
            //parse(obj);
            Imovel imov = null;
            try {
                imov = objectMapper.readValue(obj.toString(), Imovel.class);
                Sysout.s(">>>" + imov.getId());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                //throw new RuntimeException(e);
            }
            //System.out.println(iterator.next());
            retorno.add(imov);
        });

        return retorno;
    }
}