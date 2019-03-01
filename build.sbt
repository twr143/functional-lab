import Dependencies._
val catsVersion = "1.1.0"
lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.6",
      version := "0.1.0-SNAPSHOT"
    )),
    name := "functional-lab",
    libraryDependencies += "org.typelevel" %% "cats-core" % catsVersion,
    libraryDependencies += "org.typelevel" %% "cats-free" % catsVersion,
    libraryDependencies += "com.chuusai" %% "shapeless" % "2.3.3",
    libraryDependencies += scalaTest % Test
  )
