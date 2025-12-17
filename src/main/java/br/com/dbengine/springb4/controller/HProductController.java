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
public class HProductController {

    @Autowired
    private HProductDAO dao;

    @GetMapping("/hproduct/addform")
    public String hproductAddForm(Model model) {
        HProduct hproduct = new HProduct();
        //hproduct.setProduct_id(String.valueOf(dao.getNCOUNT() + 1));
        model.addAttribute("hproduct", hproduct);
        return "hasura/product_addform";
    }

    @GetMapping("/hproductList")
    public String hproductList(Model model) {
        List<HProduct> hpeopleList = new ArrayList<>();
        hpeopleList = dao.getList();
        model.addAttribute("hproductList",hpeopleList);
        return "hasura/product_list";
    }

    @PostMapping("/hproductAdd")
    public String hproductAdd(Model model, @ModelAttribute HProduct hproduct) {
        dao.add(hproduct);
        return "redirect:/hproductList";
    }

    @GetMapping("/hproductUpdForm")
    public String hproductUpdForm(@RequestParam String hproductId,Model model) {
        HProduct  hProduct = dao.getItem(hproductId);
        model.addAttribute("hproduct", hProduct);
        return "hasura/product_updform";
    }

    @PostMapping("/hproductUpdate")
    public String hproductUpdate(@ModelAttribute HProduct hproduct,
                                @RequestParam("hproductId") String hproductId) {
        Sysout.s("UPDATE hproductId..." + hproductId);
        dao.update(hproduct);
        return "redirect:/hproductList";
    }

    @GetMapping("/hproductDelete")
    public String hproductDelete(@RequestParam String hproductId) {
         dao.delete(hproductId);
        return "redirect:/hproductList";
    }

}
