package main.java.windows;

import main.java.PongMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PongWelcomeWindow extends JFrame implements ActionListener, MouseListener {

    public static PongWelcomeWindow pongWelcomeWindow = new PongWelcomeWindow();

    private JPanel totalContentPane = null;
    private JTextPane logo = null;
    private JButton play = null;

    public PongWelcomeWindow() {
        try {
            setName("Pong");
            setTitle("Pong");
            setResizable(false);
            setSize(616, 638);
            setContentPane(getPanelContentPane());

            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            play.addActionListener(this);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private JPanel getPanelContentPane() {
        try {
            totalContentPane = new JPanel();
            totalContentPane.setLayout(null);
            totalContentPane.setName("ContentPanel");
            totalContentPane.setPreferredSize(new Dimension(616, 638));

            totalContentPane.add(getLogo(), getLogo().getName());
            totalContentPane.add(getPlay(), getPlay().getName());

        } catch (Throwable t) {}
        return totalContentPane;
    }

    private JTextPane getLogo() {
        if (logo == null) {
            try {
                logo = new JTextPane();
                logo.setName("Logo");
                logo.setText("P  O  N  G");
                logo.setFont(new Font(Font.DIALOG, Font.PLAIN, 30));
                logo.setForeground(new Color(255, 0, 0));
                logo.setEditable(false);
                logo.setBounds(200, 100, 200, 100);
                PongMain.setTextAreaProperties(logo);

            } catch (Throwable t) {}
        }
        return logo;
    }

    private JButton getPlay() {
        if (play == null) {
            try {
                play = new JButton();
                play.setName("Play");
                play.setText("Click to Play!");
                play.setBounds(200, 275, 200, 50);

            } catch (Throwable t) {}
        }
        return play;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getPlay()) {
            PongMain.centerFrame(PongGameWindow.pongGameWindow);
            PongGameWindow.pongGameWindow.show();
            pongWelcomeWindow.dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
