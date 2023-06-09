package edu.guilford;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class WordExtractor {
    public static void main(String[] args) throws URISyntaxException {
        String inputFilePath = "src/main/resources/Poe.txt";
        String outputFilePath = "src/main/resources/PoeWords.txt";

        // Create a linked list to store the words
        LinkedList<String> words = new LinkedList<>();

        try {
            // Open the input file
            File inputFile = new File(inputFilePath);
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            // Read each line of the file
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into words
                String[] lineWords = line.split("\\s+");

                // Add each word to the linked list
                for (String word : lineWords) {
                    // Remove punctuation and numbers
                    word = word.replaceAll("[^a-zA-Z]", "");

                    // Convert to lower-case and add to linked list
                    if (!word.isEmpty()) {
                        words.add(word.toLowerCase());
                    }

                }
            }
            LinkedList<Word> wordList = new LinkedList<>();
            for (String word : words) {
                int count = countOccurrences(inputFilePath, word);
                if (!containsWord(wordList, word)) {
                    wordList.add(new Word(word, count));
                }
            }

            Collections.sort(wordList);

            // Close the input file
            reader.close();

            // Sort the linked list
            Collections.sort(words);

            // Open the output file

            File outputFile = new File(outputFilePath);
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)));

            // Write each word to the output file
            for (Word word : wordList) {
                writer.println(word.toString());
            }

            // Close the output file
            writer.close();

            System.out.println("Word list created successfully.");

            // Prompt the user for a word
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a word to count: ");
            String wordToCount = scanner.nextLine().toLowerCase();
            scanner.close();

            int count = countOccurrences(inputFilePath, wordToCount);
            System.out.println("The word \"" + wordToCount + "\" appears " + count + " times in the text file.");

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static boolean containsWord(LinkedList<Word> words, String word) {
        for (Word w : words) {
            if (w.getWord().equals(word)) {
                return true;
            }
        }
        return false;
    }

    // Helper method to count the number of occurrences of a word in the input file
    private static int countOccurrences(String inputFilePath, String word) throws IOException {
        Path inputFile = Paths.get(inputFilePath);
        BufferedReader reader = Files.newBufferedReader(inputFile);
        int count = 0;
        String line;
        while ((line = reader.readLine()) != null) {
            String[] lineWords = line.split("\\s+");
            for (String w : lineWords) {
                if (w.replaceAll("[^a-zA-Z]", "").toLowerCase().equals(word)) {
                    count++;
                }
            }
        }
        reader.close();
        return count;

    }
}
