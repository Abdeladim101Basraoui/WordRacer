//import javax.swing.text.Highlighter;
//import javax.swing.text.DefaultHighlighter;
//import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
//import javax .swing.text.BadLocationException;
//import java.awt.color;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class typingGUI extends Component{
    public JSplitPane splitterWorking;
    public JScrollPane scrollPaneExample;
    public JTextArea textAreaExampleText;
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
    public JLabel labelStatus;
    public JPanel panelCardContent;
    public JPanel panelWorkingContent;
    //TODO: used to take the top lvl prop
    public  JPanel panelTopLevel;
    public JLabel labelNotice;
    //

    private static final Object ABORT_KEY = new Object();

    public typingGUI() {

        buttonCancelTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
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
