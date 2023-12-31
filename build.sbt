name := "scalastudy"

version := "2.0"

scalaVersion := "2.13.4"

libraryDependencies ++= Seq(
  "org.mongodb.scala"     %% "mongo-scala-driver" % "2.9.0",
  "com.github.pureconfig" %% "pureconfig"         % "0.14.0",
  "org.scalactic"         %% "scalactic"          % "3.1.0",
  "com.typesafe.slick"    %% "slick"              % "3.3.3",
  "org.apache.camel"      % "camel-core"          % "2.24.1",
  "org.apache.camel"      % "camel-jetty"         % "2.24.1",
  "com.h2database"        % "h2"                  % "1.4.200",
  "org.slf4j"             % "slf4j-nop"           % "1.7.26",
  "org.scalatest"         %% "scalatest"          % "3.2.1" % "test"
)

addCompilerPlugin(
  "org.wartremover" %% "wartremover" % "2.4.13" cross CrossVersion.full
)
//wartremoverWarnings ++= Warts.unsafe

scalacOptions ++= Seq("-deprecation", "-Xlint")
