Common.moduleSettings("uCommon")

// Add here the specific settings for this module

resolvers ++= Seq(
  "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/",
  "Spring releases" at "http://repo.springsource.org/release",
  "Spring milestones" at "http://repo.spring.io/milestone",
  "Neo4j" at "http://m2.neo4j.org/releases/",
  "Local Maven" at "file://" + Path.userHome.absolutePath + "/.m2/repository",
  "tuxburner.github.io" at "http://tuxburner.github.io/repo"
)

//scalaVersion := "2.10.4"

libraryDependencies ++= Common.commonDependencies ++: Seq(
  "com.typesafe.play" %% "play" % "2.3.6",
  "com.typesafe.play" %% "play-java" % "2.3.6",
  "com.sun.jersey" % "jersey-core" % "1.18.1",
  // spring data stuff
  "org.springframework" % "spring-context" % "4.1.1.RELEASE",
  "org.springframework.data" % "spring-data-neo4j" % "3.2.1.RELEASE",
  "org.springframework.data" % "spring-data-neo4j-rest" % "3.2.1.RELEASE",
  // neo4j stuff
  "org.neo4j" % "neo4j" % "2.1.5",
  "org.neo4j" % "neo4j-kernel" % "2.1.5",
  cache,
  ws,
  "org.webjars" % "bootstrap" % "3.2.0"
)

