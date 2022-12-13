package Main;

import Screens.GameMenu;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel {
    private final int panelWidth = 1000; //sirka 
    private final int panelHeight = 700;
    GameMenu gameMenu = new GameMenu(this);

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

    //metoda pro zobrazovani kontentu
    public void paintComponent(Graphics g){
        super.paintComponent(g); // ??
        Graphics2D g2 = (Graphics2D)g; //vytvoreni 2d grafiky

        gameMenu.draw(g2);
    }
}
