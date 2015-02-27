package neo4j.models.uman;

import java.util.Set;

import neo4j.models.BaseModel;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.RelationshipType;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.annotation.RelatedToVia;

@NodeEntity
public class Company extends BaseModel {


	@Indexed
	public Long companyId;
	
	public String name;
	
	public String no_employees;
	public String tel;
	public String fax;
	public String email;
	
	@Fetch
	@RelatedTo(type="locate_at", direction=Direction.OUTGOING)
	public Address address;
	
	@Fetch
	@RelatedTo(type="has_account_of", direction=Direction.OUTGOING)
	public Account account;
	
	
	@Fetch
	@RelatedTo(type="contact_of", direction=Direction.INCOMING)
	public Person contact;
	
	
	@Fetch
	@RelatedTo(type="is_consumer_of", direction=Direction.OUTGOING)
	public Set<Service> services;
	
	/**
	 * no-arg constructor is required by neo4j spring data
	 */
	public Company(){
		
	}
	public Company(String name) {
	    
	//internal id needs to be
		
		this.name = name;
	}
	
	public Company(Long companyId,
					String name,
					String no_employees,
					String tel,
					String fax,
					String email,
					Address address) {
	    
			this.companyId = companyId;
			this.name = name;
			this.no_employees = no_employees;
			this.tel = tel;
			this.fax = fax;
			this.email = email;
			this.address = address;
		}

	
	@Override
	public String toString() {
		return String.format("Company{name='%s'}", name);
	}

}

