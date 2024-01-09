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
    //private HarperDBClient harperDb = new HarperDBClient();
    private static CanonicClient canDb = new CanonicClient();

    private final String URL_UPD = "https://can.canonic.dev/rep1-180hdf/api/imovelFinanc/:_id";

    public ImovelFinanceiro getItem(Integer id) {
        String resultGetAll = canDb.getList("getImovelFinancByImovelId",id);
        JSONArray results = canDb.CanonicJSONList(resultGetAll);

        ObjectMapper objectMapper=new ObjectMapper();
        JSONObject obj = (JSONObject) results.get(0);
        ImovelFinanceiro ioccFom = null;
        try {
            ioccFom = objectMapper.readValue(obj.toString(), ImovelFinanceiro.class);
        } catch (JsonProcessingException e) {
            //throw new RuntimeException(e);
            e.getMessage();
        }
        return ioccFom;
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

        jo.put("dtInicioContr", imovelFinanceiro.getDtInicioContr());
        jo.put("dtFimContr", imovelFinanceiro.getDtFimContr());

        jo.put("sindico", imovelFinanceiro.getSindico());
        jo.put("administradora", imovelFinanceiro.getAdministradora());

        return jo;
    }

}
