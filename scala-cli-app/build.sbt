name := "scala-cli-app"

version := "0.0.2"

scalaVersion := "2.12.4"

mainClass := Some("org.sansoft.samples.scalacli.Shell")

libraryDependencies ++= Seq(
  "org.jline" % "jline" % "3.6.1",
  "org.fusesource.jansi" % "jansi" % "1.17", // JLine 3.6.1 uses 1.17; needed to display ansi in Windows cmd
  "net.java.dev.jna" % "jna" % "4.2.2" // JLine 3.6.1 uses 4.2.2; needed to create a JLine system terminal
)
