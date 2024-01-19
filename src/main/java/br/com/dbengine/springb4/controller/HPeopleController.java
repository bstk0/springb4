package br.com.dbengine.springb4.controller;
/*
HASURA - PEOPLE 1 - NEON.TECH
 */
import br.com.dbengine.springb4.DAO.*;
import br.com.dbengine.springb4.dbUtil.*;
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

    @GetMapping("/hpeople/addform")
    public String hpeopleAddForm(Model model) {
        HPeople hpeople = new HPeople();
        hpeople.setId(String.valueOf(dao.getNCOUNT() + 1));
        model.addAttribute("hpeople", hpeople);
        return "/hasura/people1_addform";
    }

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

    @PostMapping("/hpeopleAdd")
    public String hpeopleAdd(Model model, @ModelAttribute HPeople hpeople) {
        dao.add(hpeople);
        return "redirect:/hpeopleList";
    }

    @GetMapping("/hpeopleUpdForm")
    public String hpeopleUpdForm(@RequestParam String hpeopleId,Model model) {
        //Cultura culturaUpd = new Cultura();
        //culturaUpd = dao.getItem(culturaId);
        HPeople  hPeople = dao.getItem(hpeopleId);
        model.addAttribute("hpeople", hPeople);
        return "/hasura/people1_updform";
    }

    @PostMapping("/hpeopleUpdate")
    public String hpeopleUpdate(@ModelAttribute HPeople hpeople,
                                @RequestParam("hpeopleId") String hpeopleId) {
        //Sysout.s("UPDATE cultura..." + cultura.get_id());
        Sysout.s("UPDATE hpeopleId..." + hpeopleId);
        //if (cultura.get_id() == null) { cultura.set_id(culturaId); };
        dao.update(hpeople);
        return "redirect:/hpeopleList";
    }

    @GetMapping("/hpeopleDelete")
    public String hpeopleDelete(@RequestParam String hpeopleId) {
         dao.delete(hpeopleId);
        return "redirect:/hpeopleList";
    }


}
