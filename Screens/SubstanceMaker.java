package Screens;

import Main.GamePanel;
import Solutions.Segment;

import java.awt.*;

public class SubstanceMaker extends Screen{
    private Segment segmentMatrix[][]; //pole segmentu
    private int segmentPositionX; //pozice segmentu na ose X
    private int segmentPositionY; //pozice segmentu na ose Y

    //konstruktor
    public SubstanceMaker(GamePanel gamePanel) {
        super(gamePanel);
        loadSegmentMatrix();
    }

    //nacteni segmenut do matrixu
    private void loadSegmentMatrix(){
        segmentMatrix = new Segment[3][3];

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                segmentMatrix[i][j] = new Segment();
            }
        }
    }

    //metoda pro vykreslovani
    public void draw(Graphics2D g2){
        segmentPositionX = 100; //nastaveni zakladni pozice X
        segmentPositionY = 100; //nastaveni zakladni pozice Y

        g2.setColor(Color.white); //barva segmentu

        //vykresleni segmentu
        for(int i = 0; i < 3; i++){
            segmentPositionX = 100;
            segmentPositionY += 50;
            for(int j = 0; j < 3; j++){
                segmentMatrix[i][j].setX(segmentPositionX);
                segmentMatrix[i][j].setY(segmentPositionY);
                segmentMatrix[i][j].draw(g2);
                segmentPositionX += 50;
            }
        }
    }
}
