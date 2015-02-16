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
		scalacOptions ++= Seq("-feature", "-deprecation", "-unchecked", "-language:reflectiveCalls"),
		resolvers += "Neo4j" at "http://m2.neo4j.org/content/repositories/releases/"
	)
<<<<<<< HEAD

	// Settings for the app, i.e. the root project
	val appSettings = settings(appName)
	
=======
  
	// Settings for the app, i.e. the root project
	val appSettings = settings(appName)
  
>>>>>>> b5a77472a4dd360bc34028b0112ba48746b54360
	// Settings for every module, i.e. for every subproject
  
	def moduleSettings (module: String) = settings(module) ++: Seq(
		javaOptions in Test += s"-Dconfig.resource=application.conf"
	)
<<<<<<< HEAD
	
=======
  
>>>>>>> b5a77472a4dd360bc34028b0112ba48746b54360
	// Settings for every service, i.e. for uShip and uMan subprojects
	def serviceSettings (module: String) = moduleSettings(module) ++: Seq(
		
	)
	
	val commonDependencies = Seq(
		cache,
		ws,
	
		// Add here more common dependencies:
		// jdbc,
		// anorm,
		// ...
<<<<<<< HEAD
    	"net.liftweb" %% "lift-json" % "2.6",
      javaCore,
      "com.sun.jersey" % "jersey-core" % "1.19",
      "com.sun.jersey" % "jersey-server" % "1.19",
      "javax.ws.rs" % "javax.ws.rs-api" % "2.0-m02",
      "javax.inject" % "javax.inject" % "1",
      "asm" % "asm" % "3.3.1",
      "org.springframework" % "spring-context" % "3.2.4.RELEASE",
    	"org.springframework.data" % "spring-data-neo4j" % "3.2.2.RELEASE",
    	"org.springframework" % "spring-context" % "3.2.4.RELEASE",
    	"org.springframework.data" % "spring-data-neo4j-rest" % "3.2.2.RELEASE" excludeAll(
			ExclusionRule(organization = "org.neo4j", name="neo4j")
		),
		"org.neo4j" % "neo4j" % "2.2.0-M03" excludeAll(
			ExclusionRule(organization = "org.neo4j", name="neo4j-kernel")
		),
		"org.neo4j" % "neo4j-kernel" % "1.9.7",
		"org.neo4j" % "neo4j-rest-graphdb" % "2.0.0-M06"
=======
    "net.liftweb" %% "lift-json" % "2.6",
	 "org.specs2" %% "specs2-core" % "2.3.12" % "test",
	 "org.mockito" % "mockito-core" % "1.9.5" % "test"
>>>>>>> b5a77472a4dd360bc34028b0112ba48746b54360
	)

}
