package br.com.dbengine.springb4.Singleton;

import br.com.dbengine.springb4.dbUtil.*;
import br.com.dbengine.springb4.entity.*;
import org.json.simple.*;

import java.util.*;

public class ImobListSingleton {

    private static List<Imobiliaria> instance;

    public static List<Imobiliaria> getInstance() {
        return instance;
    }

    public static void setInstance(List<Imobiliaria> instance) {
        ImobListSingleton.instance = instance;
    }

/*    public static void refresh() {
        new InitializeStaticData().initializeImobiliariaList();
    }*/

    public static Imobiliaria getItem(int id) {
        if(!instance.isEmpty()) {
            for (Imobiliaria imob : instance) {
                    //Sysout.s("imovel.getImovelId() : " + imovel.getImovelId());
                    if (imob.getImobid() == id) {
                        return imob;
                    }
            }
        }
        return new Imobiliaria(); //DAOInterface.super.getItem(id);
    }

    public static int getCount() {
        return instance.size();
    }

}
