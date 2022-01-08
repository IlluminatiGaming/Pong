package main.java;

import main.java.windows.PongGameWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameUpdateLoop {

    public static final GameUpdateLoop gameUpdateLoop = new GameUpdateLoop();

    Random rand = new Random();

    String ballXDirection = "";
    String ballYDirection = "";
    boolean gameStarted = false;

    boolean playerScored = false;
    String scorer = "";
    int player1Score = 0;
    int player2Score = 0;

    int scoreIncrement = 0;
    int gameIncrement = 0;

    public void startGameLoop() {
        Timer timer = new Timer(4, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               update(PongGameWindow.pongGameWindow.user1Paddle, PongGameWindow.pongGameWindow.user2Paddle, PongGameWindow.pongGameWindow.ball);
               PongGameWindow.pongGameWindow.repaint();
            }
        });
        timer.start();
    }


    public void update(JLabel paddle1, JLabel paddle2, JLabel ball) {

        if (!playerScored) {
            gameIncrement++;

            if (!gameStarted) {
                if (gameIncrement <= 199) return;

                gameStarted = true;
                PongGameWindow.pongGameWindow.scoreAlert.setVisible(false);
                PongGameWindow.pongGameWindow.score.setVisible(true);
                int startXDirection = rand.nextInt(99);
                int startYDirection = rand.nextInt(99);

                if (startXDirection <= 49) {
                    ballXDirection = "-";
                } else {
                    ballXDirection = "+";
                }

                if (startYDirection <= 49) {
                    ballYDirection = "-";
                } else {
                    ballYDirection = "+";
                }
            }
            if (gameIncrement == 200) PongGameWindow.pongGameWindow.startAlert.setVisible(true);
            if (gameIncrement == 300) PongGameWindow.pongGameWindow.startAlert.setVisible(false);

            if (ball.getY() == 2) {
                ballYDirection = "+";
            } else if (ball.getY() == 598) {
                ballYDirection = "-";
            }

            if (ball.getX() == 50) {
                if (paddle1.getY() == ball.getY() || ((ball.getY() - paddle1.getY()) <= 100 && (ball.getY() - paddle1.getY()) > 0)) {
                    ballXDirection = "+";
                }
            } else if (ball.getX() == 550) {
                if (paddle2.getY() == ball.getY() || ((ball.getY() - paddle2.getY()) <= 100 && (ball.getY() - paddle2.getY()) > 0)) {
                    ballXDirection = "-";
                }
            }

            if (ball.getX() == 25) {
                playerScored = true;
                gameIncrement = 0;
                scorer = "2";
                player2Score++;
                PongGameWindow.pongGameWindow.score.setText(player1Score + "                                                " + player2Score);
            } else if (ball.getX() == 575) {
                playerScored = true;
                gameIncrement = 0;
                scorer = "1";
                player1Score++;
                PongGameWindow.pongGameWindow.score.setText(player1Score + "                                                " + player2Score);
            }

            if (ballXDirection.equals("+") && ballYDirection.equals("+")) {
                ball.setBounds(ball.getX() + 1, ball.getY() + 1, 10, 10);
            } else if (ballXDirection.equals("+") && ballYDirection.equals("-")) {
                ball.setBounds(ball.getX() + 1, ball.getY() - 1, 10, 10);
            } else if (ballXDirection.equals("-") && ballYDirection.equals("+")) {
                ball.setBounds(ball.getX() - 1, ball.getY() + 1, 10, 10);
            } else if (ballXDirection.equals("-") && ballYDirection.equals("-")) {
                ball.setBounds(ball.getX() - 1, ball.getY() - 1, 10, 10);
            }
        } else {
            if (scoreIncrement == 400) {
                ball.setBounds(295, 295, 10, 10);
                paddle1.setBounds(40, 250, 10, 100);
                paddle2.setBounds(560, 250, 10, 100);
            }
            else if (scoreIncrement == 800) {
                playerScored = false;
                gameStarted = false;
                scoreIncrement = 0;
            }

            PongGameWindow.pongGameWindow.scoreAlert.setText("Player " + scorer + " scored!");
            PongGameWindow.pongGameWindow.scoreAlert.setVisible(true);

            scoreIncrement++;
        }
    }
}
