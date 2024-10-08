package br.com.dbengine.springb4.Singleton;

import br.com.dbengine.springb4.DAO.*;
import br.com.dbengine.springb4.dbUtil.*;
import br.com.dbengine.springb4.entity.*;
import org.json.simple.*;

import java.util.*;

public class InitializeStaticData {



    public InitializeStaticData() {
        //new ImovelDAO().getList();
        Sysout.s("Initializing Static Data ...");
        initializeImobiliariaList();
        initializeImovelList();
        //new ImovelDAO().initializeImovelList();
        //new ImobiliariaDAO().initImobiliariaDAO();
    }

    private void initializeImovelList() {
        CanonicClient canDb = new CanonicClient();
        Object obj = null;
        String resultGetAll;
        resultGetAll = canDb.getList("imovel");
        JSONArray results = canDb.CanonicJSONList(resultGetAll);
        //List<Imovel> imovelList = this.getImovelList(results); //resultGetAll);
        List<Imovel> imovelList = UtilsJSON.getListFromJSON(results,Imovel.class); //resultGetAll);
        // Singleton
        //ImovelListSingleton.setInstaceJSON((JSONArray) results);
        ImovelListSingleton.setInstance(imovelList);
    }

    private void initializeImobiliariaList() {
        //Sysout.s(" initImobiliariaDAO ...");
        CanonicClient canDb = new CanonicClient();
        Object obj = null;
        String resultGetAll;
        resultGetAll = canDb.getList("imobiliarias");
        //Sysout.s(" >>> " + resultGetAll);
        JSONArray results = canDb.CanonicJSONList(resultGetAll);
        //Sysout.s(" >>> " +results.toJSONString());
        ImobiliariaDAO.setImobList( UtilsJSON.getListFromJSON(results,Imobiliaria.class) );
    }

}
