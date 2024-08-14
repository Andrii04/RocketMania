package SpaceShooterGame;

public class Main {
    static MyFrame frame;

    public static void restartGame() {
        frame.dispose();
        main(new String[] {});
    }

    public static void main(String[] args){ frame = new MyFrame(); }
}