package isolationForest;
import java.util.ArrayList;
import java.util.List;

public class Forest {
    public List<Tree> trees;
    public int numberOfTrees;

    public Forest(int numberOfTrees) {
        this.trees = new ArrayList<>();
        this.numberOfTrees = numberOfTrees;
    }

    public void createForest(List<List<String>> data) {
        for(int i=0 ; i<numberOfTrees ; i++) {
            Tree tree = new Tree(5);
            tree.insertNode(data);
            System.out.println(tree.allLeafNodes.size());
            System.out.println(tree.getShortestPaths(tree.allLeafNodes).size());
            trees.add(tree);
        }
    }
}
