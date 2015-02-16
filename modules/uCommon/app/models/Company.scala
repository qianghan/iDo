package models

import org.springframework.data.neo4j.annotation._
import org.springframework.data.neo4j.support.index._

@NodeEntity
class Company {

  @GraphId
  var id : java.lang.Long = _

  var name : String = _
  var street: String = _
  var city: String = _
  var state: String = _
  var postal: String = _
  var country: String = _

}
