package Main;

import Screens.GameMenu;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel {
    GameMenu gameMenu = new GameMenu();

    //konstruktor
    public GamePanel(){
        this.setBackground(Color.BLACK); //nastaveni pozadi
        this.setFocusable(true); //nastaveni soustredeni
    }

    //metoda pro zobrazovani kontentu
    public void paintComponent(Graphics g){
        super.paintComponent(g); // ??
        Graphics2D g2 = (Graphics2D)g; //vytvoreni 2d grafiky

        gameMenu.draw(g2);
    }
}
