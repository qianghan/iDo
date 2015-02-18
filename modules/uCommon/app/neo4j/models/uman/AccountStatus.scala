package models

object AccountStatus extends Enumeration {
  type AccountStatus = Value
  val Trial, Activated, Deactivated, Deleted = Value
}
