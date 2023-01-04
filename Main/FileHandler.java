package Main;

import java.io.*;
import java.util.ArrayList;

public class FileHandler{
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

    public static void writeCSV(File file, String values) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        if(file.length() == 0){
            bufferedWriter.write(values);
        }else{
            bufferedWriter.write("\n"+values);
        }
        bufferedWriter.close();
    }
}
