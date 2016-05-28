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
import java.util.List;
import java.util.Random;

public class ProbabilityEngine {

	class DrawingChance {
		private String word;
	
		public DrawingChance(String _word) {
			this.word = _word;
		}
		
		public String getWord() {
			return this.word;
		}
	}
	
	private Random random = new Random();
	private List<DrawingChance> drawing = new ArrayList<DrawingChance>();
	
	private int maxWordCount = -1;
	private int maxCharacterCount = -1;
	
	public ProbabilityEngine(int _maxWordCount, int _maxCharacterCount) {
		this.maxWordCount = _maxWordCount;
		this.maxCharacterCount = _maxCharacterCount;
	}

	public String calculate(TextChain chain) {
		List<String> startingWords = chain.getStarting();
		
		for (String start : startingWords) {
			WordProbability wb = chain.getStartingProbability(start);
			
			for (int i = 0; i < wb.getCount(); i++) {
				this.drawing.add(new DrawingChance(start));
			}
		}
		
		String start = this.doDrawing();
		this.drawing.clear();
		
		String sentence = start;
		
		String word = start;
		int count = 0;
		
		while (true) {
			count++;
			WordProbability following = chain.getFollowingProbability(word);
			
			if (following == null) {
				break;
			}
			if (following.getFollowing() == null) {
				break;
			}
			
			for (String follow : following.getFollowing()) {
				for (int i = 0; i < following.getCountForWord(follow); i++) {
					this.drawing.add(new DrawingChance(follow));
				}
			}
			
			if (this.drawing.isEmpty()) {
				break;
			}
			
			String nextWord = this.doDrawing();
			this.drawing.clear();
			word = nextWord;
			
			if (this.maxCharacterCount != -1 && (sentence.length() + word.length()) > this.maxCharacterCount) {
				break;
			}
			sentence += " " + nextWord;
			
			if (this.maxWordCount != -1 && count > this.maxWordCount) {
				break;
			}
		}
		return sentence;
	}

	private String doDrawing() {
		int rnd = this.random.nextInt(this.drawing.size());
		return this.drawing.get(rnd).getWord();
	}
}
