package br.com.dbengine.springb4.DAO;

import br.com.dbengine.springb4.dbUtil.*;
import br.com.dbengine.springb4.entity.*;
import br.com.dbengine.springb4.interfaces.*;
import org.springframework.stereotype.*;

import java.util.*;

//Implement DAO interface using HashMap as data storage
@Repository
public class PersonDAOImpl implements PersonDAO {

    //HashMap to store person objects with their respective ids as keys
    private HashMap<Integer, Person> people;

    public PersonDAOImpl() {
        //Initialize HashMap
        people = new HashMap<>();

        //Add some sample data
        people.put(1, new Person(1, "John", 25));
        people.put(2, new Person(2, "Mary", 30));
        people.put(3, new Person(3, "Bob", 40));
    }

    @Override
    public List<Person> getAllPeople() {
        return new ArrayList<>(people.values());
    }

    @Override
    public Person getPersonById(int id) {
        return people.get(id);
    }

    @Override
    public void addPerson(Person person) {
        //Generate unique id for new person
        int id = 1;
        int last = people.size();
        Sysout.s("LAST:" + String.valueOf(last));
        if (last > 0) {
            Person lastPerson = (Person) people.values().toArray()[last-1];
            id = lastPerson.getId() + 1;
        }

        person.setId(id);
        //Add person to HashMap
        people.put(id, person);
    }

    @Override
    public void updatePerson(int id, Person updatedPerson) {
        //Get existing person object
        Person person = people.get(id);

        //Update data
        person.setName(updatedPerson.getName());
        person.setAge(updatedPerson.getAge());

        //Update HashMap
        people.put(id, person);
    }

    @Override
    public void deletePerson(int id) {
        //Remove person from HashMap
        people.remove(id);
    }
}
