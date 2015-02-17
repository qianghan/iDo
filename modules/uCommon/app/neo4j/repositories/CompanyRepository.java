package neo4j.repositories;

import neo4j.models.Company;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface CompanyRepository extends GraphRepository<Company> {
}
