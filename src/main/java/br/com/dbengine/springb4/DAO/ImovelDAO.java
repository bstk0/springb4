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

    private static CanonicClient canDb = new CanonicClient();

    private final String URL_UPD = "https://can.canonic.dev/rep1-180hdf/api/imovel/:_id";


    public List<Imovel> getList() { //throws ParseException {

        if ( ImovelListSingleton.getInstance() != null) {
            Sysout.s(" >> Usando singleton <<");
            return ImovelListSingleton.getInstance();
        }
        Object obj = null;
        String resultGetAll;
        resultGetAll = canDb.getList("imovel");
        JSONArray results = canDb.CanonicJSONList(resultGetAll);
        List<Imovel> imovelList = this.getImovelList(results); //resultGetAll);
        // Singleton
        //ImovelListSingleton.setInstaceJSON((JSONArray) results);
        ImovelListSingleton.setInstance(imovelList);
        return imovelList;      // (ArrayList<Imovel>) results;
    }

    @Override
    public void add(Imovel obj) {
    }

    @Override
    public void update(Imovel imovel) {
        //Sysout.s("ImovelDAO.update...");
        JSONObject obj = new JSONObject();
        JSONParser parser = new JSONParser();
        JSONObject innerObj = null;

        innerObj = convertItoJSON(imovel);
        obj.put("_id", imovel.getId());
        obj.put("input", innerObj);

        //Sysout.s("UPDATE JSON >> " + obj.toJSONString());
        String opResult = canDb.update(URL_UPD, obj.toJSONString());
        //Sysout.s("UPDATE RESULT >> " + opResult);

        ImovelListSingleton.setInstance(null);
    }

    //@Override
    public Imovel getItem(int id) {
        //Sysout.s("getItem.param " + id);
        List<Imovel> imovelList = ImovelListSingleton.getInstance();
        if(!imovelList.isEmpty()) {
            for (Imovel imovel : imovelList) {
                //Sysout.s("imovel.getImovelId() : " + imovel.getImovelId());
                if (imovel.getImovelId() == id) {
                    return imovel;
                }
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
        jo.put("imovelId", imovel.getImovelId());
        jo.put("apelido", imovel.getApelido());
        jo.put("descricao", imovel.getDescricao());
        jo.put("status", imovel.getStatus());
        jo.put("bairro",imovel.getBairro());
        jo.put("imobiliaria",imovel.getImobiliaria());
        jo.put("tipo",imovel.getTipo());
        jo.put("observacoes",imovel.getObservacoes());
        return jo;
    }

    private List<Imovel> getImovelList(JSONArray results) {
        List<Imovel> retorno = new ArrayList<Imovel>();
        ObjectMapper objectMapper=new ObjectMapper();
        results.forEach(item -> {
            JSONObject obj = (JSONObject) item;
            Imovel imov = null;
            try {
                imov = objectMapper.readValue(obj.toString(), Imovel.class);
                //Sysout.s(">>>" + imov.getId());
            } catch (JsonProcessingException e) {
                Sysout.s(e.getMessage());
                //throw new RuntimeException(e);
            }
            retorno.add(imov);
        });
        return retorno;
    }

    public String getTitulo(int imovelId) {
        Imovel desc = this.getItem(imovelId);
        String imovelDescr;
        if (desc == null) {
            imovelDescr = "SINGLETON NAO CARREGADO - IMOVEL NAO LOCALIZADO";
        } else {
            imovelDescr = desc.getApelido() + " - " + desc.getDescricao();
        }
        return imovelDescr;
    }
}