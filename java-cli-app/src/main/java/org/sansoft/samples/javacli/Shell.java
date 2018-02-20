package org.sansoft.samples.javacli;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import org.jline.reader.*;
import org.jline.reader.impl.completer.*;

/**
 * Sample application to show how jLine can be used.
 *
 * @author sandarenu
 *
 */
public class Shell {
	private String[] commandsList;

	public void init() {
		commandsList = new String[] { "help", "action1", "action2", "exit" };
	}

	public void run() {
		printWelcomeMessage();
		LineReaderBuilder readerBuilder = LineReaderBuilder.builder();
		List<Completer> completors = new LinkedList<Completer>();

		completors.add(new StringsCompleter(commandsList));
		readerBuilder.completer(new ArgumentCompleter(completors));

		LineReader reader = readerBuilder.build();

		String line;
		PrintWriter out = new PrintWriter(System.out);

		while ((line = readLine(reader, "")) != null) {
			if ("help".equals(line)) {
				printHelp();
			} else if ("action1".equals(line)) {
				System.out.println("You have selection action1");
			} else if ("action2".equals(line)) {
				System.out.println("You have selection action2");
			} else if ("exit".equals(line)) {
				System.out.println("Exiting application");
				return;
			} else {
				System.out
						.println("Invalid command, For assistance press TAB or type \"help\" then hit ENTER.");
			}
		}
	}

	private void printWelcomeMessage() {
		System.out
				.println("Welcome to jLine Sample App. For assistance press TAB or type \"help\" then hit ENTER.");

	}

	private void printHelp() {
		System.out.println("help		- Show help");
		System.out.println("action1		- Execute action1");
		System.out.println("action2		- Execute action2");
		System.out.println("exit        - Exit the app");

	}

	private String readLine(LineReader reader, String promtMessage) {
		try {
			String line = reader.readLine(promtMessage + "\nshell> ");
			return line.trim();
		}
		catch (UserInterruptException e) {
			// e.g. ^C
			return null;
		}
		catch (EndOfFileException e) {
			// e.g. ^D
			return null;
		}
	}

	public static void main(String[] args) {
		Shell shell = new Shell();
		shell.init();
		shell.run();
	}
}
