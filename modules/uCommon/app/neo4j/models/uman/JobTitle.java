package neo4j.models.uman;

import neo4j.models.BaseModel;
import java.util.Set;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class JobTitle extends BaseModel {
	
	public Set<String> title;
	
	public JobTitle(){
		
	}
}
