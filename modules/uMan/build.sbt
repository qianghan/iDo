Common.serviceSettings("uMan")

// Add here the specific settings for this module

libraryDependencies ++= Common.commonDependencies ++: Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "org.mockito" % "mockito-core" % "1.9.5" % "test",
  "com.google.code.gson" % "gson" % "1.7.1"
)
