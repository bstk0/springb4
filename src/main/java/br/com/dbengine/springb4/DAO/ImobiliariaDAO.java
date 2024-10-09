package br.com.dbengine.springb4.DAO;

import br.com.dbengine.springb4.Singleton.*;
import br.com.dbengine.springb4.dbUtil.*;
import br.com.dbengine.springb4.entity.*;
import br.com.dbengine.springb4.interfaces.*;
import org.json.simple.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
public class ImobiliariaDAO implements DAOInterface<Imobiliaria> {

    @Autowired
    private CanonicClient canDb; // = new CanonicClient();

//    private static List<Imobiliaria> imobList;

//    public void initImobiliariaDAO() {
//        Sysout.s(" initImobiliariaDAO ...");
//        //canDb  = new CanonicClient();
//        Object obj = null;
//        String resultGetAll;
//        resultGetAll = canDb.getList("imobiliarias");
//        Sysout.s(" >>> " + resultGetAll);
//        JSONArray results = canDb.CanonicJSONList(resultGetAll);
//        Sysout.s(" >>> " +results.toJSONString());
//        //List<Imovel> imovelList = this.getImovelList(results); //resultGetAll);
//        imobList = UtilsJSON.getListFromJSON(results,Imobiliaria.class); //resultGetAll);
//        Sysout.s(imobList.toString());
//    }

    @Override
    public List<Imobiliaria> getList() {
        Sysout.s("Imob.getList ...");
//        if(imobList == null) {
//            Sysout.s("Imob.getList ... is empty.");
//            this.initImobiliariaDAO();
//        }
        return ImobListSingleton.getInstance();
    }

    @Override
    public void add(Imobiliaria obj) {

    }

    @Override
    public void update(Imobiliaria obj) {

    }

    @Override
    public Imobiliaria getItem(String id) {
        return ImobListSingleton.getItem( Integer.parseInt(id)); //DAOInterface.super.getItem(id);
    }

    @Override
    public String getCount() {
        return String.valueOf(ImobListSingleton.getInstance().size());
    }

    @Override
    public String delete(String id) {
        return "";
    }

//    public static void setImobList(List<Imobiliaria> pImobLit) {
//        imobList = pImobLit;
//    }
}
