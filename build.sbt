name := "scalastudy"

version := "1.1"

scalaVersion := "2.12.10"

libraryDependencies ++= Seq(
  "com.github.pureconfig" %% "pureconfig" % "0.12.1",
  "org.scalactic" %% "scalactic" % "3.1.0",
  "com.typesafe.slick" %% "slick" % "3.3.2",
  "org.apache.camel" % "camel-core" % "2.24.1",
  "org.apache.camel" % "camel-jetty" % "2.24.1",
  "com.h2database" % "h2" % "1.4.199" % "runtime",
  "org.slf4j" % "slf4j-nop" % "1.7.26",
  "org.scalatest" %% "scalatest" % "3.1.0" % "test"
)