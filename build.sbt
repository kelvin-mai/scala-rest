name := """scala-rest"""
organization := "com.kelvinmai"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.8"

libraryDependencies += guice
libraryDependencies += jdbc
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.2" % Test
libraryDependencies += "org.reactivemongo" %% "play2-reactivemongo" % "0.17.0-play27"
libraryDependencies += "org.postgresql" % "postgresql" % "42.2.5"
libraryDependencies ++= Seq(
  "org.scalikejdbc" %% "scalikejdbc"       % "3.3.2",
  "org.scalikejdbc" %% "scalikejdbc-config" % "3.3.2",
  "ch.qos.logback" %% "logback-classic" % "1.2.3",
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.kelvinmai.controllers._"

// Adds additional packages into conf/routes
play.sbt.routes.RoutesKeys.routesImport += "play.modules.reactivemongo.PathBindables._"