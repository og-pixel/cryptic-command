lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """cryptic-command""",
    organization := "com.miloszjakubanis",
    version := "0.0.1",
    scalaVersion := "2.13.5",
    libraryDependencies ++= Seq(
      guice,
      "com.lihaoyi" %% "utest" % "0.7.7" % Test
//      "ch.qos.logback" % "logback-classic" % "latest.release",
//      "com.typesafe.scala-logging" %% "scala-logging" % "latest.release",
    ),
    scalacOptions ++= Seq(
      "-feature",
      "-language:implicitConversions",
      "-deprecation",
//      "-Xfatal-warnings",
      "-unchecked",
      "-Ywarn-dead-code",
      "-Ywarn-numeric-widen",
      "-Ywarn-value-discard",
      "-Ywarn-unused"
    )
  )

lazy val nodeServer: Project = Project("node-server", file("node-server"))
  .settings(
    name := "node-server"
  )

lazy val nodeRun = taskKey[Unit]("Node Run Project")
nodeRun := {
  import scala.sys.process._

  Seq("node", "node-server/app.js").!
}
