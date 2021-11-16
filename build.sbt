import sbtbuildinfo.BuildInfoPlugin.autoImport.buildInfoPackage

lazy val Scala_2 = "2.13.7"
lazy val Scala_3 = "3.1.0"
lazy val projectSettings = Seq(
  buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion),
  buildInfoOptions += BuildInfoOption.ToMap,
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
  ),
  packMain := Map("hello" -> "com.miloszjakubanis.crypticcommand.Main")
)

//Projects
lazy val commonProject: Project = project
  .in(file("common"))
  .enablePlugins(PackPlugin)
  .enablePlugins(BuildInfoPlugin)
  .settings(
    projectSettings,
    name := "Cryptic Command Common Library",
    buildInfoPackage := "helloCommon"
  )

lazy val client: Project = project
  .in(file("client"))
  .enablePlugins(PackPlugin)
  .enablePlugins(BuildInfoPlugin)
  .dependsOn(thoughtseize/*, commonProject*/)
  .settings(
    projectSettings,
    name := "Cryptic Command Client",
    buildInfoPackage := "helloClient"
  )

lazy val server: Project = project
  .in(file("server"))
  .enablePlugins(PackPlugin)
  .enablePlugins(BuildInfoPlugin)
  .dependsOn(thoughtseize/*, commonProject*/)
  .settings(
    projectSettings,
    name := "Cryptic Command Server",
    buildInfoPackage := "helloServer"
  )

lazy val thoughtseize = ProjectRef(
  uri("https://github.com/og-pixel/thoughtseize.git#master"),
  "thoughtseize"
)

lazy val crypticcommand: Project = project
  .in(file("."))
  .aggregate(client, server)
