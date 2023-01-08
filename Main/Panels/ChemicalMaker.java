package Main.Panels;

import Main.SidePanels.AccessPanel;
import Main.Utilities.BlueprintHandler;
import Main.Utilities.FileHandler;
import Main.Utilities.ChemicalHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ChemicalMaker extends JPanel {
    private int currentFocusedRow = 0;
    private int currentFocusedColumn = 0;
    private int lastFocusedRow = 0;
    private int lastFocusedColumn = 0;
    public static JLabel[][] elementMatrix;
    private Font elementFont;
    private Font buttonFont;
    private Action elementUp;
    private Action elementDown;
    private Action elementLeft;
    private Action elementRight;
    private Action elementSwitch;
    private AccessPanel chemicalPanel = new AccessPanel();
    private JButton get;
    private JButton makeBlueprint;
    private JButton clear;
    private File resources = new File("/home/pipa/TheChemist/Main/Files/Resources");
    private File elementValues = new File("/home/pipa/TheChemist/Main/Files/ElementValues");
    private File chemicals = new File("/home/pipa/TheChemist/Main/Files/Chemicals");
    private File blueprints = new File("/home/pipa/TheChemist/Main/Files/Blueprints");
    private ChemicalHandler chemicalHandler = new ChemicalHandler();
    private BlueprintHandler blueprintHandler = new BlueprintHandler();

    public ChemicalMaker() {
        this.setLayout(null);
        this.setBackground(Color.WHITE);

        this.add(chemicalPanel);

        loadElementMatrix();
        manageKeyBindings();
        manageButtons();
        setUpActionListeners();
    }

    /**
     * Renders the element matrix on the correct spot on the panel and sets default properties of each element label(background, default text,...)
     */
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

    /**
     * Renders the buttons on their respective spots on the panel. Also sets text and font of each of them
     */
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

    /**
     * Method for clearing the element matrix -> setting every element label to the default text
     */
    public static void clearElementMatrix(){
        for(int row = 0; row < 12; row++) {
            for (int column = 0; column < 13; column++) {
                elementMatrix[row][column].setText(".");
            }
        }
    }

    /**
     * Adds a respective action listener to each button
     */
    private void setUpActionListeners() {
        ActionListener getElement = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                try{
                    chemicalHandler.addChemicalToResources(chemicals, elementValues, resources);
                }catch(IOException e){
                    System.out.println("Error while obtaining the chemical!");
                    e.printStackTrace();
                }
            }
        };
        get.addActionListener(getElement);
        ActionListener createBlueprint = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    blueprintHandler.createBlueprint(blueprints, elementValues, chemicals);
                    } catch (IOException e) {
                        System.out.println("Error while creating blueprint of the chemical!");
                        throw new RuntimeException(e);
                    }
                }
        };
        makeBlueprint.addActionListener(createBlueprint);

        ActionListener clearElementMatrix = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                clearElementMatrix();
            }
        };
        clear.addActionListener(clearElementMatrix);
    }

    /**
     * A support method to reduce redundancy. Used with moving around the element matrix in several methods
     */
    private void manageElementMatrixFocus(){
        elementMatrix[lastFocusedRow][lastFocusedColumn].setBackground(Color.WHITE);
        elementMatrix[currentFocusedRow][currentFocusedColumn].setBackground(Color.GRAY);

        lastFocusedRow = currentFocusedRow;
        lastFocusedColumn = currentFocusedColumn;
    }

    /**
     * Takes care of the key bindings. Do I need to explain more?
     */
    private void manageKeyBindings(){
        elementUp = new ElementUp();
        elementDown = new ElementDown();
        elementLeft = new ElementLeft();
        elementRight = new ElementRight();
        elementSwitch = new ElementSwitch();

        this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "elementUp");
        this.getActionMap().put("elementUp", elementUp);
        this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "elementDown");
        this.getActionMap().put("elementDown", elementDown);
        this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "elementLeft");
        this.getActionMap().put("elementLeft", elementLeft);
        this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "elementRight");
        this.getActionMap().put("elementRight", elementRight);

        this.getInputMap().put(KeyStroke.getKeyStroke('a'), "a");
        this.getActionMap().put("a", elementSwitch);
        this.getInputMap().put(KeyStroke.getKeyStroke('b'), "b");
        this.getActionMap().put("b", elementSwitch);
        this.getInputMap().put(KeyStroke.getKeyStroke('c'), "c");
        this.getActionMap().put("c", elementSwitch);
        this.getInputMap().put(KeyStroke.getKeyStroke('d'), "d");
        this.getActionMap().put("d", elementSwitch);
        this.getInputMap().put(KeyStroke.getKeyStroke('e'), "e");
        this.getActionMap().put("e", elementSwitch);
        this.getInputMap().put(KeyStroke.getKeyStroke('f'), "f");
        this.getActionMap().put("f", elementSwitch);
        this.getInputMap().put(KeyStroke.getKeyStroke('g'), "g");
        this.getActionMap().put("g", elementSwitch);
        this.getInputMap().put(KeyStroke.getKeyStroke('h'), "h");
        this.getActionMap().put("h", elementSwitch);
        this.getInputMap().put(KeyStroke.getKeyStroke('i'), "i");
        this.getActionMap().put("i", elementSwitch);
        this.getInputMap().put(KeyStroke.getKeyStroke('k'), "k");
        this.getActionMap().put("k", elementSwitch);
        this.getInputMap().put(KeyStroke.getKeyStroke('l'), "l");
        this.getActionMap().put("l", elementSwitch);
        this.getInputMap().put(KeyStroke.getKeyStroke('m'), "m");
        this.getActionMap().put("m", elementSwitch);
        this.getInputMap().put(KeyStroke.getKeyStroke('n'), "n");
        this.getActionMap().put("n", elementSwitch);
        this.getInputMap().put(KeyStroke.getKeyStroke('o'), "o");
        this.getActionMap().put("o", elementSwitch);
        this.getInputMap().put(KeyStroke.getKeyStroke('p'), "p");
        this.getActionMap().put("p", elementSwitch);
        this.getInputMap().put(KeyStroke.getKeyStroke('r'), "r");
        this.getActionMap().put("r", elementSwitch);
        this.getInputMap().put(KeyStroke.getKeyStroke('s'), "s");
        this.getActionMap().put("s", elementSwitch);
        this.getInputMap().put(KeyStroke.getKeyStroke('t'), "t");
        this.getActionMap().put("t", elementSwitch);
        this.getInputMap().put(KeyStroke.getKeyStroke('u'), "u");
        this.getActionMap().put("u", elementSwitch);
        this.getInputMap().put(KeyStroke.getKeyStroke('v'), "v");
        this.getActionMap().put("v", elementSwitch);
        this.getInputMap().put(KeyStroke.getKeyStroke('w'), "w");
        this.getActionMap().put("w", elementSwitch);
        this.getInputMap().put(KeyStroke.getKeyStroke('y'), "y");
        this.getActionMap().put("y", elementSwitch);
        this.getInputMap().put(KeyStroke.getKeyStroke('z'), "z");
        this.getActionMap().put("z", elementSwitch);
        this.getInputMap().put(KeyStroke.getKeyStroke('-'), "-");
        this.getActionMap().put("-", elementSwitch);
        this.getInputMap().put(KeyStroke.getKeyStroke('='), "=");
        this.getActionMap().put("=", elementSwitch);
        this.getInputMap().put(KeyStroke.getKeyStroke('_'), "_");
        this.getActionMap().put("_", elementSwitch);
        this.getInputMap().put(KeyStroke.getKeyStroke('.'), ".");
        this.getActionMap().put(".", elementSwitch);
    }

    /*
    The code below creates different actions to be used with different keys. Namely actions to move up, down, left, right on the element matrix
    and choosing an element on certain element label
     */
    private class ElementUp extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(currentFocusedRow <= 0){
                currentFocusedRow = 0;
            }else {
                currentFocusedRow--;
            }

            manageElementMatrixFocus();
        }
    }
    private class ElementDown extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(currentFocusedRow >= 11){
                currentFocusedRow = 11;
            }else {
                currentFocusedRow++;
            }

            manageElementMatrixFocus();
        }
    }
    private class ElementLeft extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(currentFocusedColumn <= 0){
                currentFocusedColumn = 0;
            }else {
                currentFocusedColumn--;
            }

            manageElementMatrixFocus();
        }
    }
    private class ElementRight extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(currentFocusedColumn >= 12){
                currentFocusedColumn = 12;
            }else {
                currentFocusedColumn++;
            }

            manageElementMatrixFocus();
        }
    }
    private class ElementSwitch extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
           switch(actionEvent.getActionCommand()){
               case "a":
                   if(elementMatrix[currentFocusedRow][currentFocusedColumn].getText().equals("N")){
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("Na");
                   }else if(elementMatrix[currentFocusedRow][currentFocusedColumn].getText().equals("C")){
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("Ca");
                   }else if(elementMatrix[currentFocusedRow][currentFocusedColumn].getText().equals("G")){
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("Ga");
                   }else{
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("A");
                   }
                   break;
               case "b":
                   if(elementMatrix[currentFocusedRow][currentFocusedColumn].getText().equals("R")){
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("Rb");
                   }else if(elementMatrix[currentFocusedRow][currentFocusedColumn].getText().equals("N")){
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("Nb");
                   }else if(elementMatrix[currentFocusedRow][currentFocusedColumn].getText().equals("S")){
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("Sb");
                   }else {
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("B");
                   }
                   break;
               case "c":
                   if(elementMatrix[currentFocusedRow][currentFocusedColumn].getText().equals("S")){
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("Sc");
                   }else if(elementMatrix[currentFocusedRow][currentFocusedColumn].getText().equals("T")){
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("Tc");
                   }else {
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("C");
                   }
                   break;
               case "d":
                   if(elementMatrix[currentFocusedRow][currentFocusedColumn].getText().equals("P")){
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("Pd");
                   }else if(elementMatrix[currentFocusedRow][currentFocusedColumn].getText().equals("C")){
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("Cd");
                   }else{
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("D");
                   }
                   break;
               case "e":
                   if(elementMatrix[currentFocusedRow][currentFocusedColumn].getText().equals("B")){
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("Be");
                   }else if(elementMatrix[currentFocusedRow][currentFocusedColumn].getText().equals("F")){
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("Fe");
                   }else if(elementMatrix[currentFocusedRow][currentFocusedColumn].getText().equals("G")){
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("Ge");
                   }else if(elementMatrix[currentFocusedRow][currentFocusedColumn].getText().equals("S")){
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("Se");
                   }else if(elementMatrix[currentFocusedRow][currentFocusedColumn].getText().equals("T")){
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("Te");
                   }else{
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("E");
                   }
                   break;
               case "f": elementMatrix[currentFocusedRow][currentFocusedColumn].setText("F");
                   break;
               case "g":
                   if(elementMatrix[currentFocusedRow][currentFocusedColumn].getText().equals("M")){
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("Mg");
                   }else if(elementMatrix[currentFocusedRow][currentFocusedColumn].getText().equals("A")) {
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("Ag");
                   }else{
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("G");
                   }
                   break;
               case "h":
                   if(elementMatrix[currentFocusedRow][currentFocusedColumn].getText().equals("R")){
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("Rh");
                   }else{
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("H");
                   }
                   break;
               case "i":
                   if(elementMatrix[currentFocusedRow][currentFocusedColumn].getText().equals("L")){
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("Li");
                   }else if(elementMatrix[currentFocusedRow][currentFocusedColumn].getText().equals("S")) {
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("Si");
                   }else if(elementMatrix[currentFocusedRow][currentFocusedColumn].getText().equals("T")) {
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("Ti");
                   }else if(elementMatrix[currentFocusedRow][currentFocusedColumn].getText().equals("N")) {
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("Ni");
                   }else{
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("I");
                   }
                   break;
               case "k": elementMatrix[currentFocusedRow][currentFocusedColumn].setText("K");
                   break;
               case "l":
                   if(elementMatrix[currentFocusedRow][currentFocusedColumn].getText().equals("A")){
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("Al");
                   }else if(elementMatrix[currentFocusedRow][currentFocusedColumn].getText().equals("C")) {
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("Cl");
                   }else{
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("L");
                   }
                   break;
               case "m": elementMatrix[currentFocusedRow][currentFocusedColumn].setText("M");
                   break;
               case "n":
                   if(elementMatrix[currentFocusedRow][currentFocusedColumn].getText().equals("M")){
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("Mn");
                   }else if(elementMatrix[currentFocusedRow][currentFocusedColumn].getText().equals("I")) {
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("In");
                   }else if(elementMatrix[currentFocusedRow][currentFocusedColumn].getText().equals("S")) {
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("Sn");
                   }else if(elementMatrix[currentFocusedRow][currentFocusedColumn].getText().equals("Z")) {
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("Zn");
                   }else{
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("N");
                   }
                   break;
               case "o":
                   if(elementMatrix[currentFocusedRow][currentFocusedColumn].getText().equals("C")){
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("Co");
                   }else{
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("O");
                   }
                   break;
               case "p": elementMatrix[currentFocusedRow][currentFocusedColumn].setText("P");
                   break;
               case "r":
                   if(elementMatrix[currentFocusedRow][currentFocusedColumn].getText().equals("B")){
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("Br");
                   }else if(elementMatrix[currentFocusedRow][currentFocusedColumn].getText().equals("S")) {
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("Sr");
                   }else if(elementMatrix[currentFocusedRow][currentFocusedColumn].getText().equals("Z")) {
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("Zr");
                   }else if(elementMatrix[currentFocusedRow][currentFocusedColumn].getText().equals("C")) {
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("Cr");
                   }else{
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("R");
                   }
                   break;
               case "s":
                   if(elementMatrix[currentFocusedRow][currentFocusedColumn].getText().equals("A")){
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("As");
                   }else {
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("S");
                   }
                   break;
               case "t": elementMatrix[currentFocusedRow][currentFocusedColumn].setText("T");
                   break;
               case "u":
                   if(elementMatrix[currentFocusedRow][currentFocusedColumn].getText().equals("C")){
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("Cu");
                   }else if(elementMatrix[currentFocusedRow][currentFocusedColumn].getText().equals("R")){
                   elementMatrix[currentFocusedRow][currentFocusedColumn].setText("Ru");
                   }else{
                       elementMatrix[currentFocusedRow][currentFocusedColumn].setText("U");
                   }
                   break;
               case "v": elementMatrix[currentFocusedRow][currentFocusedColumn].setText("V");
                   break;
               case "w": elementMatrix[currentFocusedRow][currentFocusedColumn].setText("W");
                   break;
               case "y": elementMatrix[currentFocusedRow][currentFocusedColumn].setText("Y");
                   break;
               case "z": elementMatrix[currentFocusedRow][currentFocusedColumn].setText("Z");
                   break;
               case "-": elementMatrix[currentFocusedRow][currentFocusedColumn].setText("-");
                   break;
               case "=": elementMatrix[currentFocusedRow][currentFocusedColumn].setText("=");
                   break;
               case "_": elementMatrix[currentFocusedRow][currentFocusedColumn].setText("_");
                   break;
               case ".": elementMatrix[currentFocusedRow][currentFocusedColumn].setText(".");
                   break;
           }
        }
    }
}
