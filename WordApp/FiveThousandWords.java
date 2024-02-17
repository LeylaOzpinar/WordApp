import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * FiveThousandWords
 */
public class FiveThousandWords {

    private ArrayList<String> lines;

    public FiveThousandWords() {
      lines = new ArrayList<>();
      lines = readLinesFromFile();
    }

    private ArrayList<String> readLinesFromFile() {
      try {
          BufferedReader br = new BufferedReader(new FileReader("Words.txt"));
          String line;
          int i = 1;
          while ((line = br.readLine()) != null) {
              String filteredLine = line.replaceAll("\\d+\\t", "-").replaceAll("\t", " : ").trim();
              lines.add(filteredLine);
          }
          Collections.sort(lines);
          br.close();
      } catch (IOException e) {
          e.printStackTrace(); 
      }
      return lines;
  }

  
  public String getDictionaryAsString() {
    String text = "";
    for (int i = 0; i < lines.size(); i++) {
      text += lines.get(i) + "\n";   
    }
    return text;
  }

    public ArrayList<String> getLines() {
        return lines;
    }


}