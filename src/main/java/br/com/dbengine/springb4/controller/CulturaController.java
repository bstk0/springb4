package br.com.dbengine.springb4.controller;

import br.com.dbengine.springb4.DAO.CulturaDAO;
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
        dao.add(cultura);
        return "redirect:/culturaList";
    }

    @GetMapping("/culturaUpdForm")
    public String culturaUpdForm(@RequestParam String culturaId,Model model) {
        Cultura culturaUpd = new Cultura();
        culturaUpd = dao.getItem(culturaId);
        model.addAttribute("cultura", culturaUpd);
        return "cultura_updform";
    }

    @PostMapping("/culturaUpdate")
    public String culturaUpdate(@ModelAttribute Cultura cultura, @RequestParam("culturaId") String culturaId) {
        //System.out.println("UPDATE cultura..." + cultura.get_id());
        //System.out.println("UPDATE culturaId..." + culturaId);
        if (cultura.get_id() == null) { cultura.set_id(culturaId); };
        dao.update(cultura);
        return "redirect:/culturaList";
    }

    @GetMapping("/culturaDelete")
    public String culturaDelete(@RequestParam String culturaId) {
         dao.delete(culturaId);
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
