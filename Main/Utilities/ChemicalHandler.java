package Main.Utilities;

import Main.ErrorFrames.ErrorFrame;
import Main.Panels.ChemicalMaker;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ChemicalHandler {

    private ErrorFrame errorFrame;

    /**
     * This method checks, if the chemical that's currently on the element matrix is really a chemical(if it is in the Chemical folder)
     * @param file_Chemicals This is the last file I am documenting, it is 5pm, I've had no sleep and I am kinda sick of commenting all these files.
     *                       They are there, because the data from them is needed for smooth work of the methods. No special purpose
     * @param file_ElementValues
     * @return Returns true if the chemical was found in the Chemical file, thus it is a valid chemical and false if it wasn't found
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
     * @return Returns the ID in String type, because it is easier for me to work with strings later on
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
     * Retrieves the name of the chemical that is currently on the element matrix
     * @param file_Chemicals
     * @param file_ElementValues
     * @return Returns the name of the chemical, as expected
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
     * Checks if the chemical we are trying to get isn't already in the Resources file, so we won't have multiple instances of the same chemical there
     * @param id ID of the chemical we're checking
     * @param file_Resources
     * @return Returns true if the chemical is indeed in the Resources folder and false if not
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
     * Searches in the element matrix for an element, that is not actually an element(it is not present in the ElementValues folder)
     * @param file_ElementValues
     * @return Returns true if it finds one, false if not
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
     * Adds a chemical to the resource pool(more specifically to the Resources folder). Manages the amount of chemical
     * @param file_Chemicals
     * @param file_ElementValues
     * @param file_Resources
     * @throws IOException
     */
    public void addChemicalToResources(File file_Chemicals, File file_ElementValues, File file_Resources) throws IOException {
        ArrayList<String[]> resources = FileHandler.readCSV(file_Resources);
        int numberOfChemical;
        String out;

        if(searchForUnknownElement(file_ElementValues) == true){
            errorFrame = new ErrorFrame("Unknown element");
            return;
        }
        if(isChemical(file_Chemicals, file_ElementValues) == true) {
            if (isThereChemical_Resources(getChemicalID(file_ElementValues), file_Resources) == true) {
                FileHandler.writeCSV(file_Resources, "", false);
                for(String[] singleResource : resources){
                    if(singleResource[1].equals(getChemicalID(file_ElementValues))){
                        numberOfChemical = Integer.parseInt(singleResource[2]) + 1;
                        out = singleResource[0] + "," + singleResource[1] + "," + numberOfChemical;
                    }else{
                        out = singleResource[0] + "," + singleResource[1] + "," + singleResource[2];
                    }
                    FileHandler.writeCSV(file_Resources, out, true);
                }
            } else {
                out = getChemicalName(file_Chemicals, file_ElementValues) + "," + getChemicalID(file_ElementValues) + "," + 1;
                FileHandler.writeCSV(file_Resources, out, true);
            }
        }
    }
}
