package neo4j.plugins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Component;

public class ServiceProvider {
  @Autowired
  public Neo4jTemplate template;
}
