package br.com.dbengine.springb4.controller;
/*
HASURA - PEOPLE 1 - NEON.TECH
 */
import br.com.dbengine.springb4.DAO.*;
import br.com.dbengine.springb4.entity.*;
import org.json.simple.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class HPeopleController {

    //@Inject
    //private CulturaDAO dao = new CulturaDAO();
    @Autowired
    private HPeopleDAO dao;

//    @GetMapping("/cultura/form")
//    public String culturaForm(Model model) {
//        model.addAttribute("cultura", new Cultura());
//        return "cultura_form";
//    }

    //culturaList
    @GetMapping("/hpeopleList")
    public String hpeopleList(Model model) {
        //return dao.getList();
        List<HPeople> hpeopleList = new ArrayList<>();
        hpeopleList = dao.getList();
        //Sysout.s("culturaList size: " + culturaList.size());
        model.addAttribute("hpeopleList",hpeopleList);
        return "/hasura/people1_list";
    }

//    @PostMapping("/culturaAdd")
//    public String add(Model model, @ModelAttribute Cultura cultura) {
//        dao.add(cultura);
//        return "redirect:/culturaList";
//    }

//    @GetMapping("/culturaUpdForm")
//    public String culturaUpdForm(@RequestParam String culturaId,Model model) {
//        Cultura culturaUpd = new Cultura();
//        culturaUpd = dao.getItem(culturaId);
//        model.addAttribute("cultura", culturaUpd);
//        return "cultura_updform";
//    }

//    @PostMapping("/culturaUpdate")
//    public String culturaUpdate(@ModelAttribute Cultura cultura, @RequestParam("culturaId") String culturaId) {
//        //Sysout.s("UPDATE cultura..." + cultura.get_id());
//        //Sysout.s("UPDATE culturaId..." + culturaId);
//        if (cultura.get_id() == null) { cultura.set_id(culturaId); };
//        dao.update(cultura);
//        return "redirect:/culturaList";
//    }

//    @GetMapping("/culturaDelete")
//    public String culturaDelete(@RequestParam String culturaId) {
//         dao.delete(culturaId);
//        return "redirect:/culturaList";
//    }

//    private JSONObject culturaToJSON(Cultura cultura) {
//        JSONObject snuttgly = new JSONObject();
//        snuttgly.put("CulturaCodigo", cultura.getCodigo());
//        snuttgly.put("CulturaDescricao", cultura.getDescricao() );
//        //snuttgly.put("NovaColuna", "They are the best");
//        return snuttgly;
//    }
}
