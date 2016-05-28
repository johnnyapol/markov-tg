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
