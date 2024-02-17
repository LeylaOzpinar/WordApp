import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Random;

public class Gui {
    private boolean isItFirstOpening = true;
    private boolean game = false;
    private JFrame frame;
    private JTextField wordField, meaningField, deleteField, findWordField, textFileName;
    private JButton addButton, viewButton, deleteButton, quizButton, saveButton, goBackButton, confirmDeleteButton, checkButton, dictionaryButton, findWordButton, convertToTextFileButton, saveToTextButton;
    private static TakeWord takeWord;
    private static FiveThousandWords dictionary;
    private Word wordAndMeaning;
    private JTextArea welcome1, welcome2, welcome3, wordsArea, wordsArea2, wordsArea3, deleteMessage, word, meaningField2, trueOrFalse, enterTextNameBelow;
    private JScrollPane scrollPane, scrollPane2, scrollPane3;
    private int getWordIndex, wordNumber;
    private String textName;
    private JLabel backgroundLabel;
    private StringToText convert;
    private AddSound addSound;
    private ArrayList<String> wordsForGame;

    public Gui() {
        wordsForGame = new ArrayList<>();
        convert = new StringToText();
        takeWord = new TakeWord();
        dictionary = new FiveThousandWords();
        addSound = new AddSound();
        wordAndMeaning = new Word(takeWord);
        initializeFrame();
        initializeComponents();
        addBackgroundImage();
        addComponentsToFrame();
        setupActionListeners();
        showWelcomePage();
        addComponentListenerToFrame();
        frame.setVisible(true);
    }

    private void addBackgroundImage() {
        ImageIcon backgroundImage = new ImageIcon("main1.png"); // Resmin yolu
        if (game) {
            backgroundImage = new ImageIcon("gameBackGround.png");
        }
        if (takeWord.getAllWords().size()==0 && !game){
            backgroundImage = new ImageIcon("main1.png");
        }
        if (takeWord.getAllWords().size()<=1 && !game){
            backgroundImage = new ImageIcon("main2.png");
        }
        if (takeWord.getAllWords().size()<=5 && !game){
            backgroundImage = new ImageIcon("main3.png");
        }
        if (takeWord.getAllWords().size()<=9 && !game){
            backgroundImage = new ImageIcon("main4.png");
        }
        if (takeWord.getAllWords().size()<=13 && !game){
            backgroundImage = new ImageIcon("main5.png");
        }
        if (takeWord.getAllWords().size()>17 && !game){
            backgroundImage = new ImageIcon("main6.png");
        }
        backgroundLabel = new JLabel(backgroundImage);
        frame.setContentPane(backgroundLabel);
        frame.setLayout(new FlowLayout());
        resizeBackgroundImage();
    }

    private void initializeFrame() {
        frame = new JFrame("WordApp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,600);
        frame.setLayout(new FlowLayout());
    }

    private void initializeComponents() {
        // Initialize text fields, buttons, and text areas
        word = new JTextArea(5, 9);
        word.setEditable(false);
        welcome1 = new JTextArea("Welcome to WordApp.", 1, 10);
        welcome2 = new JTextArea("You can add words to your dictionary,", 1, 10);
        welcome3 = new JTextArea("You can improve your English!", 1, 10);
        welcome1.setOpaque(false);
        welcome2.setOpaque(false);
        welcome3.setOpaque(false);
        Font fontForWelcome = new Font("Arial",Font.BOLD,27);
        welcome1.setFont(fontForWelcome);
        welcome2.setFont(fontForWelcome);
        welcome3.setFont(fontForWelcome);
        checkButton = new JButton("CHECK!");
        quizButton = new JButton("GAME!");
        wordField = new JTextField(15);
        meaningField = new JTextField(15);
        meaningField2 = new JTextArea(5,9);
        goBackButton = new JButton("<GoBack");
        addButton = new JButton("Add\nWord");
        viewButton = new JButton("Show\nWords");
        deleteButton = new JButton("Delete\nWord");
        dictionaryButton = new JButton("Dictionary");
        saveButton = new JButton("Save Word");
        deleteField = new JTextField(2);
        trueOrFalse = new JTextArea("");
        deleteField.setVisible(false);
        wordsArea = new JTextArea(20, 27);
        wordsArea3 = new JTextArea(45, 40); 
        wordsArea2 = new JTextArea(20, 25);
        scrollPane = new JScrollPane(wordsArea);
        scrollPane2 = new JScrollPane(wordsArea2);
        scrollPane3 = new JScrollPane(wordsArea3);
        deleteMessage = new JTextArea("Enter the number of the word", 1, 11);
        confirmDeleteButton = new JButton("Delete");
        findWordButton = new JButton("Search");
        findWordField = new JTextField(8);
        convertToTextFileButton = new JButton("Make\nText\nFile");
        enterTextNameBelow = new JTextArea("Enter the text name below");
        textFileName = new JTextField(10);
        saveToTextButton = new JButton("SAVE");


        Font fontForButtons = new Font("Arial",Font.BOLD,23);
        addButton.setFont(fontForButtons);
        viewButton.setFont(fontForButtons);
        deleteButton.setFont(fontForButtons);
        quizButton.setFont(fontForButtons);
        dictionaryButton.setFont(fontForButtons);
        convertToTextFileButton.setFont(fontForButtons);
        goBackButton.setFont(fontForButtons);
        saveButton.setFont(fontForButtons);
        saveToTextButton.setFont(fontForButtons);
        checkButton.setFont(fontForButtons);


        Font fontForWordAreas = new Font("Arial",Font.PLAIN, 20);
        wordsArea.setFont(fontForWordAreas);
        deleteMessage.setFont(new Font("Arial",Font.PLAIN, 30));
        word.setFont(new Font("Arial",Font.PLAIN, 30));
        meaningField2.setFont(new Font("Arial",Font.PLAIN, 30));
        trueOrFalse.setFont(new Font("Arial",Font.PLAIN, 30));

        Font fontForEnterWord = new Font("Arial",Font.PLAIN, 30);
        wordField.setFont(fontForEnterWord);
        meaningField.setFont(fontForEnterWord);
        deleteField.setFont(fontForEnterWord);
        enterTextNameBelow.setFont(fontForEnterWord);
        textFileName.setFont(fontForEnterWord);

        allComponentsWithNoVisibility();


        welcome1.setVisible(true);
        welcome2.setVisible(true);
        welcome3.setVisible(true);
        addButton.setVisible(true);
        viewButton.setVisible(true);
        deleteButton.setVisible(true);
        quizButton.setVisible(true);
        dictionaryButton.setVisible(true);
        convertToTextFileButton.setVisible(true);
        
        
        deleteMessage.setEditable(false);
        wordsArea.setEditable(false);
        welcome1.setEditable(false);
        welcome2.setEditable(false);
        welcome3.setEditable(false);
        wordsArea2.setEditable(false);
        wordsArea3.setEditable(false);
        
    }

    private void allComponentsWithNoVisibility() {
        resizeBackgroundImage();
        addButton.setVisible(false);
        checkButton.setVisible(false);
        viewButton.setVisible(false);
        word.setVisible(false);
        scrollPane.setVisible(false);
        scrollPane2.setVisible(false);
        scrollPane3.setVisible(false);
        confirmDeleteButton.setVisible(false);
        deleteButton.setVisible(false);
        deleteMessage.setVisible(false);
        wordField.setVisible(false);
        quizButton.setVisible(false);
        dictionaryButton.setVisible(false);
        trueOrFalse.setVisible(false);
        wordsArea3.setVisible(false);
        findWordButton.setVisible(false);
        findWordField.setVisible(false);
        enterTextNameBelow.setVisible(false);
        textFileName.setVisible(false);
        saveToTextButton.setVisible(false);
        goBackButton.setVisible(false);
        deleteField.setVisible(false);
        meaningField.setVisible(false);
        meaningField2.setVisible(false);
        saveButton.setVisible(false);
        convertToTextFileButton.setVisible(false);
        welcome1.setVisible(false);
        welcome2.setVisible(false);
        welcome3.setVisible(false);
    }

    private void addComponentsToFrame() {
        frame.add(welcome1);
        frame.add(welcome2);
        frame.add(welcome3);
        frame.add(wordField);
        frame.add(addButton);
        frame.add(viewButton);
        frame.add(deleteButton);
        frame.add(saveButton);
        frame.add(goBackButton);
        frame.add(saveToTextButton);
        frame.add(scrollPane);
        frame.add(scrollPane2);
        frame.add(scrollPane3);
        frame.add(deleteMessage);
        frame.add(confirmDeleteButton);
        frame.add(deleteField);
        frame.add(checkButton);
        frame.add(quizButton);
        frame.add(dictionaryButton);
        frame.add(word);
        frame.add(meaningField);
        frame.add(meaningField2);
        frame.add(trueOrFalse);
        frame.add(wordsArea3);
        frame.add(findWordButton);
        frame.add(convertToTextFileButton);
        frame.add(enterTextNameBelow);
        frame.add(textFileName);
    }


    private void addComponentListenerToFrame() {
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeBackgroundImage();
            }
        });
    }


    private void resizeBackgroundImage() {
        if (backgroundLabel != null) {
            Image originalImage = new ImageIcon("background.png").getImage(); // Orjinal resmi yeniden yükle
            if (game) {
                originalImage = new ImageIcon("gameBackGround.png").getImage();
            }

        if (takeWord.getAllWords().size()==0 && !game){
            originalImage = new ImageIcon("main1.png").getImage();
        }
        if (takeWord.getAllWords().size()>0 && takeWord.getAllWords().size()<=1 && !game){
            originalImage = new ImageIcon("main2.png").getImage();
        }
        if (takeWord.getAllWords().size()>1 && takeWord.getAllWords().size()<=2 && !game){
            originalImage = new ImageIcon("main3.png").getImage();
        }
        if (takeWord.getAllWords().size()>2 && takeWord.getAllWords().size()<=3 && !game){
            originalImage = new ImageIcon("main4.png").getImage();
        }
        if (takeWord.getAllWords().size()>3 && takeWord.getAllWords().size()<=4 && !game){
            originalImage = new ImageIcon("main5.png").getImage();
        }
        if (takeWord.getAllWords().size()>4 && !game){
            originalImage = new ImageIcon("main6.png").getImage();
        }
            Image scaledImage = originalImage.getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
            backgroundLabel.setIcon(new ImageIcon(scaledImage));
        }
    }
    
    private void setupActionListeners() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddWordPage();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveWord();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showWordsPage();
            }
        });

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showWelcomePage();
            }
        });

        
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteWord();
            }
        });

        dictionaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDictionaryPage();
            }
        });

        quizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showQuizPage();
            }
        });

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
            }
        });

        convertToTextFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeTextPage();
            }
        });
        
        saveToTextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToText();
            }
        });
    }

    private void showWelcomePage() {
        // Logic to show welcome page
        wordsForGame = takeWord.getAllWords();
        allComponentsWithNoVisibility();
        game = false;
        meaningField2.setBackground(Color.WHITE);
        word.setBackground(Color.WHITE);
        quizButton.setVisible(true);
        dictionaryButton.setVisible(true);
        word.setText("");
        meaningField.setText("");
        meaningField2.setText("\n\n ");
        if(isItFirstOpening) {
            welcome1.setVisible(true);
            welcome2.setVisible(true);
            welcome3.setVisible(true);
        }
        isItFirstOpening = false;
        addButton.setVisible(true);
        viewButton.setVisible(true);
        deleteButton.setVisible(true);
        convertToTextFileButton.setVisible(true);
        frame.setSize(500, 600);
    }

    private void showAddWordPage() {
        // Logic to show add word page
        allComponentsWithNoVisibility();
        saveButton.setVisible(true);
        wordField.setVisible(true);
        meaningField.setVisible(true);
        goBackButton.setVisible(true);
        frame.setSize(500,600);
    }

    private void saveWord() {
        // Logic to save a word
        allComponentsWithNoVisibility();
        String word = wordField.getText();
        String meaning = meaningField.getText();
        takeWord.putWordAndMeaningTogether(word, meaning);
        wordField.setText("");
        meaningField.setText("");
        meaningField2.setText("\n\n ");
        showWelcomePage();
    }

    private void showWordsPage() {
        // Logic to show words page
        allComponentsWithNoVisibility();
        scrollPane.setVisible(true);
        wordsArea.setText(takeWord.writeArrayList());
        scrollPane.setVisible(true);
        wordsArea.setVisible(true);
        goBackButton.setVisible(true);
        frame.setSize(500,600);
    }

    private void deleteWord() {
        allComponentsWithNoVisibility();
        deleteMessage.setVisible(true);
        wordsArea2.setText(takeWord.writeArrayList());
        wordsArea2.setVisible(true);
        scrollPane2.setVisible(true);
        deleteField.setVisible(true);
        goBackButton.setVisible(true);
        deleteButton.setVisible(true);
        int remove = Integer.parseInt(deleteField.getText());
        takeWord.getAllWords().remove(remove-1);
        frame.setSize(500,600);
    }

    
    private void showQuizPage() {
        game = true;
        allComponentsWithNoVisibility();
        addBackgroundImage();
        addComponentsToFrame();
        meaningField2.setBackground(Color.WHITE);
        word.setBackground(Color.WHITE);
        quizButton.setVisible(true);
        if (takeWord.getAllWords().size() > 0) {
            Random rand = new Random();
            getWordIndex = rand.nextInt(takeWord.getAllWords().size());
            meaningField2.setVisible(true);
            meaningField2.setText("\n\n ");
            word.setText("\n\n " + wordAndMeaning.word(getWordIndex));
            word.setVisible(true);
            checkButton.setVisible(true);
            goBackButton.setVisible(true);
            frame.setSize(500, 500); 
            frame.revalidate(); 
            frame.repaint(); 
        } else {
            goBackButton.setVisible(true);
            frame.setSize(500, 500); 
            frame.revalidate(); 
            frame.repaint();
        }
        meaningField2.setText("\n\n ");
    }


    private void showDictionaryPage() {
        allComponentsWithNoVisibility();
        deleteField.setText("");
        String txt = dictionary.getDictionaryAsString();
        wordsArea3.setText(txt);
        wordsArea3.setVisible(true);
        scrollPane3.setViewportView(wordsArea3); // Bu satırı ekleyin
        scrollPane3.setVisible(true); // Bu satırı da ekleyin
        goBackButton.setVisible(true);
        frame.setSize(550, 850);
    } 

    private void checkAnswer() {
        String userAnswer = meaningField2.getText().trim().toLowerCase(); // Girişi temizle ve küçük harfe çevir
        String correctAnswer = wordAndMeaning.meaning(getWordIndex).trim().toLowerCase(); // Doğru cevabı temizle ve küçük harfe çevir
        trueOrFalse.setVisible(true);
        if (userAnswer.equals(correctAnswer)) {
            trueOrFalse.setText("CORRECT ANSWER!");
            Color quizGameTrue = new Color(0, 208, 147);
            meaningField2.setBackground(quizGameTrue);
            word.setBackground(quizGameTrue);
            addSound.winSound();
        }
        else {
            trueOrFalse.setText("WRONG! Answer: " + correctAnswer);
            Color quizGameFalse = new Color(209, 0, 47);
            meaningField2.setBackground(quizGameFalse);
            word.setBackground(quizGameFalse);
            addSound.loseSound();
        }

        //wordsForGame.remove(getWordIndex);
    }

    private void makeTextPage() {
        allComponentsWithNoVisibility();
        goBackButton.setVisible(true);
        enterTextNameBelow.setVisible(true);
        textFileName.setVisible(true);
        saveToTextButton.setVisible(true);
    }

    private void saveToText(){
        wordsArea2.setText(takeWord.writeArrayList());
        textName = textFileName.getText();
        textName += ".txt";
        convert = new StringToText();
        convert.convertTextToFile(takeWord.getAllWords(), textName);
    }

    /*private void showDictionaryQuizPage() {
        game = true;
        allComponentsWithNoVisibility();
        addBackgroundImage();
        addComponentsToFrame();
        meaningField2.setBackground(Color.WHITE);
        word.setBackground(Color.WHITE);
        quizButton.setVisible(true);
        if (takeWord.getAllWords().size() > 0) {
            Random rand = new Random();
            getWordIndex = rand.nextInt(takeWord.getAllWords().size());
            meaningField2.setVisible(true);
            meaningField2.setText("\n\n ");
            word.setText("\n\n " + wordAndMeaning.word(getWordIndex));
            word.setVisible(true);
            checkButton.setVisible(true);
            goBackButton.setVisible(true);
            frame.setSize(500, 500); 
            frame.revalidate(); 
            frame.repaint(); 
        } else {
            goBackButton.setVisible(true);
            frame.setSize(500, 500); 
            frame.revalidate(); 
            frame.repaint();
        }
        meaningField2.setText("\n\n ");
    }*/

    public static void main(String[] args) {
        new Gui();
    }
}
