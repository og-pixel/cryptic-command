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
    "com.lihaoyi" %% "utest" % "latest.integration" % Test,
    //Circe for JSon Parsing
    "io.circe" %% "circe-core" % "0.14.1",
    "io.circe" %% "circe-generic" % "0.14.1",
    "io.circe" %% "circe-parser" % "0.14.1"
  ),
//  githubOwner := "supermanue",
//  githubRepository := "example-library",
  testFrameworks += new TestFramework("utest.runner.Framework"),
  scalacOptions ++= Seq(
    "-feature",
    "-language:implicitConversions",
    "-deprecation",
    "-unchecked"
  ),
  packMain := Map("hello" -> "com.miloszjakubanis.crypticcommand.Main"),
  resolvers := Seq(
    "Sonatype Nexus Repository Manager" at s"https://artifact.miloszjakubanis.com/repository/milosz/",
  ),
  //Credentials
  credentials += Credentials(new File(Path.userHome.absolutePath + "/.nexus/credentials"))
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
  .dependsOn(/*thoughtseize,*/ commonProject)
  .settings(
    projectSettings,
    name := "Cryptic Command Client",
    buildInfoPackage := "helloClient",
    libraryDependencies ++= Seq(
      "com.miloszjakubanis" %% "thoughtseize" % "0.0.1",
      "com.miloszjakubanis" %% "flusterstorm" % "0.0.1",
    ),
  )

lazy val server: Project = project
  .in(file("server"))
  .enablePlugins(PackPlugin)
  .enablePlugins(BuildInfoPlugin)
  .settings(
    projectSettings,
    name := "Cryptic Command Server",
    buildInfoPackage := "helloServer",
    libraryDependencies ++= Seq(
      "com.miloszjakubanis" %% "thoughtseize" % "0.0.1",
      "com.miloszjakubanis" %% "flusterstorm" % "0.0.1",
    ),
  )
  .dependsOn(/*thoughtseize, flusterstorm,*/ commonProject)

//lazy val thoughtseize = ProjectRef(
//  uri("https://github.com/og-pixel/thoughtseize.git#master"),
//  "thoughtseize"
//)
//
//lazy val flusterstorm = ProjectRef(
//  uri("https://github.com/og-pixel/flusterstorm.git#master"),
//  "flusterstorm"
//)

lazy val crypticcommand: Project = project
  .in(file("."))
  .aggregate(client, server)//, flusterstorm)
