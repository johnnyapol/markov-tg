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
