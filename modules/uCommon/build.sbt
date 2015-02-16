Common.moduleSettings("uCommon")

// Add here the specific settings for this module
resolvers += "tuxburner.github.io" at "http://tuxburner.github.io/repo"

<<<<<<< HEAD
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
=======
resolvers += "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"

resolvers += "Neo4j Maven Repo" at "http://m2.neo4j.org/releases"

libraryDependencies ++= Common.commonDependencies ++: Seq(
	// Add here the specific dependencies for this module:
	cache,
  	ws,
  	"com.github.tuxBurner" %% "play-neo4jplugin" % "1.4.1"
)

>>>>>>> b5a77472a4dd360bc34028b0112ba48746b54360
