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

import me.johnnyapol.markovtg.probability.ProbabilityEngine;
import me.johnnyapol.markovtg.probability.TextChain;

public class SentenceGenerator {

	private ProbabilityEngine engine = null;
	
	private TextChain chain = null;
	
	public SentenceGenerator(int _maxWordCount, int _maxCharacterCount) {
		this.engine = new ProbabilityEngine(_maxWordCount, _maxCharacterCount);
		this.chain = new TextChain();
	}
	
	/**
	 * Analyzes text and adds it to the Markov Chain
	 * @param text The sequence of text to add
	 */
	public void input(String text) {
		String[] words = text.split(" ");
		
		this.chain.addStartingWord(words[0]);
		
		for (int i = 1; i < words.length; i++) {
			String previousWord = words[i - 1];
			String word = words[i];
			chain.addFollowingWord(previousWord, word, (i==1));
		}
	}
	
	public String generateSentence() {
		return this.engine.calculate(this.chain);
	}
	
}
