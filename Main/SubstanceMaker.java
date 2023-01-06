package Main;

import Main.TabbedPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SubstanceMaker extends JPanel {
    private int currentRow = 0;
    private int currentColumn = 0;
    private int lastFocusedRow = 0;
    private int lastFocusedColumn = 0;
    public static JLabel[][] elementMatrix;
    private Font elementFont;
    private Font buttonFont;
    Action elementUp;
    Action elementDown;
    Action elementLeft;
    Action elementRight;
    Action elementSwitch;
    ChemicalPanel chemicalPanel = new ChemicalPanel();
    JButton get;
    JButton makeBlueprint;
    JButton clear;

    public SubstanceMaker() {
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.add(chemicalPanel);
        loadElementMatrix();
        manageKeyBindings();
        manageButtons();
        setUpActionListeners();
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
        clear = new JButton("C");
        buttonFont = new Font("Serif", Font.BOLD, 30);

        get.setBounds(50, 610, 100, 50);
        makeBlueprint.setBounds(200, 610, 300, 50);
        clear.setBounds(630, 610, 60, 50);

        get.setFont(buttonFont);
        makeBlueprint.setFont(buttonFont);
        clear.setFont(buttonFont);

        this.add(get);
        this.add(makeBlueprint);
        this.add(clear);
    }

    private boolean isChemical() throws IOException {
        File file = new File("/home/pipa/TheChemist/Main/solutions");
        ArrayList<String[]> solutions = FileHandler.readCSV(file);

        for(String[] solution : solutions){
            if(Integer.parseInt(solution[1]) == getChemicalID()){
                return true;
            }
        }
        return false;
    }

    private int getChemicalID() throws IOException {
        File file = new File("/home/pipa/TheChemist/Main/elementValues");
        ArrayList<String[]> elementValues = FileHandler.readCSV(file);
        int chemicalID = 0;

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 13; j++) {
                if(elementMatrix[i][j].getText().equals(".")){
                    continue;
                }
                for(String[] element : elementValues){
                    if(element[0].equals(elementMatrix[i][j].getText())){
                        chemicalID += Integer.parseInt(element[1]);
                    }
                }
            }
        }
        return chemicalID;
    }

    private String getChemical() throws IOException {
        File file = new File("/home/pipa/TheChemist/Main/solutions");
        ArrayList<String[]> solutions = FileHandler.readCSV(file);
        String chemical = "";

                for(String[] solution : solutions){
                    if(Integer.parseInt(solution[1]) == getChemicalID()){
                        chemical = solution[0];
                        break;
                    }
                }
        return chemical;
    }

    private boolean isThere(String id) throws IOException {
        File file = new File("/home/pipa/TheChemist/Main/resources");
        ArrayList<String[]> resources = FileHandler.readCSV(file);
        for(String[] resource : resources){
            if(resource[1].equals(id)){
                return true;
            }
        }
        return false;
    }

    private String numberIsThere(String id) throws IOException {
        File file = new File("/home/pipa/TheChemist/Main/resources");
        ArrayList<String[]> resources = FileHandler.readCSV(file);
        for(String[] resource : resources){
            if(resource[1].equals(id)){
                return resource[2];
            }
        }
        return null;
    }

    private void createBlueprint() throws IOException {
        String blueprint = "";
        File file = new File("/home/pipa/TheChemist/Main/BlueprintCodes");
        blueprint += getChemicalID();
        for(int i = 0; i < 12; i++){
            for(int j = 0; j < 13; j++){
                if(elementMatrix[i][j].getText().equals(".") == false){
                    blueprint += "," + elementMatrix[i][j].getText() + "," + i + "," + j;
                }
            }
        }
        FileHandler.writeCSV(file, blueprint, true);
    }

    private void setUpActionListeners() {
        ActionListener getElement = new ActionListener() {
            File file = new File("/home/pipa/TheChemist/Main/resources");
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                try{
                    if(isChemical() == true) {
                        if(isThere(String.valueOf(getChemicalID())) == true){
                            int nextValue = Integer.parseInt(numberIsThere(String.valueOf(getChemicalID()))) + 1;
                            String out = getChemical() + "," + getChemicalID() + "," + nextValue;
                            FileHandler.writeCSV(file, out, true);
                        }else {
                            String out = getChemical() + "," + getChemicalID() + "," + 1;
                            FileHandler.writeCSV(file, out, true);
                        }
                    }
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        };
        get.addActionListener(getElement);
        ActionListener createBlueprint = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                if(isChemical() == true) {
                    createBlueprint();
                }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        };
        makeBlueprint.addActionListener(createBlueprint);

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
