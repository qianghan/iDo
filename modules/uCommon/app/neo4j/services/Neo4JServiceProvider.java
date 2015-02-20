package neo4j.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



import neo4j.repositories.uman.PersonRepository;
import neo4j.services.uman.UserManagementService;

import neo4j.plugins.Neo4JPlugin;
import neo4j.plugins.ServiceProvider;



@Component
public class Neo4JServiceProvider extends ServiceProvider {

  @Autowired
  public UserManagementService userManagementService;

  public static Neo4JServiceProvider get() {
    return Neo4JPlugin.get();
  }

}
