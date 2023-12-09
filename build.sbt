// build.sbt

name := "DistributedWordleGame"

version := "0.1"

scalaVersion := "2.13.12" 

// Add your Akka version
val akkaVersion = "2.6.18" 

// Library dependencies
libraryDependencies ++= Seq(
  // Akka libraries
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster-tools" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster-sharding-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-persistence-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-serialization-jackson" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion % Test,

  // Logback dependencies
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "ch.qos.logback" % "logback-core" % "1.2.3",

  // ScalaFX for UI
  "org.scalafx" %% "scalafx" % "18.0.2-R29", 
  "org.openjfx" % "javafx" % "18.0.2" % "provided",

  // ScalaTest for unit testing
  "org.scalatest" %% "scalatest" % "3.2.1" % Test
)

javaOptions in run ++= Seq(
  "--module-path", "/javafx-sdk-18.0.2/lib",
  "--add-modules", "javafx.controls,javafx.fxml"
)
