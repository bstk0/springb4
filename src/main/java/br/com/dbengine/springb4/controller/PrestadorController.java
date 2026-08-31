package br.com.dbengine.springb4.controller;

import br.com.dbengine.springb4.DAO.*;
import br.com.dbengine.springb4.dbUtil.*;
import br.com.dbengine.springb4.entity.*;
import br.com.dbengine.springb4.interfaces.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//Create controller for handling HTTP requests
//@RestController
@Controller
public class PrestadorController {

    //Inject PersonDAO object
    @Autowired
    private PrestadorDAO prestadorDAO;

    //GET request for retrieving all people
    //@GetMapping("/people")
    //public List<Person> getAllPeople() {
    //    return personDAO.getAllPeople();
    //}

    @GetMapping("/prestadorList")
    public String personList(Model model,@RequestParam(required = false) String isUpdate) {
        Sysout.s("opa prestadorList");

        //InitialData(model);

        List<Prestador> personList = new ArrayList<Prestador>();
        personList = prestadorDAO.getList();
        Sysout.s(String.valueOf(personList.size()));
        model.addAttribute("prestadorList",personList);
        return "prestador/list";
    }

    //GET request for retrieving person by id
    @GetMapping("/prestador/{id}")
    public Prestador getPersonById(@PathVariable int id) {
        return prestadorDAO.getItem(id);
    }

    @GetMapping("/prestador/{id}/edit")
    public String getPersonToEdit(Model model, @PathVariable int id) {
        Sysout.s("..ToEdit");
        InitialData(model);
        Prestador person = prestadorDAO.getItem(id);
        model.addAttribute("updatedPerson",person);
        return "persons"; //"redirect:/personList?isUpdate=true";
    }

    private void InitialData(Model model) {
        List<Prestador> personList = new ArrayList<Prestador>();
        personList = prestadorDAO.getList();
        Sysout.s(String.valueOf(personList.size()));
        model.addAttribute("persons",personList);
        model.addAttribute("person",new Person());
    }

    //POST request for adding new person
    @PostMapping("/prestador")
    public String addPerson(@ModelAttribute Prestador person) { //@RequestBody Person person) {
        Sysout.s("addPerson...");
        Sysout.s(person.getNome());
        prestadorDAO.add(person);
        Sysout.s("saindo ...");
        return "redirect:/prestadorList";
    }

    //PUT request for updating existing person
    //public void updatePerson(@PathVariable int id, @RequestBody Person updatedPerson) {
    @PutMapping("/prestador/{id}")
    public String updatePerson(@PathVariable int id, @ModelAttribute Prestador updatedPerson) {
        Sysout.s(" updatePerson ...");
        prestadorDAO.update(updatedPerson);
        return "redirect:/prestadorList";
    }

    //DELETE request for deleting existing person
    @DeleteMapping("/prestador/{id}")
    public String deletePerson(@PathVariable int id) {
        prestadorDAO.delete(id);
        return "redirect:/prestadorList";
    }
}
