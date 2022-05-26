import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
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
    static final int WORDCOUNTPERLINE = 12;
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
    //

//    //HIghlight colors
//static final Highlighter.HighlightPainter GREEN = new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN);
//static final Highlighter.HighlightPainter YELLOW = new DefaultHighlighter.DefaultHighlightPainter(Color.yellow);
//static final Highlighter.HighlightPainter RED = new DefaultHighlighter.DefaultHighlightPainter(Color.RED);
//static final Highlighter.HighlightPainter BLUE_BACKGROUND = new DefaultHighlighter.DefaultHighlightPainter(Color.CYAN);
//    //




























    public static void main(String[] args) throws IOException {
        trySetNimbus();
        createAndShowGUI();
        welcome();
    }


    public static void typingProcess(KeyEvent e)
    {
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
                    // True word control after space
                    if(cleanAnsiAndSetColor((String) words.get(latestWordIndex),"").equals(typedWord))
                        correctCharacterCount += typedWord.length() + 1;
                    else{
                        words.set(latestWordIndex, cleanAnsiAndSetColor((String) words.get(latestWordIndex), RED));
                        wrongCharacterCount += cleanAnsiAndSetColor((String) words.get(latestWordIndex),"").length() + 1;
                    }

                    test(currentWordIndex, typedWord = "");
                }
            }

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

// Listening methods.
//    TODO 1-1
    public void keyPressed(KeyEvent e) {
      typingProcess(e);
    }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}


    //Main test method.
    //TODO 1-2
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
//System.out.println(cleanAnsiAndSetColor("─────────────── ", YELLOW) + count + cleanAnsiAndSetColor(" ───────────────", YELLOW));
                CalculationThread.x_view.labelNotice.setText(String.valueOf(count));
                count--;
            }
        }, 0, 1000);
    }

    //TODO 1-3
    static void test(int currentWordIndex, String typedWord) {
        if(isTestActive){
            if(typedWord != null || startControl){
                startControl = true;
//                for(int i=3; i < 7; i++){
//                 System.out.print(String.format("\033[%dA", i));
//                    System.out.println("something something else");
//                   System.out.print("\033[2K");
//                }
            }

            printWords(currentWordIndex, typedWord);
           CalculationThread.x_view.lblTyped.setText("");
           CalculationThread.x_view.lblTyped.setText("\nTyped: " + (typedWord == null ? "" : typedWord));

//            System.out.print("────────────────────────────────────────\n\n");
        }
    }

    //TODO 1 - 4
    //Word coloring and geting method.
    static void printWords(int currentWordIndex, String typedWord){
        //New line
        if(currentWordIndex == 0 && typedWord == null){
            lineCounter += WORDCOUNTPERLINE;
            words = readWordsFromFile(lineCounter);

            words.set(currentWordIndex, cleanAnsiAndSetColor((String) words.get(currentWordIndex), BLUE_BACKGROUND));
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
                File file = new File("ProjectFiles/src/words.txt");
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
            CThread.cleanVariablesAndShowResultScreen(true);
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
    private static void createAndShowGUI() {
         CalculationThread.x_view = new typingGUI();
        CalculationThread.x_view.initApp();
        JFrame x_frame = new JFrame("Let's TEST");
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

