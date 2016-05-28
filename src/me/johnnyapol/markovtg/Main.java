package me.johnnyapol.markovtg;

import java.io.Console;

public class Main {

	public static void main(String[] args) {
		Console console = System.console();
		
		SentenceGenerator sGen = new SentenceGenerator(-1, -1);
		
		while (true) {
			String txt = console.readLine();
			if (txt.equalsIgnoreCase("run-job")) {
				System.out.println("Markov:" + sGen.generateSentence());
				sGen = new SentenceGenerator(-1, -1);
				continue;
			}
			sGen.input(txt);
			
		}
	}
}
