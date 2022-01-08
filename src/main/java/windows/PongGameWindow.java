package main.java.windows;

import main.java.GameUpdateLoop;
import main.java.PongMain;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class PongGameWindow extends JFrame implements ActionListener, MouseListener, KeyListener {

    public static PongGameWindow pongGameWindow = new PongGameWindow();

    Set<Integer> player1Keys = new HashSet<>();
    Set<Integer> player2Keys = new HashSet<>();

    private JPanel totalContentPane = null;
    public JLabel user1Paddle = null;
    public JLabel user2Paddle = null;
    public JLabel ball = null;

    public JTextPane scoreAlert = null;
    public JTextPane score = null;
    public JTextPane startAlert = null;


    public PongGameWindow() {
        try {
            setName("Pong");
            setTitle("Pong");
            setResizable(false);
            setSize(616, 638);
            setContentPane(getPanelContentPane());

            addKeyListener(this);

            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            GameUpdateLoop.gameUpdateLoop.startGameLoop();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private JPanel getPanelContentPane() {
        totalContentPane = new JPanel();
        totalContentPane.setLayout(null);
        totalContentPane.setName("ContentPanel");
        totalContentPane.setPreferredSize(new Dimension(616, 638));

        totalContentPane.add(getUser1Paddle(), getUser1Paddle().getName());
        totalContentPane.add(getUser2Paddle(), getUser2Paddle().getName());
        totalContentPane.add(getBall(), getBall().getName());
        totalContentPane.add(getScore(), getScore().getName());
        totalContentPane.add(getScoreAlert(), getScoreAlert().getName());
        totalContentPane.add(getStartAlert(), getStartAlert().getName());

        return totalContentPane;
    }

    private JLabel getUser1Paddle() {
        if (user1Paddle == null) {
            try {
                BufferedImage paddle = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("main/resources/paddle.png")));
                Image paddleScaled = paddle.getScaledInstance(10, 100, Image.SCALE_SMOOTH);

                user1Paddle = new JLabel(new ImageIcon(paddleScaled));
                user1Paddle.setName("Paddle1");
                user1Paddle.setBounds(40, 250, 10, 100);

            } catch (IOException e) {}
        }
        return user1Paddle;
    }

    private JLabel getUser2Paddle() {
        if (user2Paddle == null) {
            try {
                BufferedImage paddle = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("main/resources/paddle.png")));
                Image paddleScaled = paddle.getScaledInstance(10, 100, Image.SCALE_SMOOTH);

                user2Paddle = new JLabel(new ImageIcon(paddleScaled));
                user2Paddle.setName("Paddle2");
                user2Paddle.setBounds(560, 250, 10, 100);

            } catch (IOException e) {}
        }
        return user2Paddle;
    }

    private JLabel getBall() {
        if (ball == null) {
            try {
                BufferedImage ballImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("main/resources/ball.png")));
                Image ballScaled = ballImage.getScaledInstance(10, 10, Image.SCALE_SMOOTH);

                ball = new JLabel(new ImageIcon(ballScaled));
                ball.setName("Ball");
                ball.setBounds(295, 295, 10, 10);

            } catch (IOException e) {}
        }
        return ball;
    }

    private JTextPane getScore() {
        if (score == null) {
            try {
                score = new JTextPane();
                score.setName("Score");
                score.setText("0                                             0");
                score.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
                score.setForeground(new Color(0, 0, 0));
                score.setEditable(false);
                score.setVisible(false);
                score.setBounds(75, 15, 450, 50);
                PongMain.setTextAreaProperties(score);

            } catch (Throwable t) {}
        }
        return score;
    }

    private JTextPane getScoreAlert() {
        if (scoreAlert == null) {
            try {
                scoreAlert = new JTextPane();
                scoreAlert.setName("ScoreAlert");
                scoreAlert.setText("Player 1 Scored!");
                scoreAlert.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
                scoreAlert.setForeground(new Color(255, 0, 0));
                scoreAlert.setEditable(false);
                scoreAlert.setVisible(false);
                scoreAlert.setBounds(200, 15, 200, 50);
                PongMain.setTextAreaProperties(scoreAlert);

            } catch (Throwable t) {}
        }
        return scoreAlert;
    }

    private JTextPane getStartAlert() {
        if (startAlert == null) {
            try {
                startAlert = new JTextPane();
                startAlert.setName("StartAlert");
                startAlert.setText("GO");
                startAlert.setFont(new Font(Font.DIALOG, Font.BOLD, 42));
                startAlert.setForeground(new Color(0, 0, 0));
                startAlert.setEditable(false);
                startAlert.setVisible(false);
                startAlert.setBounds(250, 350, 100, 50);
                PongMain.setTextAreaProperties(startAlert);
            } catch (Throwable t) {}
        }
        return startAlert;
    }


    @Override
    public void actionPerformed(ActionEvent e) {}

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

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            player1Keys.add(KeyEvent.VK_W);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            player1Keys.add(KeyEvent.VK_S);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            player2Keys.add(KeyEvent.VK_UP);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player2Keys.add(KeyEvent.VK_DOWN);
        }
        multiKeyHandling();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            player1Keys.remove(KeyEvent.VK_W);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            player1Keys.remove(KeyEvent.VK_S);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            player2Keys.remove(KeyEvent.VK_UP);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player2Keys.remove(KeyEvent.VK_DOWN);
        }
    }


    public void multiKeyHandling() {
        for (Integer key : player2Keys) {
            switch (key) {
                case 38:
                    if (user2Paddle.getY() > 0) {
                        user2Paddle.setBounds(560, (user2Paddle.getY() - 5), 10, 100);
                    }
                    break;
                case 40:
                    if (user2Paddle.getY() < 500) {
                        user2Paddle.setBounds(560, (user2Paddle.getY() + 5), 10, 100);
                    }
                    break;
            }
        }
        for (Integer key : player1Keys) {
            switch (key) {
                case 87:
                    if (user1Paddle.getY() > 0) {
                        user1Paddle.setBounds(40, (user1Paddle.getY() - 5), 10, 100);
                    }
                    break;
                case 83:
                    if (user1Paddle.getY() < 500) {
                        user1Paddle.setBounds(40, (user1Paddle.getY() + 5), 10, 100);
                    }
                    break;
            }
        }
    }
}
