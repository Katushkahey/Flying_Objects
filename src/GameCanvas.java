import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JPanel {

    private GameWindow gameWindow;
    private long lastFrameTime;

    GameCanvas (GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        lastFrameTime = System.nanoTime();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        long currentTime = System.nanoTime();
        float delta = (currentTime - lastFrameTime)*0.000000001f;
        lastFrameTime = currentTime;
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        gameWindow.onDrawFrame(this, g, delta);
        repaint();
    }

    int getLeft() {
        return 0;
    }

    int getRight() {
        return getWidth() - 1;
    }

    int getTop() {
        return 0;
    }

    int getBottom() {
        return getHeight() - 1;
    }
}
