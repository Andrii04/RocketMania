package SpaceShooterGame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GameFrame implements KeyListener, MouseListener, ActionListener {
    static JFrame gameFrame = new JFrame();
    JLayeredPane layeredPane;
    TwoDbackgroundPanel Background;
    static JPanel CharacterPane = new JPanel();
    static List<Enemy> enemies;
    Player player;
    static int points;
    static JLabel pointCount;
    static int lives;
    static JLabel livesCount;

    Timer spawnRate = new Timer(4000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            spawnEnemies(spawnRate);
        }
    });

    int spawnRateCount = 0;

    Timer enemySpeed = new Timer(10, this);

    int frameWidth = 650;
    int frameHeight = 800;

    public GameFrame() {

        enemies = new ArrayList<>();
        points = 0;
        lives = 3;
        livesCount = new JLabel(lives + "");
        livesCount.setForeground(Color.green);
        livesCount.setOpaque(false);
        Font livesCountFont = new Font("Comic SANS MS", Font.PLAIN, 30);
        livesCount.setFont(livesCountFont);
        livesCount.setBounds(10, frameHeight-80, 30, 30);

        pointCount = new JLabel(points + "");
        pointCount.setForeground(Color.WHITE);
        pointCount.setOpaque(false);
        Font pointsCountFont = new Font("Comic Sans MS", Font.PLAIN, 20);
        pointCount.setFont(pointsCountFont);
        pointCount.setBounds(frameWidth/2, 0, 40, 40);

        layeredPane = new JLayeredPane();
        CharacterPane.setBounds(0, 0, frameWidth, frameHeight);
        CharacterPane.setLayout(null);
        CharacterPane.setOpaque(false);
        CharacterPane.setVisible(true);

        gameFrame.setSize(frameWidth, frameHeight);
        gameFrame.setLayout(null);
        gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Background = new TwoDbackgroundPanel("C:\\Users\\andri\\IdeaProjects\\RocketMania\\src\\SpaceShooterGame\\spacePixelBackgroundPerfect.jpg");
        Background.setBounds(0, 0, frameWidth, frameHeight);
        Background.add(pointCount);
        Background.add(livesCount);


        player = new Player("C:\\Users\\andri\\IdeaProjects\\RocketMania\\src\\SpaceShooterGame\\playerRocketIcon.png",
                CharacterPane);
        Projectile projectile = new Projectile("C:\\Users\\andri\\IdeaProjects\\RocketMania\\src\\SpaceShooterGame\\fireball.jpeg",
                player, CharacterPane);
        player.setProjectile(projectile);

        CharacterPane.add(player.getPlayer());
        gameFrame.addKeyListener(this);
        gameFrame.addMouseListener(this);

        layeredPane.add(Background, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(CharacterPane, JLayeredPane.PALETTE_LAYER);
        layeredPane.setBounds(0, 0, frameWidth, frameHeight);

        gameFrame.add(layeredPane);
        gameFrame.setVisible(true);

        spawnRate.start();
        enemySpeed.start();

    }

    public void spawnEnemies(Timer spawnRate) {

        switch(spawnRateCount) {
            case 7 -> spawnRate.setDelay(3000);
            case 20 -> spawnRate.setDelay(2000);
            case 50 -> spawnRate.setDelay(1000);
            case 70 -> spawnRate.setDelay(700);
        }

        Enemy newEnemy = new Enemy("C:\\Users\\andri\\IdeaProjects\\RocketMania\\src\\SpaceShooterGame\\spaceEnemy.png" ,frameWidth, frameHeight, CharacterPane, gameFrame);
        newEnemy.getEnemy().setBounds(newEnemy.getX(), 0, 88, 50);
        enemies.add(newEnemy);
        CharacterPane.add(newEnemy.getEnemy());
        CharacterPane.repaint();
        spawnRateCount++;


    }
    @Override
    public void keyTyped(KeyEvent e) {
        switch(e.getKeyChar()) {
            case 'a' -> {
                if (player.getX()-15 <= 0) player.updateLocation(0);
                else player.updateLocation(player.getX()-15);
            }
            case 'd' -> {
                if (player.getX()+15+player.getPlayerIcon().getIconWidth()+10
                        >= frameWidth) player.updateLocation(frameWidth-player.
                        getPlayerIcon().getIconWidth()-10);
                else player.updateLocation(player.getX()+15);
            }
        }
        CharacterPane.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Projectile newProjectile = new Projectile(player.getProjectile()
                .getProjectileIconPath(), player, CharacterPane);

        CharacterPane.add(newProjectile.getProjectile());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i=0; i<enemies.size(); i++) {
            if (!(enemies.get(i) == null)) {
                enemies.get(i).updateLocation();
                CharacterPane.repaint();
            }
        }
    }

    public static void enemyDeath(Enemy enemy) {
        enemies.set(enemies.indexOf(enemy), null);
        CharacterPane.remove(enemy.getEnemy());
        points ++;
        pointCount.setText(points + "");
    }
    public static void enemyOver(Enemy enemy) {
        enemies.set(enemies.indexOf(enemy), null);
        CharacterPane.remove(enemy.getEnemy());
        lives--;
        if (lives == 0) {
            gameFrame.dispose();
            SwingUtilities.invokeLater(() -> Main.main(new String[]{}));
            return;
        }

        livesCount.setText(lives + "");
    }
}
