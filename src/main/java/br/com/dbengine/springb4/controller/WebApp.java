package br.com.dbengine.springb4.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import br.com.dbengine.springb4.dbUtil.Sysout;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

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

    @GetMapping("/admin")
    public String admin(Model m, Authentication authentication) {
        String localHostAddress = null;
        String localHostName = null;
        String remoteHostAddress = null;
        String remoteHostName = null;
        String erro1 = null;
        String erro2 = null;

        try {
            //System.out.println("LOCAL CONFIG::" +    // Local address
            localHostAddress = InetAddress.getLocalHost().getHostAddress() ;
            localHostName = InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            //throw new RuntimeException(e);
            erro1 = e.getMessage();
        }

        try {
            //System.out.println("REMOTE CONFIG::" +
            remoteHostAddress = InetAddress.getLoopbackAddress().getHostAddress();
            remoteHostName = InetAddress.getLoopbackAddress().getHostName();
        } catch (Exception e) {
            //throw new RuntimeException(e);
            erro2 = e.getMessage();
        }
        m.addAttribute("username", authentication.getName());
        m.addAttribute("localHostAddress", localHostAddress);
        m.addAttribute("localHostName", localHostName);
        m.addAttribute("remoteHostAddress", remoteHostAddress);
        m.addAttribute("remoteHostName", remoteHostName);
        m.addAttribute("erro1", erro1);
        m.addAttribute("erro2", erro2);

        return "admin";
    }

    @RequestMapping("/geturl")
    public String geturl(HttpServletRequest request, Model m) {
        String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request)
                .replacePath(null)
                .build()
                .toUriString();

        Sysout.s(">> "+ baseUrl);

        m.addAttribute("name", baseUrl);
        return "greeting";
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