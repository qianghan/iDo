package neo4j.services;

import neo4j.models.Person;
import neo4j.repositories.PersonRepository;
import neo4j.repositories.CompanyRepository;
import org.neo4j.graphalgo.GraphAlgoFactory;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.helpers.collection.IteratorUtil;
import org.neo4j.kernel.Traversal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import  play.api.Logger;
import org.springframework.data.neo4j.conversion.Result;

@Service
public class UserManagementService{

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private CompanyRepository comRepository;

  public Person findByFirstName(String firstName){

    Person person = personRepository.findBySchemaPropertyValue("firstName", firstName);

    System.out.println("Found >>> " + person);

    //List<Person> persons = getAllPersons();

    //Person person=null;

    //for (Person p: persons){
      //System.out.println("[Debug] person's email is: " + p.email);
      //if(p.firstName.equals(fname)) { 
          //person=p;
          //System.out.println("[Debug] person is found.");
      //}
    //}

    return person;
  }

  public Person findByEmail(String email){
    Person person = personRepository.findByPropertyValue("email", email);
    System.out.println("Found >>> " + person);
    return person;
  }

  public List<Person> getAllPersons() {
    return new ArrayList<Person>(IteratorUtil.asCollection(personRepository.findAll()));
  }

}
