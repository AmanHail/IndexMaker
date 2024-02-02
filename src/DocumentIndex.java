/**
 * HONOR PLEDGE: All work here is honestly obtained and is my own.  Signed:  Aman Haileyesus
 * @author haileyesusa
 * Date of Completion:  1/06/2023
 * Assignment:  Ch11 Index Maker 2.0
 * 
 * Attribution: Ch11 Reading
 * 
 * General Description: Extends (mine uses a field instead) an array list of index entries  
 * 						And contains methods to return stastical information about the document index
 * 
 * Advanced:  
 * 
 * Errata:  
 *
 */


import java.util.ArrayList;

public class DocumentIndex{

	public ArrayList<IndexEntry> test; //list of index entries for each word
	
	public DocumentIndex() {
		 test = new ArrayList<IndexEntry>();
	}
	public DocumentIndex(int capacity)  {
		 test = new ArrayList<IndexEntry>(capacity);
	}
	
	/**
	 * Getter to return the array list since document index is not itself an array list
	 * @return
	 */
	public ArrayList<IndexEntry> getTest() {
		return test;
	}
	
	/**
	 * Finds (or creates) an index for a word in DocumentIndex and 
	 * adds a new line number to the index
	 * @param word
	 * @param lineNum
	 */
	public void add(String word, int lineNum) {
		int pos = foundOrInserted(word); //find pos or create new pos of word
		IndexEntry entry = test.get(pos); //store the index
		entry.addLineNumber(lineNum); //add the line number to the index
	}
	
	/**
	 * Takes a line, extracts all full words from the list,
	 * and either creates or finds a new index
	 * and adds the page number to it
	 * @param lineFromList
	 * @param lineNum
	 */
	public void addAllWords(String lineFromList, int lineNum) {
		String[] words = lineFromList.split("\\W+");  //create an array of all real words in the line
		for (String word : words) { //for each word in the line
			if (word.length()>0) { 
				add(word, lineNum); 
			}
		}
		
	}
	
	/**
	 * Helper Method
	 * Searches the document index for an index of a word
	 * If found, return the index
	 * If not found, create it in its' position alphabetically
	 * and returns that index
	 * @param word
	 * @return
	 */
	private int foundOrInserted(String word) {
		word = word.toUpperCase(); 
		
		//Loop through the whole middle 
		for (int i =0; i<test.size(); i++) {
			IndexEntry entry = test.get(i); //store the current word in the list
			
			if (entry.getWord().equals(word)) { //if its the given word
				entry.increaseFreq(); //
				return i;
			}
			//Else, if its alphabetical spot has been found, create a new entry with the word and return i
			if (entry.getWord().compareTo(word) > 0) { 
				IndexEntry newEntry = new IndexEntry(word);
				test.add(i, newEntry);
				return i;
			}
		}
		//If we reach the end, word belongs LAST 
		IndexEntry newEntry = new IndexEntry(word);
		test.add(newEntry); //add to the end of the list
		return test.size()-1; //return last index
		
	}
	
	/**
	 * Returns the word that appears least frequently in a text
	 * PRE: Document Index has at least 1 index
	 * @return The least frequent word
	 */
	public String leastFreq() {
		IndexEntry least = test.get(0); //holder for the least, start with first entry
		for (IndexEntry entry : test) {
			if (entry.getFreq() < least.getFreq()) { //if the current entry appears less
				least = entry; //set it as the new "least"
			}
		}
		return least.getWord(); //return the word
	}
	
	/**
	 * Returns the word that appears most frequently in a text
	 * PRE: Document Index has at least 1 index
	 * @return The most frequent word
	 */
	public String mostFreq() {
		IndexEntry most = test.get(0); //holder for the most, start with first entry
		for (IndexEntry entry : test) {
			if (entry.getFreq() > most.getFreq()) { //if the current entry appears more
				most = entry; //set it as the new "most"
			}
		}
		return most.getWord();
	}
	
	/**
	 * Returns the longest word (length-wise) in the text
	 * PRE: Document index has at least 1 index
	 * @return The longest word
	 */
	public String longestWord() {
		IndexEntry longest = test.get(0);
		for (IndexEntry entry : test) {
			if (entry.getWord().length() > longest.getWord().length()) {
				longest = entry;
			}
		}
		return longest.getWord();
	}
	
	/**
	 * Returns the shortest word (length-wise) in the text
	 * PRE: Document index has at least 1 index
	 * @return The shortest word
	 */
	public String shortestWord() {
		String shortest = this.longestWord(); //variable to store the shortest current word
		for (IndexEntry entry : test) {
			
			//if the current word is shorter than the shortest AND is at least 3 letters, set it to shortest
			if (entry.getWord().length() < shortest.length() && entry.getWord().length() > 3) { 
				shortest = entry.getWord();
			}
		}
		return shortest;
	}

	
}
