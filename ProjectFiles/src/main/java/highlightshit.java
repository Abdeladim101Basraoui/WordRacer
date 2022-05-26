public class highlightshit {

    /**
     * Support for keyword searching with highlights that match search test.
     * Loosely based on: http://stackoverflow.com/a/6531715
     * @param evt - The key press & release.
     */
//    private void searchFieldKeyTyped(java.awt.event.KeyEvent evt) {
//        //GEN-FIRST:event_searchFieldKeyTyped
//        // The string we are trying to highlight
//        String highlightThis;
//
//        if( Character.isLetter(evt.getKeyChar()) || Character.isDigit(evt.getKeyChar())){
//            highlightThis = searchField.getText() + Character.toString(evt.getKeyChar());
//        }
//        // Ignore all characters that are not letters or numbers
//        else{
//            highlightThis = searchField.getText();
//        }
//        highlightMatches(highlightThis);
//    }

    /**
     * This function acts as the main logic for keyword searching and highlighting.
     * @param highlightThis - The string to highlight within the textArea
     */
//    private void highlightMatches(String highlightThis){
//        // Try to highlight matches of keyword search
//        try {
//            // The length of the amount of text in the text window
//            int textLength = textAreaTyping.getDocument().getLength();
//            // Store the entire contents of the text window in one string
//            String text = textAreaTyping.getDocument().getText(0, textLength);
//            // Initialize our cyan highlighter object
//            Highlighter.HighlightPainter painter = new
//                    DefaultHighlighter.DefaultHighlightPainter(Color.cyan);
//
//            // If text exists and there is something to highlight...
//            if(! searchField.getText().equals("") ||! highlightThis.equals("") ){
//                // Where do we begin the highlight?
//                int offset = text.indexOf(highlightThis);
//                // How far does the highlight go?
//                int length = highlightThis.length();
//                // Since the search string has changed, remove old highlights
//                textAreaTyping.getHighlighter().removeAllHighlights();
//                // For all instances where we can highlight
//                while (offset != -1){
//                    // Highlight from beginning to end with cyan
//                    textAreaTyping.getHighlighter().addHighlight(offset, offset + length, painter);
//                    // Get the next instance
//                    offset = text.indexOf(highlightThis, offset+1);
//                }
//                // After highlights done, update count of highlights
//                occuranceText.setText("Number of Occurences: " +
//                        textAreaTyping.getHighlighter().getHighlights().length);
//            }
//            // If there is nothing in the text field, remove highlights & reset count
//            else{
//                textAreaTyping.getHighlighter().removeAllHighlights();
//                occuranceText.setText("Number of Occurences: 0");
//            }
//            // Unless the location of a highlight is invalid
//        } catch (BadLocationException ex) {
//            textAreaTyping.append(ex.toString());
//        }
//    }
}
