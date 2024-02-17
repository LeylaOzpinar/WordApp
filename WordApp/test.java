import java.util.HashMap;
import java.util.Scanner;

public class test {

  private static int choice;

  public static void main(String[] args) {
    TakeWord takeWord = new TakeWord();
    Scanner scan = new Scanner(System.in);
    test mainScr = new test();
    choice = mainScr.mainScreen();
    while (choice != 5) {
      if (choice == 1) {
        System.out.println("Press 0 for word and meaning to go bact to menu.");
        String unknown = takeWord.enterUnknownWord();
        String mean = takeWord.enterMeaning();
        takeWord.putWordAndMeaningTogether(unknown,mean);
        if (unknown.equals("0") || mean.equals("0")){
          takeWord.getAllWords().remove(takeWord.getAllWords().size()-1);
          choice = mainScr.mainScreen();
        }
      }
      else if (choice == 2) {
        takeWord.writeArrayList();
        choice = mainScr.mainScreen();
      }
      else if (choice == 3) {
        takeWord.writeArrayList();
        int delete = scan.nextInt();
        if (delete == 0) {
          choice = mainScr.mainScreen();
        }
        else {
          takeWord.getAllWords().remove(delete-1);
        }
      }
      else if (choice == 4) {
        HashMap<String, String> allWordsAsMap = new HashMap<>();
        allWordsAsMap = takeWord.getWordAndMeaning();
        //bu tamamlanacak



      }
    
      
    } 
    System.out.println("isim: ");
      String isim = scan.nextLine();
      isim += ".txt";
    
    StringToText convert = new StringToText();
    convert.convertTextToFile(takeWord.getAllWords(), isim);
    
  }

  public int getChoice() {
    return choice;
  }


  public int mainScreen () {
    Scanner scan = new Scanner(System.in);
    System.out.print("\n1-Enter Word\n2-See Words\n3-Delete Word\n4-Play Game\n5-exit\n");
    int choice = scan.nextInt();
    System.out.println();
    return choice;
  }
}

