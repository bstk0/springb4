package br.com.dbengine.springb4.controller;

import br.com.dbengine.springb4.DAO.ImobiliariaDAO;
import br.com.dbengine.springb4.DAO.ImovelDAO;
import br.com.dbengine.springb4.entity.Imobiliaria;
import br.com.dbengine.springb4.entity.Imovel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
@Controller
public class ImobiliariaController {

    private ImobiliariaDAO dao = new ImobiliariaDAO();

    @GetMapping("/imobiliariaList")
    public String imovelList(Model model) {
        List<Imobiliaria> imobiliarialList = new ArrayList<Imobiliaria>();
        imobiliarialList = dao.getList();
        //Sysout.s("imobiliarialList size: " + imobiliarialList.size());
        model.addAttribute("imobiliariaList",imobiliarialList);
        return "imobiliaria/list";
    }

}
