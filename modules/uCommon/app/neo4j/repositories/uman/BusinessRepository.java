package neo4j.repositories.uman;

import org.springframework.data.neo4j.repository.GraphRepository;
import neo4j.models.uman.Business;
import java.util.Set;
import org.springframework.data.neo4j.annotation.Query;

public interface BusinessRepository extends GraphRepository<Business> {
	@Query (    "match (business)" +
	           "return business.values")
	public Set<String> findAllBusiness();
}
