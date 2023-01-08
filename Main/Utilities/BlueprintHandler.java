package Main.Utilities;

import Main.ErrorFrames.ErrorFrame;
import Main.Panels.ChemicalMaker;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BlueprintHandler {
    private ChemicalHandler chemicalHandler = new ChemicalHandler();
    private ErrorFrame errorFrame;

    /**
     * It creates a blueprint. It writes it to the Blueprints file where it stays until later use
     * @param file_Blueprints Just a file needed for this to work. File with blueprints
     * @param file_ElementValues Just a file needed for this to work. File with values of each element
     * @param file_Chemicals Just a file needed for this to work. File with all creatable chemicals
     * @throws IOException
     */
    public void createBlueprint(File file_Blueprints, File file_ElementValues, File file_Chemicals) throws IOException {
        if(chemicalHandler.searchForUnknownElement(file_ElementValues) == true){
            errorFrame = new ErrorFrame("Unknown element");
            return;
        }
        if (isThereChemical_Blueprints(chemicalHandler.getChemicalID(file_ElementValues), file_Blueprints) == true) {
            return;
        } else {
            if (chemicalHandler.isChemical(file_Chemicals, file_ElementValues) == true) {
                String blueprint = "";
                blueprint += chemicalHandler.getChemicalID(file_ElementValues);
                for (int row = 0; row < 12; row++) {
                    for (int column = 0; column < 13; column++) {
                        if (ChemicalMaker.elementMatrix[row][column].getText().equals(".") == false) {
                            blueprint += "," + ChemicalMaker.elementMatrix[row][column].getText() + "," + row + "," + column;
                        }
                    }
                }
                FileHandler.writeCSV(file_Blueprints, blueprint, true);
            }
        }
    }

    /**
     * This loads blueprint to your element matrix upon clicking respective blueprint button
     * @param blueprint Blueprint button, so we know what to load
     * @param file_Blueprints Just a file needed for this to work. File with blueprints
     * @param file_Chemicals Just a file needed for this to work. File with all creatable chemicals
     * @throws IOException
     */
    public void loadBlueprint(JButton blueprint, File file_Blueprints, File file_Chemicals) throws IOException {
        ArrayList<String[]> chemicals = FileHandler.readCSV(file_Chemicals);
        ArrayList<String[]> blueprints = FileHandler.readCSV(file_Blueprints);
        String currentID = null;
        int row;
        int column;
        String element;
        int tracker = 1;

        for(String[] singleChemical : chemicals){
            if(singleChemical[0].equals(blueprint.getText())){
                currentID = singleChemical[1];
                break;
            }
        }


        for(String[] singleBlueprint : blueprints){
            if(singleBlueprint[0].equals(currentID)){
                while(tracker < singleBlueprint.length){
                    element = singleBlueprint[tracker];
                    row = Integer.parseInt(singleBlueprint[tracker + 1]);
                    column = Integer.parseInt(singleBlueprint[tracker + 2]);
                    ChemicalMaker.elementMatrix[row][column].setText(element);
                    
                    tracker += 3;
                }
                break;
            }
        }
    }

    /**
     * A method that checks if the respective blueprint is not already there
     * @param id ID of the chemical we want to check
     * @param file_Blueprints Just a file needed for this to work. File with blueprints
     * @return return true if the respective blueprint already exists and false if not
     * @throws IOException
     */
    private boolean isThereChemical_Blueprints(String id, File file_Blueprints) throws IOException {
        ArrayList<String[]> blueprints = FileHandler.readCSV(file_Blueprints);
        for(String[] singleBlueprint : blueprints){
            if(singleBlueprint[0].equals(id)){
                return true;
            }
        }
        return false;
    }
}
