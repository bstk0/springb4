package br.com.dbengine.springb4.DAO;

import br.com.dbengine.springb4.dbUtil.HarperDBClient;
import br.com.dbengine.springb4.dbUtil.JSONValidations;
import br.com.dbengine.springb4.dbUtil.Sysout;
import br.com.dbengine.springb4.entity.ImovelFinanceiro;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

@Component
public class ImovelFinanceiroDAO {
    //@Autowired
    private HarperDBClient harperDb = new HarperDBClient();

    public ImovelFinanceiro getItem(Integer id) {
        String strQuery = "select * FROM rep1.imovelFinanc where imovel_id = '" + id + "'";
        JSONObject iocc = harperDb.getJSONItem(strQuery);

        ImovelFinanceiro ioccFom = getImovelFinanc(iocc);
        return ioccFom;

    }

    public void update(ImovelFinanceiro imovelFin) {
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

    private static ImovelFinanceiro getImovelFinanc(JSONObject jfin) {
        String formattedDate = JSONValidations.parseAttrToDateTimeBR(jfin.get("__createdtime__"));
        String dataUpdate = JSONValidations.parseAttrToDateTimeBR(jfin.get("__updatedtime__"));
        //System.out.println("COM FORMAT: " + formattedDate);

        ImovelFinanceiro ifim = new ImovelFinanceiro();
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

//        ImovelFinanceiro ifim = new ImovelFinanceiro(
//                JSONValidations.validaAtributo(iocc.get("id")),
//                JSONValidations.parseAttrToInteger(iocc.get("imovel_id")),
//                JSONValidations.validaAtributo(iocc.get("descricao")),
//                JSONValidations.validaAtributo(iocc.get("numero_ref")),
//                JSONValidations.validaAtributo(iocc.get("status_final")),
//                formattedDate,
//                dataUpdate);
        return ifim;
    }

    private JSONObject convertIFtoJSON(ImovelFinanceiro imovelFinanceiro) {
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

        //jo.put("numero_ref", imovelOcorrencia.getNumero_ref());
        //jo.put("status_final", imovelOcorrencia.getStatus_final());
        jo.put("createdBy" , imovelFinanceiro.getCreatedBy());
        jo.put("updatedBy" , imovelFinanceiro.getUpdatedBy());
        return jo;
    }



}
