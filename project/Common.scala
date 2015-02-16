import sbt._
import Keys._
import play.PlayImport._
import com.typesafe.sbt.web.SbtWeb.autoImport.{Assets, pipelineStages}
import com.typesafe.sbt.less.Import.LessKeys
import com.typesafe.sbt.rjs.Import.{rjs, RjsKeys}
import com.typesafe.sbt.digest.Import.digest
import com.typesafe.sbt.gzip.Import.gzip

object Common {

	def appName = "uDo"
	def appVersion = "0.2.3"
	
	// Common settings for every project
	def settings (theName: String) = Seq(
		name := theName,
		organization := "com.machool",
		version := "1.0-SNAPSHOT",
		scalaVersion := "2.11.1",
		doc in Compile <<= target.map(_ / "none"),
		scalacOptions ++= Seq("-feature", "-deprecation", "-unchecked", "-language:reflectiveCalls"),
		resolvers += "Neo4j" at "http://m2.neo4j.org/content/repositories/releases/"
	)

	// Settings for the app, i.e. the root project
	val appSettings = settings(appName)
	
	// Settings for every module, i.e. for every subproject
	def moduleSettings (module: String) = settings(module) ++: Seq(
		javaOptions in Test += s"-Dconfig.resource=application.conf"
	)
	
	// Settings for every service, i.e. for uShip and uMan subprojects
	def serviceSettings (module: String) = moduleSettings(module) ++: Seq(
		includeFilter in (Assets, LessKeys.less) := "*.less",
		excludeFilter in (Assets, LessKeys.less) := "_*.less",
		pipelineStages := Seq(rjs, digest, gzip),
		RjsKeys.mainModule := s"main-$module"
	)
	
	val commonDependencies = Seq(
		cache,
		ws,
		"org.webjars" % "jquery" % "2.1.1",
		"org.webjars" % "bootstrap" % "3.2.0",
		"org.webjars" % "requirejs" % "2.1.14-1",
		// Add here more common dependencies:
		// jdbc,
		// anorm,
		// ...
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
	)

}
