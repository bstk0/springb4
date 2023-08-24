package br.com.dbengine.springb4.controller;

import java.util.List;

import br.com.dbengine.springb4.dbUtil.Sysout;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//import com.example.dto.PeriodoDTO;

@Controller
public class WebApp {

    @GetMapping("/login")
    //@RequestMapping("/process-login")
    public String login() {
        Sysout.s("WebApp.login...");
        return "login"; // <<< Retorna a página de login
    }
    @GetMapping({"", "/"})
    public String home(Model m) {
        m.addAttribute("msg", "Hello World from Ctrl A");
        return "homePage";
    }

    @GetMapping("/versions")
    public String versions() {
        return "versions";
    }

    //@GetMapping("/books")
    //public String books(Model m) {
    //    m.addAttribute("msg", "Books");
    //    return "books";
    //}

    //@GetMapping("/imovelhistorico")
    //public String imovelhistorico() {
    //    return "imovelhistorico";
    //}

    //@GetMapping("/imovel")
    //public String imovel() {
    //    return "imovel";
    //}

    //@GetMapping("/bit_imovel")
    //public String bit_imovel() {
    //   return "bit_imovel";
    //}

    @GetMapping("/sbox")
    public String sbox() {
        return "sbox";
    }

    //@GetMapping("/teste_hash_uuid")
    //public String teste_hash_uuid() {
    //    return "teste_hash_uuid";
    //}


    //@GetMapping("/periodos")
    //public String getPeriodos(Model model) {
    //    List<PeriodoDTO> periodos = new ApiController().getPeriodos();
    //    model.addAttribute("periodos", periodos);
    //    return "periodos/allPeriodos";
    //}

    @GetMapping("/harper1")
    public String harper1() {
        return "harper1";
    }

    @GetMapping("/datastax")
    public String datastax() {
        return "datastax";
    }



}