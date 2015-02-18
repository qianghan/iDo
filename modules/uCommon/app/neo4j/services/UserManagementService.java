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

import com.google.common.collect.Lists;

@Service
public class UserManagementService{

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private CompanyRepository comRepository;

  public Person findByFirstName(String firstName){
    ArrayList<Person> persons = Lists.newArrayList(personRepository.findByFirstName(firstName).iterator());
    if (persons!=null && persons.size() > 0)
      return persons.get(0);
    else
      return null;
  }

  public Person findByEmail(String email){
    return personRepository.findByEmail(email);
  }

  public List<Person> getAllPersons() {
    return new ArrayList<Person>(IteratorUtil.asCollection(personRepository.findAll()));
  }

  public long getNumberOfPerson() {
    return personRepository.count();
  }

  public Person savePerson(Person person){
    return personRepository.save(person);
  }

  public void delPerson(Person person){
    personRepository.delete(person);
  }

}
