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
import java.awt.Color;

public class TicTacToeGUI extends JFrame {
    private Container pane;
    private static int x =0;
    private static int n;
    private int style = 0;
    private static String currentPlayer;
    private static JButton [][] board;
    private static boolean hasWinner;
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
    private void initializeBoard() {
        getContentPane().removeAll();
        getContentPane().repaint();
        n= 9;
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
                                !hasWinner) {
                            btn.setText(currentPlayer);
                            //hasWinner();
                            hasWinnerBeta();
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
    /*private void hasWinner() {
        boardFilled();
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
    }*/
    public static boolean hasWinnerBeta(){//y is column, x is row
        boardFilled();
        boolean hasWon;
        int y = 0;
        String Player = board[x][y].toString();
        boolean onDiagonal = (x == y) || (y == -1 * x + (board.length-1));
        boolean HorizontalWin = true;
        boolean VerticalWin = true;
        boolean DiagonalWinOne = true;
        boolean DiagonalWinTwo = true;

        for(int i= 0; i < 3; i++){
            if(!board[x][i].getText().equals(currentPlayer)){
                HorizontalWin = false;
            }
            if(!board[i][y].getText().equals(currentPlayer)){
                VerticalWin = false;
            }
        }
        if(onDiagonal){
            // Check the diagonals
            for(int n = 0; n < 3; n++){
                if(!board[n][n].getText().equals(currentPlayer))
                    DiagonalWinOne = false;
                if(!board[n][-1*n+(3-1)].getText().equals(currentPlayer))
                    DiagonalWinTwo = false;
            }
        }
        else{
            DiagonalWinOne = false;
            DiagonalWinTwo = false;
        }
        hasWon = (HorizontalWin || VerticalWin || DiagonalWinOne || DiagonalWinTwo);

        if(hasWon){
            JOptionPane.showMessageDialog(null,"Player "+ currentPlayer+" has won.");
            hasWinner = true;
        }

        return hasWon;
    }
    public static void boardFilled(){
        n=9;
        for (int i=0;i<board.length;i++){
            for(int j = 0; j< board.length;j++){
                if(board[i][j].getText()!=""){
                    n--;
                }
                else{
                    break;
                }
            }
            if(n == 0){
                if(hasWinner==false){
                    JOptionPane.showMessageDialog(null,"Game is a draw!");
                }
            }
        }

    }

}
