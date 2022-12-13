package Screens;

import java.awt.*;

public class GameMenu {

    //metoda pro kresleni, resp. zobrazovani kontentu
    public void draw(Graphics2D g2){
        g2.setColor(Color.white);
        g2.setFont(new Font("SansSerif", Font.BOLD, 64));
        g2.drawString("TheChemist", 100, 100);

    }
}
