package Main;

import Screens.GameMenu;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    private final int panelWidth = 1000; //sirka 
    private final int panelHeight = 700;
    private final double updateRate = 1.0/60.0;
    GameMenu gameMenu = new GameMenu(this);
    Thread gameThread;

    //konstruktor
    public GamePanel(){
        this.setPreferredSize(new Dimension(panelWidth, panelHeight)); //nastaveni velikosti
        this.setBackground(Color.BLACK); //nastaveni pozadi
        this.setFocusable(true); //nastaveni soustredeni
    }

    //gettery a settery
    public int getPanelWidth() {
        return panelWidth;
    }

    public int getPanelHeight() {
        return panelHeight;
    }

    //metoda pro start vlakna
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    //metoda pro zobrazovani kontentu
    public void paintComponent(Graphics g){
        super.paintComponent(g); // ??
        Graphics2D g2 = (Graphics2D)g; //vytvoreni 2d grafiky

        gameMenu.draw(g2);
    }

    public void Update(){

    }

    //gameLoop
    @Override
    public void run() {
        int i = 0;
        double accumulator = 0;
        long currentTime, lastUpdate = System.currentTimeMillis();
        double lastRenderTimeInSeconds;

        while(gameThread != null){
            currentTime = System.currentTimeMillis();
            lastRenderTimeInSeconds = (currentTime - lastUpdate) /1000;
            accumulator += lastRenderTimeInSeconds;
            lastUpdate = currentTime;

            while(accumulator > updateRate){
                Update();
                accumulator -= updateRate;
            }
            repaint();
        }
    }
}
