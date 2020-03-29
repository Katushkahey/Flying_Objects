import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class GameWindow extends JFrame {

    private final static int POS_X = 600;
    private final static int POS_Y = 200;
    private final static int WINDOW_WIDTH = 800;
    private final static int WINDOW_HEIGHT = 600;
    private Sprite[] sprites;
    private Background bc;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameWindow();
            }
        });
    }

    private GameWindow() {
        sprites = new Sprite[10];
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Flying objects");
        GameCanvas gameCanvas = new GameCanvas(this);
        add(gameCanvas, BorderLayout.CENTER);
        setVisible(true);
        initApplication();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getButton()== MouseEvent.BUTTON1) {
                    sprites = new Sprite[sprites.length + 1];
                    initApplication();
                }
                if(e.getButton()== MouseEvent.BUTTON3) {
                    if (sprites.length == 1) {
                        return;
                    }
                    sprites = new Sprite[sprites.length - 1];
                    initApplication();
                }
            }
        });
    }

    private void initApplication(){
        bc = new Background();
        for (int i = 0; i < sprites.length ; i++) {
            sprites[i] = new Square();
        }
    }

    void onDrawFrame(GameCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    private void update(GameCanvas canvas, float deltaTime){
        bc.update(canvas,deltaTime);
        for (int i = 0; i < sprites.length ; i++) {
            sprites[i].update(canvas, deltaTime);
        }
    }

    private void render(GameCanvas canvas, Graphics g) {
        bc.render(canvas,g);
        for (int i = 0; i < sprites.length ; i++) {
            sprites[i].render(canvas, g);
        }
    }
}
