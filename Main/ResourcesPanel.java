package Main;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ResourcesPanel extends JPanel {
    JLabel[][] resourceMatrix;
    Font resourceFont = new Font("Serif", Font.PLAIN, 10);

    public ResourcesPanel() throws IOException {
        this.setPreferredSize(new Dimension(500, 400));
        this.setLayout(null);
        setUpResourceMatrix();
        loadResources();
    }

    private void setUpResourceMatrix(){
        resourceMatrix = new JLabel[17][6];
        int x = 5;
        int y = 5;

        for(int i = 0; i < 17; i++){
            for(int j = 0; j < 6; j++){
                resourceMatrix[i][j] = new JLabel();
                resourceMatrix[i][j].setFont(resourceFont);
                resourceMatrix[i][j].setBounds(x, y, 75, 20);
                this.add(resourceMatrix[i][j]);
                x += 80;
            }
            x = 5;
            y += 23;
        }
    }

    private void loadResources() throws IOException {
        File file = new File("/home/pipa/TheChemist/Main/resources");
        int i = 0;
        int j = 0;

        ArrayList<String[]> resources = FileHandler.readCSV(file);
        for(String[] resource : resources){
            resourceMatrix[i][j].setText(resource[0]);
            i++;
            if(i == 16){
                i = 0;
                j++;
            }
        }
    }
}
