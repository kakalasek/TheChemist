package Main.SidePanels.AP_Features;

import Main.Panels.ChemicalMaker;
import Main.Utilities.BlueprintHandler;
import Main.Utilities.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BlueprintsPanel extends JPanel {
    private JButton[][] blueprintMatrix;
    private File blueprints = new File("/home/pipa/TheChemist/Main/Files/Blueprints");
    private File chemicals = new File("/home/pipa/TheChemist/Main/Files/Chemicals");
    private Font buttonFont;
    BlueprintHandler blueprintHandler = new BlueprintHandler();

    public BlueprintsPanel() throws IOException {
        this.setPreferredSize(new Dimension(500, 400));
        this.setLayout(null);

        loadButtons();
        loadBlueprints();
    }

    /**
     * Renders the matrix of buttons that represent blueprints. Just creates them, doesn't give them any special behavior
     */
    private void loadButtons(){
        blueprintMatrix = new JButton[11][6];
        buttonFont = new Font("Serif", Font.PLAIN, 10);
        int x = 5;
        int y = 3;

        for(int row = 0; row < 11; row++) {
            for (int column = 0; column < 6; column++) {
                blueprintMatrix[row][column] = new JButton();
                blueprintMatrix[row][column].setBounds(x, y, 73, 30);
                blueprintMatrix[row][column].setFont(buttonFont);
                this.add(blueprintMatrix[row][column]);
                x += 83;
            }
            x = 5;
            y += 35;
        }
    }

    /**
     * Is used to give an ability to load a blueprint to respective buttons
     * @throws IOException
     */
    private void loadBlueprints() throws IOException {
        ArrayList<String[]> blueps = FileHandler.readCSV(blueprints);
        ArrayList<String[]> chems = FileHandler.readCSV(chemicals);
        int row = 0;
        int column = 0;

        for(String[] singleBlueprint : blueps){
            for(String[] singleChemical : chems){
                if(singleBlueprint[0].equals(singleChemical[1])){
                    blueprintMatrix[row][column].setText(singleChemical[0]);
                    int finalX = row;
                    int finalY = column;
                    blueprintMatrix[row][column].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            try {
                                ChemicalMaker.clearElementMatrix();
                                blueprintHandler.loadBlueprint(blueprintMatrix[finalX][finalY], blueprints, chemicals);
                            } catch (IOException e) {
                                System.out.println("Error while loading blueprints!");
                                throw new RuntimeException(e);
                            }
                        }
                    });
                    row++;
                    break;
                }
                if(row == 11){
                    row = 0;
                    column++;
                }
            }
        }
    }
}
