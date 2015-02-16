Common.moduleSettings("uCommon")

// Add here the specific settings for this module
//resolvers += "tuxburner.github.io" at "http://tuxburner.github.io/repo"
//resolvers += "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"
//resolvers += "Neo4j Maven Repo" at "http://m2.neo4j.org/releases"

resolvers ++= Seq(
    "tuxburner.github.io" at "http://tuxburner.github.io/repo",
    "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository",
    "Neo4j Maven Repo" at "http://m2.neo4j.org/releases"
//  "anormcypher" at "http://repo.anormcypher.org/",
//  "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= Common.commonDependencies ++: Seq(
	//"org.anormcypher" %% "anormcypher" % "0.6.0"
	//"org.springframework.data" % "spring-data-neo4j" % "3.2.2.RELEASE"
	// Add here the specific dependencies for this module:
	// jdbc,
	// anorm
  cache,
  ws,
  "com.github.tuxBurner" %% "play-neo4jplugin" % "1.4.1"
)
