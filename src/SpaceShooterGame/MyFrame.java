package SpaceShooterGame;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.*;

public class MyFrame extends JFrame implements ActionListener, MouseListener {
    GameFrame gameFrame;
    JLayeredPane layeredPane;
    JButton Start;
    JButton Exit;
    JPanel Menu;
    JLabel Title;
    AnimatedBackgroundPanel Background;
    Color darkRed;
    Color darkYellow;

    public MyFrame() {
        layeredPane = new JLayeredPane();
        this.setSize(850, 528);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);

        darkRed = new Color(139, 0, 0);
        darkYellow = new Color(204, 144, 0);

        UIManager.put("Button.select", darkRed);
        Start = new JButton();
        Start.addActionListener(this);
        Exit = new JButton();
        Exit.addActionListener(this);
        Menu = new JPanel();
        Background = new AnimatedBackgroundPanel("C:\\Users\\andri\\IdeaProjects\\RocketMania\\src\\SpaceShooterGame\\spacegif2.gif");
        Background.setBounds(0, 0, 850, 528);
        layeredPane.setBounds(0, 0, 850, 528);

        Menu.setLayout(new GridLayout(2, 1));
        Menu.setBounds(325, 325, 170, 70);

        Start.setText("Start");
        Font MenuFont = new Font("Comic Sans MS", Font.BOLD, 20);
        Start.setFont(MenuFont);
        Start.setBackground(Color.BLACK);
        Start.setForeground(Color.CYAN);
        Start.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        Start.setFocusPainted(false);
        Start.addMouseListener(this);
        Exit.setText("Exit");
        Exit.setFont(MenuFont);
        Exit.setBackground(Color.BLACK);
        Exit.setForeground(Color.CYAN);
        Exit.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        Exit.setFocusPainted(false);
        Exit.addMouseListener(this);

        Menu.add(Start);
        Menu.add(Exit);
        Menu.setVisible(true);

        Title = new JLabel();
        Title.setText("Rocket Mania");
        Font TitleFont = new Font("Comic Sans MS", Font.BOLD, 28);
        Title.setForeground(Color.CYAN);
        Title.setBounds(320, 20, 200, 100);
        Title.setFont(TitleFont);

        layeredPane.add(Menu, JLayeredPane.MODAL_LAYER);
        layeredPane.add(Background, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(Title, JLayeredPane.PALETTE_LAYER);
        layeredPane.setVisible(true);
        this.add(layeredPane);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(Exit)) this.dispose();
        else if (e.getSource().equals(Start)) {
            this.dispose();
            gameFrame = new GameFrame();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource().equals(Exit)) Exit.setBackground(darkYellow);
        else if (e.getSource().equals(Start)) Start.setBackground(darkYellow);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(Start)) Start.setBackground(Color.BLACK);
        else if (e.getSource().equals(Exit)) Exit.setBackground(Color.BLACK);
    }
}
