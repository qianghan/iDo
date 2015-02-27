package neo4j.models.uman;

import java.util.Set;

import neo4j.models.*;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Service extends BaseModel{
	@Indexed
	public Long serviceId;
	public String name;
	public String description;
	public String type;
	public String settings;
	
	@Fetch
	@RelatedTo(type="is_consumer_of", direction=Direction.INCOMING)
	public Company company;
	
	@Fetch
	@RelatedTo(type="has_account_of", direction=Direction.OUTGOING)
	public Set<Account> accounts;
	
	public Service(){
		
	}
	
	public Service( Long serviceId,
					String name,
					String description,
					String type,
					String settings){
		this.serviceId = serviceId;
		this.name = name;
		this.description = description;
		this.type = type;
		this.settings = settings;
		
	}
	
	
}
