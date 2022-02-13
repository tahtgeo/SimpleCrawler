name := "SimpleCrawler"

version := "1.0"

lazy val `simplecrawler` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"

scalaVersion := "2.13.5"

libraryDependencies ++= Seq(guice,
  "net.ruippeixotog" %% "scala-scraper" % "2.2.1",
  "io.circe" %% "circe-generic-extras" % "0.12.2",
  "io.circe" %% "circe-parser" % "0.12.2",
  "com.dripower" %% "play-circe" % "2812.0",
  "org.scalatest" %% "scalatest" % "3.2.11" % "test",
  "commons-validator" % "commons-validator" % "1.7",
  "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.4")