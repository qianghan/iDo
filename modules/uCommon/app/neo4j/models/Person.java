package neo4j.models;

import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.RelationshipType;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Person extends BaseModel {

  public String firstName;
  public String lastName;
  public int age;
  public String sex;
  public String telephone;

  @RelatedTo(type = "WORK_FOR", direction = Direction.OUTGOING)
  public @Fetch Company company;

  @Indexed
  public String email;

  public Person(){
  }

  public Person(String firstName,
                String lastName,
                int age,
                String sex,
                String telephone,
                String company,
                String email){
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.sex = sex;
    this.telephone = telephone;
    this.company = new Company(company);
    this.email = email;
  }

  @Override
  public String toString() {
    return String.format("Person { firstName: '%s', lastName: '%s', age: %d, sex: '%s', telephone: '%s', email: '%s', company: '%s' }", 
                    this.firstName,
                    this.lastName,
                    this.age,
                    this.sex,
                    this.telephone,
                    this.email, 
                    this.company.getName()
                );
  }

}
