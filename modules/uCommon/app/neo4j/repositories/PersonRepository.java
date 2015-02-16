package neo4j.repositories;

import neo4j.models.Person;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.neo4j.repository.GraphRepository;


public interface PersonRepository extends GraphRepository<Person> {
    
}