package models.uman;

import java.util.Set;

import neo4j.models.uman.Role;

public class User {
	
	public String account;
	public Set<Role> roles;
	
	public Set<String> authorizedServices;
	
	public String authToken;
	
	public StatusCode status;
	
	
	public User(String account){
		this.account = account;
	}
}
