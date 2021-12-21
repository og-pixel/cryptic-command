import sbt.Keys.libraryDependencies
import sbtbuildinfo.BuildInfoPlugin.autoImport.buildInfoPackage

lazy val Scala_2 = "2.13.7"
lazy val projectSettings = Seq(
  buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion),
  buildInfoOptions += BuildInfoOption.ToMap,
  organization := "com.miloszjakubanis",
  scalaVersion := Scala_2,
  version := "0.0.1-SNAPSHOT",
  libraryDependencies ++= Seq(
    "com.github.scopt" %% "scopt" % "4.0.1",
    //Create config files and CLI
//    "com.typesafe" % "config" % "1.4.1",
    //Netty Async Network Library
//    "io.netty" % "netty-all" % "4.1.59.Final",
    //Logback
//    "ch.qos.logback" % "logback-classic" % "1.2.6",
//    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4",
    //Testing Framework
    "com.lihaoyi" %% "utest" % "latest.integration" % Test,
    //Circe for JSon Parsing
//    "io.circe" %% "circe-core" % "0.14.1",
//    "io.circe" %% "circe-generic" % "0.14.1",
//    "io.circe" %% "circe-parser" % "0.14.1",
    //Personal
    "com.miloszjakubanis" %% "thoughtseize" % "0.0.2-SNAPSHOT" changing (),
    "com.miloszjakubanis" %% "flusterstorm" % "0.0.2-SNAPSHOT" changing (),
    //Terminal GUI
    "com.googlecode.lanterna" % "lanterna" % "3.1.1",
    //Redis API
    "net.debasishg" %% "redisclient" % "3.41"
  ),
  testFrameworks += new TestFramework("utest.runner.Framework"),
  scalacOptions ++= Seq(
    "-feature",
    "-language:implicitConversions",
    "-deprecation",
    "-unchecked"
  ),
  packMain := Map("hello" -> "com.miloszjakubanis.crypticcommand.Main"),
  resolvers := {
    Seq(
      "releases" at "https://artifact.miloszjakubanis.com/repository/earth/",
      "snapshots" at "https://artifact.miloszjakubanis.com/repository/moon/"
    )
  },
  //Credentials
  versionScheme := Some("early-semver"),
  publishMavenStyle := true,
  credentials += Credentials(
    new File(Path.userHome.absolutePath + "/.nexus/credentials")
  ),
  publishTo := Some("Sonatype Nexus Repository Manager" at {
    if (isSnapshot.value)
      s"https://artifact.miloszjakubanis.com/repository/moon"
    else
      s"https://artifact.miloszjakubanis.com/repository/earth"
  })
)

//Projects
//lazy val commonProject: Project = project
//  .in(file("common"))
//  .enablePlugins(PackPlugin, BuildInfoPlugin)
//  .settings(
//    projectSettings,
//    name := "Cryptic Command Common Library",
//    buildInfoPackage := "helloCommon"
//  )

lazy val client: Project = project
  .in(file("client"))
  .enablePlugins(PackPlugin, BuildInfoPlugin)
//  .dependsOn(commonProject)
  .settings(
    projectSettings,
    name := "Cryptic Command Client",
    buildInfoPackage := "helloClient"
  )

lazy val server: Project = project
  .in(file("server"))
  .enablePlugins(PackPlugin, BuildInfoPlugin, PlayScala)
//  .dependsOn(commonProject)
  .settings(
    projectSettings,
    name := "Cryptic Command Server",
    libraryDependencies ++= Seq(
      guice
    ),
    buildInfoPackage := "helloServer"
  )

lazy val crypticcommand: Project = project
  .in(file("."))
  .aggregate(client, server)
