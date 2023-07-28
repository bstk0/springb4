package br.com.dbengine.springb4.controller;

import br.com.dbengine.springb4.dao.CulturaDAO;
import br.com.dbengine.springb4.entity.Cultura;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CulturaController {

    //@Inject
    private CulturaDAO dao = new CulturaDAO();

    @GetMapping("/cultura/form")
    public String culturaForm(Model model) {
        model.addAttribute("cultura", new Cultura());
        return "cultura_form";
    }

    //culturaList
    @GetMapping("/culturaList")
    public String culturaList(Model model) {
        //return dao.getList();
        List<Cultura> culturaList = new ArrayList<>();
        culturaList = dao.getList();
        //System.out.println("culturaList size: " + culturaList.size());
        model.addAttribute("culturaList",culturaList);
        return "cultura_list";
    }
    //@GetMapping("/cultura/add")
    //@RequestMapping(value="/culturaAdd", method= RequestMethod.GET)
    @PostMapping("/culturaAdd")
    public String add(Model model, @ModelAttribute Cultura cultura) {
        System.out.println("ADD cultura...");
        dao.add(cultura);
        //JSONObject snuttgly = culturaToJSON(cultura);
        //String resultWoobly = restDb.post(COLLECTION, snuttgly.toJSONString());
        //return "cultura_list";
        return "redirect:/culturaList";
    }

    private JSONObject culturaToJSON(Cultura cultura) {
        JSONObject snuttgly = new JSONObject();
        snuttgly.put("CulturaCodigo", cultura.getCodigo());
        snuttgly.put("CulturaDescricao", cultura.getDescricao() );
        //snuttgly.put("NovaColuna", "They are the best");
        return snuttgly;
    }
}
