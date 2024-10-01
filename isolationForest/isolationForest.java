package isolationForest;
import java.util.List;

public class isolationForest {
    public static void main(String[] args) {

        String filePath = "data//winequality-red.csv";

        csvReader reader = new csvReader();
        List<List<String>> data = reader.readCSV(filePath);

        //System.out.println("Data from CSV: " + data.get(0));

        /*if (data.isEmpty() || data.get(0).isEmpty()) {
            System.out.println("Data is empty or no attributes found.");
            return;
        }*/

        Tree tree = new Tree(5);
        
        /*int attributeIndex =tree.randomAttributeSelection(data);
        System.err.println(attributeIndex);
        double value = tree.randomSplittingValueSelection(data,attributeIndex);
        System.err.println(value);*/

        tree.insertNode(data);
        System.out.println("Tree structure and splits:");
        tree.printTree(tree.getRoot(),"");
    }
    
}
