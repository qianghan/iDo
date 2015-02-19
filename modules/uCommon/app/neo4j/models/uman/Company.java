package neo4j.models.uman;

import java.util.Set;

import neo4j.models.AbstractNode;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.RelationshipType;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.annotation.RelatedToVia;

@NodeEntity
public class Company extends AbstractNode {

	@Indexed
	public int id;
	
	public String name;
	
	public String no_employees;
	public String tel;
	public String fax;
	public String email;
	
	
	@Fetch
	@RelatedTo(type="HAS_ACCOUNT_OF", direction=Direction.OUTGOING)
	public Account account;
	
	@Fetch 
	@RelatedToVia(type = "HAS_ACCOUNT_OF", direction = Direction.OUTGOING)
	public HasAccount account_type;
	
	@Fetch
	@RelatedTo(type="CONTACT_OF", direction=Direction.INCOMING)
	public Person contact;

	public Company(String name) {
		this.name = name;
	}

	
	@Override
	public String toString() {
		return String.format("Company{name='%s'}", name);
	}

}