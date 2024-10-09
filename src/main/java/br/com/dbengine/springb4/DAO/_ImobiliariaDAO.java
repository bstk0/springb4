package br.com.dbengine.springb4.DAO;

import br.com.dbengine.springb4.Singleton.*;
import br.com.dbengine.springb4.dbUtil.HarperDBClient;
import br.com.dbengine.springb4.entity.Imobiliaria;
import br.com.dbengine.springb4.interfaces.DAOInterface;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class _ImobiliariaDAO implements DAOInterface<Imobiliaria> {

    private static HarperDBClient harperDb = new HarperDBClient();
    @Override
    public List<Imobiliaria> getList() {
        JSONParser parser = new JSONParser();
        Object obj = null;
        String resultGetAll;
        try {
            resultGetAll = harperDb.getList("select * FROM rep1.imobiliaria");
            obj = parser.parse(resultGetAll);
            JSONArray results = (JSONArray) (obj);
            return (ArrayList<Imobiliaria>) results;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new ArrayList<Imobiliaria>();
    }

    @Override
    public void add(Imobiliaria obj) {

    }

    @Override
    public void update(Imobiliaria obj) {

    }

    @Override
    public Imobiliaria getItem(String id) {
        return ImobListSingleton.getItem(Integer.parseInt(id));
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