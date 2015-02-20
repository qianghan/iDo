package neo4j.models

/**
* This is temporary until Neo4J hook is resolved
*/
case class Account(id: Long, firstName: String, lastName: String, age: Int, sex: String, telephone: String, company: String)

object Account {

  def list: List[Account] = List(
    Account(1, "Eson", "Paguia", 30, "M", "639209531590", "TELUS"),
    Account(2, "Qiang", "Han", 30, "M", "16043519437", "SAP"),
    Account(3, "Kamyar", "Asadibeiky", 30, "M", "16047275176", "Machool"),
    Account(4, "Jorge", "Whittembury", 30, "M", "16047204262", "Machool")
  )

}
