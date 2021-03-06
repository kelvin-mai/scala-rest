name := """scala-rest"""
organization := "com.kelvinmai"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.8"

libraryDependencies += guice
libraryDependencies += jdbc
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.2" % Test
libraryDependencies += "org.postgresql" % "postgresql" % "42.2.5"
libraryDependencies += "com.typesafe.slick" %% "slick" % "3.3.1"
libraryDependencies += "org.slf4j" % "slf4j-nop" % "1.7.26"
libraryDependencies += "com.typesafe.slick" %% "slick-hikaricp" % "3.3.1"
libraryDependencies += "org.mindrot" % "jbcrypt" % "0.3m"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.13"
libraryDependencies += "com.pauldijou" %% "jwt-core" % "2.1.0"
libraryDependencies += "com.pauldijou" %% "jwt-play-json" % "2.1.0"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.kelvinmai.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "play.modules.reactivemongo.PathBindables._"
