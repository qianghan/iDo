package neo4j.repositories.uman;


import neo4j.models.uman.Role;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface RoleRepository extends GraphRepository<Role>{
	
	@Query(    "match (role)" +
	           "where role.roleId = {0} " +
	           "return role")
	public Role findByRoleId(Long id);
	
	@Query(    "match (role)" +
	           "where role.role = {0} " +
	           "return role")
	public Role findByName(String name);

}
