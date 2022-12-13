package Screens;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameMenu extends Screen{
    public GameMenu(GamePanel gamePanel) {
        super(gamePanel);
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.white);

        //nazev
        g2.setFont(new Font("SansSerif", Font.BOLD, 64));
        g2.drawString("TheChemist", gamePanel.getPanelWidth()/2 - (g2.getFontMetrics().stringWidth("TheChemist")/2), 200);

        //setting, start, load
        g2.setFont(new Font("SansSerif", Font.ITALIC, 32));
        g2.drawString("Start", gamePanel.getPanelWidth()/2 - (g2.getFontMetrics().stringWidth("Start")/2), 330);
        g2.drawString("Load", gamePanel.getPanelWidth()/2 - (g2.getFontMetrics().stringWidth("Load")/2), 400);
        g2.drawString("Settings", gamePanel.getPanelWidth()/2 - (g2.getFontMetrics().stringWidth("Settings")/2), 480);

        //obrazek
        BufferedImage labBottle = null;
        try {
            labBottle = ImageIO.read(new File("Resources/LabBottle.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g2.drawImage(labBottle, gamePanel.getPanelWidth()/2 + (g2.getFontMetrics().stringWidth("TheChemist")), 100, 150, 150, null);
    }
}
