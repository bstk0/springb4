package br.com.dbengine.springb4.controller;

import br.com.dbengine.springb4.DAO.*;
import br.com.dbengine.springb4.entity.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class ControlePesoController {

    @Autowired
    private ControlePesoDAO dao;

    @GetMapping("/controlePesoList")
    public String controlePesoList(Model model) {
        List<ControlePeso> hpesoList = new ArrayList<>();
        hpesoList = dao.getList();
        model.addAttribute("hpesoList", hpesoList);
        return "/hasura/controlepeso_list";
    }

    @GetMapping("/controlePeso/addform")
    public String controlePesoAddForm(Model model) {
        ControlePeso hpeso = new ControlePeso();
        model.addAttribute("hpeso", hpeso);
        return "/hasura/controlepeso_addform";
    }

    @PostMapping("/controlePesoAdd")
    public String controlePesoAdd(Model model, @ModelAttribute ControlePeso hpeso) {
        dao.add(hpeso);
        return "redirect:/controlePesoList";
    }

}
