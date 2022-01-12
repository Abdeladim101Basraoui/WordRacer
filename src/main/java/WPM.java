import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.Timer;


public class WPM implements KeyListener {
    static final int WORDCOUNTPERLINE = 12;

    static boolean isTestActive = false;

    static ArrayList<String> wordsFromFile = new ArrayList<>();
    static List words;
    static int lineCounter = 0, latestWordIndex = 0;

    static int currentWordIndex = 0;
    static String typedWord = null;

    static int allTypedCharacterCount=0, correctCharacterCount = 0, wrongCharacterCount = 0;

    static boolean startControl = false;

    static CalculationThread thread = new CalculationThread();
    static int[] threadDatas = new int[3];

    //Colors
    static final String GREEN = "\033[0;32m";
    static final String YELLOW = "\033[0;33m";
    static final String RED = "\033[0;31m";

    static final String BLUE_BACKGROUND = "\033[44m";

    public static void main(String[] args) throws IOException {
        JFrame jf = new JFrame("WPM Typing Scanner");
        jf.setSize(400,400);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.addKeyListener(new WPM());
        final JLabel label = new JLabel("Focus here during type.");
        jf.add(label);

        jf.setVisible(true);

        cleanScreen();
        welcome();
    }

    // Listening methods.
    public void keyPressed(KeyEvent e) {
       char character = e.getKeyChar();

       if(character == KeyEvent.VK_ENTER && !isTestActive)
           countDown();

       if(isTestActive){
           if(character == KeyEvent.VK_SPACE && (typedWord == null ? false : typedWord.length() != 0 ? true : false)){
               currentWordIndex++;
               allTypedCharacterCount++;

               if(currentWordIndex == WORDCOUNTPERLINE)
                   test(currentWordIndex = 0, typedWord = null);
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

           threadDatas[0] = correctCharacterCount;
           threadDatas[1] = wrongCharacterCount;
           threadDatas[2] = allTypedCharacterCount;
       }
    }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}


    private static void welcome() {
        System.out.println(
                cleanAnsiAndSetColor("────────────────────────────────────────────────────────────────\n", YELLOW)+
                "        ╔╗   ╔╗                            ╔╗      ╔╗   ╔╗\n" +
                "        ║║  ╔╝╚╗                           ║║      ║║  ╔╝╚╗\n" +
                "╔═╦══╦══╣║  ╚╗╔╬╦╗╔╦══╗ ╔╗╔╗╔╦══╦╗╔╗ ╔══╦══╣║╔══╦╗╔╣║╔═╩╗╔╬══╦═╗\n" +
                "║╔╣║═╣╔╗║║   ║║╠╣╚╝║║═╣ ║╚╝╚╝║╔╗║╚╝║ ║╔═╣╔╗║║║╔═╣║║║║║╔╗║║║╔╗║╔╝\n" +
                "║║║║═╣╔╗║╚╗  ║╚╣║║║║║═╣ ╚╗╔╗╔╣╚╝║║║║ ║╚═╣╔╗║╚╣╚═╣╚╝║╚╣╔╗║╚╣╚╝║║\n" +
                "╚╝╚══╩╝╚╩═╝  ╚═╩╩╩╩╩══╝  ╚╝╚╝║╔═╩╩╩╝ ╚══╩╝╚╩═╩══╩══╩═╩╝╚╩═╩══╩╝\n" +
                "                             ║║\n" +
                "                             ╚╝ by axd99\n"+
                cleanAnsiAndSetColor("────────────────────────────────────────────────────────────────\n", YELLOW));
        System.out.println("Press \"Enter\" to start.");
    }

    static void countDown() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int count = 3;

            public void run() {
                cleanScreen();

                if (count == 0) {
                    timer.cancel();

                    isTestActive =true;
                    test(0, null);

                    CalculationThread thread= new CalculationThread();
                    CalculationThread.thread = new Thread(thread);
                    CalculationThread.thread.start();
                } else
                    System.out.println(cleanAnsiAndSetColor("─────────────── ", YELLOW) + count + cleanAnsiAndSetColor(" ───────────────", YELLOW));

                count--;
            }
        }, 0, 1000);
    }

    //Main test method.
    static void test(int currentWordIndex, String typedWord) {
       if(isTestActive){
           if(typedWord != null || startControl){
               startControl = true;
               for(int i=3; i < 7; i++){
                   System.out.print(String.format("\033[%dA", i));
                   System.out.print("\033[2K");
               }
           }

           printWords(currentWordIndex, typedWord);
           System.out.println("\nTyped: " + (typedWord == null ? "" : typedWord));

           System.out.print("────────────────────────────────────────\n\n");
       }
    }
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

                if(typedWord.length() != 0){
                    if(currentWord.startsWith(typedWord)) {
                        if(currentWord.length() == typedWord.length())
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
        System.out.println(showingWords.substring(1, showingWords.length() - 1));
    }

    static ArrayList<String> readWordsFromFile(int lineCounter){
        if(wordsFromFile.size() == 0){
            try {
                File file = new File("words.txt");
                Scanner words = new Scanner(file);

                while (words.hasNext()) {
                    wordsFromFile.add(words.next().toLowerCase(Locale.ROOT));
                }

            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        ArrayList<String> words = new ArrayList<>(wordsFromFile.subList(lineCounter - WORDCOUNTPERLINE, (lineCounter - WORDCOUNTPERLINE) + WORDCOUNTPERLINE * 2));
        words.set(WORDCOUNTPERLINE-1, words.get(WORDCOUNTPERLINE-1) + "\n");

        return words;
    }

    static String cleanAnsiAndSetColor(String word, String color){
        if(color.equals(""))
            return word.replaceAll("\u001B\\[[\\d;]*[^\\d;]","");
        else
            return color + word.replaceAll("\u001B\\[[\\d;]*[^\\d;]","") + "\033[0m";
    }

    static void cleanScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

class CalculationThread extends Thread{
    static Thread thread;
    int aCC=0, cCC=0, wCC=0, grossWPM = 0, netWPM = 0, duration = 59;
    float accuracy = 0f;

    @Override
    public void run() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                cCC = WPM.threadDatas[0];
                wCC = WPM.threadDatas[1];
                aCC = WPM.threadDatas[2];

                if(aCC != 0){
                    grossWPM =Math.round((cCC+wCC) / 5f / ((60 - duration)/60f));
                    netWPM = grossWPM - Math.round((wCC / 5f / ((60 - duration)/60f)));
                    accuracy = (float) cCC / aCC * 100;

                }

                System.out.print(String.format("\033[%dA", 1));
                System.out.print("\033[2K");

                if (duration == 0) {
                    timer.cancel();
                    cleanVariables();
                    thread.stop();
                    resultScreen();
                }else
                    System.out.print("Time: "+  duration + "s | Net WPS: " + netWPM + " | Accuracy: " + String.format("%.2f", accuracy) + "%\n");

                duration--;
            }
        }, 0, 1000);
    }

    void cleanVariables(){
        WPM.isTestActive = false;
        WPM.lineCounter = 0;
        WPM.latestWordIndex = 0;
        WPM.currentWordIndex = 0;
        WPM.typedWord = null;
        WPM.allTypedCharacterCount = 0;
        WPM.correctCharacterCount = 0;
        WPM.wrongCharacterCount = 0;
        WPM.startControl = false;
    }

    void resultScreen(){
        WPM.cleanScreen();

        System.out.println(WPM.cleanAnsiAndSetColor("───────────────", WPM.YELLOW) + " Results " + WPM.cleanAnsiAndSetColor("─────────────── ", WPM.YELLOW));
        System.out.println("Net WPM: " + WPM.cleanAnsiAndSetColor(String.valueOf(netWPM), WPM.YELLOW) + " | Gross WPM: " + WPM.cleanAnsiAndSetColor(String.valueOf(grossWPM), WPM.YELLOW));
        System.out.println("────────");
        System.out.println("Accuracy: " + WPM.cleanAnsiAndSetColor(String.format("%.2f", accuracy) + "%", WPM.YELLOW));
        System.out.println("─────────");
        System.out.println("All entrys: " + aCC + " | Correct: " + WPM.cleanAnsiAndSetColor(String.valueOf(cCC), WPM.GREEN) + " | Wrong: " + WPM.cleanAnsiAndSetColor(String.valueOf(wCC), WPM.RED));
        System.out.println("\nPress \"Enter\" for restart.");
    }
}