import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class TicTacToeGUI extends JFrame {
    private Container pane;
    private String currentPlayer;
    private JButton [][] board;
    private boolean hasWinner;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem quit;
    private JMenuItem newGame;
    private final int boardSize = 3;

    public TicTacToeGUI() {
        super ();
        pane = getContentPane();
        pane.setLayout(new GridLayout(3,3));
        setTitle("Tic Tac Toe");
        setSize(500,500);
        setResizable(false); //TODO:experiment with true
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        currentPlayer = "x";
        board = new JButton[3][3];
        hasWinner = false;
        initializeBoard();
        initializeMenuBar();
    }
    private void initializeMenuBar() {
        menuBar = new JMenuBar();
        menu = new JMenu("File");

        newGame =  new JMenuItem("New Game");
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetBoard();
            }
        });
        quit = new JMenuItem("Quit");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(newGame);
        menu.add(quit);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }
    private void resetBoard(){
        currentPlayer = "x";
        hasWinner = false;
        for (int i = 0; i<boardSize; i++){
            for (int j = 0; j<3; j++) {
                board[i][j].setText("");
            }
        }
    }
    private void initializeBoard() {
        for (int i = 0; i<boardSize; i++){
            for (int j = 0; j<3; j++) {
                JButton btn = new JButton();
                btn.setFont(new Font(Font.DIALOG,Font.BOLD,100));
                board [i][j] = btn;
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (((JButton)e.getSource()).getText().equals("") &&
                                hasWinner == false) {
                            btn.setText(currentPlayer);
                            hasWinner();
                            togglePlayer();
                        }
                    }
                });
                pane.add(btn);
            }
        }
    }
    private void togglePlayer() {
        if (currentPlayer.equals("x"))
            currentPlayer = "o";
         else
            currentPlayer = "x";

    }


    private void hasWinner() { //TODO: Optimization

        if (board[0][0].getText().equals(currentPlayer) && board[1][0].getText().equals(currentPlayer) && board[2][0].getText().equals(currentPlayer)){
            JOptionPane.showMessageDialog(null,"Player "+ currentPlayer+" has won.");
            hasWinner = true;
        }
        if (board[0][1].getText().equals(currentPlayer) && board[1][1].getText().equals(currentPlayer) && board[2][1].getText().equals(currentPlayer)){
            JOptionPane.showMessageDialog(null,"Player "+ currentPlayer+" has won.");
            hasWinner = true;
        }
        if (board[0][2].getText().equals(currentPlayer) && board[1][2].getText().equals(currentPlayer) && board[2][2].getText().equals(currentPlayer)){
            JOptionPane.showMessageDialog(null,"Player "+ currentPlayer+" has won.");
            hasWinner = true;
        }
        if (board[0][0].getText().equals(currentPlayer) && board[0][1].getText().equals(currentPlayer) && board[0][2].getText().equals(currentPlayer)){
            JOptionPane.showMessageDialog(null,"Player "+ currentPlayer+" has won.");
            hasWinner = true;
        }
        if (board[1][0].getText().equals(currentPlayer) && board[1][1].getText().equals(currentPlayer) && board[1][2].getText().equals(currentPlayer)){
            JOptionPane.showMessageDialog(null,"Player "+ currentPlayer+" has won.");
            hasWinner = true;
        }
        if (board[2][0].getText().equals(currentPlayer) && board[2][1].getText().equals(currentPlayer) && board[2][2].getText().equals(currentPlayer)){
            JOptionPane.showMessageDialog(null,"Player "+ currentPlayer+" has won.");
            hasWinner = true;
        }
        if (board[2][0].getText().equals(currentPlayer) && board[1][1].getText().equals(currentPlayer) && board[0][2].getText().equals(currentPlayer)){
            JOptionPane.showMessageDialog(null,"Player "+ currentPlayer+" has won.");
            hasWinner = true;
        }
        if (board[0][0].getText().equals(currentPlayer) && board[1][1].getText().equals(currentPlayer) && board[2][2].getText().equals(currentPlayer)){
            JOptionPane.showMessageDialog(null,"Player "+ currentPlayer+" has won.");
            hasWinner = true;
        }
    }
}
