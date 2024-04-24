package br.com.dbengine.springb4.controller;

import br.com.dbengine.springb4.DAO.*;
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
        return "/hasura/controleglic_list";
    }

    @GetMapping("/controleglic/addform")
    public String hpeopleAddForm(Model model) {
        ControleGlicemia hglic = new ControleGlicemia();
        model.addAttribute("hglic", hglic);
        return "/hasura/controleglic_addform";
    }

    @PostMapping("/controleglicAdd")
    public String hpeopleAdd(Model model, @ModelAttribute ControleGlicemia hglic) {
        dao.add(hglic);
        return "redirect:/controleGlicemiaList";
    }

}
