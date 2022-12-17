package Main;

import Screens.GameMenu;
import Screens.SubstanceMaker;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyListener;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable{
    public static int gameState = 0; //momentalni stav hry, resp. vykreslovana obrazovka
    private final int panelWidth = 1000; //sirka panelu/okna
    private final int panelHeight = 700; //vyska panelu/okne
    private final double updateRate = 1.0/60.0; //FPS
    private GameMenu gameMenu;
    private SubstanceMaker substanceMaker;
    private Thread gameThread;

    //konstruktor
    public GamePanel() throws IOException {
        this.setPreferredSize(new Dimension(panelWidth, panelHeight)); //nastaveni velikosti
        this.setBackground(Color.BLACK); //nastaveni pozadi
        this.setLayout(null); //zadny layout
        gameMenu = new GameMenu(this);
        substanceMaker = new SubstanceMaker(this);
        this.addKeyListener(gameMenu); //pridani keylisteneru s gameMenu
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

        //vyber vykreslovane obrazovky
        if(gameState == 0){
            gameMenu.draw(g2);
        }else if(gameState == 1){
            substanceMaker.draw(g2);
        }
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
