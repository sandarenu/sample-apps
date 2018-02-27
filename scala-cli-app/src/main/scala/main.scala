package org.sansoft.samples.scalacli

import java.io.PrintWriter

import org.fusesource.jansi.AnsiConsole

import org.jline.reader._
import org.jline.reader.impl.completer._
import org.jline.utils._

object Shell {
  def main(args: Array[String]) = {
    val shell: Shell = new Shell()
    shell.run()
  }
}

class Shell {
  var commandsList: Seq[String] = Seq("help", "action1", "action2", "exit")

  def run(): Unit = {
    AnsiConsole.systemInstall() // needed to support asni on Windows cmd

    printWelcomeMessage()

    val readerBuilder: LineReaderBuilder = LineReaderBuilder.builder()

    var completors: Seq[Completer] = Seq(new StringsCompleter(commandsList: _*))

    readerBuilder.completer(new ArgumentCompleter(completors: _*))

    val reader: LineReader = readerBuilder.build()

    var line: String = null
    var continue: Boolean = true

    val out: PrintWriter = new PrintWriter(System.out)

    while (continue) {
      line = readLine(reader, "")

      if (line == "help") {
        printHelp()
      }
      else if (line == "action1") {
        val a = new AttributedStringBuilder()
          .append("You have selected ")
          .append("action1", AttributedStyle.BOLD.foreground(AttributedStyle.RED))
          .append("!")

        println(a.toAnsi)
      }
      else if (line == "action2") {
        val a = new AttributedStringBuilder()
          .append("You have selected ")
          .append("action2", AttributedStyle.BOLD.foreground(AttributedStyle.RED))
          .append("!")

        println(a.toAnsi)
      }
      else if (line == null || line == "exit") {
        println("Exiting application")
        continue = false
      }
      else {
        println("Invalid command, For assistance press TAB or type \"help\" then hit ENTER.")
      }

      out.flush()
    }

    AnsiConsole.systemUninstall()
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

  def readLine(reader: LineReader, promtMessage: String): String = {
    try {
      reader.readLine(promtMessage + "\nshell> ").trim()
    }
    catch {
      case e: UserInterruptException => null // e.g. ^C
      case e: EndOfFileException => null // e.g. ^D
    }
  }
}
