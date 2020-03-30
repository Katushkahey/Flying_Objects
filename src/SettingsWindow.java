import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SettingsWindow extends JFrame {

    private static final int STNG_HEIGHT = 150;
    private static final int STNG_WIDTH = 280;
    private static final int STNG_POS_X = 200;
    private static final int STNG_POS_Y = 150;

    private JRadioButton s = new JRadioButton("Squares", true);
    private JRadioButton b = new JRadioButton("Balls");
    private ButtonGroup gameMode = new ButtonGroup();

    SettingsWindow() throws HeadlessException {
        setBounds(STNG_POS_X, STNG_POS_Y, STNG_WIDTH, STNG_HEIGHT);
        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Cancel");
        JPanel jp = new JPanel(new GridLayout(1,2));
        setVisible(false);
        setTitle("Game parameters");
        setLayout(new GridLayout(4,1));
        addGameMode();
        jp.add(ok);
        jp.add(cancel);
        add(jp);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonOkIsClicked();
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonCancelIsClicked();
            }
        });
    }

    private void addGameMode() {
        add(new JLabel("Choose game mode"));
        gameMode.add(s);
        gameMode.add(b);
        add(s);
        add(b);
    }

    private void buttonOkIsClicked () {
        Logic.gameMode = (s.isSelected()) ? Logic.GAME_MODE_SQUARES : Logic.GAME_MODE_BALLS;
        setVisible(false);
    }

    private void buttonCancelIsClicked () {
        setVisible(false);
    }
    
}
