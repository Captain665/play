name := """play"""
organization := "play framework"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.15"

libraryDependencies += guice

PlayKeys.externalizeResourcesExcludes += baseDirectory.value / "conf" / "META-INF" / "META-INF/persistence.xml"

libraryDependencies += javaJpa

// https://mvnrepository.com/artifact/org.hibernate.orm/hibernate-core
libraryDependencies += "org.hibernate.orm" % "hibernate-core" % "6.6.4.Final"

// https://mvnrepository.com/artifact/org.postgresql/postgresql
libraryDependencies += "org.postgresql" % "postgresql" % "42.7.4"

// https://mvnrepository.com/artifact/jakarta.persistence/jakarta.persistence-api
libraryDependencies += "jakarta.persistence" % "jakarta.persistence-api" % "3.2.0"

libraryDependencies += "org.hibernate" % "hibernate-entitymanager" % "3.6.9.Final"

// https://mvnrepository.com/artifact/org.playframework/play-java-jpa
libraryDependencies += "org.playframework" %% "play-java-jpa" % "3.0.5" exclude("javax.validation", "validation-api")

