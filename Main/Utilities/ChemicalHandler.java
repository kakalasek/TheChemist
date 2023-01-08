package Main.Utilities;

import Main.ErrorFrames.ErrorFrame;
import Main.Panels.ChemicalMaker;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ChemicalHandler {

    private ErrorFrame errorFrame;

    /**
     * This method checks, if the chemical currently on element matrix is really a chemical.
     * @param file_Chemicals This is the last file I am documenting, it is 5pm, I've had no sleep and I am kinda sick of commenting all these files.
     *                       They are there, because the data from them are needed for smooth work of the methods. No special purpose
     * @param file_ElementValues
     * @return Return true if yes and false if not
     * @throws IOException
     */
    public boolean isChemical(File file_Chemicals, File file_ElementValues) throws IOException {
        ArrayList<String[]> chemicals = FileHandler.readCSV(file_Chemicals);

        for(String[] singleChemical : chemicals){
            if(singleChemical[1].equals(getChemicalID(file_ElementValues))){
                return true;
            }
        }
        return false;
    }

    /**
     * This method retrieves the ID of chemical that is currently on the element matrix
     * @param file_ElementValues
     * @return Returns the ID in String type, because it is easier for me to work with strings here
     * @throws IOException
     */
    public String getChemicalID(File file_ElementValues) throws IOException {
        ArrayList<String[]> elementValues = FileHandler.readCSV(file_ElementValues);
        int chemicalID = 0;

        for (int row = 0; row < 12; row++) {
            for (int column = 0; column < 13; column++) {
                if(ChemicalMaker.elementMatrix[row][column].getText().equals(".")){
                    continue;
                }

                for(String[] element : elementValues){
                    if(element[0].equals(ChemicalMaker.elementMatrix[row][column].getText())){
                        chemicalID += Integer.parseInt(element[1]);
                        break;
                    }
                }
            }
        }
        return String.valueOf(chemicalID);
    }

    /**
     * Retrieves the name of the chemicals that is currently on the element matrix
     * @param file_Chemicals
     * @param file_ElementValues
     * @return return the chemicals name as expected
     * @throws IOException
     */
    public String getChemicalName(File file_Chemicals, File file_ElementValues) throws IOException {
        ArrayList<String[]> chemicals = FileHandler.readCSV(file_Chemicals);
        String chemicalName = "";

        for(String[] singleChemical : chemicals){
            if(singleChemical[1].equals(getChemicalID(file_ElementValues))){
                chemicalName = singleChemical[0];
                break;
            }
        }
        return chemicalName;
    }

    /**
     * Checks if the chemical we are trying to get, isn't already there, so we won't have multiple instances of the same chemical there
     * @param id id of the chemical were checking
     * @param file_Resources
     * @return return true if yes, false if not
     * @throws IOException
     */
    private boolean isThereChemical_Resources(String id, File file_Resources) throws IOException {
        ArrayList<String[]> resources = FileHandler.readCSV(file_Resources);

        for(String[] singleResource : resources){
            if(singleResource[1].equals(id)){
                return true;
            }
        }
        return false;
    }

    /**
     * Searches for an element that is not actually an element
     * @param file_ElementValues
     * @return Return true if it finds one, false if not
     * @throws IOException
     */
    public boolean searchForUnknownElement(File file_ElementValues) throws IOException {
        ArrayList<String[]> elementValues = FileHandler.readCSV(file_ElementValues);
        int elementID;

        for (int row = 0; row < 12; row++) {
            for (int column = 0; column < 13; column++) {
                elementID = 0;
                if(ChemicalMaker.elementMatrix[row][column].getText().equals(".")) {
                    continue;
                }

                for(String[] element : elementValues){
                    if(element[0].equals(ChemicalMaker.elementMatrix[row][column].getText())){
                        elementID += Integer.parseInt(element[1]);
                        break;
                    }
                }
                if(elementID == 0){
                    return  true;
                }
            }
        }
        return false;
    }

    /**
     * Adds a chemical to the resource pool
     * @param file_Chemicals
     * @param file_ElementValues
     * @param file_Resources
     * @throws IOException
     */
    public void addChemicalToResources(File file_Chemicals, File file_ElementValues, File file_Resources) throws IOException {
        if(searchForUnknownElement(file_ElementValues) == true){
            errorFrame = new ErrorFrame("Unknown element");
            return;
        }
        if(isChemical(file_Chemicals, file_ElementValues) == true) {
            if (isThereChemical_Resources(getChemicalID(file_ElementValues), file_Resources) == true) {
                return;
            } else {
                String out = getChemicalName(file_Chemicals, file_ElementValues) + "," + getChemicalID(file_ElementValues);
                FileHandler.writeCSV(file_Resources, out, true);
            }
        }
    }
}
