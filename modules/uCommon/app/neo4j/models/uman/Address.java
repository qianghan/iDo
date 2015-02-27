package neo4j.models.uman;

import neo4j.models.BaseModel;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.Indexed;

@NodeEntity
public class Address extends BaseModel {
	
	
	public String unit;
	public String line1;
	public String line2;
	public String city;
	public String province;
	public String country;
	
	public String postcode;
	
	public Address(){
		
	}
	
	public Address ( String unit,
						 String line1,
						 String line2,
						 String city,
						 String province,
						 String country, 
						 String postcode
	){
		this.unit = unit;
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
		this.province = province;
		this.country = country;
		this.postcode = postcode;
	}

}
