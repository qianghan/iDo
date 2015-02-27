package neo4j.models.uman;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.RelationshipType;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import neo4j.models.BaseModel;

import java.util.Set;


@NodeEntity
public class Account extends BaseModel{
	@Indexed
	public String username;
	
	public String password;
	
	public String token;
	
	@Fetch
	@RelatedTo(type="has_account_of", direction=Direction.INCOMING)
    public BaseModel owner;
	
	@Fetch
	@RelatedTo(type="is_member_of", direction=Direction.OUTGOING)
	public Set<Role> roles;
	
	public Account(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	public Account(){
		
	}
	
	public Account(	String username, 
					String password,
					Set<Role> roles){
		
		this.username = username;
		this.password = password;

		this.roles = roles;
		
	}

}
