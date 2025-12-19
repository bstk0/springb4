package br.com.dbengine.springb4.controller;

import br.com.dbengine.springb4.Enum.*;
import br.com.dbengine.springb4.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class BancoAplicRestController {
    @Autowired
    private BancoAplicacaoService bancoAplicacaoService;

    @GetMapping("/banco/{banco}/tipos")
    public Set<TipoAplicacaoEnum> listarTipos(@PathVariable BancoEnum banco) {
        // Ex: GET /banco/BANCO_B/tipos
        // Retorna: ["TIPO_1", "TIPO_2"]
        return bancoAplicacaoService.getTiposPorBanco(banco);
    }

    @GetMapping("/banco/{banco}/validar/{tipo}")
    public String validarTipo(@PathVariable BancoEnum banco, @PathVariable TipoAplicacaoEnum tipo) {
        // Ex: GET /banco/BANCO_A/validar/TIPO_2
        // Retorna: "Tipo TIPO_2 NÃO é válido para o Banco BANCO_A"
        if (bancoAplicacaoService.isTipoValidoParaBanco(banco, tipo)) {
            return "Tipo " + tipo + " é válido para o Banco " + banco;
        } else {
            return "Tipo " + tipo + " NÃO é válido para o Banco " + banco;
        }
    }


}
