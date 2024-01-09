package br.com.dbengine.springb4.controller;


import br.com.dbengine.springb4.form.PeopleForm;
import org.springframework.web.bind.annotation.*;

@RestController
public class PeopleRestController {
    //@PostMapping("peopleForm")
    @RequestMapping(value = "/people/save", method = RequestMethod.POST, consumes = "application/json")
    public String upsertPeople(@RequestBody PeopleForm peopleForm) {
        // funcionou ok para testes locais
        // Sysout.s("PeopleRestController.Nome : " + peopleForm.getNome());
        // Sysout.s("PeopleRestController.Observacao : " + peopleForm.getObservacao());
        return "OK";
    }
}
