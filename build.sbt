name := "scalastudy"

version := "1.0"

scalaVersion := "2.11.12"

libraryDependencies ++= Seq(
  "com.github.pureconfig" %% "pureconfig" % "0.9.0",
  "org.scalactic" %% "scalactic" % "3.0.1",
  "com.typesafe.slick" %% "slick" % "3.3.1",
  "org.apache.camel" % "camel-core" % "2.24.1",
  "org.apache.camel" % "camel-jetty" % "2.24.1",
  "com.h2database" % "h2" % "1.4.199" % "runtime",
  "org.slf4j" % "slf4j-nop" % "1.7.26",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
)