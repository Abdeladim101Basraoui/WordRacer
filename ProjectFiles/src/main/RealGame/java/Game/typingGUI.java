package Game;
import lvlpackages.MatchingFrame;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Logger;

public class typingGUI extends JFrame{
    public JSplitPane splitterWorking;
    public JScrollPane scrollPaneTyping;
    public JTextArea textAreaTyping;
    public JLabel labelYou;
    public JProgressBar progressBarYou;
    public JLabel labelHeaderTime;
    public JLabel labelTimePassed;
    public JLabel labelHeaderCharsTyped;
    public JLabel labelCharsTyped;
    public JLabel labelHeaderWordsTyped;
    public JLabel labelcorrect;
    public JLabel labelHeaderCharTypos;
    public JLabel labelWrong;
    public JLabel labelHeaderWordTypos;
    public JLabel labelWPM;
    public JButton buttonCancelTest;
    public JPanel panelCardContent;
    public JPanel panelWorkingContent;
    //TODO: used to take the top lvl prop
    public  JPanel panelTopLevel;
    public JLabel labelNotice;
    public JLabel lblTyped;
    private JLabel lblwrite;
    public  JLabel lblSQLStatus;
    //
    public int lvl;
    public  int getLvl() {
        return lvl;
    }
    public  void setLvl(int lvl) {
this.lvl = lvl;


    }

    private static final Object ABORT_KEY = new Object();


    public typingGUI() {
        textAreaTyping.addKeyListener(new KeyAdapter() {


            @Override
            public void keyPressed(KeyEvent e) {
                WPM.typingProcess(e);
            }

        });

        textAreaTyping.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {}
                String highlightThis;
                if( Character.isLetter(e.getKeyChar()) || Character.isDigit(e.getKeyChar())){
                    highlightThis = WPM.typedWord ;//+ Character.toString(e.getKeyChar());
                }
                // Ignore all characters that are not letters or numbers
                else{
                    highlightThis = WPM.typedWord;
                }
                highlightMatches(highlightThis);
            }
        });



        buttonCancelTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MatchingFrame fm=new MatchingFrame();
                    fm.setVisible(true);
                    WPM.x_frame.dispose();
            }
        });
    }



    public void highlightMatches(String highlightThis){
        // Try to highlight matches of keyword search
        try {

            String text = textAreaTyping.getText();
            // Initialize our cyan highlighter object
            Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.cyan);

            // If text exists and there is something to highlight...
            if(WPM.typedWord!=null || highlightThis!=null) {
                if (!WPM.typedWord.equals("") || !highlightThis.equals("")) {
                    // Where do we begin the highlight?
                    int offset = text.indexOf(highlightThis);
                    // How far does the highlight go?
                    int length = highlightThis.length();
                    // Since the search string has changed, remove old highlights
                    textAreaTyping.getHighlighter().removeAllHighlights();
                    // For all instances where we can highlight
//                    while (offset != -1) {
                    // Highlight from beginning to end with cyan
                    textAreaTyping.getHighlighter().addHighlight(offset, offset + length, painter);
                    // Get the next instance
                    offset = text.indexOf(highlightThis, offset + 1);
//                    }
//                // After highlights done, update count of highlights
//                occuranceText.setText("Number of Occurences: " +
//                        new JTextArea().getHighlighter().getHighlights().length);
                }
            }
            // If there is nothing in the text field, remove highlights & reset count
            else{
                textAreaTyping.getHighlighter().removeAllHighlights();
//                occuranceText.setText("Number of Occurences: 0");
            }
            // Unless the location of a highlight is invalid
        } catch (BadLocationException ex) {
            System.out.append(ex.toString());
        }
    }
    public void initApp() {
        // Actions
        panelWorkingContent.getActionMap().put(ABORT_KEY, new ActionAbort());
        progressBarYou.setMaximum(100);
        progressBarYou.setMinimum(0);
        progressBarYou.setStringPainted(true);
    }

    public static class ActionAbort extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[] options = {"OK","Cancel"};
            Component source = (Component) e.getSource();
            Component rootContainer = SwingUtilities.getRoot(source);
            int selectOpt = JOptionPane.showOptionDialog(rootContainer, "do yout want to cancel the test","Cancelling",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        }
    }

}
