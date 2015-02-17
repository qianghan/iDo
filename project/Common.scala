import sbt._
import Keys._
import play.PlayImport._

object Common {

  def appName = "uDo"
  def appVersion = "0.2.3"

  // Common settings for every project
  def settings(theName: String) = Seq(
    name := theName,
    organization := "com.machool",
    version := "0.2.1",
    scalaVersion := "2.10.4",
    doc in Compile <<= target.map(_ / "none"),
    scalacOptions ++= Seq("-feature", "-deprecation", "-unchecked", "-language:reflectiveCalls")
  )

  // Settings for the app, i.e. the root project
  val appSettings = settings(appName)

  // Settings for every module, i.e. for every subproject
  def moduleSettings (module: String) = settings(module) ++: Seq(
    javaOptions in Test += s"-Dconfig.resource=application.conf"
  )

  // Settings for every service, i.e. for uShip and uMan subprojects
  def serviceSettings (module: String) = moduleSettings(module) ++: Seq(

  )

  val commonDependencies = Seq(
    cache,
    ws,
    // Add here more common dependencies:
    // jdbc,
    // anorm
    "net.liftweb" %% "lift-json" % "2.6",
    "org.specs2" %% "specs2-core" % "2.3.12" % "test",
    "org.mockito" % "mockito-core" % "1.9.5" % "test"
  )

}
