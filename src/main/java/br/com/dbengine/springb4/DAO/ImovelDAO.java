package br.com.dbengine.springb4.DAO;

import br.com.dbengine.springb4.Singleton.*;
import br.com.dbengine.springb4.dbUtil.*;
import br.com.dbengine.springb4.entity.Imovel;
import br.com.dbengine.springb4.interfaces.DAOInterface;
//import org.json.simple.*;
import org.jetbrains.annotations.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Component;


import java.util.*;

@Component
public class ImovelDAO implements DAOInterface<Imovel> {

    @Autowired
    private CanonicClient canDb;

    public List<Imovel> getList() { //throws ParseException {
        return ImovelListSingleton.getInstance();
    }

    @Override
    public void add(Imovel obj) {
    }

    @Override
    public void update(Imovel imovel) {
        String URL_UPD = canDb.CANONIC_REP1_BASE + "imovel/:_id";
        //Sysout.s("ImovelDAO.update...");
        JSONObject obj = new JSONObject();
        JSONParser parser = new JSONParser();
        JSONObject innerObj = null;

        innerObj = convertItoJSON(imovel);
        obj.put("_id", imovel.getId());
        obj.put("input", innerObj);

        Sysout.s("UPDATE JSON >> " + obj.toJSONString());
        String opResult = canDb.update(URL_UPD, obj.toJSONString());
        //Sysout.s("UPDATE RESULT >> " + opResult);

        //ImovelListSingleton.setInstance(null);
        ImovelListSingleton.refresh();
    }

    @Override
    public Imovel getItem(String id) {
        return ImovelListSingleton.getItem(Integer.parseInt(id));
    }

    public Imovel getItem(int id) {
        return ImovelListSingleton.getItem(id);
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
        //jo.put("imobiliaria",imovel.getImobiliaria());
        jo.put("imobiliaria", ImobListSingleton.getItem(imovel.getImobid()).getNome());
        jo.put("tipo",imovel.getTipo());
        jo.put("observacoes",imovel.getObservacoes());
        jo.put("imobid", imovel.getImobid());
        return jo;
    }


    public String getTitulo(int imovelId) {
        Imovel desc = this.getItem(imovelId);
        String imovelDescr;
        if (desc == null) {
            imovelDescr = "SINGLETON NAO CARREGADO - IMOVEL NAO LOCALIZADO";
        } else {
            imovelDescr = desc.getApelido() + " - " + desc.getDescricao();
            if (imovelDescr.equals("null - null")) {
                imovelDescr = "SINGLETON NAO CARREGADO - IMOVEL NAO LOCALIZADO";
            }
        }
        return imovelDescr;
    }
    public String getApelido(int imovelId) {
        Imovel desc = this.getItem(imovelId);
        String imovelDescr;
        if (desc == null) {
            imovelDescr = "SINGLETON NAO CARREGADO";
        } else {
            imovelDescr = desc.getApelido(); // + " - " + desc.getDescricao();
            Sysout.s(" ^^^ " + imovelDescr);
            if ((imovelDescr == null) || (imovelDescr.equals("null"))) {
                imovelDescr = "SINGLETON NAO CARREGADO";
            }
        }
        return imovelDescr;
    }
}