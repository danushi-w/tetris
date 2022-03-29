import javax.swing.*;

public class Window {

    private JFrame window;
    private Board board;

    public static final int WIDTH = 500;
    public static final int HEIGHT = 700;

    public Window() {
        window = new JFrame("Tetris");
        window.setSize(WIDTH,HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);

        board = new Board();
        window.add(board);
        window.addKeyListener(board);
        window.setVisible(true);
    }

    public static void main(String[] args) {
        new Window();
    }
}
