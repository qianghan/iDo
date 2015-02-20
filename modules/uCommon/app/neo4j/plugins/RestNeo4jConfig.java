package neo4j.plugins;

import com.typesafe.config.ConfigFactory;
import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.repository.GraphRepositoryFactoryBean;
import org.springframework.data.neo4j.rest.SpringRestGraphDatabase;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
* Configuration for Remote Neo4JDatabase
*/
@EnableTransactionManagement
@Configuration
@EnableNeo4jRepositories(basePackages = "neo4j.repositories",repositoryFactoryBeanClass = GraphRepositoryFactoryBean.class)
@ComponentScan("neo4j")
public class RestNeo4jConfig extends Neo4JBaseConfiguration {
  private static String REST_DB_HOST_CFG_KEY = "neo4j.restDB.host";
  private static String REST_DB_USER_CFG_KEY = "neo4j.restDB.user";
  private static String REST_DB_PASSWORD_CFG_KEY = "neo4j.restDB.password";

  @Bean
  public GraphDatabaseService graphDatabaseService() {
    String restDbHost = ConfigFactory.load().getString(REST_DB_HOST_CFG_KEY);
    String restDbUser = ConfigFactory.load().getString(REST_DB_USER_CFG_KEY);
    String restDbPassword = ConfigFactory.load().getString(REST_DB_PASSWORD_CFG_KEY);
    SpringRestGraphDatabase springRestGraphDatabase = new SpringRestGraphDatabase(restDbHost,restDbUser,restDbPassword);
    return springRestGraphDatabase;
  }

}
