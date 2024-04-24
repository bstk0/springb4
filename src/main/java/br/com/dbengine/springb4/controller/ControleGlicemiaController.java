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
        //return dao.getList();
        List<ControleGlicemia> hgliceList = new ArrayList<>();
        hgliceList = dao.getList();
        //Sysout.s("culturaList size: " + culturaList.size());
        model.addAttribute("hgliceList", hgliceList);
        return "/hasura/controleglic_list";
    }
}
