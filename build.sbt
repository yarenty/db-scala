import com.typesafe.sbteclipse.plugin.EclipsePlugin.EclipseKeys
import sbt.Keys._
import sbt._
import sbtassembly.Plugin.AssemblyKeys._
import sbtassembly.Plugin._

assemblySettings

EclipseKeys.createSrc in ThisBuild := EclipseCreateSrc.Default + EclipseCreateSrc.Resource

name := "DB example"

organization := "com.yarenty"

jarName in assembly := "db_1.0.0.jar"

mainClass in assembly := Some("com.yarenty.db.Test")

//no scala in assembly:
assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)

version := "1.0.0"

fork in run := true

scalaVersion := "2.11.6"

scalacOptions ++= Seq("-deprecation", "-feature")

scalacOptions in Test ++= Seq("-Yrangepos")

// append several options to the list of options passed to the Java compiler
javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-g")

javaOptions in(Test, run) += "-XstartOnFirstThread"


// LIBRARIES

// specific to CSV Importer

libraryDependencies += "com.github.tototoshi" %% "scala-csv" % "1.1.2"

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.34"

libraryDependencies += "org.jooq" % "jooq" % "3.6.2"

libraryDependencies += "org.jooq" % "jooq-scala" % "3.6.2"

libraryDependencies += "org.jooq" % "jooq-codegen" % "3.6.2"

libraryDependencies += "org.jooq" % "jooq-meta" % "3.6.2"



//-------------------
//all for test
libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"

libraryDependencies += "junit" % "junit" % "4.12" % "test"

libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.12.2" % "test"

libraryDependencies += "org.specs2" %% "specs2-core" % "3.1" % "test"

// --------------------
//scala default libraries

//Standard library for the Scala Programming Language 
libraryDependencies += "org.scala-lang" % "scala-library" % "2.11.6" % "provided"

//Compiler for the Scala Programming Language
libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.11.6" % "provided"

//Compiler for the Scala Programming Language
libraryDependencies += "org.scala-lang" % "scala-compiler" % "2.11.6" % "provided"

//swing for scala
libraryDependencies += "org.scala-lang" % "scala-swing" % "2.11.0-M7" % "provided"

//better readline ;-)
libraryDependencies += "org.scala-lang" % "jline" % "2.11.0-M3" % "provided"


//-------------------
// logging
//The slf4j API 
libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.10"

//SLF4J Simple binding
libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.7.10"

//Log4j implemented over SLF4J 
libraryDependencies += "org.slf4j" % "log4j-over-slf4j" % "1.7.10"

libraryDependencies += "log4j" % "log4j" % "1.2.17"


//-------------------
// some additional stuff
//REST framework
//libraryDependencies += "com.squareup.retrofit" % "retrofit" % "1.9.0"


//akka
//libraryDependencies += "com.typesafe.akka" % "akka-actor_2.11" % "2.3.9"

//libraryDependencies += "com.typesafe.akka" % "akka-testkit_2.11" % "2.3.9"

//libraryDependencies += "com.typesafe.akka" % "akka-slf4j_2.11" % "2.3.9"


//RESOLVERS

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

resolvers += "Local Maven Repository" at "" + Path.userHome.asFile.toURI.toURL + ".m2/repository"



//no tests in assembly
test in assembly := {}


mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) => {
  case PathList("javax", "servlet", xs@_*) => MergeStrategy.first
  case PathList(ps@_*) if ps.last endsWith ".html" => MergeStrategy.first
  case PathList(ps@_*) if ps.last endsWith ".RSA" => MergeStrategy.first
  case PathList(ps@_*) if ps.last endsWith ".mailcap" => MergeStrategy.first
  case PathList(ps@_*) if ps.last endsWith ".properties" => MergeStrategy.first
  case PathList(ps@_*) if ps.last endsWith ".xml" => MergeStrategy.first
  case PathList(ps@_*) if ps.last endsWith ".class" => MergeStrategy.first
  case PathList(ps@_*) if ps.last endsWith ".thrift" => MergeStrategy.first
  case PathList(ps@_*) if ps.last endsWith ".jnilib" => MergeStrategy.first
  case PathList(ps@_*) if ps.last endsWith ".dll" => MergeStrategy.first
  case PathList(ps@_*) if ps.last endsWith ".so" => MergeStrategy.first
  case "application.conf" => MergeStrategy.concat
  case "log4j.properties" => MergeStrategy.discard
  case x => old(x)
}
}



