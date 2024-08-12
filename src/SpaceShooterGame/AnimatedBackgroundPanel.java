package SpaceShooterGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimatedBackgroundPanel extends JPanel implements BackgroundPanel {

    private ImageIcon backgroundGif;

    public AnimatedBackgroundPanel(String backgroundGif) {
        this.backgroundGif = new ImageIcon(backgroundGif);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        backgroundGif.paintIcon(this, g, 0, 0);
    }

}
