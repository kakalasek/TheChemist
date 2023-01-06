package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BlueprintsPanel extends JPanel {

    JButton[][] blueprintMatrix;

    public BlueprintsPanel() throws IOException {
        this.setPreferredSize(new Dimension(500, 400));
        this.setLayout(null);
        loadButtons();
        loadBlueprints();
    }

    private void loadButtons(){
        blueprintMatrix = new JButton[11][6];
        int x = 5;
        int y = 3;

        for(int i = 0; i < 11; i++) {
            for (int j = 0; j < 6; j++) {
                blueprintMatrix[i][j] = new JButton();
                blueprintMatrix[i][j].setBounds(x, y, 73, 30);
                this.add(blueprintMatrix[i][j]);
                x += 83;
            }
            x = 5;
            y += 35;
        }
    }

    private void clearMatrix(){
        for(int i = 0; i < 12; i++) {
            for (int j = 0; j < 13; j++) {
                SubstanceMaker.elementMatrix[i][j].setText(".");
            }
        }
    }

    private void loadBlueprint(int x, int y) throws IOException {
        File file = new File("/home/pipa/TheChemist/Main/BlueprintCodes");
        File file2 = new File("/home/pipa/TheChemist/Main/solutions");
        ArrayList<String[]> solutions = FileHandler.readCSV(file2);
        ArrayList<String[]> blueprints = FileHandler.readCSV(file);
        String currentID = null;
        int i;
        int j;
        String element;
        int tracker = 1;

        for(String[] solution : solutions){
            if(solution[0].equals(blueprintMatrix[x][y].getText())){
                currentID = solution[1];
                break;
            }
        }

        for(String[] blueprint : blueprints){
            if(blueprint[0].equals(currentID)){
                while(tracker < blueprint.length){
                    element = blueprint[tracker];
                    i = Integer.parseInt(blueprint[tracker + 1]);
                    j = Integer.parseInt(blueprint[tracker + 2]);
                    SubstanceMaker.elementMatrix[i][j].setText(element);

                    tracker += 3;
                }
                break;
            }
        }
    }

    private void loadBlueprints() throws IOException {
        File file = new File("/home/pipa/TheChemist/Main/BlueprintCodes");
        File file2 = new File("/home/pipa/TheChemist/Main/solutions");
        ArrayList<String[]> blueprints = FileHandler.readCSV(file);
        ArrayList<String[]> solutions = FileHandler.readCSV(file2);
        int x = 0;
        int y = 0;

        for(String[] blueprint : blueprints){
            for(String[] solution : solutions){
                if(blueprint[0].equals(solution[1])){
                    blueprintMatrix[x][y].setText(solution[0]);
                    int finalX = x;
                    int finalY = y;
                    blueprintMatrix[x][y].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            try {
                                clearMatrix();
                                loadBlueprint(finalX, finalY);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                    x++;
                    break;
                }
                if(x == 11){
                    x = 0;
                    y++;
                }
            }
        }
    }
}
