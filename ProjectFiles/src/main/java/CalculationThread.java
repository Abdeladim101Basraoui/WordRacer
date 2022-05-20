import java.util.Timer;
import java.util.TimerTask;

class CalculationThread extends Thread{

    public static typingGUI x_view;
    static Thread thread;
    int aCC=0, cCC=0, wCC=0, grossWPM = 0, netWPM = 0;
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
                    grossWPM =Math.round((cCC+wCC) / 5f / ((60 - WPM.duration)/60f));
                    netWPM = grossWPM - Math.round((wCC / 5f / ((60 - WPM.duration)/60f)));
                    accuracy = (float) cCC / aCC * 100;

                }

                System.out.print(String.format("\033[%dA", 1));
                System.out.print("\033[2K");

                if (WPM.duration == 0) {
                    timer.cancel();
                    thread.interrupt();
                    cleanVariablesAndShowResultScreen(false);
                }else
                x_view.labelTimePassed.setText(String.valueOf(WPM.duration)+"s");
                x_view.labelCharsTyped.setText(String.valueOf(aCC));
                x_view.labelWPM.setText(String.valueOf(netWPM));
                x_view.labelcorrect.setText(String.valueOf(cCC));
                x_view.labelWrong.setText(String.valueOf(wCC));
                x_view.progressBarYou.setValue( (int)accuracy);
//                    System.out.print("Time: "+  WPM.duration + "s | Net WPS: " + netWPM + " | Accuracy: " + String.format("%.2f", accuracy) + "%\n");

                WPM.duration--;
            }
        }, 0, 1000);
    }

    void cleanVariablesAndShowResultScreen(boolean isEndOfWords){
        WPM.isTestActive = false;
        WPM.duration = 59;
        WPM.lineCounter = 0;
        WPM.latestWordIndex = 0;
        WPM.currentWordIndex = 0;
        WPM.typedWord = null;
        WPM.allTypedCharacterCount = 0;
        WPM.correctCharacterCount = 0;
        WPM.wrongCharacterCount = 0;
        WPM.startControl = false;

        //WPM.cleanScreen();

        System.out.println(WPM.cleanAnsiAndSetColor("───────────────", WPM.YELLOW) + " Results " + WPM.cleanAnsiAndSetColor("─────────────── ", WPM.YELLOW));
        System.out.println("Net WPM: " + WPM.cleanAnsiAndSetColor(String.valueOf(netWPM), WPM.YELLOW) + " | Gross WPM: " + WPM.cleanAnsiAndSetColor(String.valueOf(grossWPM), WPM.YELLOW));
        System.out.println("────────");
        System.out.println("Accuracy: " + WPM.cleanAnsiAndSetColor(String.format("%.2f", accuracy) + "%", WPM.YELLOW));
        System.out.println("─────────");
        System.out.println("All entrys: " + aCC + " | Correct: " + WPM.cleanAnsiAndSetColor(String.valueOf(cCC), WPM.GREEN) + " | Wrong: " + WPM.cleanAnsiAndSetColor(String.valueOf(wCC), WPM.RED));

        if(!isEndOfWords)
//            System.out.println("\nPress \"Enter\" for restart.");
            x_view.labelNotice.setText("Press \"Enter\" for restart.");
    }
}
