name := "Shapeless Semigroups"

version := "0.1.0"

scalaVersion := "2.13.5"

organization := "com.bencebalogh"

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)

libraryDependencies ++= Seq(
  "com.chuusai" %% "shapeless" % "2.3.3",
  "org.typelevel" %% "cats-core" % "2.3.0"
)