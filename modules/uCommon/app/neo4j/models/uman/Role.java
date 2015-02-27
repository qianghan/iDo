package neo4j.models.uman;

import java.util.Set;

import neo4j.models.*;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Role extends BaseModel{
	@Indexed
	public Long roleId; 
	
	public String role;
	
	@Fetch
	@RelatedTo(type="is_member_of", direction=Direction.INCOMING)
	public Set<Account> members;
	
	
	public Role(Long id, String role){
		this.roleId = id;
		this.role = role;
	}
	
	public Role(){
		
	}
	
	public static Role getRoot(){
		return new Role(new Long(1), "root");
	}
	
	public static Role getSuperRoot(){
		return new Role(new Long(0), "superroot");
	}
	
	public static Role getServiceAdmin(){
		return new Role(new Long(2), "service_admin");
	}
	
	public static Role getServiceUser(){
		return new Role(new Long(3), "service_user");
	}
}
