package SpaceShooterGame;

import javax.swing.*;
import java.awt.*;

public class TwoDbackgroundPanel extends JPanel implements BackgroundPanel{
    ImageIcon backgroundImage;

    public TwoDbackgroundPanel(String backgroundImage) {
        this.backgroundImage = new ImageIcon(backgroundImage);
        setLayout(null);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        backgroundImage.paintIcon(this, g, 0, 0);
    }
}
