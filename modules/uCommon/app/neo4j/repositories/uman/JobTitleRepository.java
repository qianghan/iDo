package neo4j.repositories.uman;

import java.util.Set;
import org.springframework.data.neo4j.annotation.Query;

import org.springframework.data.neo4j.repository.GraphRepository;

import neo4j.models.uman.JobTitle;

public interface JobTitleRepository extends GraphRepository<JobTitle>{
	@Query (    "match (jobtitle)" +
	           "return jobtitle.titles")
	public Set<String> findAllTitles();
}
