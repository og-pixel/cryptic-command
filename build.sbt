lazy val Scala_2 = "2.13.7"
lazy val Scala_3 = "3.1.0"

lazy val thoughtseize = ProjectRef(
  uri("https://github.com/og-pixel/thoughtseize.git#master"),
  "thoughtseize"
)
//lazy val thoughtseize = ProjectRef(uri("https://github.com/og-pixel/thoughtseize/tree/master"), "thoughtseize")

lazy val crypticcommand: Project = project
  .in(file("."))
  .enablePlugins(PackPlugin)
  .enablePlugins(BuildInfoPlugin)
  .dependsOn(thoughtseize)
  .settings(
    buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion),
    buildInfoPackage := "hello",
    buildInfoOptions += BuildInfoOption.ToMap,

    name := "Cryptic Commanda",
    organization := "com.miloszjakubanis",
    scalaVersion := Scala_2,
    version := "0.0.1",
    libraryDependencies ++= Seq(
      "com.github.scopt" %% "scopt" % "4.0.1",
      //Create config files and CLI
      "com.typesafe" % "config" % "1.4.1",
      //Netty Async Network Library
      "io.netty" % "netty-all" % "4.1.59.Final",
      //Logback
      "ch.qos.logback" % "logback-classic" % "1.2.6",
      "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4",
      //Testing Framework
      "com.lihaoyi" %% "utest" % "latest.integration" % Test
    ),
    testFrameworks += new TestFramework("utest.runner.Framework"),
    scalacOptions ++= Seq(
      "-feature",
      "-language:implicitConversions",
      "-deprecation",
      "-unchecked"
//      "-Yexplicit-nulls",
//      "-Ysafe-init",
//      "-new-syntax"
    ),
    // [Optional] Specify main classes manually
    // This example creates `hello` command (target/pack/bin/hello) that calls org.mydomain.Hello#main(Array[String])
    packMain := Map("hello" -> "com.miloszjakubanis.crypticcommand.Main")
  )
