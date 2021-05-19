lazy val playServer = (project in file("play-server"))
  .enablePlugins(PlayScala)
  .settings(
    name := "play-server",
    organization := "com.miloszjakubanis",
    version := "0.0.1",
    scalaVersion := "2.13.5",
    libraryDependencies ++= Seq(
      guice,
      "com.lihaoyi" %% "utest" % "0.7.10" % Test
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

lazy val scala3: Project = Project("scala3-server", file("scala3-server"))
  .settings(
    name := "scala3-server",
    version := "0.0.1",
    scalaVersion := "3.0.0",
    organization := "com.miloszjakubanis",
    libraryDependencies ++= Seq(
      "com.lihaoyi" %% "utest" % "0.7.10" % Test,
    ),
    testFrameworks += new TestFramework("utest.runner.Framework"),
    scalacOptions ++= Seq(
      "-Yexplicit-nulls",
      "-new-syntax",
    )
  )
  // .dependsOn(playServer)

lazy val nodeServer: Project = Project("node-server", file("node-server"))
  .settings(
    name := "node-server"
  )


//Tasks
lazy val nodeRun = taskKey[Unit]("Node Run Project")
nodeRun := {
  import scala.sys.process._
  Seq("node", "node-server/app.js").!
}


/**
 * 
 * Those are additional projects that compile to javascript and native
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