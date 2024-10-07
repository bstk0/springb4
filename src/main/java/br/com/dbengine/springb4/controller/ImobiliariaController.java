package br.com.dbengine.springb4.controller;

import br.com.dbengine.springb4.DAO._ImobiliariaDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ImobiliariaController {

    private _ImobiliariaDAO dao = new _ImobiliariaDAO();

/*    @GetMapping("/imobiliariaList")
    public String imovelList(Model model) {
        List<Imobiliaria> imobiliarialList = new ArrayList<Imobiliaria>();
        imobiliarialList = dao.getList();
        //Sysout.s("imobiliarialList size: " + imobiliarialList.size());
        model.addAttribute("imobiliariaList",imobiliarialList);
        return "imobiliaria/list";
    }*/

    @GetMapping("/imobiliariaList")
    public String imovelList(Model model) {
        return "ops";
    }
}
