package Main.SidePanels.AP_Features;

import Main.Utilities.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ResourcesPanel extends JPanel {
    private JLabel[][] resourceMatrix;
    private Font labelFont;
    private File resources = new File("/home/pipa/TheChemist/Main/Files/Resources");

    public ResourcesPanel() throws IOException {
        this.setPreferredSize(new Dimension(500, 400));
        this.setLayout(null);

        setUpResourceMatrix();
        loadResources();
    }

    /**
     * Renders the matrix of resources
     */
    private void setUpResourceMatrix(){
        labelFont = new Font("Serif", Font.PLAIN, 10);
        resourceMatrix = new JLabel[17][6];
        int x = 5;
        int y = 5;

        for(int row = 0; row < 17; row++){
            for(int column = 0; column < 6; column++){
                resourceMatrix[row][column] = new JLabel();
                resourceMatrix[row][column].setFont(labelFont);
                resourceMatrix[row][column].setBounds(x, y, 75, 20);
                this.add(resourceMatrix[row][column]);
                x += 80;
            }
            x = 5;
            y += 23;
        }
    }

    /**
     * Loads all resources from Resources file
     * @throws IOException
     */
    private void loadResources() throws IOException {
        int row = 0;
        int column = 0;

        ArrayList<String[]> res = FileHandler.readCSV(resources);
        for(String[] singleResource : res){
            resourceMatrix[row][column].setText(singleResource[0] + ";" + singleResource[2]);
            row++;
            if(row == 16){
                row = 0;
                column++;
            }
        }
    }
}
