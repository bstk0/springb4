package br.com.dbengine.springb4.Singleton;

import br.com.dbengine.springb4.dbUtil.JSONValidations;
import br.com.dbengine.springb4.entity.Imovel;
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

    /*
    Transforma JSON em objeto para evitar erros de CAST
    (java.lang.ClassCastException)
     */
    public static void setInstaceJSON(JSONArray array) {
        //System.out.println(">> setInstaceJSON ..." + array.size());
        List<Imovel> imovelList = new ArrayList<Imovel>();
        JSONObject iocc = null;
        Imovel imovel = null;
        for (int i = 0; i < array.size() ; i++) {
            iocc = (JSONObject) array.get(i);
            imovel = new Imovel();
            imovel.setId(JSONValidations.validaAtributo(iocc.get("id")));
            imovel.setImovelId( JSONValidations.parseAttrToInteger(iocc.get("imovelId")));
            imovel.setDescricao(JSONValidations.validaAtributo(iocc.get("descricao")));
            imovel.setApelido(JSONValidations.validaAtributo(iocc.get("apelido")));
            imovel.setBairro(JSONValidations.validaAtributo(iocc.get("bairro")));
            imovel.setTipo(JSONValidations.validaAtributo(iocc.get("tipo")));
            imovel.setStatus(JSONValidations.validaAtributo(iocc.get("status")));

            imovelList.add(imovel);
        }

        setInstance(imovelList);
    }
}
