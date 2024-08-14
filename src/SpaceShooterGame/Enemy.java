package SpaceShooterGame;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Random;

public class Enemy extends Entity{
    private JLabel enemy;
    private int X;
    private int Y;
    private ImageIcon enemyIcon;
    private Random random;
    int frameWidth;
    int frameHeight;
    JPanel panel;
    JFrame frame;
    Timer enemySpeed;

    public Enemy(String enemyIcon, int frameWidth, int frameHeight, JPanel panel, JFrame frame) {
        this.frame = frame;
        this.panel = panel;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        random = new Random();
        enemy = new JLabel();
        this.enemyIcon = new ImageIcon(enemyIcon);
        X = random.nextInt(0, frameWidth-this.enemyIcon.getIconWidth());
        Y = 0;

        enemy.setIcon(this.enemyIcon);
        enemy.setVisible(true);

        enemy.putClientProperty("owner", this);
    }
    public JLabel getEnemy() {return enemy;}
    public int getX() {return X;}
    public int getY() {return Y;}
    public ImageIcon getEnemyIcon() {return enemyIcon;}

    @Override
    public void updateLocation() {
        if (Y >= frameHeight) {
            GameFrame.enemyOver(this);
            return;
        }
        Y += 2;
        enemy.setLocation(X, Y);
    }

    @Override
    public void die() {
        GameFrame.enemyDeath(this);
    }
}
