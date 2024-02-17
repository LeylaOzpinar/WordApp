import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class StringToText {
  
  public void convertTextToFile(ArrayList<String> allWords, String filePath) {
    String text = "";
    String tempText = "";
    for (int i = 0; i < allWords.size() ;i++) {
      tempText = (i+1) + "_)" + allWords.get(i) + "\n";
      text += tempText;
    }
    try {
      FileWriter fileWriter = new FileWriter(filePath);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
      bufferedWriter.write(text);
      bufferedWriter.close();
    } 
    catch (IOException e) {
      System.out.println("Tekrar dene. Hata: " + e.getMessage());
    }
  }
  
}
