lazy val root = project
  .in(file("."))
  .settings(
    name := "waro-scala3",
    description := "sbt project that compiles using Scala 3",
    version := "0.1.0",
    scalaVersion := "3.0.0"
  )
