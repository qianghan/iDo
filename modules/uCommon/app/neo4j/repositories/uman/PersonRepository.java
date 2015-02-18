package neo4j.repositories.uman;

import neo4j.models.uman.Person;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.annotation.Query;

public interface PersonRepository extends GraphRepository<Person> {
	
	
	@Query(    "match (person)" +
	           "where person.firstName = {0} " +
	           "return person")
	
	public Iterable<Person> findByFirstName(String firstName);
	
	@Query(    "match (person)" +
	           "where person.sex = {0} " +
	           "return person")
	public Iterable<Person> findBySex(String sex);
	
	@Query(    "match (person)" +
	           "where person.email = {0} " +
	           "return person")
    public Person findByEmail(String email);
	
}