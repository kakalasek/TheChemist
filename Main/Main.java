package Main;

import javax.swing.JFrame;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {

        //Nastaveni okna
        JFrame frame = new JFrame();
        frame.setResizable(false); //zakaz zmeny velikosti okna
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE); //ukonceni programu po ukonceni okna

        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel); //pridani panelu
        frame.pack();

        frame.setLocationRelativeTo(null); //zarovnani doprostred obrazovky
        frame.setVisible(true); //zviditelneni okna

        gamePanel.startGameThread(); //start vlakna
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    }
}
