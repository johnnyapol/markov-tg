/* This file is part of markov-tg.

    markov-tg is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    markov-tg is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with markov-tg.  If not, see <http://www.gnu.org/licenses/>. */
package me.johnnyapol.markovtg;

import java.io.Console;

/** 
 * Simple main example class - you can ignore for now
 * @author John C. Allwein <johnnyapol>
 *
 */
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
