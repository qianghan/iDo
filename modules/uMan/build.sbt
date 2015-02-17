Common.serviceSettings("uMan")

// Add here the specific settings for this module

libraryDependencies ++= Common.commonDependencies ++: Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "org.mockito" % "mockito-core" % "1.9.5" % "test"
)
