package main.java;

import main.java.windows.PongWelcomeWindow;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class PongMain {
    public static void main(String[] args) {
        centerFrame(PongWelcomeWindow.pongWelcomeWindow);
        PongWelcomeWindow.pongWelcomeWindow.show();
    }


    public static void centerFrame(JFrame frame) {
        Rectangle rectangle = frame.getBounds();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(0, 0, screenSize.width, screenSize.height);

        int newX = screenRectangle.x + (screenRectangle.width - rectangle.width) / 2;
        int newY = screenRectangle.y + (screenRectangle.height - rectangle.height) / 2;

        if (newX < 0) newX = 0;
        if (newY < 0) newY = 0;

        frame.setBounds(newX, newY, rectangle.width, rectangle.height);
    }

    public static void setTextAreaProperties(JTextPane textArea) {
        textArea.setEditable(false);
        textArea.setHighlighter(null);
        textArea.setEnabled(true);
        textArea.setOpaque(false);

        StyledDocument sd = textArea.getStyledDocument();
        SimpleAttributeSet centerAttribute = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
        sd.setParagraphAttributes(0, sd.getLength(), centerAttribute, false);
    }

}
