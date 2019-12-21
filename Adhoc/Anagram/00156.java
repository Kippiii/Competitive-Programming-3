import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Word> words = new ArrayList<Word>();
        while (true) {
            String[] wordStrs = in.nextLine().split(" ", 0);
            if (wordStrs[0].equals("#")) break;
            for (String word : wordStrs) {
                if (word.trim().isEmpty()) continue;
                words.add(new Word(word));
            }
        }
        Collections.sort(words);

        ArrayList<String> anagrams = new ArrayList<String>();
        Word previous = words.get(0);
        boolean repeating = false;
        for (int i = 1; i < words.size(); i++) {
            Word word = words.get(i);
            if (word.compareTo(previous) == 0) {
                repeating = true;
            } else if (repeating) {
                repeating = false;
                previous = word;
            } else {
                anagrams.add(previous.word);
                previous = word;
            }
        }
        if (!repeating) {
            anagrams.add(previous.word);
        }

        Collections.sort(anagrams);
        for (String anagram : anagrams) {
            System.out.println(anagram);
        }
    }
}

class Word implements Comparable {
    String word;
    String sorted;
    public Word(String word) {
        this.word = word;
        char charArray[] = word.toLowerCase().toCharArray();
        Arrays.sort(charArray);
        this.sorted = new String(charArray);
    }
    @Override
    public int compareTo(Object o) {
        return this.sorted.compareTo(((Word) o).sorted);
    }
}
