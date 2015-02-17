package controllers;

import models.Account;
import services.AccountService;
import services.Neo4JServiceProvider;
import play.mvc.Controller;
import play.mvc.Result;
import neo4jplugin.Transactional;
import java.util.List;

public class ApplicationController extends Controller {

  @Transactional
  public static Result index() {

    final AccountService accountService = Neo4JServiceProvider.get().accountService;

    final List<Account> allAccounts = accountService.getAllAccounts();

    return ok("hello");
  }

}
