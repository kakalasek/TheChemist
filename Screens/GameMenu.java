package Screens;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameMenu extends Screen {
    private int selectNum = 0;
    private int selectState = 0;
    public GameMenu(GamePanel gamePanel) {
        super(gamePanel);
    }

    //gettery a settery
    public int getSelectState(){
        return selectState;
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.white);

        //nazev
        g2.setFont(new Font("SansSerif", Font.BOLD, 64));
        g2.drawString("TheChemist", gamePanel.getPanelWidth()/2 - (g2.getFontMetrics().stringWidth("TheChemist")/2), 200);

        //settings, start, load
        g2.setFont(new Font("SansSerif", Font.ITALIC, 32));
        g2.drawString("Start", gamePanel.getPanelWidth() / 2 - (g2.getFontMetrics().stringWidth("Start") / 2), 330);
        g2.drawString("Load", gamePanel.getPanelWidth() / 2 - (g2.getFontMetrics().stringWidth("Load") / 2), 400);
        g2.drawString("Settings", gamePanel.getPanelWidth() / 2 - (g2.getFontMetrics().stringWidth("Settings") / 2), 470);

        //aby kursor byl pouze na moznostech a nezmizel
        if(selectNum < 0){
            selectNum = 0;
        }if(selectNum > 2){
            selectNum = 2;
        }

        if(selectNum == 0) { //kursor na startu
            g2.drawString(">", gamePanel.getPanelWidth() / 2 - (g2.getFontMetrics().stringWidth("Start") / 2) - 30, 330);
        }else if(selectNum == 1){ //kursor na loadu
            g2.drawString(">", gamePanel.getPanelWidth() / 2 - (g2.getFontMetrics().stringWidth("Load") / 2) - 30, 400);
        }else if(selectNum == 2) { //kursor na settings
            g2.drawString(">", gamePanel.getPanelWidth() / 2 - (g2.getFontMetrics().stringWidth("Settings") / 2) - 30, 470);
        }

        //obrazek
        BufferedImage labBottle = null;
        try {
            labBottle = ImageIO.read(new File("Resources/LabBottle.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g2.drawImage(labBottle, gamePanel.getPanelWidth()/2 + (g2.getFontMetrics().stringWidth("TheChemist")), 100, 150, 150, null);


    }


    //KeyListener
    @Override
    public void keyTyped(KeyEvent keyEvent) {
        switch (keyEvent.getKeyChar()) {
            case 'w': selectNum--;
                break;
            case 's': selectNum++;
            break;
            case '\n':
                if(selectNum == 0){
                    selectState = 1;
            }
            break;
        }

    }

}
