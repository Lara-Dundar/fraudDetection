package isolationForest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Forest {
    public List<Tree> trees;
    public int numberOfTrees;
    public HashMap<dataPoint, Integer> tableForCollectiveEvaluation;

    public Forest(int numberOfTrees) {
        this.trees = new ArrayList<>();
        this.numberOfTrees = numberOfTrees;
        this. tableForCollectiveEvaluation = new HashMap<>();
    }

    public void createForest(List<List<String>> data) {
        for(int i=0 ; i<numberOfTrees ; i++) {
            Tree tree = new Tree(5);
            tree.insertNode(data);
            System.out.println(tree.allLeafNodes.size());
            System.out.println(tree.getShortestPaths(tree.allLeafNodes).size());

            List<dataPoint> dataPoints= createDataPoints(tree);
            for(int j=0 ; j<dataPoints.size() ;j++) {
                dataPoint each_dataPoint = new dataPoint();
                if(!tableForCollectiveEvaluation.containsKey(each_dataPoint)) {
                    // Continue here.
                }
            }
            trees.add(tree);
        }
    }

        public List<dataPoint> createDataPoints(Tree tree) {
        List<dataPoint> dataPointPerTree = new ArrayList<>();
        for(int i=0 ; i<tree.dataPathLengths.size() ; i++) {
            List<String> each_data = tree.dataPathLengths.get(i);
            List<Double> each_dataPoint = new ArrayList<>();

            for (String eachString : each_data) {
                each_dataPoint.add(Double.parseDouble(eachString));
            }
            dataPoint data_Point = new dataPoint();
            data_Point.data = each_dataPoint;
            dataPointPerTree.add(data_Point);
        }
        return dataPointPerTree;
    }
}
