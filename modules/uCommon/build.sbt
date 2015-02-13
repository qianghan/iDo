Common.moduleSettings("uCommon")

// Add here the specific settings for this module
resolvers += "tuxburner.github.io" at "http://tuxburner.github.io/repo"

resolvers += "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"

resolvers += "Neo4j Maven Repo" at "http://m2.neo4j.org/releases"

libraryDependencies ++= Common.commonDependencies ++: Seq(
	// Add here the specific dependencies for this module:
	cache,
  	ws,
  	"com.github.tuxBurner" %% "play-neo4jplugin" % "1.4.1"
)

