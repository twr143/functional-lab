import Dependencies._

val catsVersion = "1.1.0"
val freesVersion = "0.8.2"
organization := "com.cats.example"
scalaVersion := "2.12.6"
version := "0.1.0-SNAPSHOT"
name := "functional-lab"
libraryDependencies += "org.typelevel" %% "cats-core" % catsVersion
libraryDependencies += "org.typelevel" %% "cats-free" % catsVersion
libraryDependencies += "org.typelevel" %% "cats-effect" % catsVersion
libraryDependencies += "com.chuusai" %% "shapeless" % "2.3.3"
libraryDependencies += "io.frees" %% "frees-core" % freesVersion
libraryDependencies += scalaTest % Test
addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.9")
addCompilerPlugin("org.scalameta" %% "paradise" % "3.0.0-M11" cross CrossVersion.full)
