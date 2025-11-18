package br.com.dbengine.springb4.controller;

import br.com.dbengine.springb4.DAO.*;
import br.com.dbengine.springb4.Singleton.*;
import br.com.dbengine.springb4.entity.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class ImobiliariaController {

    @Autowired
    private ImobiliariaDAO dao; // = new ImobiliariaDAO();

    @GetMapping("/imobiliariaList")
    public String imovelList(Model model) {
        List<Imobiliaria> imobiliarialList = new ArrayList<Imobiliaria>();
        imobiliarialList = dao.getList();
        //Sysout.s("imobiliarialList size: " + imobiliarialList.size());
        model.addAttribute("imobiliariaList",imobiliarialList);
        return "imobiliaria/list";
    }

/*
    @GetMapping("/reloadImobList")
    public String imobReload() {
        //ImovelListSingleton.setInstance(null);
        ImobListSingleton.refresh();
        return "redirect:/imobiliariaList";
    }
*/

}
