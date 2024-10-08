package br.com.dbengine.springb4.DAO;

import br.com.dbengine.springb4.dbUtil.*;
import br.com.dbengine.springb4.entity.*;
import br.com.dbengine.springb4.form.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ImovelOcorrenciaDAO { //implements DAOInterface<ImovelOcorrencia> {
    private static CanonicClient canDb = new CanonicClient();
    private final String URL_ADD = canDb.CANONIC_REP1_BASE + "imovelOcorrencia";
    private final String URL_GET = canDb.CANONIC_REP1_BASE + "imovelOcorrencia/:_id";
    private final String URL_UPD = canDb.CANONIC_REP1_BASE + "imovelOcorrencia/:_id";
    private final String URL_DEL = canDb.CANONIC_REP1_BASE + "imovelOcorrencia/:_id";


    public List<ImovelOcorrencia> getList(int imovelId) {
        String resultGetAll = canDb.getList("getImovelOcorrByImovelId",imovelId);
        //Sysout.s(resultGetAll);
        JSONArray results = canDb.CanonicJSONList(resultGetAll);
        //List<ImovelOcorrencia> imovelOcorrList = this.getImovelOcorrList(results); //resultGetAll);
        List<ImovelOcorrencia> imovelOcorrList = UtilsJSON.getListFromJSON(results, ImovelOcorrencia.class); //resultGetAll);
        return imovelOcorrList;
    }

    public List<ImovelOcorrEmAberto> getListEmAberto() {
        String resultGetAll = canDb.getList("getImovelOcorrenciaEmAndamento");
        //Sysout.s(resultGetAll);
        JSONArray results = canDb.CanonicJSONList(resultGetAll);
        List<ImovelOcorrEmAberto> imovelOcorrList = this.getImovelOcorrEmAbertoList(results); //resultGetAll);
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
        String createdAt = UtilsJSON.cvtUTCDateToBr(iocc.get("createdAt"));
        String updatedAt = UtilsJSON.cvtUTCDateToBr(iocc.get("updatedAt"));

        //Sysout.s(" DAO - getImovelOcorrForm : " + iocc.toJSONString());

        String ioId = UtilsJSON.validaAtributo(iocc.get("id"));
        if (ioId.equals("")) {
            ioId = UtilsJSON.validaAtributo(iocc.get("_id"));
        }

        ImovelOcorrForm ioccFom = new ImovelOcorrForm(
                ioId,
                UtilsJSON.parseAttrToInteger(iocc.get("imovelId")),
                UtilsJSON.validaAtributo(iocc.get("descricao")),
                UtilsJSON.validaAtributo(iocc.get("nr_ref")),
                UtilsJSON.validaAtributo(iocc.get("statusFinal")),
                createdAt,   //JSONValidations.validaAtributo(iocc.get("createdAt")), //formattedDate,
                updatedAt);  //JSONValidations.validaAtributo(iocc.get("updatedAt"))); //dataUpdate);
        return ioccFom;
    }


    private List<ImovelOcorrEmAberto> getImovelOcorrEmAbertoList(JSONArray results) {
        List<ImovelOcorrEmAberto> retorno = new ArrayList<ImovelOcorrEmAberto>();
        ObjectMapper objectMapper=new ObjectMapper();
        results.forEach(item -> {
            JSONObject obj = (JSONObject) item;
            ImovelOcorrEmAberto imov = null;
            try {
                imov = objectMapper.readValue(obj.toString(), ImovelOcorrEmAberto.class);
                //Sysout.s(">>>" + imov.getId());
                // Descriçáo do Imovel
                if (imov != null) {
                    String imovelDescr = new ImovelDAO().getApelido(imov.getImovelId());
                    imov.setImovelDescricao(imovelDescr);
                    imov.setCreatedAt(UtilsJSON.cvtUTCDateToBr(imov.getCreatedAt()));
                    imov.setUpdatedAt(UtilsJSON.cvtUTCDateToBr(imov.getUpdatedAt()));
                    //String createdAt = JSONValidations.cvtUTCDateToBr(iocc.get("createdAt"));
                    //String updatedAt = JSONValidations.cvtUTCDateToBr(iocc.get("updatedAt"));
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                //throw new RuntimeException(e);
            }
            retorno.add(imov);
        });
        return retorno;
    }
}
