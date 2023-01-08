package Main.Utilities;

import java.io.*;
import java.util.ArrayList;

public class FileHandler{

    /**
     * Is used to read a csv file and provide a usable output for me
     * @param file Defines a file from which I want to read
     * @return Returns an arrayList of string arrays. Each array holds all data of one line in the respective file
     * @throws IOException
     */
    public static ArrayList<String[]> readCSV(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        ArrayList<String[]> finalList = new ArrayList<String[]>();
        String[] currentLine;
        String line = "";

        while((line = bufferedReader.readLine()) != null){
            currentLine = line.split(",");
            finalList.add(currentLine);
        }
        bufferedReader.close();

        return finalList;
    }

    /**
     * Is used to write to a csv file. Nothing less, nothing more
     * @param file Defines to which I want to write. How unexpected...
     * @param values Defines what I want to write. It would probably be good to point out, that it accepts only already formatted values
     * @param rewrite Is there just in case I ever wanted to rewrite the file instead appending. I could drop this parameter and just write "true" there,
     *                but I like it like this more
     * @throws IOException
     */
    public static void writeCSV(File file, String values, boolean rewrite) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, rewrite));
        if(file.length() == 0){
            bufferedWriter.write(values);
        }else{
            bufferedWriter.write("\n"+values);
        }
        bufferedWriter.close();
    }
}
