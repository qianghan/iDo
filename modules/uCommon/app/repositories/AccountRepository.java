package repositories;

import org.springframework.data.neo4j.repository.GraphRepository;
import models.Account;

public interface AccountRepository extends GraphRepository<Account> {

}
