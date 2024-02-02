//Comments on DocumentIndex.java
//General Description: A class with both a word from a text and a list of page numbers with it

import java.util.ArrayList;

public class IndexEntry implements Comparable<IndexEntry>{
	private String word; 
	private ArrayList<Integer> numsList; //list of page numbers word is found on
	private int freq; //records how many times the word appears
	
	public IndexEntry(String w) {
		word = w.toUpperCase();
		numsList = new ArrayList<Integer>();
		freq = 1;
	}
	
	public boolean equals(Object other) {
		return this.compareTo((IndexEntry)(other)) == 0;
	}
	public int compareTo(IndexEntry other) {
		return word.compareTo(other.word);
	}
	
	/**
	 * Given a line number on which "word" appears,
	 * adds that line number to the list of lines if it does not already exist
	 * @param num
	 */
	
	public void addLineNumber(int num) {
		if (!numsList.contains(num)) {
			numsList.add(num);
		}
	}
	//Getter for word
	public String getWord() {
		return word;
	}
	//Getter for frequency
	public int getFreq() {
		return freq;
	}
	//Increases the frequency by one when called
	public void increaseFreq() {
		freq++;
	}
	

	//Print the word (uppercase), frequency, and page numbers of a word
	public String toString() {
		String store = new String();
		store += word + " (found " + freq + " times): ";
		for (int i = 0; i<numsList.size(); i++) {
			store+=numsList.get(i) + ", ";
		}
		return store.substring(0, store.length()-2);
	}
}
