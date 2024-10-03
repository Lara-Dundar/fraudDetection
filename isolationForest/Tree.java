package isolationForest;

import java.util.ArrayList;
import java.util.List;

public class Tree {
    private Node root;
    private int maxDepth;
    public List<Node> allLeafNodes;
    public List<Node> shortestPathLeafNodes;
    public List<List<String>> dataPathLengths;

    public Tree(int maxDepth) {
        root = null;
        this.maxDepth = maxDepth;
        this.allLeafNodes = new ArrayList<>();
        this.shortestPathLeafNodes = new ArrayList<>();
        this.dataPathLengths = new ArrayList<>();
    }

    public Node getRoot() {
        return root;
    }

    public void insertNode(List<List<String>> data) {
        root = buildTree(data, 0);
    }

    private Node buildTree(List<List<String>> data, int depth) {
        
        if (depth >= maxDepth) { // For the leaf nodes.
            Node leaf = new Node(data, data.size(), depth);
            allLeafNodes.add(leaf);

            for (int i = 0; i < data.size(); i++) {
                List<String> each_data = data.get(i);
                each_data.add(Integer.toString(depth));
                dataPathLengths.add(each_data);
            }

            return leaf;
        }

        int randomAttributeIndex = randomAttributeSelection(data);
        double randomSplittingValue = randomSplittingValueSelection(data, randomAttributeIndex);

        List<List<String>> leftData = new ArrayList<>();
        List<List<String>> rightData = new ArrayList<>();

        for (int i = 1; i < data.size(); i++) {
            List<String> each_data = data.get(i);
            if (Double.parseDouble(each_data.get(randomAttributeIndex)) <= randomSplittingValue) {
                leftData.add(each_data);
            } else {
                rightData.add(each_data);
            }
        }

        if (leftData.isEmpty() && rightData.isEmpty()) {
            Node leaf = new Node(data, data.size(), depth);
            allLeafNodes.add(leaf);

            for (int i = 0; i < data.size(); i++) {
                List<String> each_data = data.get(i);
                each_data.add(Integer.toString(depth));
                dataPathLengths.add(each_data);
            }

            return leaf;
        }

        Node internalNode = new Node(randomAttributeIndex, randomSplittingValue);

        if (!leftData.isEmpty()) {
            internalNode.setLeft(buildTree(leftData, depth + 1));
        } else {
            Node leaf = new Node(leftData, leftData.size(), depth);
            allLeafNodes.add(leaf);
            internalNode.setLeft(leaf);

            for (int i = 0; i < leftData.size(); i++) {
                List<String> each_data = leftData.get(i);
                each_data.add(Integer.toString(depth));
                dataPathLengths.add(each_data);
            }

        }
    
        if (!rightData.isEmpty()) {
            internalNode.setRight(buildTree(rightData, depth + 1));
        } else {
            Node leaf = new Node(rightData, rightData.size(), depth);
            allLeafNodes.add(leaf);
            internalNode.setRight(leaf);

            for (int i = 0; i < rightData.size(); i++) {
                List<String> each_data = rightData.get(i);
                each_data.add(Integer.toString(depth));
                dataPathLengths.add(each_data);
            }

        }

        return internalNode;
    }

    public int randomAttributeSelection(List<List<String>> data) {
        List<String> attributes = data.get(0);
        int minIndex = 0;
        int maxIndex = attributes.size()-1;
        int randomAttributeIndex = (int) Math.round(minIndex + (maxIndex - minIndex) * Math.random());
        return randomAttributeIndex;
    }

    public double randomSplittingValueSelection(List<List<String>> data, int randomAttributeIndex) {
        double minValue = Double.MAX_VALUE;
        double maxValue = Double.MIN_VALUE;

        for(int i=1 ; i<data.size() ; i++) {
            List<String> each_data = data.get(i);

            if (Double.parseDouble(each_data.get(randomAttributeIndex)) < minValue) {
                minValue = Double.parseDouble(each_data.get(randomAttributeIndex));
            }
            if (Double.parseDouble(each_data.get(randomAttributeIndex)) > maxValue) {
                maxValue = Double.parseDouble(each_data.get(randomAttributeIndex));
            }
        }

        double randomSplittingValue = minValue + (maxValue - minValue) * Math.random();
        return randomSplittingValue;
    }

    // This method is here to test if splitting works correctly.
    public void printTree(Node node, String prefix) {
        if (node != null) {
            System.out.println(prefix + "-> ");
            node.printNode();
            printTree(node.getLeft(), prefix + "   | ");
            printTree(node.getRight(), prefix + "   | ");
        }
    }

    public List<Node> getShortestPaths(List<Node> allLeafNodes) {

        for(int i=0 ; i<allLeafNodes.size() ; i++) {
            if(allLeafNodes.get(i).getPathLength() < maxDepth) {
                shortestPathLeafNodes.add(allLeafNodes.get(i));
            }
        }
        return shortestPathLeafNodes;
    }
}
