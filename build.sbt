name := "SimpleCrawler"

version := "1.0"

lazy val `simplecrawler` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"

scalaVersion := "2.13.5"

libraryDependencies ++= Seq(guice,
  "net.ruippeixotog" %% "scala-scraper" % "2.2.1",
  "commons-validator" % "commons-validator" % "1.7",
  "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.4")