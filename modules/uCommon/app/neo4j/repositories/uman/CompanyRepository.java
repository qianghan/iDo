package neo4j.repositories.uman;

import neo4j.models.uman.Company;

import org.springframework.data.neo4j.repository.GraphRepository;



public interface CompanyRepository extends GraphRepository<Company> {
}