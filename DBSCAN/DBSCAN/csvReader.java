package DBSCAN;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class csvReader {

    public List<List<String>> readCSV(String filePath) {
        try {
            List<List<String>> data = new ArrayList<>(); // Creates a list of lists to store data.
			String csvFilePath = filePath;
			FileReader fileReader = new FileReader(csvFilePath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = bufferedReader.readLine();

            while(line != null) {
                List<String> lineData = Arrays.asList(line.split(","));
				data.add(lineData);
				line = bufferedReader.readLine();
            }

            // This for loop is for printing the data.
            /*for(List<String> list : data) {
                for(String str : list) {
                    System.out.print(str + " ");
                }
                System.out.println();
            }*/

            bufferedReader.close();
            return data;

        } catch (Exception e) {
            System.out.print(e);
        }
        return null;
    }
}
