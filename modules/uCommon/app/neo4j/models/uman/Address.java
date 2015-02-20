package neo4j.models.uman;

import neo4j.models.BaseModel;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.Indexed;

@NodeEntity
public class Address extends BaseModel {
	
	@Indexed
	public Long id;
	
	public String unit;
	public String line1;
	public String line2;
	public String city;
	public String province;
	public String country;
	
	public String hashcode;
	
	public Address(){
		
	}

}
