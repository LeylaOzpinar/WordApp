import java.util.HashMap;
import java.util.ArrayList;

public class Word {

    private HashMap<String, String> allWordsAndMeanings;
    private ArrayList<String> allWords;
    private TakeWord takeWord;

    public Word(TakeWord takeWord) {
        this.takeWord = takeWord;
        allWordsAndMeanings = takeWord.getWordAndMeaning();
        allWords = takeWord.getAllWords();
    }

    public String word(int indexOfWord) {
        if (indexOfWord >= 0 && indexOfWord < allWords.size()) {
            String wordAndMean = allWords.get(indexOfWord);
            int wordLength = wordAndMean.indexOf(":");
            return wordAndMean.substring(0, wordLength);
        }
        return ""; // Geçersiz indeks durumunda boş string dön
    }

    public String meaning(int indexOfWord) {
        if (indexOfWord >= 0 && indexOfWord < allWords.size()) {
            String wordAndMean = allWords.get(indexOfWord);
            int wordLength = wordAndMean.indexOf(":");
            return wordAndMean.substring(wordLength + 2);
        }
        return ""; // Geçersiz indeks durumunda boş string dön
    }
}
