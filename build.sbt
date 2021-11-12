lazy val Scala_2 = "2.13.7"
lazy val Scala_3 = "3.1.0"

lazy val crypticcommand: Project = project.in(file("."))
  .settings(
    name := "Cryptic Command",
    organization := "com.miloszjakubanis",
    scalaVersion := Scala_2,
    version := "0.0.1",
    libraryDependencies ++= Seq(
      "com.typesafe" % "config" % "1.4.1",
      "com.lihaoyi" %% "utest" % "0.7.10" % Test
    ),
    testFrameworks += new TestFramework("utest.runner.Framework"),
    scalacOptions ++= Seq(
      "-feature",
      "-language:implicitConversions",
      "-deprecation",
      "-unchecked",
//      "-Yexplicit-nulls",
//      "-Ysafe-init",
//      "-new-syntax"
    )
  )
