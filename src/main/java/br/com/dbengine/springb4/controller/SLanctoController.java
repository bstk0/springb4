package br.com.dbengine.springb4.controller;
/*
HASURA - PEOPLE 1 - NEON.TECH
 */
import br.com.dbengine.springb4.DAO.*;
import br.com.dbengine.springb4.dbUtil.*;
import br.com.dbengine.springb4.entity.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class SLanctoController {

    @Autowired
    private SLanctoDAO dao;

    @GetMapping("/slancto/addform")
    public String hproductAddForm(Model model) {
        SLancto objAdd = new SLancto();
        model.addAttribute("slancto", objAdd);
        return "/supabase/lancto_addform";
    }

    @GetMapping("/slanctoList")
    public String slanctoList(Model model) {
        List<SLancto> hpeopleList = new ArrayList<>();
        hpeopleList = dao.getList();
        model.addAttribute("slanctoList",hpeopleList);
        return "/supabase/lancto_list";
    }

    @PostMapping("/slanctoAdd")
    public String slanctoAdd(Model model, @ModelAttribute SLancto objAdd) {
        dao.add(objAdd);
        return "redirect:/slanctoList";
    }

    @GetMapping("/slanctoUpdForm")
    public String slanctoUpdForm(@RequestParam String slanctoId,Model model) {
        SLancto slancto = dao.getItem(slanctoId);
        model.addAttribute("slancto", slancto);
        return "/supabase/lancto_updform";
    }

    @PostMapping("/slanctoUpdate")
    public String slanctoUpdate(@ModelAttribute SLancto slancto,
                                @RequestParam("slanctoId") String slanctoId) {
        Sysout.s("UPDATE slanctoId..." + slanctoId);
        dao.update(slancto);
        return "redirect:/slanctoList";
    }

    @GetMapping("/slanctoDelete")
    public String slanctoDelete(@RequestParam String slanctoId) {
         dao.delete(slanctoId);
        return "redirect:/slanctoList";
    }

}
