import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class TicTacToe extends JFrame implements ActionListener {
    JPanel buttonsPanel;
    JLabel playerTurnLabel, player1NameLabel, player2NameLabel;
    JButton[] buttons = new JButton[9];
    boolean player1Turn;
    boolean gameEnded = false;
    JButton newGame, endGame;

    TicTacToe() {
        String player1Name = JOptionPane.showInputDialog(this, "Enter Player 1 Name (X):");
        String player2Name = JOptionPane.showInputDialog(this, "Enter Player 2 Name (O):");

        if (player1Name == null || player1Name.trim().isEmpty()) {
            player1Name = "Player 1";
        }
        if (player2Name == null || player2Name.trim().isEmpty()) {
            player2Name = "Player 2";
        }

        this.setSize(600, 600);
        this.setResizable(false);
        this.setLocationRelativeTo(rootPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 3));
        buttonsPanel.setBounds(150, 50, 240, 240);
        buttonsPanel.setBackground(Color.BLACK);

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].addActionListener(this);
            buttons[i].setFocusable(false);
            buttons[i].setFont(new Font("", Font.ITALIC, 40));
            buttons[i].setBackground(Color.CYAN);
            buttons[i].setForeground(Color.BLUE);
            buttonsPanel.add(buttons[i]);
        }

        playerTurnLabel = new JLabel();
        firstTurn();
        playerTurnLabel.setFont(new Font("Constantia", Font.ROMAN_BASELINE, 34));
        playerTurnLabel.setBounds(160, 320, 300, 40);

        newGame = new JButton("New Game");
        newGame.setBounds(150, 420, 100, 40);
        newGame.setFocusable(false);
        newGame.addActionListener(this);

        endGame = new JButton("Exit Game");
        endGame.setBounds(290, 420, 100, 40);
        endGame.setFocusable(false);
        endGame.addActionListener(this);

        // Adding player name labels
        player1NameLabel = new JLabel(player1Name + " (X)");
        player1NameLabel.setBounds(50, 150, 100, 30);
        player1NameLabel.setFont(new Font("Arial", Font.BOLD, 14));

        player2NameLabel = new JLabel(player2Name + " (O)");
        player2NameLabel.setBounds(440, 150, 100, 30);
        player2NameLabel.setFont(new Font("Arial", Font.BOLD, 14));

        this.add(buttonsPanel);
        this.add(playerTurnLabel);
        this.add(newGame);
        this.add(endGame);
        this.add(player1NameLabel);
        this.add(player2NameLabel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameEnded) {
            for (int i = 0; i < 9; i++) {
                if (e.getSource() == buttons[i]) {
                    if (player1Turn) {
                        if (buttons[i].getText().equals("")) {
                            buttons[i].setForeground(Color.BLUE);
                            buttons[i].setText("X");
                            player1Turn = false;
                            playerTurnLabel.setText("Player O Turn");
                            checkWin();
                            checkDraw();
                        }
                    } else {
                        if (buttons[i].getText().equals("")) {
                            buttons[i].setForeground(Color.RED);
                            buttons[i].setText("O");
                            player1Turn = true;
                            playerTurnLabel.setText("Player X Turn");
                            checkWin();
                            checkDraw();
                        }
                    }
                }
            }
        }
        if (e.getSource() == newGame) {
            new TicTacToe();
            dispose();
        } else if (e.getSource() == endGame) {
            this.dispose();
        }
    }

    public void firstTurn() {
        Random random = new Random();
        if (random.nextInt(2) == 0) {
            playerTurnLabel.setText("Player X Turn");
            player1Turn = true;
        } else {
            playerTurnLabel.setText("Player O Turn");
            player1Turn = false;
        }
    }

    public void checkDraw() {
        if (!gameEnded) {
            boolean gameDrew = true;
            for (int i = 0; i < 9; i++) {
                if (buttons[i].getText().equals("")) {
                    gameDrew = false;
                    break;
                }
            }

            if (gameDrew) {
                playerTurnLabel.setBounds(210, 320, 300, 40);
                playerTurnLabel.setText("A Draw");
                gameEnded = true;
            }
        }
    }

    public void checkWin() {
        // Horizontal check for X
        if (buttons[0].getText().equals("X") && buttons[1].getText().equals("X") && buttons[2].getText().equals("X")) {
            playerTurnLabel.setText("Player X wins");
            xWins(0, 1, 2);
            return;
        }
        if (buttons[3].getText().equals("X") && buttons[4].getText().equals("X") && buttons[5].getText().equals("X")) {
            playerTurnLabel.setText("Player X wins");
            xWins(3, 4, 5);
            return;
        }
        if (buttons[6].getText().equals("X") && buttons[7].getText().equals("X") && buttons[8].getText().equals("X")) {
            playerTurnLabel.setText("Player X wins");
            xWins(6, 7, 8);
            return;
        }

        // Vertical check for X
        if (buttons[0].getText().equals("X") && buttons[3].getText().equals("X") && buttons[6].getText().equals("X")) {
            playerTurnLabel.setText("Player X wins");
            xWins(0, 3, 6);
            return;
        }
        if (buttons[1].getText().equals("X") && buttons[4].getText().equals("X") && buttons[7].getText().equals("X")) {
            playerTurnLabel.setText("Player X wins");
            xWins(1, 4, 7);
            return;
        }
        if (buttons[2].getText().equals("X") && buttons[5].getText().equals("X") && buttons[8].getText().equals("X")) {
            playerTurnLabel.setText("Player X wins");
            xWins(2, 5, 8);
            return;
        }

        // Diagonal check for X
        if (buttons[0].getText().equals("X") && buttons[4].getText().equals("X") && buttons[8].getText().equals("X")) {
            playerTurnLabel.setText("Player X wins");
            xWins(0, 4, 8);
            return;
        }
        if (buttons[2].getText().equals("X") && buttons[4].getText().equals("X") && buttons[6].getText().equals("X")) {
            playerTurnLabel.setText("Player X wins");
            xWins(2, 4, 6);
            return;
        }

        // Horizontal check for O
        if (buttons[0].getText().equals("O") && buttons[1].getText().equals("O") && buttons[2].getText().equals("O")) {
            playerTurnLabel.setText("Player O wins");
            oWins(0, 1, 2);
            return;
        }
        if (buttons[3].getText().equals("O") && buttons[4].getText().equals("O") && buttons[5].getText().equals("O")) {
            playerTurnLabel.setText("Player O wins");
            oWins(3, 4, 5);
            return;
        }
        if (buttons[6].getText().equals("O") && buttons[7].getText().equals("O") && buttons[8].getText().equals("O")) {
            playerTurnLabel.setText("Player O wins");
            oWins(6, 7, 8);
            return;
        }

        // Vertical check for O
        if (buttons[0].getText().equals("O") && buttons[3].getText().equals("O") && buttons[6].getText().equals("O")) {
            playerTurnLabel.setText("Player O wins");
            oWins(0, 3, 6);
            return;
        }
        if (buttons[1].getText().equals("O") && buttons[4].getText().equals("O") && buttons[7].getText().equals("O")) {
            playerTurnLabel.setText("Player O wins");
            oWins(1, 4, 7);
            return;
        }
        if (buttons[2].getText().equals("O") && buttons[5].getText().equals("O") && buttons[8].getText().equals("O")) {
            playerTurnLabel.setText("Player O wins");
            oWins(2, 5, 8);
            return;
        }

        // Diagonal check for O
        if (buttons[0].getText().equals("O") && buttons[4].getText().equals("O") && buttons[8].getText().equals("O")) {
            playerTurnLabel.setText("Player O wins");
            oWins(0, 4, 8);
            return;
        }
        if (buttons[2].getText().equals("O") && buttons[4].getText().equals("O") && buttons[6].getText().equals("O")) {
            playerTurnLabel.setText("Player O wins");
            oWins(2, 4, 6);
            return;
        }
    }

    public void xWins(int a, int b, int c) {
        buttons[a].setForeground(Color.yellow);
        buttons[a].setBackground(Color.black);

        buttons[b].setForeground(Color.yellow);
        buttons[b].setBackground(Color.black);

        buttons[c].setForeground(Color.yellow);
        buttons[c].setBackground(Color.black);

        gameEnded = true;
    }

    public void oWins(int a, int b, int c) {
        buttons[a].setForeground(Color.yellow);
        buttons[a].setBackground(Color.black);

        buttons[b].setForeground(Color.yellow);
        buttons[b].setBackground(Color.black);

        buttons[c].setForeground(Color.yellow);
        buttons[c].setBackground(Color.black);

        gameEnded = true;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
