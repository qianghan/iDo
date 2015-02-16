package models;

import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.*;

@NodeEntity
public class Account {

  @GraphId
  public Long id;

  @Indexed
  public String username;

  public String toString() {
    return String.format("User %d %s",id, username);
  }

}
