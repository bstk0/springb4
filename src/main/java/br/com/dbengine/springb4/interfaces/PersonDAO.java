package br.com.dbengine.springb4.interfaces;

import br.com.dbengine.springb4.entity.*;
import org.springframework.stereotype.*;

import java.util.*;

//Create DAO interface for CRUD operations
@Component
public interface PersonDAO {

    public List<Person> getAllPeople();

    public Person getPersonById(int id);

    public void addPerson(Person person);

    public void updatePerson(int id, Person person);

    public void deletePerson(int id);
}