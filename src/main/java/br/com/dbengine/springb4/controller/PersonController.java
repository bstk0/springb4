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
public class PersonController {

    //Inject PersonDAO object
    @Autowired
    private PersonDAO personDAO;

    //GET request for retrieving all people
    //@GetMapping("/people")
    //public List<Person> getAllPeople() {
    //    return personDAO.getAllPeople();
    //}

    @GetMapping("/personList")
    public String personList(Model model,@RequestParam(required = false) String isUpdate) {
        Sysout.s("opa personList");
        InitialData(model);
        //if( isUpdate == null ) {
        //    Sysout.s("isUpdate is null");
        //    model.addAttribute("updatedPerson", new Person());
        //}
        //else
        //    Sysout.s("isUpdate is NOT null");
        model.addAttribute("updatedPerson", new Person());
        return "persons";
    }

    //GET request for retrieving person by id
    @GetMapping("/person/{id}")
    public Person getPersonById(@PathVariable int id) {
        return personDAO.getPersonById(id);
    }

    @GetMapping("/person/{id}/edit")
    public String getPersonToEdit(Model model, @PathVariable int id) {
        Sysout.s("..ToEdit");
        InitialData(model);
        Person person = personDAO.getPersonById(id);
        model.addAttribute("updatedPerson",person);
        return "persons"; //"redirect:/personList?isUpdate=true";
    }

    private void InitialData(Model model) {
        List<Person> personList = new ArrayList<Person>();
        personList = personDAO.getAllPeople();
        Sysout.s(String.valueOf(personList.size()));
        model.addAttribute("persons",personList);
        model.addAttribute("person",new Person());
    }

    //POST request for adding new person
    @PostMapping("/person")
    public String addPerson(@ModelAttribute Person person) { //@RequestBody Person person) {
        Sysout.s("addPerson...");
        Sysout.s(person.getName());
        personDAO.addPerson(person);
        Sysout.s("saindo ...");
        return "redirect:/personList";
    }

    //PUT request for updating existing person
    //public void updatePerson(@PathVariable int id, @RequestBody Person updatedPerson) {
    @PutMapping("/person/{id}")
    public String updatePerson(@PathVariable int id, @ModelAttribute Person updatedPerson) {
        Sysout.s(" updatePerson ...");
        personDAO.updatePerson(id, updatedPerson);
        return "redirect:/personList";
    }

    //DELETE request for deleting existing person
    @DeleteMapping("/person/{id}")
    public String deletePerson(@PathVariable int id) {
        personDAO.deletePerson(id);
        return "redirect:/personList";
    }
}
