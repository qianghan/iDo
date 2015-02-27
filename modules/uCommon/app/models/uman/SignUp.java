package models.uman;

import neo4j.models.uman.Account;
import neo4j.models.uman.Company;
import neo4j.models.uman.Person;

public class SignUp {
	
	public Account account;
	public Person contact;
	public Company company;
	
	
	public SignUp(Account account, Person contact, Company company){
		this.account = account;
		this.contact = contact;
		this.company = company;
	}
	
	public SignUp(Account account){
		this.account = account;
	}

}
