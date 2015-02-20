package neo4j.models.uman;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.RelationshipType;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import neo4j.models.BaseModel;

import java.util.List;


@NodeEntity
public class Account extends BaseModel{
	@Indexed
	public String username;
	
	public String password;
	
	public List<String> tokens;
	
	@Fetch
	@RelatedTo(type="HAS_ACCOUNT_OF", direction=Direction.INCOMING)
    public BaseModel owner;
	
	public Account(String username, String password){
		this.username = username;
		this.password = password;
	}

}
