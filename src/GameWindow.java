import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameWindow extends JFrame {

    private final static int POS_X = 200;
    private final static int POS_Y = 150;
    private final static int WINDOW_WIDTH = 800;
    private final static int WINDOW_HEIGHT = 600;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameWindow();
            }
        });
    }

    private GameWindow() {
        Logic.gameObjects = new GameObject[1];
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Flying objects");
        GameCanvas gameCanvas = new GameCanvas(this);
        gameCanvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    Logic.addGameObject(e.getX(), e.getY());
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    Logic.removeGameObject();
                }
            }
        });
        initApplication();
        add(gameCanvas, BorderLayout.CENTER);
        setVisible(true);
    }

    private void initApplication() {
        Logic.addGameObject(new Background());
    }

    void onDrawFrame(GameCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    private void update(GameCanvas canvas, float deltaTime) {
        for (int i = 0; i < Logic.spritesCount; i++) {
            Logic.gameObjects[i].update(canvas, deltaTime);
        }
    }

    private void render(GameCanvas canvas, Graphics g) {
        for (int i = 0; i < Logic.spritesCount; i++) {
            Logic.gameObjects[i].render(canvas, g);
        }
    }

    void startNewGame() {
        Logic.spritesCount = 0;
        Logic.gameObjects = new GameObject[1];
        initApplication();
    }

}