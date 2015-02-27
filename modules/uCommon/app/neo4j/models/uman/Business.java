package neo4j.models.uman;

import org.springframework.data.neo4j.annotation.NodeEntity;

import neo4j.models.BaseModel;
import java.util.Set;

@NodeEntity
public class Business extends BaseModel {
	
	public Set<String> values;
	
	public Business(){
		
	}

}
