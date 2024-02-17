import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TakeWord {

  private Scanner scan;
  private HashMap<String, String> wordAndMeaning;
  private ArrayList<String> allWords;

  public TakeWord() {
    this.scan = new Scanner(System.in);
    this.wordAndMeaning = new HashMap<>();
    this.allWords = new ArrayList<>();
  }


  public String enterUnknownWord() {
    //these are for test class.
    System.out.print("Enter Word: ");
    String unknownWord = scan.nextLine();
    return unknownWord;
  }


  public String enterMeaning() {
    //these are for test class.
    System.out.print("Enter Meaning: ");
    String meaning = scan.nextLine();
    System.out.println();
    return meaning;
  }


  public void putWordAndMeaningTogether(String word, String meaning) {
    //put word and meaning together
    wordAndMeaning.put(word, meaning);

    //add word and meaning to arraylist
    String wordWithMeaning = word + ": " + meaning;
    allWords.add(wordWithMeaning);
  }

  public String writeArrayList() {
    String word = "";
    for (int i = 0; i < allWords.size(); i++) {
      String getWords = allWords.get(i);
      word += (i+1) + "_)" + getWords + "\n";
    }
    return word;
  }


  public ArrayList<String> getAllWords()  { return allWords; }

  public HashMap<String,String> getWordAndMeaning()  { return wordAndMeaning; }

}
