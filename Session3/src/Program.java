/**
 * Created by q on 11/5/2016.
 */
public class Program {
    public static void main(String[] args) {
        System.out.println("Hello there!");
        //Game game=new Game();
        GameWindow gameWindow = new GameWindow();
        Thread thread = new Thread(gameWindow);
        thread.start();
    }
}
