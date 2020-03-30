import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameCanvas extends JPanel {

    private GameWindow gameWindow;
    private long lastFrameTime;
    private SettingsWindow sw;

    GameCanvas (GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        lastFrameTime = System.nanoTime();
        sw = new SettingsWindow();
        JButton settings = new JButton("Settings");
        JButton newGame = new JButton("New Game");
        JButton exit = new JButton("Exit");
        JPanel jp = new JPanel(new GridLayout(1,3));
        SpringLayout layout = new SpringLayout();
        setLayout(layout);
        jp.add(settings);
        jp.add(newGame);
        jp.add(exit);
        add(jp);
        layout.putConstraint(SpringLayout.SOUTH, jp, 0, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.EAST, jp, 0, SpringLayout.EAST, this);
        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sw.setVisible(true);
            }
        });
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.startNewGame();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        long currentTime = System.nanoTime();
        float delta = (currentTime - lastFrameTime) * 0.000000001f;
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
