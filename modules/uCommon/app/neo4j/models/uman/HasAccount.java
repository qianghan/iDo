package neo4j.models.uman;

import neo4j.models.BaseModel;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;
import org.springframework.data.neo4j.annotation.EndNode;

@RelationshipEntity(type="HAS_ACCOUNT_OF")
public class HasAccount extends BaseModel{
	@StartNode 
	public BaseModel owner;
	
	@EndNode 
	public Account account;
	
	public String type;
}
