import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;

public class TicTacToeGUI extends JFrame {
    private Container pane;
    public int R;
    public int G;
    public int B;

    private int style = 0;
    private String currentPlayer;
    private JButton [][] board;
    private boolean hasWinner;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem quit;
    private JMenuItem newGame;
    Border lightBorder = new LineBorder(new Color (146, 149, 155), 4);
    Border darkBorder = new LineBorder(new Color (92, 100, 112), 4);
    private final int boardSize = 3;

    public TicTacToeGUI() {
        super ();
        pane = getContentPane();
        pane.setLayout(new GridLayout(3,3));
        setTitle("Tic Tac Toe");
        setSize(500,500);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        currentPlayer = "X";
        board = new JButton[3][3];
        hasWinner = false;
        initializeBoard();
        initializeMenuBar();

    }
    private void initializeMenuBar() {
        Action toggleAction = new AbstractAction("Dark Mode") {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton button = (AbstractButton)e.getSource();
                if (button.isSelected()) {
                    button.setText("Light Mode");
                    style = 1;
                    resetBoard();
                    initializeBoard();
                } else {
                    button.setText("Dark Mode");
                    style = 0;
                    resetBoard();
                    initializeBoard();
                }
            }
        };
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
        //menuBar.add(new JMenu("Appearance"));
        menuBar.add(new JToggleButton(toggleAction));
        setJMenuBar(menuBar);
    }
    private void resetBoard(){
        currentPlayer = "X";
        hasWinner = false;
        for (int i = 0; i<boardSize; i++){
            for (int j = 0; j<3; j++) {
                board[i][j].setText("");
            }
        }
    }
    /*public void darkStyle(){ 
        btn.setOpaque(true);
        btn.setBorder(darkBorder);
        btn.setForeground(new Color(111, 153, 237));
    }

    public void lightStyle() {
        btn.setOpaque(true);
        btn.setBorder(lightBorder);
        btn.setForeground(new Color(16, 92, 242));
    }*/
    private void initializeBoard() {
        /*if (style  ==  0){
            lightStyle();
        } else {
            darkStyle();
        }*/
        getContentPane().removeAll();
        getContentPane().repaint();
        for (int i = 0; i<boardSize; i++){
            for (int j = 0; j<3; j++) {
                JButton btn = new JButton();
                btn.setFont(new Font(Font.DIALOG,Font.BOLD,100));
                if (style == 0){
                    btn.setOpaque(true);
                    btn.setBorder(lightBorder);
                    btn.setBackground(Color.white);
                    btn.setForeground(new Color(16, 92, 242));
                } else {
                    btn.setOpaque(true);
                    btn.setBorder(darkBorder);
                    btn.setBackground(Color.black);
                    btn.setForeground(new Color(111, 153, 237));
                }
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
        if (currentPlayer.equals("X"))
            currentPlayer = "O";
        else
            currentPlayer = "X";

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