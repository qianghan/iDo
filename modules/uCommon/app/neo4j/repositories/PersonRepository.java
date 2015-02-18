package neo4j.repositories;

import neo4j.models.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.annotation.Query;

public interface PersonRepository extends GraphRepository<Person> {

  @Query( "MATCH (person)" +
          "WHERE person.firstName = {0} " +
          "RETURN person")
  public Iterable<Person> findByFirstName(String firstName);

  @Query( "MATCH (person)" +
          "WHERE person.sex = {0} " +
          "RETURN person")
  public Iterable<Person> findBySex(String sex);

  @Query( "MATCH (person)" +
          "WHERE person.email = {0} " +
          "RETURN person")
  public Person findByEmail(String email);

}
