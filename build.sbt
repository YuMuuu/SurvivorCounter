name := "SurvivorCounter"

version := "0.1"

scalaVersion := "2.13.4"

libraryDependencies ++= Seq(
  "net.debasishg" %% "redisclient" % "3.30",
"org.typelevel" %% "cats-core" % "2.4.2",
  "org.typelevel" %% "cats-effect" % "3.0-8096649"


)