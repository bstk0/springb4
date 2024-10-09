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

    public static Imobiliaria getItem(int id) {
        //List<Imobiliaria> imobList = ImobListSingleton.getInstance();
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

    /*
    Transforma JSON em objeto para evitar erros de CAST
    (java.lang.ClassCastException)
     */
//    public static void setInstaceJSON(JSONArray array) {
//        //Sysout.s(">> setInstaceJSON ..." + array.size());
//        List<Imovel> imovelList = new ArrayList<Imovel>();
//        JSONObject iocc = null;
//        Imovel imovel = null;
//        for (int i = 0; i < array.size() ; i++) {
//            iocc = (JSONObject) array.get(i);
//            imovel = new Imovel();
//            imovel.setId(UtilsJSON.validaAtributo(iocc.get("id")));
//            imovel.setImovelId( UtilsJSON.parseAttrToInteger(iocc.get("imovelId")));
//            imovel.setDescricao(UtilsJSON.validaAtributo(iocc.get("descricao")));
//            imovel.setApelido(UtilsJSON.validaAtributo(iocc.get("apelido")));
//            imovel.setBairro(UtilsJSON.validaAtributo(iocc.get("bairro")));
//            imovel.setTipo(UtilsJSON.validaAtributo(iocc.get("tipo")));
//            imovel.setStatus(UtilsJSON.validaAtributo(iocc.get("status")));
//            imovel.setImobiliaria(UtilsJSON.validaAtributo(iocc.get("imobiliaria")));
//            imovel.setObservacoes(UtilsJSON.validaAtributo(iocc.get("observacoes")));
//
//            imovelList.add(imovel);
//        }
//
//        setInstance(imovelList);
//    }
}
