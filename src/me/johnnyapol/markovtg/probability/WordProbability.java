package me.johnnyapol.markovtg.probability;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordProbability {

	private List<String> words = new ArrayList<String>();
	private Map<String, Integer> wordCount = new HashMap<String, Integer>();
	private int followingCount = 0;
	private int count = 1;
	
	public void increment() {
		this.count++;
	}
	
	public int getCount() {
		return this.count;
	}
	
	public void addFollowingWord(String word) {
		if (!this.words.contains(word)) {
			this.words.add(word);
			
			this.wordCount.put(word, 1);
			this.followingCount++;
		} else {
			this.wordCount.put(word, this.wordCount.get(word) + 1);
			this.followingCount++;
		}
	}
	
	public int getTotalFollowing() {
		return this.followingCount;
	}
	
	public List<String> getFollowing() {
		return this.words;
	}
	
	public int getCountForWord(String word) {
		return this.wordCount.get(word);
	}
}
