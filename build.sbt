Common.appSettings


lazy val uMan = (project in file("modules/uMan")).enablePlugins(PlayScala)

lazy val uShip = (project in file("modules/uShip")).enablePlugins(PlayScala).dependsOn(uMan)

lazy val root = (project in file(".")).enablePlugins(PlayScala).aggregate(uMan, uShip).dependsOn(uMan, uShip)


libraryDependencies ++= Common.commonDependencies
