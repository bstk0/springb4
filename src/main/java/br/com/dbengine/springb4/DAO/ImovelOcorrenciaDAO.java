package br.com.dbengine.springb4.DAO;

import br.com.dbengine.springb4.Singleton.*;
import br.com.dbengine.springb4.dbUtil.*;
import br.com.dbengine.springb4.entity.*;
import br.com.dbengine.springb4.form.ImovelOcorrForm;
import br.com.dbengine.springb4.interfaces.DAOInterface;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ImovelOcorrenciaDAO { //implements DAOInterface<ImovelOcorrencia> {
    private final String URL_ADD = "https://can.canonic.dev/rep1-180hdf/api/imovelOcorrencia";
    private final String URL_GET = "https://can.canonic.dev/rep1-180hdf/api/imovelOcorrencia/:_id";
    private final String URL_UPD = "https://can.canonic.dev/rep1-180hdf/api/imovelOcorrencia/:_id";
    private final String URL_DEL = "https://can.canonic.dev/rep1-180hdf/api/imovelOcorrencia/:_id";
    private static CanonicClient canDb = new CanonicClient();


    public List<ImovelOcorrencia> getList(int imovelId) {
        String resultGetAll = canDb.getList("getImovelOcorrByImovelId",imovelId);
        //Sysout.s(resultGetAll);
        JSONArray results = canDb.CanonicJSONList(resultGetAll);
        List<ImovelOcorrencia> imovelOcorrList = this.getImovelOcorrList(results); //resultGetAll);
        return imovelOcorrList;
    }

    public List<ImovelOcorrForm> getListForm(int imovelId) {
        String resultGetAll = canDb.getList("getImovelOcorrByImovelId",imovelId);
        //Sysout.s(resultGetAll);
        JSONArray imovelOccList = canDb.CanonicJSONList(resultGetAll);
        List<ImovelOcorrForm> iOccListForm = new ArrayList<ImovelOcorrForm>();
        for (Object o : imovelOccList) {
            JSONObject iocc = (JSONObject) o;
            ImovelOcorrForm ioccFom = getImovelOcorrForm(iocc);
            iOccListForm.add(ioccFom);
        }
        return iOccListForm;
    }

    public void add(ImovelOcorrencia imovelOcorrencia) {

        JSONObject obj = new JSONObject();
        JSONParser parser = new JSONParser();
        JSONObject innerObj = null;

        innerObj = this.convertIOtoJSON(imovelOcorrencia);
        obj.put("input", innerObj);

        //Sysout.s(" ADD ANTES >> " + obj.toJSONString());
        String opResult = canDb.add(URL_ADD, obj.toJSONString());
        //Sysout.s(" ADD RESULT >> " + opResult);
    }

    //@Override
    public void update(ImovelOcorrencia imovelOcc) {
        //Sysout.s("DAO - ImovelOcorrenciaDAO.update...");
        JSONObject obj = new JSONObject();
        JSONParser parser = new JSONParser();
        JSONObject innerObj = null;

        innerObj = convertIOtoJSON(imovelOcc);
        obj.put("_id", imovelOcc.getId());
        obj.put("input", innerObj);

        //Sysout.s("UPDATE ANTES >> " + obj.toJSONString());
        String opResult = canDb.update(URL_UPD, obj.toJSONString());
        //Sysout.s(" UPDATE RESULT >> " + opResult);
    }

    public ImovelOcorrForm getItemForm(String id) {
        String resultGetAll = canDb.getItemById(URL_GET,id);
        //Sysout.s(resultGetAll);
        JSONObject iocc = canDb.CanonicJSONItem(resultGetAll);
        ImovelOcorrForm ioccFom = getImovelOcorrForm(iocc);
        return ioccFom;
    }

    public void delete(String id) {
        canDb.deleteItemById(URL_DEL,id);
    }

    private JSONObject convertIOtoJSON(ImovelOcorrencia imovelOcorrencia) {
        JSONObject jo = new JSONObject();
        jo.put("id", imovelOcorrencia.getId());
        jo.put("imovelId", imovelOcorrencia.getImovelId());
        jo.put("descricao", imovelOcorrencia.getDescricao());
        jo.put("nr_ref", imovelOcorrencia.getNr_ref());
        jo.put("statusFinal", imovelOcorrencia.getStatusFinal());
        jo.put("imovel_id","00"); // TODO: RETIRAR DEPOIS - BUG DO CANONIC
        //jo.put("createdBy" , imovelOcorrencia.getCreatedBy());
        //jo.put("updatedBy" , imovelOcorrencia.getUpdatedBy());
        return jo;
    }

    @NotNull
    private static ImovelOcorrForm getImovelOcorrForm(JSONObject iocc) {
        //String formattedDate = JSONValidations.parseAttrToDateTimeBR(iocc.get("__createdtime__"));
        //String dataUpdate = JSONValidations.parseAttrToDateTimeBR(iocc.get("__updatedtime__"));

        //Sysout.s(" DAO - getImovelOcorrForm : " + iocc.toJSONString());

        String ioId = JSONValidations.validaAtributo(iocc.get("id"));
        if (ioId.equals("")) {
            ioId = JSONValidations.validaAtributo(iocc.get("_id"));
        }

        ImovelOcorrForm ioccFom = new ImovelOcorrForm(
                ioId,
                JSONValidations.parseAttrToInteger(iocc.get("imovelId")),
                JSONValidations.validaAtributo(iocc.get("descricao")),
                JSONValidations.validaAtributo(iocc.get("nr_ref")),
                JSONValidations.validaAtributo(iocc.get("statusFinal")),
                JSONValidations.validaAtributo(iocc.get("createdAt")), //formattedDate,
                JSONValidations.validaAtributo(iocc.get("updatedAt"))); //dataUpdate);
        return ioccFom;
    }

    private List<ImovelOcorrencia> getImovelOcorrList(JSONArray results) {
        List<ImovelOcorrencia> retorno = new ArrayList<ImovelOcorrencia>();
        ObjectMapper objectMapper=new ObjectMapper();
        results.forEach(item -> {
            JSONObject obj = (JSONObject) item;
            ImovelOcorrencia imov = null;
            try {
                imov = objectMapper.readValue(obj.toString(), ImovelOcorrencia.class);
                Sysout.s(">>>" + imov.getId());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                //throw new RuntimeException(e);
            }
            retorno.add(imov);
        });
        return retorno;
    }
}
