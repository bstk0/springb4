package br.com.dbengine.springb4.DAO;

import br.com.dbengine.springb4.dbUtil.HarperDBClient;
import br.com.dbengine.springb4.entity.ImovelOcorrencia;
import br.com.dbengine.springb4.form.ImovelPagtoListForm;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReportsDAO {

    private HarperDBClient harperDb = new HarperDBClient();

    public List<ImovelPagtoListForm> getImovelPagtosList() {
        //String strQuery = "select * FROM rep1.imovelOcorrencia where imovel_id = " + imovelId;
        //strQuery += " order by __createdtime__ desc";
        String strQuery = "select a.imovel_id, a.diaPagtoAluguel, b.apelido, a.vlAluguel, c.nome as nomeImobiliaria";
        strQuery += " from rep1.imovelFinanc AS a";
        strQuery += " INNER JOIN rep1.imovel AS b ON b.id = a.imovel_id";
        strQuery += " INNER JOIN rep1.imobiliaria AS c ON c.id = a.imobiliaria_id";
        strQuery += " where a.diaPagtoAluguel > 0";
        strQuery += " order by a.diaPagtoAluguel";
        JSONParser parser = new JSONParser();
        Object obj = null;
        String resultGetAll;
        try {
            resultGetAll = harperDb.getList(strQuery);
            obj = parser.parse(resultGetAll);
            JSONArray results = (JSONArray) (obj);
            return (ArrayList<ImovelPagtoListForm>) results;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new ArrayList<ImovelPagtoListForm>();
    }

}
