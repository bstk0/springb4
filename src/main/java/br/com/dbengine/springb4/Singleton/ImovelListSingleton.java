package br.com.dbengine.springb4.Singleton;

import br.com.dbengine.springb4.dbUtil.JSONValidations;
import br.com.dbengine.springb4.entity.Imovel;
import br.com.dbengine.springb4.form.ImovelForm;
import br.com.dbengine.springb4.form.ImovelOcorrForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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

    public static void setInstaceJSON(JSONArray array) {
        System.out.println(">> setInstaceJSON ..." + array.size());
        List<Imovel> imovelList = new ArrayList<Imovel>();
        Imovel imovel = null;
        for (int i = 0; i < array.size() ; i++) {
            JSONObject iocc = (JSONObject) array.get(i);
            //String formattedDate = JSONValidations.parseAttrToDateBR(iocc.get("__createdtime__"));
            //System.out.println("COM FORMAT: " + formattedDate);

            imovel = new Imovel();
            imovel.setId(JSONValidations.validaAtributo(iocc.get("id")));
            imovel.setImovel(JSONValidations.validaAtributo(iocc.get("imovel")));
            imovel.setApelido(JSONValidations.validaAtributo(iocc.get("apelido")));
            imovel.setBairro(JSONValidations.validaAtributo(iocc.get("bairro")));
            imovel.setTipo(JSONValidations.validaAtributo(iocc.get("tipo")));
            imovel.setStatus(JSONValidations.validaAtributo(iocc.get("status")));

//            ImovelOcorrForm ioccFom = new ImovelOcorrForm(
//                    JSONValidations.validaAtributo(iocc.get("id")),
//                    JSONValidations.parseAttrToInteger(iocc.get("imovel_id")),
//                    JSONValidations.validaAtributo(iocc.get("descricao")),
//                    JSONValidations.validaAtributo(iocc.get("numero_ref")),
//                    JSONValidations.validaAtributo(iocc.get("status_final")),
//                    formattedDate);
            imovelList.add(imovel);
        }

        setInstance(imovelList);
    }
}
