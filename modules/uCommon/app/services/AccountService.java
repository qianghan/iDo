package services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.neo4j.graphalgo.GraphAlgoFactory;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.helpers.collection.IteratorUtil;
import org.neo4j.kernel.Traversal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;

import models.Account;
import repositories.AccountRepository;

@Service
public class AccountService {

  @Autowired
  private AccountRepository repo;

  public long getCount() {
    return repo.count();
  }

  public List<Account> getAllAccounts() {
    return new ArrayList<Account>(IteratorUtil.asCollection(repo.findAll()));
  }

}
