package wordCounter;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class WordCounter {

	
	public String[] getWordArray(String textFile) throws FileNotFoundException {
		
		int count = 0;
		Scanner in_ = null;
		Scanner in_1 = null;
		
		try {
			
		String file = textFile;
		FileInputStream inputFile = new FileInputStream(file);
		FileInputStream inputFile1 = new FileInputStream(file);
		in_ = new Scanner(inputFile);
		in_1 = new Scanner(inputFile1);
		
		}
		catch (FileNotFoundException e) {
			System.out.println("Error: File not found! \nPlease enter a valid file and check active directory...");
		}
		
		while (in_.hasNext()) {
			in_.next();
			count++;
			
		}
		
		in_.close();
		
		String[] wordArray = new String[count];
		
		for (int i = 0; i < wordArray.length; i++) {
			wordArray[i] = in_1.next().toString();
		}
		
		in_1.close();
		
		System.out.println("Number of Words on File: " + wordArray.length + "\n");
		return wordArray;
		
	}
	
	
	public void getCountsOfWord(HashMap<String, Integer> map) {
		
		@SuppressWarnings("resource")
		Scanner scnr = new Scanner(System.in);
		
		System.out.println("Please enter a word to search: ");
		
		String text = scnr.next();
		
		System.out.println("Instances of \"" + text + "\": " + map.get(text) + "\n");
		
	}
	
	
	public void getValues(HashMap<String, Integer> map) {
		
		Set<String> keys = map.keySet();
		
		for (String key : keys) {
			System.out.println("Word: " + key + "\nCount: " + map.get(key) + "\n");
		}
		
	}
	
	
	public <K, V extends Comparable<V>> void getLargestEntry(HashMap<K, V> map) {
		
		Map.Entry<K, V> maxEntry = null;
		
		for (Map.Entry<K, V> entry: map.entrySet()) {
			
			if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
				
				maxEntry = entry;
			}
		}
		
		System.out.print("The most used word(s): " + maxEntry.getKey());
		
		for (Map.Entry<K, V> entry: map.entrySet()) {
			if (entry.getValue().compareTo(maxEntry.getValue()) == 0 && entry.getKey() != maxEntry.getKey()) {
				System.out.print(", " + entry.getKey());
			}
		}
		
		System.out.println(". With a total of " + maxEntry.getValue() + " occurrences!\n");
		
	}
	
	
	public <K, V extends Comparable<V>> void getSmallestEntry(HashMap<K, V> map) {
		
		Map.Entry<K, V> lowEntry = null;
		
		for (Map.Entry<K, V> entry: map.entrySet()) {
			if (lowEntry == null || entry.getValue().compareTo(lowEntry.getValue()) < 0) {
				
				lowEntry = entry;
			}
		}
		
		System.out.print("The least used word(s): " + lowEntry.getKey());
		
		for (Map.Entry<K, V> entry: map.entrySet()) {
			if (entry.getValue().compareTo(lowEntry.getValue()) == 0 && entry.getKey() != lowEntry.getKey()) {
				System.out.print(", " + entry.getKey());
			}
		}
		
		System.out.println(". With a total of " + lowEntry.getValue() + " occurrences!\n");
		
	}
	
	
	public HashMap<String, Integer> setMap(String[] wordArray) {
		
		String word;
		HashMap<String, Integer> map = new HashMap<>();
		
		for (int i = 0; i < wordArray.length; i++) {
			int wordCount = 0;
			word = wordArray[i];
			
			for (int j = 1; j < wordArray.length; j++) {
				if (wordArray[j].toString().equalsIgnoreCase(word)) {
					wordCount++;
					
				}
			}
			
			if (wordArray[0].equals(wordArray[i])) {
				map.put(word, wordCount += 1);
			} else {
				map.put(word, wordCount);
			}
			
		}
		
		System.out.println("Number of Words Used: " + map.size() + "\n");
		return map;
		
	}
		
	
	public static void main(String[] args) throws IOException {
		
		Scanner scnr = new Scanner(System.in);
		char character;
		
		WordCounter wordCounter = new WordCounter();
		
		System.out.println("Enter Name of a Valid Text File to Scan:");
		String fileName = scnr.nextLine();
		System.out.println();
		String[] wordArray = wordCounter.getWordArray(fileName);
		HashMap<String, Integer> map = wordCounter.setMap(wordArray);
		
		System.out.println("\nEnter a Valid Key to Continue! Enter 'q' to Quit:\n");
		System.out.println("w - Get Count of a Specific Word.");
		System.out.println("v - Get Values of All Words Found.");
		System.out.println("m - Get Most Used Word(s)");
		System.out.println("l - Get Least Used Word(s)");
		
		character = scnr.next().toUpperCase().charAt(0);
		
		System.out.println();
		
		while (character != 'Q') {
			
			switch (character) {
			
				case 'W':
					wordCounter.getCountsOfWord(map);
					break;
				case 'V':
					wordCounter.getValues(map);
					break;
				case 'M':
					wordCounter.getLargestEntry(map);
					break;
				case 'L':
					wordCounter.getSmallestEntry(map);
					break;
				default:
					System.out.println("Please enter a valid key!\n");
					break;
					
			}
			
			System.out.println("\nEnter a Valid Key to Continue! Enter 'q' to Quit:\n");
			System.out.println("w - Get Counts of a Specific Word.");
			System.out.println("v - Get Values of All Words Found.");
			System.out.println("m - Get Most Used Word(s)");
			System.out.println("l - Get Least Used Word(s)");
			
			character = scnr.next().toUpperCase().charAt(0);
			
			System.out.println();
			
		}
		
		scnr.close();
	}
	
	
}
