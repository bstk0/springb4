package br.com.dbengine.springb4.controller;

import br.com.dbengine.springb4.DAO.*;
import br.com.dbengine.springb4.dbUtil.*;
import br.com.dbengine.springb4.entity.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class ControleGlicemiaController {

    @Autowired
    private ControleGlicemiaDAO dao;

    @GetMapping("/controleGlicemiaList")
    public String controleGlicemiaList(Model model) {
        List<ControleGlicemia> hgliceList = new ArrayList<>();
        hgliceList = dao.getList();
        model.addAttribute("hgliceList", hgliceList);
        return "hasura/controleglic_list";
    }

    @GetMapping("/controleglic/addform")
    public String controleGlicemiaAddForm(Model model) {
        ControleGlicemia hglic = new ControleGlicemia();
        model.addAttribute("hglic", hglic);
        return "hasura/controleglic_addform";
    }

    @PostMapping("/controleglicAdd")
    public String hpeopleAdd(Model model, @ModelAttribute ControleGlicemia hglic) {
        dao.add(hglic);
        return "redirect:/controleGlicemiaList";
    }

    @GetMapping("/hglicUpdForm")
    public String hglicUpdForm(@RequestParam String hglicId,Model model) {
        ControleGlicemia  hglic = dao.getItem(hglicId);
        model.addAttribute("hglic", hglic);
        return "hasura/controleglic_updform";
    }

    @PostMapping("/controleglicUpd")
    public String controleglicUpd(@ModelAttribute ControleGlicemia hglic,
                                 @RequestParam("hglicId") String hglicId) {
        Sysout.s("UPDATE hproductId..." + hglicId);
        dao.update(hglic);
        return "redirect:/controleGlicemiaList";
    }

}
