name := """play-jwt-example"""
organization := "com.techmonad"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.5"


libraryDependencies ++=
  Seq(
    guice,
    "com.github.jwt-scala" %% "jwt-core" % "7.1.3",
    "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
  )

