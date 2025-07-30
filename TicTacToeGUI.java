import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeGUI extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private char currentPlayer = 'X';

    public TicTacToeGUI() {
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));
	    try {
        Image icon = Toolkit.getDefaultToolkit().getImage("icon.png");
        setIconImage(icon);
    	} catch (Exception e) {
        System.out.println("Icon not found");
    	}
        initializeButtons();

        setVisible(true);
    }

    public void initializeButtons() {
        Font font = new Font("Arial", Font.BOLD, 60);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(font);
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }
    }

    public boolean checkWin(char player) {
        // Rows & Columns
        for (int i = 0; i < 3; i++) {
            if (
                buttons[i][0].getText().equals(String.valueOf(player)) &&
                buttons[i][1].getText().equals(String.valueOf(player)) &&
                buttons[i][2].getText().equals(String.valueOf(player))
            ) return true;

            if (
                buttons[0][i].getText().equals(String.valueOf(player)) &&
                buttons[1][i].getText().equals(String.valueOf(player)) &&
                buttons[2][i].getText().equals(String.valueOf(player))
            ) return true;
        }

        // Diagonals
        if (
            buttons[0][0].getText().equals(String.valueOf(player)) &&
            buttons[1][1].getText().equals(String.valueOf(player)) &&
            buttons[2][2].getText().equals(String.valueOf(player))
        ) return true;

        if (
            buttons[0][2].getText().equals(String.valueOf(player)) &&
            buttons[1][1].getText().equals(String.valueOf(player)) &&
            buttons[2][0].getText().equals(String.valueOf(player))
        ) return true;

        return false;
    }

    public boolean isDraw() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (buttons[i][j].getText().equals("")) return false;
        return true;
    }

    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (!clicked.getText().equals("")) return; // already clicked

        clicked.setText(String.valueOf(currentPlayer));

        if (checkWin(currentPlayer)) {
            JOptionPane.showMessageDialog(this, "🎉 Player " + currentPlayer + " wins!");
            resetBoard();
            return;
        }

        if (isDraw()) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetBoard();
            return;
        }

        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public void resetBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                buttons[i][j].setText("");
        currentPlayer = 'X';
    }

    public static void main(String[] args) {
        new TicTacToeGUI();
    }
}
