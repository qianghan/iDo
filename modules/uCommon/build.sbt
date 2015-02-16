Common.moduleSettings("uCommon")

// Add here the specific settings for this module

//resolvers ++= Seq(
//  "anormcypher" at "http://repo.anormcypher.org/",
//  "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/"
//)

libraryDependencies ++= Common.commonDependencies ++: Seq(
	//"org.anormcypher" %% "anormcypher" % "0.6.0"
	//"org.springframework.data" % "spring-data-neo4j" % "3.2.2.RELEASE"
	// Add here the specific dependencies for this module:
	// jdbc,
	// anorm
)
