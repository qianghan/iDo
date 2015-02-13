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
public class Person extends AbstractNode {

	@Indexed
	public String email;
	
	public String fname;
	public String lname;
	public int age;
	public String sex;
	public String tel;
	
	@RelatedTo(type = "WORK_FOR", direction = Direction.OUTGOING)
	public @Fetch Company com;

    

	public Person(String email) {
		this.email = email;
	}
    
    public Person(){
    
    }

	@Override
	public String toString() {
		return String.format("Person{email='%s'}", email);
	}


}