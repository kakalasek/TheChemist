package Main;

import Main.TabbedPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SubstanceMaker extends JPanel {
    private int currentRow = 0;
    private int currentColumn = 0;
    private int lastFocusedRow = 0;
    private int lastFocusedColumn = 0;
    private JLabel[][] elementMatrix;
    private Font elementFont;
    private Font buttonFont;
    Action elementUp;
    Action elementDown;
    Action elementLeft;
    Action elementRight;
    Action elementSwitch;
    BlueprintPanel blueprintPanel = new BlueprintPanel();
    JButton get;
    JButton makeBlueprint;
    JButton info;

    public SubstanceMaker() {
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.add(blueprintPanel);
        loadElementMatrix();
        manageKeyBindings();
        manageButtons();
    }

    private void loadElementMatrix(){
        int x = 3;
        int y = -3;

        elementMatrix = new JLabel[12][13];
        elementFont = new Font("Serif", Font.BOLD, 24);

        for(int i = 0; i < 12; i++){
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

    private void manageFocus(){
        elementMatrix[lastFocusedRow][lastFocusedColumn].setBackground(Color.WHITE);
        elementMatrix[currentRow][currentColumn].setBackground(Color.GRAY);

        lastFocusedRow = currentRow;
        lastFocusedColumn = currentColumn;
    }

    private void manageButtons(){
        get = new JButton("Get");
        makeBlueprint = new JButton("Make Blueprint");
        info = new JButton("i");
        buttonFont = new Font("Serif", Font.BOLD, 30);

        get.setBounds(50, 610, 100, 50);
        makeBlueprint.setBounds(200, 610, 300, 50);
        info.setBounds(630, 610, 50, 50);

        get.setFont(buttonFont);
        makeBlueprint.setFont(buttonFont);
        info.setFont(buttonFont);

        this.add(get);
        this.add(makeBlueprint);
        this.add(info);
    }

    private void manageKeyBindings(){
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

    private class ElementUp extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(currentRow <= 0){
                currentRow = 0;
            }else {
                currentRow--;
            }

            manageFocus();
        }
    }
    private class ElementDown extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(currentRow >= 11){
                currentRow = 11;
            }else {
                currentRow++;
            }

            manageFocus();
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

            manageFocus();
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

            manageFocus();
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
