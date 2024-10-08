package br.com.dbengine.springb4.Singleton;

import br.com.dbengine.springb4.dbUtil.UtilsJSON;
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

    public static void refresh() {
        new InitializeStaticData().initializeImovelList();
    }

    /*
    Transforma JSON em objeto para evitar erros de CAST
    (java.lang.ClassCastException)
     */
    public static void setInstaceJSON(JSONArray array) {
        //Sysout.s(">> setInstaceJSON ..." + array.size());
        List<Imovel> imovelList = new ArrayList<Imovel>();
        JSONObject iocc = null;
        Imovel imovel = null;
        for (int i = 0; i < array.size() ; i++) {
            iocc = (JSONObject) array.get(i);
            imovel = new Imovel();
            imovel.setId(UtilsJSON.validaAtributo(iocc.get("id")));
            imovel.setImovelId( UtilsJSON.parseAttrToInteger(iocc.get("imovelId")));
            imovel.setDescricao(UtilsJSON.validaAtributo(iocc.get("descricao")));
            imovel.setApelido(UtilsJSON.validaAtributo(iocc.get("apelido")));
            imovel.setBairro(UtilsJSON.validaAtributo(iocc.get("bairro")));
            imovel.setTipo(UtilsJSON.validaAtributo(iocc.get("tipo")));
            imovel.setStatus(UtilsJSON.validaAtributo(iocc.get("status")));
            imovel.setImobiliaria(UtilsJSON.validaAtributo(iocc.get("imobiliaria")));
            imovel.setObservacoes(UtilsJSON.validaAtributo(iocc.get("observacoes")));

            imovelList.add(imovel);
        }

        setInstance(imovelList);
    }
}
