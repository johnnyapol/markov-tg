package me.johnnyapol.markovtg.probability;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextChain {

	private List<String> startingWords = new ArrayList<String>();
	private List<String> followingWords = new ArrayList<String>();
	
	private Map<String, WordProbability> startingProbability = new HashMap<String, WordProbability>();
	private Map<String, WordProbability> followingProbability = new HashMap<String, WordProbability>();

	public void addFollowingWord(String previous, String word, boolean starting) {
		if (!this.followingWords.contains(word)) {
			this.followingWords.add(word);
		}
		
		if (!starting) {
			if (this.followingProbability.get(previous) == null) {
				this.followingProbability.put(previous, new WordProbability());
			}
			this.followingProbability.get(previous).addFollowingWord(word);
			this.followingProbability.get(previous).increment();
		} else {
			WordProbability wb = new WordProbability();
			wb.addFollowingWord(word);
			this.followingProbability.put(previous, wb);
		}
	}

	public void addStartingWord(String string) {
		if (!this.startingWords.contains(string)) {
			this.startingWords.add(string);
			this.startingProbability.put(string, new WordProbability());
		}
		
		this.startingProbability.get(string).increment();
	}

	public List<String> getStarting() {
		return this.startingWords;
	}
	
	public WordProbability getStartingProbability(String word) {
		return this.startingProbability.get(word);
	}
	
	public WordProbability getFollowingProbability(String word) {
		return this.followingProbability.get(word);
	}
}
