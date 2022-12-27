package Screens;

import Main.TabbedPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SubstanceMaker extends JPanel {
    private int currentRow = 0;
    private int currentColumn = 0;
    private int lastFocusedRow = 0;
    private int lastFocusedColumn = 0;
    private JLabel[][] elementMatrix;
    private Font elementFont;
    Action elementUp;
    Action elementDown;
    Action elementLeft;
    Action elementRight;
    Action elementSwitch;

    public SubstanceMaker() {
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        loadElementMatrix();

        elementUp = new ElementUp();
        elementDown = new ElementDown();
        elementLeft = new ElementLeft();
        elementRight = new ElementRight();
        elementSwitch = new ElementSwitch();

        this.getInputMap().put(KeyStroke.getKeyStroke('w'), "elementUp");
        this.getActionMap().put("elementUp", elementUp);
        this.getInputMap().put(KeyStroke.getKeyStroke('s'), "elementDown");
        this.getActionMap().put("elementDown", elementDown);
        this.getInputMap().put(KeyStroke.getKeyStroke('a'), "elementLeft");
        this.getActionMap().put("elementLeft", elementLeft);
        this.getInputMap().put(KeyStroke.getKeyStroke('d'), "elementRight");
        this.getActionMap().put("elementRight", elementRight);

        this.getInputMap().put(KeyStroke.getKeyStroke('h'), "hydrogen");
        this.getActionMap().put("hydrogen", elementSwitch);
        this.getInputMap().put(KeyStroke.getKeyStroke('c'), "carbon");
        this.getActionMap().put("carbon", elementSwitch);
        this.getInputMap().put(KeyStroke.getKeyStroke('n'), "nitrogen");
        this.getActionMap().put("nitrogen", elementSwitch);
        this.getInputMap().put(KeyStroke.getKeyStroke('o'), "oxygen");
        this.getActionMap().put("oxygen", elementSwitch);
        this.getInputMap().put(KeyStroke.getKeyStroke('f'), "fluorine");
        this.getActionMap().put("fluorine", elementSwitch);
        this.getInputMap().put(KeyStroke.getKeyStroke('-'), "bond");
        this.getActionMap().put("bond", elementSwitch);
        this.getInputMap().put(KeyStroke.getKeyStroke('='), "double bond");
        this.getActionMap().put("double bond", elementSwitch);
        this.getInputMap().put(KeyStroke.getKeyStroke('_'), "triple bond");
        this.getActionMap().put("triple bond", elementSwitch);
        this.getInputMap().put(KeyStroke.getKeyStroke('.'), "dot");
        this.getActionMap().put("dot", elementSwitch);
    }

    private void loadElementMatrix(){
        int x = 3;
        int y = -3;

        elementMatrix = new JLabel[13][13];
        elementFont = new Font("Serif", Font.BOLD, 24);

        for(int i = 0; i < 13; i++){
            for(int j = 0; j < 13; j++){
                elementMatrix[i][j] = new JLabel(".");
                elementMatrix[i][j].setFont(elementFont);
                elementMatrix[i][j].setBounds(x, y, 50,50);
                elementMatrix[i][j].setOpaque(true);
                elementMatrix[i][j].setBackground(Color.WHITE);
                this.add(elementMatrix[i][j]);
                x += 50;
            }
            x = 3;
            y += 50;
        }
    }

    private class ElementUp extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(currentRow <= 0){
                currentRow = 0;
            }else {
                currentRow--;
            }
            elementMatrix[lastFocusedRow][lastFocusedColumn].setBackground(Color.WHITE);
            elementMatrix[currentRow][currentColumn].setBackground(Color.GRAY);

            lastFocusedRow = currentRow;
            lastFocusedColumn = currentColumn;
        }
    }
    private class ElementDown extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(currentRow >= 12){
                currentRow = 12;
            }else {
                currentRow++;
            }
            elementMatrix[lastFocusedRow][lastFocusedColumn].setBackground(Color.WHITE);
            elementMatrix[currentRow][currentColumn].setBackground(Color.GRAY);

            lastFocusedRow = currentRow;
            lastFocusedColumn = currentColumn;
        }
    }
    private class ElementLeft extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(currentColumn <= 0){
                currentColumn = 0;
            }else {
                currentColumn--;
            }
            elementMatrix[lastFocusedRow][lastFocusedColumn].setBackground(Color.WHITE);
            elementMatrix[currentRow][currentColumn].setBackground(Color.GRAY);

            lastFocusedRow = currentRow;
            lastFocusedColumn = currentColumn;
        }
    }
    private class ElementRight extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(currentColumn >= 12){
                currentColumn = 12;
            }else {
                currentColumn++;
            }
            elementMatrix[lastFocusedRow][lastFocusedColumn].setBackground(Color.WHITE);
            elementMatrix[currentRow][currentColumn].setBackground(Color.GRAY);

            lastFocusedRow = currentRow;
            lastFocusedColumn = currentColumn;
        }
    }
    private class ElementSwitch extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
           switch(actionEvent.getActionCommand()){
               case "h": elementMatrix[currentRow][currentColumn].setText("H");
                   break;
               case "c": elementMatrix[currentRow][currentColumn].setText("C");
                   break;
               case "n": elementMatrix[currentRow][currentColumn].setText("N");
                   break;
               case "o": elementMatrix[currentRow][currentColumn].setText("O");
                   break;
               case "f": elementMatrix[currentRow][currentColumn].setText("F");
                   break;
               case "-": elementMatrix[currentRow][currentColumn].setText("-");
                   break;
               case "=": elementMatrix[currentRow][currentColumn].setText("=");
                   break;
               case "_": elementMatrix[currentRow][currentColumn].setText("_");
                   break;
               case ".": elementMatrix[currentRow][currentColumn].setText(".");
                   break;
           }
        }
    }
}
