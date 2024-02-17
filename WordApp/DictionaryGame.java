import java.util.ArrayList;

public class DictionaryGame {

  public FiveThousandWords allDictionary;
  public ArrayList<String> lines, word, mean;

  public DictionaryGame() {
    allDictionary = new FiveThousandWords();
    lines = new ArrayList<>();
    word = new ArrayList<>();
    mean = new ArrayList<>();
    lines = allDictionary.getLines();
  }

  public void wordAndMean() {
    int seperationIndex;
    for (int i = 0; i < (lines.size()-1)*2; i++) {
      seperationIndex = lines.get(i).indexOf(":");
      word.add(lines.get(i).substring(1, seperationIndex));
      mean.add(lines.get(i).substring(seperationIndex+2, lines.get(i).length()));
    }
  }

  public ArrayList<String> getWord() {
    return word;
  }
  public ArrayList<String> getMean() {
    return mean;
  }

}
