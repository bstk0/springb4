package br.com.dbengine.springb4.DAO;

import br.com.dbengine.springb4.Singleton.ImovelListSingleton;
import br.com.dbengine.springb4.dbUtil.HarperDBClient;
import br.com.dbengine.springb4.entity.Imovel;
import br.com.dbengine.springb4.interfaces.DAOInterface;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class ImovelDAO implements DAOInterface<Imovel> {

    private static HarperDBClient harperDb = new HarperDBClient();
    @Override
    public List<Imovel> getList() {

        if ( ImovelListSingleton.getInstance() != null) {
            //System.out.println("Usando singleton");
            return ImovelListSingleton.getInstance();
        }
        JSONParser parser = new JSONParser();
        Object obj = null;
        String resultGetAll;
        try {
            resultGetAll = harperDb.getList();
            obj = parser.parse(resultGetAll);
            JSONArray results = (JSONArray) (obj);
            List<Imovel> imovelList = (ArrayList<Imovel>) results;
            //singleton
            //ImovelListSingleton.setInstance(imovelList);
            ImovelListSingleton.setInstaceJSON((JSONArray) results);
            return imovelList;      // (ArrayList<Imovel>) results;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new ArrayList<Imovel>();

    }

    @Override
    public void add(Imovel obj) {

    }

    @Override
    public void update(Imovel obj) {

    }

    @Override
    public Imovel getItem(String id) {
        List<Imovel> imovelList = ImovelListSingleton.getInstance();
        for(Imovel imovel : imovelList) {
            if(imovel.getId().equals(id)) {
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
}