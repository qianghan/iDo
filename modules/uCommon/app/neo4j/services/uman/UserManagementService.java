package neo4j.services.uman;


import models.uman.SignUp;
import models.uman.StatusCode;
import models.uman.User;
import neo4j.models.uman.Account;
import neo4j.models.uman.Person;
import neo4j.repositories.uman.*;
import neo4j.services.Neo4JServiceProvider;

import org.neo4j.graphalgo.GraphAlgoFactory;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.helpers.collection.IteratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.data.neo4j.template.Neo4jOperations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import play.mvc.Http;

import org.springframework.data.neo4j.conversion.Result;

import com.google.common.collect.Lists;

import neo4j.models.uman.*;

import java.util.HashSet;

import play.Logger;




@Service
public class UserManagementService{
	
    @Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private CompanyRepository comRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	private  Logger.ALogger logger = play.Logger.of(UserManagementService.class);
	
	
	public static UserManagementService self(){
		return Neo4JServiceProvider.get().userManagementService;
	}
	
	
	
	/**
	 * SignUp function that create account sub graph
	 * 
	 * TODO
	 * JobTitle + Business Type are not handled yet
	 * 
	 * if failed, a status code (non 200) is returned as part of User
	 * @param signup
	 * @return
	 */
	@Transactional
	public User signUp(SignUp signup) throws Exception{
		logger.info("Signup ...");
		
		User user = new User(signup.account.username);
		
		user.status = new StatusCode(Http.Status.OK);
		user.roles = new HashSet<Role>();
	    user.roles.add(roleRepository.findByName("root"));
		
		logger.debug("to check if  account >> " + signup.account.username + "  exists");
	
		try{
			if (accountRepository.findAccountByName(signup.account.username) == null){
				logger.info("Account does not exist, and create a new one ...");
				
				signup.account.token = java.util.UUID.randomUUID().toString();
				user.authToken = signup.account.token;
				signup.account.roles = user.roles;				
				signup.company.account = signup.account;
				signup.account.owner = signup.company;
				signup.company.contact = signup.contact;	
				signup.contact.company = signup.company;
				comRepository.save(signup.company);					
				user.authToken = signup.account.token;
				
				logger.debug("Account sub-graph has been created successfully");
				
				
			}else {
				logger.info("Account exists, return an error Conflict");
				
				user.status.status = Http.Status.CONFLICT;
				user.status.error ="No account is created.";
				user.status.cause ="Duplicate account name >> " + signup.account.username;
				user.status.message = "Account already exists, please sign up with different account name.";
				
			}
				
		}catch (Exception e){
			handleException(e, logger);
		}
		
		return user;
	}
	
	/**
	 * Handel Runtime Exception
	 * uCommon Exception is not passed down to any other modules
	 * @param e
	 * @param logger
	 * @return
	 */
	
	private StatusCode handleException(Exception e,  Logger.ALogger logger){
		logger.info("A runtime exception has been thrown.");
		logger.debug("stacktrace >>>>" + e.getStackTrace());
		logger.debug("message >>> " +e.getMessage());
		logger.debug("cause >>>" + e.getCause());
		
		StatusCode status = new StatusCode(Http.Status.INTERNAL_SERVER_ERROR);
		
		
		status.error ="Runtime exception.";
		status.cause =e.getCause().toString();
		status.message = e.getMessage();
		
		return status;
	}
	
	/**
	 * 
	 * @param account
	 * @return User, with status 
	 */
	
	public User login(Account account){
		
		User user = new User(account.username);
		
		user.status = new StatusCode(Http.Status.OK);
		
		Account acc = accountRepository.findAccountByName(account.username);
		
		try{
			if ( acc != null){
				if ( acc.password == account.password ){
					logger.info("Log in successfully.");
					user.roles = acc.roles;
					user.authToken = acc.token;
					
				}else{
					logger.info("Password does not match, return Forbidden.");
					user.status.status = Http.Status.FORBIDDEN;
					user.status.error ="Login failed.";
					user.status.cause =" password does not match";
					user.status.message = "password does not match";
					
				}
				
			}else{
				logger.info("Account does not exist, return an error NOTFOUND");
				
				user.status.status = Http.Status.NOT_FOUND;
				user.status.error ="Login failed.";
				user.status.cause =" Account >> " + account.username + " does not exist.";
				user.status.message = "login name does not exist.";
			}
		
		}catch (Exception e){
			handleException(e, logger);
		}
		
		return user;
	}
	
	/**
	 * 
	 * @param account
	 * return HTTP status code
	 */
	public int forgetPassword(Account account){
		
		return Http.Status.OK;
		
	}
	
	
	
	
	public ArrayList<Person> findByFirstName(String firstName){
		
		   ArrayList<Person> persons = Lists.newArrayList(personRepository.findByFirstName(firstName).iterator());
		   return persons;
	}
	
	public Person findByEmail(String email){
		return personRepository.findByEmail(email);

	}
	
	public List<Person> getAllPersons() {
		return new ArrayList<Person>(IteratorUtil.asCollection(personRepository.findAll()));
	}
	
	public long getNumberOfPerson() {
		return personRepository.count();
	}
	
	public Person savePerson(Person person){
		return personRepository.save(person);
	}
	
	public void delPerson(Person person){
		 personRepository.delete(person);
	}

}