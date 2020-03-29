import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameWindow extends JFrame {

    private final static int POS_X = 600;
    private final static int POS_Y = 200;
    private final static int WINDOW_WIDTH = 800;
    private final static int WINDOW_HEIGHT = 600;
    private GameObject[] gameObjects = new GameObject[1];
    private int spritesCount;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameWindow();
            }
        });
    }

    private GameWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Flying objects");
        GameCanvas gameCanvas = new GameCanvas(this);
        gameCanvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getButton()== MouseEvent.BUTTON1) {
                    addGameObject(new Square(e.getX(), e.getY()));
                }
                if(e.getButton()== MouseEvent.BUTTON3) {
                    if (gameObjects.length == 1) {
                        return;
                    }
                    removeSprite();
                }
            }
        });
        initApplication();
        add(gameCanvas, BorderLayout.CENTER);
        setVisible(true);
    }

    private void addGameObject (GameObject gameObject) {
        if (spritesCount == gameObjects.length) {
            GameObject[] temp = new GameObject[gameObjects.length * 2];
            System.arraycopy(gameObjects, 0, temp, 0, gameObjects.length);
            gameObjects = temp;
        }
        gameObjects[spritesCount++] = gameObject;
    }

    private void removeSprite () {
        if (spritesCount > 1) {
            spritesCount--;
        }

    }

    private void initApplication(){
        addGameObject(new Background());
    }

    void onDrawFrame(GameCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    private void update(GameCanvas canvas, float deltaTime){
        for (int i = 0; i < spritesCount ; i++) {
            gameObjects[i].update(canvas, deltaTime);
        }
    }

    private void render(GameCanvas canvas, Graphics g) {
        for (int i = 0; i < spritesCount ; i++) {
            gameObjects[i].render(canvas, g);
        }
    }
}
