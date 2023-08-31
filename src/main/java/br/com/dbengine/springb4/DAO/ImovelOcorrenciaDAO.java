package br.com.dbengine.springb4.DAO;

import br.com.dbengine.springb4.dbUtil.HarperDBClient;
import br.com.dbengine.springb4.dbUtil.JSONValidations;
import br.com.dbengine.springb4.dbUtil.Sysout;
import br.com.dbengine.springb4.entity.ImovelOcorrencia;
import br.com.dbengine.springb4.form.ImovelOcorrForm;
import br.com.dbengine.springb4.interfaces.DAOInterface;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ImovelOcorrenciaDAO implements DAOInterface<ImovelOcorrencia> {

    private static HarperDBClient harperDb = new HarperDBClient();

    public List<ImovelOcorrencia> getList(String imovelId) {
        String strQuery = "select * FROM rep1.imovelOcorrencia where imovel_id = " + imovelId;
        strQuery += " order by __createdtime__ desc";
        JSONParser parser = new JSONParser();
        Object obj = null;
        String resultGetAll;
        try {
            resultGetAll = harperDb.getList(strQuery);
            obj = parser.parse(resultGetAll);
            JSONArray results = (JSONArray) (obj);
            return (ArrayList<ImovelOcorrencia>) results;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new ArrayList<ImovelOcorrencia>();
    }

    public List<ImovelOcorrForm> getListForm(String imovelId) {
        String strQuery = "select * FROM rep1.imovelOcorrencia where imovel_id = " + imovelId;
        strQuery += " order by __createdtime__ desc";

        JSONArray imovelOccList=  harperDb.getJSONList(strQuery);
        //System.out.println("imovelOccList size: " + imovelOccList.size());
        List<ImovelOcorrForm> iOccListForm = new ArrayList<ImovelOcorrForm>();
        for (int i = 0; i < imovelOccList.size() ; i++) {
            JSONObject iocc = (JSONObject) imovelOccList.get(i);
            ImovelOcorrForm ioccFom = getImovelOcorrForm(iocc);
            iOccListForm.add(ioccFom);
        }
        return iOccListForm;
    }



    @Override
    public List<ImovelOcorrencia> getList() {
        return null;
    }

    @Override
    public void add(ImovelOcorrencia imovelOcorrencia) {

        JSONObject obj = new JSONObject();
//        try {
            obj.put("operation", "insert");
            obj.put("schema", "rep1");
            obj.put("table", "imovelOcorrencia");

            JSONArray list = new JSONArray();

            JSONParser parser = new JSONParser();
            JSONObject innerObj = null;

            //System.out.println(" objToString >> " + imovelOcorrencia.toString());

            innerObj = convertIOtoJSON(imovelOcorrencia);
            list.add(innerObj);
            obj.put("records", list);
//        } catch (ParseException e) {
//            //throw new RuntimeException(e);
//            System.out.println("ERRO PARSE: " + e.toString());
//        }
//        System.out.println(">> " + obj.toJSONString());
        String opResult = harperDb.execOperation(obj.toJSONString());
//        System.out.println(" RESULT >> " + opResult);

    }

    @Override
    public void update(ImovelOcorrencia imovelOcc) {
        System.out.println("ImovelOcorrenciaDAO.update...");
        JSONObject objJS = new JSONObject();
//        try {
        objJS.put("operation", "update");
        objJS.put("schema", "rep1");
        objJS.put("table", "imovelOcorrencia");

        JSONArray list = new JSONArray();

        JSONParser parser = new JSONParser();
        JSONObject innerObj = null;

        innerObj = convertIOtoJSON(imovelOcc);
        list.add(innerObj);
        objJS.put("records", list);

        Sysout.s("DAO-117: " + objJS.toJSONString());

        String opResult = harperDb.execOperation(objJS.toJSONString());

        Sysout.s("UPDATE: " + opResult);

    }

    public ImovelOcorrencia getItem(String id) {
        return null;
    }

    public ImovelOcorrForm getItemForm(String id) {
        //return DAOInterface.super.getItem(id);
        //TODO: Vai ter que fazer algo igual ao getJSONList ...

        //JSONObject iocc = getJSONItem(id);
        String strQuery = "select * FROM rep1.imovelOcorrencia where id = '" + id + "'";
        JSONObject iocc = harperDb.getJSONItem(strQuery);

        ImovelOcorrForm ioccFom = getImovelOcorrForm(iocc);
//
//        String formattedDate = JSONValidations.parseAttrToDateBR(iocc.get("__createdtime__"));
//        //System.out.println("COM FORMAT: " + formattedDate);
//
//        ImovelOcorrForm ioccFom = new ImovelOcorrForm(
//                JSONValidations.validaAtributo(iocc.get("id")),
//                JSONValidations.parseAttrToInteger(iocc.get("imovel_id")),
//                JSONValidations.validaAtributo(iocc.get("descricao")),
//                JSONValidations.validaAtributo(iocc.get("numero_ref")),
//                JSONValidations.validaAtributo(iocc.get("status_final")),
//                formattedDate);
        return ioccFom;
    }

    @Override
    public String getCount() {
        return null;
    }

    @Override
    public String delete(String id) {
        //return null;
        JSONObject obj = new JSONObject();
//        try {
        obj.put("operation", "delete");
        obj.put("schema", "rep1");
        obj.put("table", "imovelOcorrencia");

        JSONArray list = new JSONArray();
        JSONParser parser = new JSONParser();

        List<String> listString = new ArrayList<String>();
        listString.add(id);

        obj.put("hash_values", listString); //list);

        Sysout.s(">> " + obj.toJSONString());

        String opResult = harperDb.execOperation(obj.toJSONString());
        Sysout.s("DELETE: " + opResult);
        return opResult;
    }

    private JSONObject convertIOtoJSON(ImovelOcorrencia imovelOcorrencia) {
        JSONObject jo = new JSONObject();
        jo.put("id", imovelOcorrencia.getId());
        jo.put("imovel_id", imovelOcorrencia.getImovel_id());
        jo.put("descricao", imovelOcorrencia.getDescricao());
        jo.put("numero_ref", imovelOcorrencia.getNumero_ref());
        jo.put("status_final", imovelOcorrencia.getStatus_final());
        jo.put("createdBy" , imovelOcorrencia.getCreatedBy());
        jo.put("updatedBy" , imovelOcorrencia.getUpdatedBy());
        return jo;
    }

    @NotNull
    private static ImovelOcorrForm getImovelOcorrForm(JSONObject iocc) {
        String formattedDate = JSONValidations.parseAttrToDateTimeBR(iocc.get("__createdtime__"));
        String dataUpdate = JSONValidations.parseAttrToDateTimeBR(iocc.get("__updatedtime__"));
        //System.out.println("COM FORMAT: " + formattedDate);

        ImovelOcorrForm ioccFom = new ImovelOcorrForm(
                JSONValidations.validaAtributo(iocc.get("id")),
                JSONValidations.parseAttrToInteger(iocc.get("imovel_id")),
                JSONValidations.validaAtributo(iocc.get("descricao")),
                JSONValidations.validaAtributo(iocc.get("numero_ref")),
                JSONValidations.validaAtributo(iocc.get("status_final")),
                formattedDate,
                dataUpdate);
        return ioccFom;
    }

}
