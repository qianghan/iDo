Common.appSettings


lazy val uCommon = (project in file("modules/uCommon")).enablePlugins(PlayScala)

lazy val uMan = (project in file("modules/uMan")).enablePlugins(PlayScala).dependsOn(uCommon)

lazy val uShip = (project in file("modules/uShip")).enablePlugins(PlayScala).dependsOn(uCommon)

lazy val root = (project in file(".")).enablePlugins(PlayScala).aggregate(uCommon, uMan, uShip).dependsOn(uCommon, uMan, uShip)


libraryDependencies ++= Common.commonDependencies
