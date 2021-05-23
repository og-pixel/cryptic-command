lazy val SCALA_3 = "3.0.0"
lazy val SCALA_2 = "2.13.6"

lazy val root = (project in file("."))
  .settings(
    name := "cryptic-command",
    organizationName := "Cryptic Command",
    organization := "com.miloszjakubanis",
    version := "0.0.1",
    scalacOptions ++= Seq(
      "-feature",
      "-language:implicitConversions",
      "-deprecation",
      "-unchecked",
      "-Ywarn-dead-code",
      "-Ywarn-numeric-widen",
      "-Ywarn-value-discard",
      "-Ywarn-unused",
    )
  ).aggregate(scala3, nodeServer)


  
lazy val scala3: Project = Project("scala3-server", file("scala3-server"))
  .settings(
    name := "scala3-server",
    version := "0.0.1",
    scalaVersion := SCALA_3,
    organization := "com.miloszjakubanis",
    libraryDependencies ++= Seq(
      "com.lihaoyi" %% "cask" % "0.7.11" withSources,
      "commons-cli" % "commons-cli" % "1.4" withSources,
      "com.lihaoyi" %% "utest" % "0.7.10" % Test withSources,
    ),
    testFrameworks += new TestFramework("utest.runner.Framework"),
    scalacOptions ++= Seq(
      // "-Yexplicit-nulls",
      "-Ysafe-init",
      "-new-syntax",
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

/**
 * 
 * Those are additional projects that compile to different backends or scala version 2
 */
// val scalaJS: Project = Project("scalajs-server", file("scalajs-server"))
//   .enablePlugins(ScalaJSBundlerPlugin)
//   .enablePlugins(ScalaJSPlugin)
//   .settings(
//     name := "scalaJS",
//     version := "0.0.1",
//     scalaVersion := "2.13.5",
//     organization := "com.miloszjakubanis",
//     scalaJSUseMainModuleInitializer := true
//   )

// val scalaNative: Project = Project("scala-native", file("scala-native"))
//   .enablePlugins(ScalaNativePlugin)
//   .settings(
//     name := "scalaNative",
//     version := "0.0.1",
//     scalaVersion := "2.13.5",
//     organization := "com.miloszjakubanis"
//   )

// lazy val playServer = (project in file("play-server"))
//   .enablePlugins(PlayScala)
//   .settings(
//     name := "play-server",
//     scalaVersion := SCALA_2,
//     libraryDependencies ++= Seq(
//       guice,
//       "com.lihaoyi" %% "utest" % "0.7.10" % Test
//     ),
//     testFrameworks += new TestFramework("utest.runner.Framework"),
//     scalacOptions ++= Seq(
//       "-Ytasty-reader",
//     )
//   ).dependsOn(scala3)