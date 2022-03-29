import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JPanel implements KeyListener {

    private static int FPS = 60;
    private static int delay = 1000 / FPS;
    public static final int BOARD_WIDTH = 10;
    public static final int BOARD_HEIGHT = 22;
    public static final int BLOCK_SIZE = 30;

    private Timer looper;
    private Color[][] board = new Color[BOARD_WIDTH][BOARD_HEIGHT];

    private Color[][] shape = {
            {Color.RED, Color.RED, Color.RED},
            {null,Color.RED,null}
    };

    private int x = 4;
    private int y = 0;
    private int normal = 600;
    private int fast = 50;
    private int shapeDelay = normal;
    private long beginTime;

    public Board() {
        looper = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (System.currentTimeMillis() - beginTime > shapeDelay) {
                    y++;
                    beginTime = System.currentTimeMillis();
                }
                repaint();
            }
        });
        looper.start();;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0,0, getWidth(), getHeight());

        // draw shape
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j] != null) {
                    g.setColor(shape[i][j]);
                    g.fillRect(j * BLOCK_SIZE + x * BLOCK_SIZE, i * BLOCK_SIZE + y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        }
        g.setColor(Color.white);
        for (int row = 0; row < BOARD_HEIGHT + 1; row++) {
            g.drawLine(0, BLOCK_SIZE * row, BLOCK_SIZE * BOARD_WIDTH, BLOCK_SIZE * row);
        }
        for (int col = 0; col < BOARD_WIDTH + 1; col++) {
            g.drawLine(BLOCK_SIZE * col, 0, BLOCK_SIZE * col, BLOCK_SIZE * BOARD_HEIGHT);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            shapeDelay = fast;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            shapeDelay = normal;
    }
}
