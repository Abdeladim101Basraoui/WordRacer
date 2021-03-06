package Game;
import elasri.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.Timer;


public class WPM implements KeyListener {


    /***The GUI - define the look and feel type {javax.swing.plat.nimbus.NimbuslookAndfell}
     */
    private static final String NIMBUS = "Nimbus"; // NON-NLS

    //Total word count per line. Customizable (e.g 15, 10, 20, ...) must be WORDCOUNTPERLINE > 0.
    static final int  WORDCOUNTPERLINE  = 12;

    //Test duration.
    static  int duration = 59;
    static CalculationThread CThread = new CalculationThread();
    static boolean isTestActive = false;

    //words of the test
    static ArrayList<String> wordsFromFile = new ArrayList<>();
    static List words;
    static int lineCounter = 0, latestWordIndex = 0;
    static int currentWordIndex = 0;
    static String typedWord = null;

    //all the typed words,  //the correct words      //the wrong words
    static int allTypedCharacterCount=0, correctCharacterCount = 0, wrongCharacterCount = 0;
    static boolean startControl = false;
    static CalculationThread thread = new CalculationThread();
    static int[] threadDatas = new int[3];

    // shell Colors
    static final String GREEN = "\033[0;32m";
    static final String YELLOW = "\033[0;33m";
    static final String RED = "\033[0;31m";
    static final String BLUE_BACKGROUND = "\033[44m";


    public WPM(int level)
    {
        trySetNimbus();
        createAndShowGUI(level);
        welcome();

    }

    public static void typingProcess(KeyEvent e) {
        char character = e.getKeyChar();

        if(character == KeyEvent.VK_ENTER && !isTestActive)
            countDown();

        if(isTestActive){
            if(character == KeyEvent.VK_SPACE && (typedWord == null ? false : typedWord.length() != 0 ? true : false)){
                currentWordIndex++;
                allTypedCharacterCount++;

                if(currentWordIndex == WORDCOUNTPERLINE){
                    latestWordIndex = 0;
                    test(currentWordIndex = 0, typedWord = null);
                }
                else{
                    if(words.get(latestWordIndex).equals(typedWord))
                        correctCharacterCount += typedWord.length() + 1;
                    else{
                        // words.set(latestWordIndex, cleanAnsiAndSetColor((String) words.get(latestWordIndex), RED));
                        wrongCharacterCount += ((String)words.get(latestWordIndex)).length() + 1;
                    }

                    test(currentWordIndex, typedWord = "");
                }
            }
//TODO importtant
            if(character == KeyEvent.VK_BACK_SPACE && (typedWord == null ? false : typedWord.length() != 0 ? true : false))
                test(currentWordIndex, typedWord = typedWord.substring(0, typedWord.length() - 1));
            if(Character.isAlphabetic(character)){
                allTypedCharacterCount++;
                typedWord = typedWord == null ? "" : typedWord;
                typedWord += Character.toString(character);
                test(currentWordIndex, typedWord);
            }
//TODO: the count of the typed values
            threadDatas[0] = correctCharacterCount;
            threadDatas[1] = wrongCharacterCount;
            threadDatas[2] = allTypedCharacterCount;
        }
    }
    public void keyPressed(KeyEvent e) {
        typingProcess(e);
    }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
    static void countDown() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int count = 3;
            public void run() {
                //cleanScreen();
                if (count == 0) {
                    timer.cancel();
                    isTestActive = true;
                    test(0, null);
                    CalculationThread.thread = new Thread(CThread);
                    CalculationThread.thread.start();
                } else
//System.out.println(cleanAnsiAndSetColor("????????????????????????????????????????????? ", YELLOW) + count + cleanAnsiAndSetColor(" ?????????????????????????????????????????????", YELLOW));
                    CalculationThread.x_view.labelNotice.setText(String.valueOf(count));
                count--;
            }
        }, 0, 1000);
    }
    static void test(int currentWordIndex, String typedWord) {
        if(isTestActive){
            if(typedWord != null || startControl)
                startControl = true;

            printWords(currentWordIndex, typedWord);
            CalculationThread.x_view.lblTyped.setText("");
            CalculationThread.x_view.lblTyped.setText("\nTyped: " + (typedWord == null ? "" : typedWord));
        }
    }
    static void printWords(int currentWordIndex, String typedWord){
        if(currentWordIndex == 0 && typedWord == null){
            lineCounter += WORDCOUNTPERLINE;
            //TODO: Read the text and insert it in LIST
            words = readWordsFromFile(lineCounter);

            //words.set(currentWordIndex, cleanAnsiAndSetColor((String) words.get(currentWordIndex), BLUE_BACKGROUND));
            // CalculationThread.x_view.textAreaTyping
        }
        //Same line
        else {
            //Still same word
            if(latestWordIndex == currentWordIndex){
                String currentWord = cleanAnsiAndSetColor((String) words.get(currentWordIndex),"");
                if(currentWordIndex == WORDCOUNTPERLINE - 1)
                    currentWord = currentWord.replace("\n", "");

                if(typedWord.length() != 0){
                    if(currentWord.startsWith(typedWord)) {
                        if(currentWord.equals(typedWord))
                            words.set(currentWordIndex, cleanAnsiAndSetColor((String) words.get(currentWordIndex), GREEN));
                        else
                            words.set(currentWordIndex, cleanAnsiAndSetColor((String) words.get(currentWordIndex), YELLOW));
                    }else{
                        words.set(currentWordIndex, cleanAnsiAndSetColor((String) words.get(currentWordIndex), RED));
                    }
                }
                else
                    words.set(currentWordIndex, cleanAnsiAndSetColor((String) words.get(currentWordIndex), BLUE_BACKGROUND));
            }
            //New word
            else {
                latestWordIndex = currentWordIndex;
                words.set(currentWordIndex, cleanAnsiAndSetColor((String) words.get(currentWordIndex), BLUE_BACKGROUND));
            }
        }

        String showingWords= words.toString().replace(",", "");
        //todo:show text
        // System.out.println(showingWords.substring(1, showingWords.length() - 1));
        CalculationThread.x_view.textAreaTyping.setText(showingWords.substring(1,showingWords.length()-1));
    }



    //TODO read the word to display
    static ArrayList<String> readWordsFromFile(int lineCounter){
        if(wordsFromFile.size() == 0){
            try {
//ProjectFiles/src/main/RealGame/java/Game/lvl
                File file = new File("ProjectFiles/src/RealGame/Game/lvl"+ CalculationThread.x_view.getLvl()+".txt");
                Scanner words = new Scanner(file);

                while (words.hasNext()) {
                    wordsFromFile.add(words.next().toLowerCase(Locale.ROOT));
                }

            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }

        ArrayList<String> words = null;
        try {
            words = new ArrayList<>(wordsFromFile.subList(lineCounter - WORDCOUNTPERLINE, (lineCounter - WORDCOUNTPERLINE) + WORDCOUNTPERLINE * 2));
            words.set(WORDCOUNTPERLINE-1, words.get(WORDCOUNTPERLINE-1) + "\n");


        }catch (Exception e){
            CThread.cleanVariablesAndShowResultScreen(true, Login.IDPlayer);
            System.exit(1);
        }
        return words;
    }





//static void cleanScreen() {
//        System.out.print("\033[H\033[2J");
//        System.out.flush();
//    }

    /*** COLORISE THE TEXT
     * @param word
     * @param color
     * @return
     */
    static String cleanAnsiAndSetColor(String word, String color){
        //if(color.equals(""))
        // return word.replaceAll("\u001B\\[[\\d;]*[^\\d;]","");
        //else
        //return color + word.replaceAll("\u001B\\[[\\d;]*[^\\d;]","") + "\033[0m";
        return word;
    }

    /*** Highlite colorise the text
     * @param word
     * @param color
     * @return
     */
//    public static String cleanAnsiAndSetColor(String word, Highlighter.HighlightPainter color){
//        try {
//        if (color.equals(null))
//        {
//            //the highlight of the text
//            Highlighter high =  CalculationThread.x_view.textAreaExampleText.getHighlighter();
//            high.removeAllHighlights();
//
//           high.addHighlight(0,word.length(), DefaultHighlighter.DefaultPainter);
//           return "";
//        } else
//            {
//            return "else";
//            }
//
//        }catch (BadLocationException ex)
//        {
//            System.out.println(ex.getMessage());
//        }
//
//        return word;
//    }
//

    /**
     * TODO:the GUI
     */
    public static JFrame x_frame;
    private static void createAndShowGUI(int lvl) {
        CalculationThread.x_view = new typingGUI();
        CalculationThread.x_view.setLvl(lvl);

        CalculationThread.x_view.initApp();
        x_frame = new JFrame("Let's TEST");
        x_frame.setContentPane(CalculationThread.x_view.panelTopLevel);
        x_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        x_frame.pack();

        // Now centering the frame at the screen, resizing if necessary
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        int height = x_frame.getHeight();
        int width = x_frame.getWidth();
        if (height > screenHeight) {
            x_frame.setSize(width, screenHeight);
            x_frame.pack();
            height = screenHeight;
        }
        if (width > screenWidth) {
            x_frame.setSize(screenWidth, height);
            x_frame.pack();
            width = screenWidth;
        }
        x_frame.setLocation((screenWidth - width) / 2, (screenHeight - height) / 2);
        x_frame.setVisible(true);
    }
    public static void trySetNimbus() {

        //the UIManager control a group of controls
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if (NIMBUS.equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (UnsupportedLookAndFeelException e) {
                    try {
                        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                    } catch (Exception e1) {
                        System.err.println(e1.getMessage());
                        System.exit(1);
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    System.exit(1);
                }
                break;
            }
        }
    }








    /*** The Console GUI
     */
//welcome console
    private static void welcome() {
//    System.out.println(cleanAnsiAndSetColor("Always stay focused on the frame for typing.\n", YELLOW));
        CalculationThread.x_view.labelNotice.setText("Always stay focused on the frame for typing");
        CalculationThread.x_view.labelNotice.setText("Press \"Enter\" to start.");
//    System.out.println("Press \"Enter\" to start.");
    }
    /***
     * END
     */
}