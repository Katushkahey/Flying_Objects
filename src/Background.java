import java.awt.*;

class Background implements GameObject {

    private float time;
    private Color color;

    @Override
    public void update(GameCanvas canvas, float deltaTime) {
        float AMPLITUDE = 255f/2f;
        time += deltaTime;
        int red = Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time * 3));
        int green = Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time * 2));
        int blue = Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time));
        color = new Color(red, green, blue);
    }

    @Override
    public void render(GameCanvas canvas, Graphics g) {
        canvas.setBackground(color);
    }

}
