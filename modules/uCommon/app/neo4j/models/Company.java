package neo4j.models;

import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.RelationshipType;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Company extends AbstractNode {

  @Indexed
  public String name;

  public Company(String name) {
    this.name = name;
  }

  public Company() {
  }

  @Override
  public String toString() {
    return String.format("Company{name='%s'}", name);
  }

}
