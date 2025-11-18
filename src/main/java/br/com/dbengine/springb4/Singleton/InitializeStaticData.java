package br.com.dbengine.springb4.Singleton;

import br.com.dbengine.springb4.DAO.*;
import br.com.dbengine.springb4.dbUtil.*;
import br.com.dbengine.springb4.entity.*;
import org.json.simple.*;
import org.springframework.stereotype.*;

import java.util.*;

public class InitializeStaticData {

    private static boolean isLoaded = false;

    public InitializeStaticData() {
    }

    public void AllData() {
        if (!isLoaded) {
            Sysout.s("Initializing ALL Static Data ...");
            //initializeImobiliariaList();
            //initializeImovelList();
            isLoaded = true;
        }
    }

/*    public void initializeImovelList() {
        Sysout.s("Initializing Static Data ...Imovel List");
        CanonicClient canDb = new CanonicClient();
        Object obj = null;
        String resultGetAll;
        resultGetAll = canDb.getList("imovel");
        JSONArray results = canDb.CanonicJSONList(resultGetAll);

        List<Imovel> imovelList = UtilsJSON.getListFromJSON(results,Imovel.class); //resultGetAll);

        ImovelListSingleton.setInstance(imovelList);
    }

    public void initializeImobiliariaList() {
        Sysout.s("Initializing Static Data ...Imob List");
        CanonicClient canDb = new CanonicClient();
        Object obj = null;
        String resultGetAll;
        resultGetAll = canDb.getList("imobiliarias");
        JSONArray results = canDb.CanonicJSONList(resultGetAll);

        ImobListSingleton.setInstance( UtilsJSON.getListFromJSON(results,Imobiliaria.class) );
    }*/

}
