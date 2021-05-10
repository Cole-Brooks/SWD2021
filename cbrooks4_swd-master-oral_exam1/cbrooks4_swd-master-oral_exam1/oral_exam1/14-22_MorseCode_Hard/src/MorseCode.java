// Fig. 26.9: TextFieldFrame.java
// JTextFields and JPasswordFields.
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.util.HashMap;
import java.util.Map;

public class MorseCode extends JFrame
{
    /**
     * English Input: the text field where you can input english to be translated to morse code
     */
    private final JTextArea englishInput; // text field with set size
    /**
     * morseCodeInput: the text field where you can input morse code to translate it to english
     */
    private final JTextArea morseCodeInput; // text field with text

    /**
     * eToMc: hashmap that translates from english to morse code
     */
    public static HashMap<Character, String> eToMc = new HashMap<Character, String>();
    /**
     * mcToE: hashmap that translates from morse code to english
     */
    public static HashMap<String, Character> mcToE = new HashMap<String,Character>();
    static{
        eToMc.put('a',".-");
        eToMc.put('b', "-...");
        eToMc.put('c', "-.-.");
        eToMc.put('d', "-..");
        eToMc.put('e', ".");
        eToMc.put('f', "..-.");
        eToMc.put('g', "--.");
        eToMc.put('h', "....");
        eToMc.put('i', "..");
        eToMc.put('j', ".---");
        eToMc.put('k', "-.-");
        eToMc.put('l', ".-..");
        eToMc.put('m', "--");
        eToMc.put('n', "-.");
        eToMc.put('o', "---");
        eToMc.put('p', ".--.");
        eToMc.put('q', "--.-");
        eToMc.put('r', ".-.");
        eToMc.put('s', "...");
        eToMc.put('t', "-");
        eToMc.put('u', "..-");
        eToMc.put('v', "...-");
        eToMc.put('w', ".--");
        eToMc.put('x', "-..-");
        eToMc.put('y', "-.--");
        eToMc.put('z', "--..");
        // digits
        eToMc.put('0',"-----");
        eToMc.put('1',".----");
        eToMc.put('2',"..---");
        eToMc.put('3',"...--");
        eToMc.put('4',"....-");
        eToMc.put('5',".....");
        eToMc.put('6',"-....");
        eToMc.put('7',"--...");
        eToMc.put('8',"---..");
        eToMc.put('9',"----.");
        // space
        eToMc.put(' ', " ");

        // create mcToE map
        for(Map.Entry<Character,String> entry : eToMc.entrySet()){
            mcToE.put(entry.getValue(), entry.getKey());
        }
    }

    /**
     * englishContent: keeps track of the english content in the english text box
     */
    private String englishContent;
    /**
     * morseContent: keeps track of the morse code content in the morse code text box
     */
    private String morseContent;
    /**
     * englishFocused: keeps track of which text box has focus so that we know which box needs to be updated when text
     *                 is updated
     */
    private boolean englishFocused; // keep track of which text area to update

    // MorseCode constructor adds JTextFields to JFrame

    /**
     * MorseCode:
     * constructor for the MorseCode class.
     */
    public MorseCode()
    {
        super("Morse Code Translater");
        TextFieldHandler englishHandler = new TextFieldHandler(true);
        TextFieldHandler morseHandler = new TextFieldHandler(false);
        setLayout(new FlowLayout());

        // construct textfield with 10 columns
        englishInput = new JTextArea("Enter English Here", 5,40);
        englishInput.setEditable(true);
        englishInput.setLineWrap(true);
        englishInput.setWrapStyleWord(true);
        Document englishDoc = englishInput.getDocument();
        englishDoc.addDocumentListener(englishHandler);
        add(englishInput); // add textField1 to JFrame

        // construct textfield with default text
        morseCodeInput = new JTextArea("Enter Morse Code Here",5,40);
        morseCodeInput.setEditable(true);
        morseCodeInput.setLineWrap(true);
        morseCodeInput.setWrapStyleWord(true);
        Document morseCodeDoc = morseCodeInput.getDocument();
        morseCodeDoc.addDocumentListener(morseHandler);
        add(morseCodeInput); // add textField2 to JFrame

        // Add focus listeners
        englishInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(englishInput.getText().equals("Enter English Here"))
                englishInput.setText("");
                englishFocused = true;
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(englishInput.getText().equals("")){
                    englishInput.setText("Enter English Here");
                }
                englishFocused = false;
            }
        });
        morseCodeInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(morseCodeInput.getText().equals("Enter Morse Code Here")){
                    morseCodeInput.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(morseCodeInput.getText().equals("")){
                    morseCodeInput.setText("Enter Morse Code Here");
                }
            }
        });

    } // end MorseCode constructor
    // private inner class for event handling
    private class TextFieldHandler implements ActionListener, DocumentListener
    {
        /**
         * TextFieldHandler::isEnglishField - keeps track of which field the TextFieldHandler is responsible for.
         *                                    if true, then english field. Else, morse code field.
         */
        private final boolean isEnglishField;
        // Constructor so that we know which text field we're listening to

        /**
         * TextFieldHandler
         * @param isEnglishField    boolean to keep track of which field this TextFieldHandler is in charge of
         */
        public TextFieldHandler(boolean isEnglishField){
            this.isEnglishField = isEnglishField;
        }

        // Implement DocumentListener abstract methods

        /**
         * changedUpdated: an overridden function which doesn't actually do anything in our context.
         * @param e DocumentEvent which fired changedUpdated
         */
        @Override
        public void changedUpdate(DocumentEvent e) {
            // Do nothing override
        }

        /**
         * insertUpdate: an overridden function which is called whenever text is added to a text area
         * @param e the event which fired this function
         */
        @Override
        public void insertUpdate(DocumentEvent e) {
            if(isEnglishField){
                englishUpdated();
            }
            else{
                morseUpdated();
            }
        }

        /**
         * removeUpdate: an overridden function which is called whenever text is removed from a text area
         * @param e the event which fired this function
         */
        @Override
        public void removeUpdate(DocumentEvent e) {
            insertUpdate(e); // whenever things are removed just run updated content again and it will translate
                             // whatever is left in the text area
        }
        // process text field events

        /**
         * actionPerformed: an overridden function which is called whenever an action is performed in the text areas.
         *                  it allows for the text boxes to contain the data input from the user.
         * @param event event which fired this function
         */
        @Override
        public void actionPerformed(ActionEvent event)
        {
            String string = "";
            // user pressed Enter in JTextField textField1
            if (event.getSource() == englishInput)
                string = String.format("textField1: %s",
                        event.getActionCommand());

                // user pressed Enter in JTextField textField2
            else if (event.getSource() == morseCodeInput)
                string = String.format("textField2: %s",
                        event.getActionCommand());

            // display JTextField content
            JOptionPane.showMessageDialog(null, string);
        }
    } // end private inner class TextFieldHandler

    /**
     * getEnglishContent:
     * @return String contents in the english text area
     */
    public String getEnglishContent() {
        return englishContent;
    }

    /**
     * setEnglishContent:
     * @param englishContent    new string to set the english text area to.
     */
    public void setEnglishContent(String englishContent) {
        this.englishContent = englishContent;
    }

    /**
     * getMorseContent:
     * @return String contents in the morse code text area
     */
    public String getMorseContent() {
        return morseContent;
    }

    /**
     * setMorseContent:
     * @param morseContent  new string to set the morse code text area to
     */
    public void setMorseContent(String morseContent) {
        this.morseContent = morseContent;
    }

    /**
     * englishUpdated:  called whenever the english field has text removed or added to it.  Translates english content into morse codes
     *                  and puts that translation into the morse code text area.
     */
    public void englishUpdated(){
        if(englishFocused) {
            Runnable doAssist = new Runnable() {
                @Override
                public void run() {
                    boolean isValid = true;
                    StringBuffer sb = new StringBuffer();
                    // translate focused area to non-focused area

                    String englishContent = englishInput.getText();
                    for (int i = 0; i < englishContent.length(); i++) {
                        if (eToMc.get(Character.toLowerCase(englishContent.charAt(i))) == null) {
                            isValid = false;
                        }
                        sb.append(eToMc.get(Character.toLowerCase(englishContent.charAt(i))));
                        sb.append(" ");
                    }

                    if (isValid) {
                        morseCodeInput.setText(sb.toString());
                    } else {
                        morseCodeInput.setText("Invalid English Entry");
                    }
                }
            };
            SwingUtilities.invokeLater(doAssist);
        }
    }

    /**
     * morseUpdated: called whenever the morse code text area has text removed or added to it.  Translates the morse code content into english
     *               and sets the translation as the text in the english text area.
     */
    public void morseUpdated(){
        if(!englishFocused) {
            Runnable doAssist = new Runnable() {
                @Override
                public void run() {
                    boolean isValid = true;
                    StringBuffer newContent = new StringBuffer();
                    StringBuffer morseChar = new StringBuffer();
                    int lastSpace = -1;
                    // translate focused area to non-focused area
                    
                    // Converting from morse to english is a little more cumbersome...
                    String morseContent = morseCodeInput.getText();
                    for (int i = 0; i < morseContent.length(); i++) {
                        // if the character is a space we can evaluate the previous letter and add it to the english content
                        if(morseContent.charAt(i) == ' ') {
                            if (lastSpace + 1 == i) {
                                newContent.append(" ");
                                lastSpace = i;
                            }
                            else {
                                Character buffer = mcToE.get((morseContent.substring(lastSpace + 1, i)).toLowerCase());
                                if (buffer == null) {
                                    isValid = false;
                                    i = morseContent.length();
                                } else {
                                    newContent.append(buffer);
                                    lastSpace = i;
                                }
                            }
                        }
                    }

                    if (isValid) {
                        englishInput.setText(newContent.toString());
                    } else {
                        englishInput.setText("Invalid Morse Entry");
                    }
                }
            };
            SwingUtilities.invokeLater(doAssist);
        }
    }
} // end class TextFieldFrame
