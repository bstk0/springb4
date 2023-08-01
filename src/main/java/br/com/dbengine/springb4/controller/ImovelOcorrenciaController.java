package br.com.dbengine.springb4.controller;

import br.com.dbengine.springb4.DAO.ImovelOcorrenciaDAO;
import br.com.dbengine.springb4.entity.ImovelOcorrencia;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ImovelOcorrenciaController {

    private ImovelOcorrenciaDAO dao = new ImovelOcorrenciaDAO();

    @GetMapping("/imovelOcorrenciaList")
    public String imovelOcorrenciaList(Model model, @RequestParam String imovelId) {
        List<ImovelOcorrencia> imovelOccList = new ArrayList<ImovelOcorrencia>();
        imovelOccList = dao.getList(imovelId);
        //System.out.println("imovelList size: " + imovelList.size());
        model.addAttribute("imovelIdAttr",imovelId);
        model.addAttribute("imovelOccList",imovelOccList);
        return "imovelOcorrencia/list";
    }

    @GetMapping("/imovelOcorrenciaAdd")
    public String imovelOcorrenciaAdd(Model model, @RequestParam Integer imovelId) {
        ImovelOcorrencia imovOccAdd = new ImovelOcorrencia();
        imovOccAdd.setImovel_id(imovelId);
        model.addAttribute("imovelIdAttr",imovelId);
        model.addAttribute("imovelOcorrencia",imovOccAdd);
        return "imovelOcorrencia/add";
    }

    //@GetMapping("/imovelOcorrenciaSave")
    @PostMapping("/imovelOcorrenciaSave")
    public String imovelOcorrenciaSave(@ModelAttribute ImovelOcorrencia imovelOcorrencia)  {
        //JSONObject snuttgly = new JSONObject();
        //snuttgly = HarperDBOperation;
        //snuttgly.put("operation","sql");
        //snuttgly.put("sql","select * FROM rep1.imovel");
        //System.out.println(">> " + snuttgly.toJSONString());
        //System.out.println(">> " + imovelOcorrencia.getDescricao());
        dao.add(imovelOcorrencia);
        //return "imovelOcorrencia/list";
        //return "/imovelOcorrenciaList?imovelId=" + imovelOcorrencia.getImovel_id();
        String s = "redirect:/imovelOcorrenciaList?imovelId=" + imovelOcorrencia.getImovel_id();
        return s;
    }

}
