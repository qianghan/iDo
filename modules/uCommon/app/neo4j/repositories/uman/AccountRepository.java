package neo4j.repositories.uman;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import neo4j.models.uman.Account;

public interface AccountRepository extends GraphRepository<Account> {
	
	@Query(    "match (account)" +
	           "where account.username = {0} " +
	           "return account")
	public Account findAccountByName(String username);
	
	@Query(    "match (account)" +
	           "where account.username = {0} " +
	           "return account.token")
	public String findAuthTokenByName(String username);
	
	@Query(    "match (account)" +
	           "where account.token = {0} " +
	           "return account")
	public Account findAccountByAuthToken(String token);
	
	
}
