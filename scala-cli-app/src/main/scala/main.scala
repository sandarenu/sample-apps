package org.sansoft.samples.scalacli

import java.io.PrintWriter

import collection.JavaConversions._

import jline.console._
import jline.console.completer._

object Shell {
  def main(args: Array[String]) = {
    val shell: Shell = new Shell()
    shell.run()
  }
}

class Shell {
  var commandsList: Seq[String] = Seq("help", "action1", "action2", "exit");

  def run(): Unit = {
    printWelcomeMessage()

    val reader: ConsoleReader = new ConsoleReader()
    reader.setBellEnabled(false)

    var completors: Seq[Completer] = Seq(new StringsCompleter(commandsList))

    reader.addCompleter(new ArgumentCompleter(completors))

    var line: String = null
    var continue: Boolean = true

    val out: PrintWriter = new PrintWriter(System.out)

    while (continue) {
      line = readLine(reader, "")

      if (line == "help") {
        printHelp()
      }
      else if (line == "action1") {
        println("You have selection action1")
      }
      else if (line == "action2") {
        println("You have selection action2")
      }
      else if (line == "exit") {
        println("Exiting application")
        continue = false
      }
      else {
        println("Invalid command, For assistance press TAB or type \"help\" then hit ENTER.")
      }

      out.flush()
    }
  }

  def printWelcomeMessage(): Unit = {
    println("Welcome to jLine Sample App. For assistance press TAB or type \"help\" then hit ENTER.")
  }

  def printHelp(): Unit = {
    println("help     - Show help")
    println("action1  - Execute action1")
    println("action2  - Execute action2")
    println("exit     - Exit the app")
  }

  def readLine(reader: ConsoleReader, promtMessage: String): String = {
    reader.readLine(promtMessage + "\nshell> ").trim()
  }
}
