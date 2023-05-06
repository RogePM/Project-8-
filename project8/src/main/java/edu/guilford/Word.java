package edu.guilford;



// Word class to store the word and its frequency in the input file
public class Word implements Comparable<Word> {
    private String word;
    private int frequency;

    public Word(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    public String getWord() {
        return word;
    }

    public int getFrequency() {
        return frequency;
    }

    @Override
    public int compareTo(Word other) {
        return this.word.compareTo(other.word);
    }

    @Override
    public String toString() {
        return word + " " + frequency; 
}
}
