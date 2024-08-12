package SpaceShooterGame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Player extends Entity implements ActionListener {
    private int X;
    private int Y;
    private JLabel player;
    private int lives;
    private boolean ableToShoot;
    private Timer fireRatio;
    private ImageIcon playerIcon;
    JPanel panel;
    Projectile projectile;

    public Player(String playerIcon, JPanel panel) {
        this.panel = panel;
        this.playerIcon = new ImageIcon(playerIcon);
        X = panel.getWidth()/2-this.playerIcon.getIconWidth();
        Y = panel.getHeight()-this.playerIcon.getIconHeight()-32;
        player = new JLabel();
        player.setIcon(this.playerIcon);
        lives = 3;
        ableToShoot = true;
        fireRatio = new Timer(30, this);
        player.setBounds(X, Y, this.playerIcon.getIconWidth(), this.playerIcon.getIconHeight());
        player.setVisible(true);
    }

    public void setProjectile(Projectile projectile) {this.projectile = projectile;}

    public void updateLocation(int newX) {
        X = newX;
        player.setLocation(X, Y);
    }

    @Override
    public void die() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public JLabel getPlayer() {return player;}
    public int getX() {return X;}
    public int getY() {return Y;}
    public int getLives() {return lives;}
    public boolean getAbleToShoot() {return ableToShoot;}
    public Timer getFireRatio() {return fireRatio;}
    public ImageIcon getPlayerIcon() {return playerIcon;}
    public Projectile getProjectile() {return projectile;}
}
