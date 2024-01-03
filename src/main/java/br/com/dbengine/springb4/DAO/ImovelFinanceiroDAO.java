package br.com.dbengine.springb4.DAO;

import br.com.dbengine.springb4.dbUtil.*;
import br.com.dbengine.springb4.entity.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ImovelFinanceiroDAO {
    //@Autowired
    private HarperDBClient harperDb = new HarperDBClient();
    private static CanonicClient canDb = new CanonicClient();

    private final String URL_UPD = "https://can.canonic.dev/rep1-180hdf/api/imovelFinanc/:_id";

    public ImovelFinanceiro getItem(Integer id) {
        //String strQuery = "select * FROM rep1.imovelFinanc where imovel_id = '" + id + "'";

        String resultGetAll = canDb.getList("getImovelFinancByImovelId",id);
        //Sysout.s(resultGetAll);
        //obj = parser.parse(resultGetAll);
        //29.12
        JSONArray results = canDb.CanonicJSONList(resultGetAll);
        //29.12 - FIM

        ObjectMapper objectMapper=new ObjectMapper();
        JSONObject obj = (JSONObject) results.get(0);
        ImovelFinanceiro ioccFom = null;
        try {
            ioccFom = objectMapper.readValue(obj.toString(), ImovelFinanceiro.class);
        } catch (JsonProcessingException e) {
            //throw new RuntimeException(e);
            e.getMessage();
        }
        //Sysout.s("[[29.12]] " + results.get(0).toString());
        //ImovelFinanceiro ioccFom = (ImovelFinanceiro) results.get(0);
        //ImovelFinanceiro ioccFom = getImovelFinanc(iocc);
        return ioccFom;

    }

    public _ImovelFinanceiro _getItem(Integer id) {
        String strQuery = "select * FROM rep1.imovelFinanc where imovel_id = '" + id + "'";
        JSONObject iocc = harperDb.getJSONItem(strQuery);

        _ImovelFinanceiro ioccFom = getImovelFinanc(iocc);
        return ioccFom;

    }

    public void _update(ImovelFinanceiro imovelFin) {
        System.out.println("ImovelFinanceiroDAO.update...");
        JSONObject objJS = new JSONObject();
//        try {
        objJS.put("operation", "update");
        objJS.put("schema", "rep1");
        objJS.put("table", "imovelFinanc");

        JSONArray list = new JSONArray();

        JSONParser parser = new JSONParser();
        JSONObject innerObj = null;

        innerObj = convertIFtoJSON(imovelFin);
        list.add(innerObj);
        objJS.put("records", list);

        Sysout.s("DAO-46: " + objJS.toJSONString());

        String opResult = harperDb.execOperation(objJS.toJSONString());

        Sysout.s("UPDATE: " + opResult);

    }

    public void update(ImovelFinanceiro imovelFin) {
        System.out.println("C - ImovelFinanceiroDAO.update...");

        JSONObject obj = new JSONObject();
        JSONParser parser = new JSONParser();
        JSONObject innerObj = null;

        innerObj = convertIFtoJSON(imovelFin);
        obj.put("_id", imovelFin.getId());
        obj.put("input", innerObj);

        Sysout.s(">> " + obj.toJSONString());
        String opResult = canDb.update(URL_UPD, obj.toJSONString());
        Sysout.s(" RESULT >> " + opResult);


/*
        JSONObject objJS = new JSONObject();
//        try {
        objJS.put("operation", "update");
        objJS.put("schema", "rep1");
        objJS.put("table", "imovelFinanc");

        JSONArray list = new JSONArray();

        JSONParser parser = new JSONParser();
        JSONObject innerObj = null;

        innerObj = convertIFtoJSON(imovelFin);
        list.add(innerObj);
        objJS.put("records", list);

        Sysout.s("DAO-46: " + objJS.toJSONString());

        String opResult = harperDb.execOperation(objJS.toJSONString());

        Sysout.s("UPDATE: " + opResult);
*/

    }

    private static _ImovelFinanceiro getImovelFinanc(JSONObject jfin) {
        String formattedDate = JSONValidations.parseAttrToDateTimeBR(jfin.get("__createdtime__"));
        String dataUpdate = JSONValidations.parseAttrToDateTimeBR(jfin.get("__updatedtime__"));
        //System.out.println("COM FORMAT: " + formattedDate);

        _ImovelFinanceiro ifim = new _ImovelFinanceiro();
        ifim.setImovel_id(JSONValidations.parseAttrToInteger(jfin.get("imovel_id")));
        ifim.setVlAluguel(Sysout.getaDouble(
                JSONValidations.validaAtributo(jfin.get("vlAluguel"))
        ));
        ifim.setVlCondominio(Sysout.getaDouble(
                JSONValidations.validaAtributo(jfin.get("vlCondominio"))
        ));
        ifim.setVlIPTU(Sysout.getaDouble(
                JSONValidations.validaAtributo(jfin.get("vlIPTU"))
        ));
        ifim.setVlIPTUDesc(Sysout.getaDouble(
                JSONValidations.validaAtributo(jfin.get("vlIPTUDesc"))
        ));
        ifim.setNroContrato(JSONValidations.validaAtributo(jfin.get("nroContrato")));

        ifim.setDiaPagtoAluguel(JSONValidations.parseAttrToInteger(jfin.get("diaPagtoAluguel")));
        ifim.setDiaPagtoCondominio(JSONValidations.parseAttrToInteger(jfin.get("diaPagtoCondominio")));
        ifim.setImobiliaria_id(JSONValidations.parseAttrToInteger(jfin.get("imobiliaria_id")));
        ifim.setCodLUZ(JSONValidations.validaAtributo(jfin.get("codLUZ")));
        ifim.setCodDAEM(JSONValidations.validaAtributo(jfin.get("codDAEM")));

        ifim.setDtInicioContrato(
                JSONValidations.parseAttrToDateBR(jfin.get("dtInicioContrato"))
        );

        ifim.setDtFimContrato(
                JSONValidations.parseAttrToDateBR(jfin.get("dtFimContrato"))
        );

        ifim.setCpfCadastrado(JSONValidations.validaAtributo(jfin.get("cpfCadastrado")));
        ifim.setNroInscrPrefeitura(JSONValidations.validaAtributo(jfin.get("nroInscrPrefeitura")));
        ifim.setDadosGerais(JSONValidations.validaAtributo(jfin.get("dadosGerais")));

        ifim.setSindico(JSONValidations.validaAtributo(jfin.get("sindico")));
        ifim.setAdministradora(JSONValidations.validaAtributo(jfin.get("administradora")));
        return ifim;
    }

    private JSONObject _convertIFtoJSON(_ImovelFinanceiro imovelFinanceiro) {
        JSONObject jo = new JSONObject();
        //jo.put("id", imovelFinanceiro.getImovel_id();
        jo.put("imovel_id", imovelFinanceiro.getImovel_id());
        jo.put("dadosGerais", imovelFinanceiro.getDadosGerais() );
        jo.put("vlAluguel", imovelFinanceiro.getVlAluguel() );
        jo.put("vlCondominio", imovelFinanceiro.getVlCondominio() );
        jo.put("vlIPTU", imovelFinanceiro.getVlIPTU() );
        jo.put("vlIPTUDesc", imovelFinanceiro.getVlIPTUDesc() );
        jo.put("imobiliaria_id", imovelFinanceiro.getImobiliaria_id());
        jo.put("codLUZ", imovelFinanceiro.getCodLUZ());
        jo.put("codDAEM", imovelFinanceiro.getCodDAEM());
        jo.put("diaPagtoAluguel", imovelFinanceiro.getDiaPagtoAluguel());
        jo.put("diaPagtoCondominio", imovelFinanceiro.getDiaPagtoCondominio());
        jo.put("cpfCadastrado", imovelFinanceiro.getCpfCadastrado());
        jo.put("nroContrato", imovelFinanceiro.getNroContrato());
        jo.put("nroInscrPrefeitura", imovelFinanceiro.getNroInscrPrefeitura());


        if(imovelFinanceiro.getDtInicioContrato() != null && !("".equals(imovelFinanceiro.getDtFimContrato()))) {
            jo.put("dtInicioContrato", Sysout.dateStringtoUnix(imovelFinanceiro.getDtInicioContrato()));
        }

        Sysout.s(" >> dtFimContrato antes : " + imovelFinanceiro.getDtFimContrato());

        if(imovelFinanceiro.getDtFimContrato() != null && !("".equals(imovelFinanceiro.getDtFimContrato()))) {
            jo.put("dtFimContrato", Sysout.dateStringtoUnix(imovelFinanceiro.getDtFimContrato()));
        }

        jo.put("sindico", imovelFinanceiro.getSindico());
        jo.put("administradora", imovelFinanceiro.getAdministradora());

        //jo.put("numero_ref", imovelOcorrencia.getNumero_ref());
        //jo.put("status_final", imovelOcorrencia.getStatus_final());
        jo.put("createdBy" , imovelFinanceiro.getCreatedBy());
        jo.put("updatedBy" , imovelFinanceiro.getUpdatedBy());
        return jo;
    }

    private JSONObject convertIFtoJSON(ImovelFinanceiro imovelFinanceiro) {
        JSONObject jo = new JSONObject();
        jo.put("id", imovelFinanceiro.getId());
        jo.put("imovel_id", imovelFinanceiro.getImovelId());
        jo.put("dadosGerais", imovelFinanceiro.getDadosGerais() );
        jo.put("vl_aluguel", imovelFinanceiro.getVl_aluguel() );
        jo.put("vl_condom", imovelFinanceiro.getVl_condom() );
        jo.put("vl_iptu", imovelFinanceiro.getVl_iptu() );
        jo.put("vl_iptu_desc", imovelFinanceiro.getVl_iptu_desc() );
        //jo.put("imobiliaria_id", imovelFinanceiro.getImobiliaria_id());
        jo.put("cd_luz", imovelFinanceiro.getCd_luz());
        jo.put("cd_daem", imovelFinanceiro.getCd_daem());
        jo.put("diaPagtoAluguel", imovelFinanceiro.getDiaPagtoAluguel());
        jo.put("diaPagtoCond", imovelFinanceiro.getDiaPagtoCond());
        jo.put("cpfCadastrado", imovelFinanceiro.getCpfCadastrado());
        jo.put("nr_contrato", imovelFinanceiro.getNr_contrato());
        jo.put("nr_inscr", imovelFinanceiro.getNr_inscr());

//        if(imovelFinanceiro.getDtInicioContrato() != null && !("".equals(imovelFinanceiro.getDtFimContrato()))) {
//            jo.put("dtInicioContrato", Sysout.dateStringtoUnix(imovelFinanceiro.getDtInicioContrato()));
//        }
//
//        Sysout.s(" >> dtFimContrato antes : " + imovelFinanceiro.getDtFimContrato());
//
//        if(imovelFinanceiro.getDtFimContrato() != null && !("".equals(imovelFinanceiro.getDtFimContrato()))) {
//            jo.put("dtFimContrato", Sysout.dateStringtoUnix(imovelFinanceiro.getDtFimContrato()));
//        }

        jo.put("dtInicioContr", imovelFinanceiro.getDtInicioContr());
        jo.put("dtFimContr", imovelFinanceiro.getDtFimContr());

        jo.put("sindico", imovelFinanceiro.getSindico());
        jo.put("administradora", imovelFinanceiro.getAdministradora());

        //jo.put("numero_ref", imovelOcorrencia.getNumero_ref());
        //jo.put("status_final", imovelOcorrencia.getStatus_final());
        //jo.put("createdBy" , imovelFinanceiro.getCreatedBy());
        //jo.put("updatedBy" , imovelFinanceiro.getUpdatedBy());
        return jo;
    }


}
