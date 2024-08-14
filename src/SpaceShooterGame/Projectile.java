package SpaceShooterGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Projectile extends Entity{
    JLabel projectile;
    private int X;
    private int Y;
    private ImageIcon projectileIcon;
    private String ImageIconPath;
    private Player player;
    private JPanel panel;
    Timer projectileTimer;

    public Projectile(String projectileIcon, Player player, JPanel panel) {
        this.panel = panel;
        this.ImageIconPath = projectileIcon;
        projectile = new JLabel();
        this.projectileIcon = new ImageIcon(projectileIcon);
        projectile.setIcon(this.projectileIcon);
        this.X = player.getX() + player.getPlayerIcon().getIconWidth()/2 - 15;
        this.Y = player.getY() - player.getPlayerIcon().getIconHeight() + 35;
        projectile.setBounds(X, Y, this.projectileIcon.getIconWidth(), this.projectileIcon.getIconHeight());

        projectileTimer = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateLocation();
                panel.repaint();
            }
        });

        projectile.putClientProperty("owner", this);

        projectileTimer.start();
    }

    @Override
    public void updateLocation() {
        Component component = SwingUtilities.getDeepestComponentAt(panel, X, Y);

        if (!(component instanceof JPanel) && component != null) {
            JLabel label = (JLabel) component;
            Object classCheck = label.getClientProperty("owner");

            if (classCheck instanceof Enemy) {
                Enemy enemy = (Enemy) label.getClientProperty("owner");
                enemy.die();
                die();
            }
        }

        if (Y<=0) {
            die();
            return;
        }
        else Y-= 10;
        projectile.setLocation(X, Y);
    }

    @Override
    public void die() {
        panel.remove(projectile);
        projectileTimer.stop();
    }

    public ImageIcon getProjectileIcon() {return projectileIcon;}
    public String getProjectileIconPath() {return ImageIconPath;}
    public int getX() {return X;}
    public int getY() {return Y;}
    public Player getPlayer() {return player;}
    public JLabel getProjectile() {return projectile;}
}
