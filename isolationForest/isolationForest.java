package isolationForest;
import java.util.List;

public class isolationForest {
    public static void main(String[] args) {

        String filePath = "FraudDetection//data//winequality-red.csv";

        csvReader reader = new csvReader();
        List<List<String>> data = reader.readCSV(filePath);

        //System.out.println("Data from CSV: " + data.get(0));

        /*if (data.isEmpty() || data.get(0).isEmpty()) {
            System.out.println("Data is empty or no attributes found.");
            return;
        }*/
        
        /*int attributeIndex =tree.randomAttributeSelection(data);
        System.err.println(attributeIndex);
        double value = tree.randomSplittingValueSelection(data,attributeIndex);
        System.err.println(value);*/

        // System.out.println("Tree structure and splits:");
        // tree.printTree(tree.getRoot(),"");

        Forest forest = new Forest(10);
        forest.createForest(data);
        forest.printHashMap();

    }
}
