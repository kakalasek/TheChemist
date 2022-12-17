package Solutions;

import java.awt.*;

public class Segment {
    private int state = 0; //stav segmentu
    private int value = 0; //hodnota segmentu
    private int x; //pozice x
    private int y; //pozice y

    //konstuktor
    public Segment(){
    }

    //gettery a settery
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    //metoda pro vykresleni
    public void draw(Graphics2D g2){
        g2.drawString("Hello", x, y); //nakresleni tvaru na souradnice
    }
}
