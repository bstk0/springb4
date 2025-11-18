package br.com.dbengine.springb4.Singleton;

import br.com.dbengine.springb4.dbUtil.UtilsJSON;
import br.com.dbengine.springb4.entity.*;
import br.com.dbengine.springb4.interfaces.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ImovelListSingleton {

    private static List<Imovel> instance;

    public static List<Imovel> getInstance() {
        return instance;
    }

    public static void setInstance(List<Imovel> instance) {
        ImovelListSingleton.instance = instance;
    }

/*    public static void refresh() {
        new InitializeStaticData().initializeImovelList();
    }*/

    public static Imovel getItem(int id) {
        if(!instance.isEmpty()) {
            for (Imovel imovel : instance) {
                //Sysout.s("imovel.getImovelId() : " + imovel.getImovelId());
                if (imovel.getImovelId() == id) {
                    return imovel;
                }
            }
        }
        return new Imovel(); //DAOInterface.super.getItem(id);
    }

    public static int getCount() {
        return instance.size();
    }

}
