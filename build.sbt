name := """play-jwt-example"""
organization := "com.techmonad"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.7"


libraryDependencies ++=
  Seq(
    guice,
    "com.github.jwt-scala" %% "jwt-core" % "9.0.2",
    "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test
  )

