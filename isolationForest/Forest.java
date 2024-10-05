package isolationForest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Forest {
    public List<Tree> trees;
    public int numberOfTrees;
    public HashMap<dataPoint, Integer> tableForCollectiveEvaluation;

    public Forest(int numberOfTrees) {
        this.trees = new ArrayList<>();
        this.numberOfTrees = numberOfTrees;
        this.tableForCollectiveEvaluation = new HashMap<>();
    }

    public void createForest(List<List<String>> data) {
        for(int i=0 ; i<numberOfTrees ; i++) {
            Tree tree = new Tree(5);
            tree.insertNode(data);
            System.out.println(tree.allLeafNodes.size());
            System.out.println(tree.getShortestPaths(tree.allLeafNodes).size());

            List<dataPoint> dataPoints= createDataPoints(tree);
            List<Integer> pathLengths = createPathLengths(tree);

            for(int j=0 ; j<dataPoints.size() ; j++) {
                dataPoint each_dataPoint = dataPoints.get(j);
                int each_pathLength = pathLengths.get(j);

                if (tableForCollectiveEvaluation.containsKey(each_dataPoint)) {
                    int currentPathLength = tableForCollectiveEvaluation.get(each_dataPoint);
                    tableForCollectiveEvaluation.put(each_dataPoint, currentPathLength + each_pathLength);
                } else {
                    tableForCollectiveEvaluation.put(each_dataPoint, each_pathLength);
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

            for (int j = 0; j < each_data.size() - 1; j++) {
                each_dataPoint.add(Double.parseDouble(each_data.get(j)));
            }
            dataPoint data_Point = new dataPoint();
            data_Point.data = each_dataPoint;
            dataPointPerTree.add(data_Point);
        }
        return dataPointPerTree;
    }

    public List<Integer> createPathLengths(Tree tree) {
        List<Integer> pathLengths = new ArrayList<>();

        for(int i=0 ; i<tree.dataPathLengths.size() ; i++) {
            List<String> each_data = tree.dataPathLengths.get(i);
            pathLengths.add(Integer.parseInt(each_data.get(each_data.size()-1)));
        }
        return pathLengths;
    }

    public void printHashMap() {
        for (Map.Entry<dataPoint, Integer> entry : tableForCollectiveEvaluation.entrySet()) {
            System.out.println("DataPoint: " + entry.getKey().data + " -> Path Length: " + entry.getValue());
        }
    }
}
