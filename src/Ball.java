import java.awt.*;

public class Ball extends Sprite {

    private float vx = 150 + (float)(Math.random() * 200f);
    private float vy = 150 + (float)(Math.random() * 200f);
    private final Color COLOR = new Color(
            (int)(Math.random() * 255),
            (int)(Math.random() * 255),
            (int)(Math.random() * 255)
    );

    Ball() {
        halfHeight = halfHeight = 20 + (float)(Math.random() * 50f);
    }

    @Override
    void update(GameCanvas canvas, float deltaTime) {
        x += vx * deltaTime;
        y += vy * deltaTime;
        if (getLeft() < canvas.getLeft()) {
            setLeft(canvas.getLeft());
            vx = -vx;
        }
        if (getRight() > canvas.getRight()) {
            setRight(canvas.getRight());
            vx = -vx;
        }
        if (getBottom() > canvas.getBottom()) {
            setBottom(canvas.getBottom());
            vy = -vy;
        }
        if (getTop() < canvas.getTop()) {
            setTop(canvas.getTop());
            vy = -vy;
        }
    }

    @Override
    void render(GameCanvas canvas, Graphics g) {
        g.setColor(COLOR);
        g.fillOval((int)(getLeft()), (int)(getTop()),
                (int)(getWidth()), (int)(getHeight()));
    }
}