package isolationForest;

import java.util.List;

public class Node {
    private Node left;
    private Node right;
    private int attributeIndex;
    private double splitValue;
    private boolean isLeaf;
    private int sizeOfDataInLeaf;
    private int pathLength;
    private List<List<String>> dataPoints;

    public Node(int attributeIndex, double splitValue) {
        this. attributeIndex = attributeIndex;
        this.splitValue = splitValue;
        left = null;
        right = null;
        this.isLeaf = false;
    }

    public Node(List<List<String>> dataPoints, int sizeOfDataInLeaf, int pathLength) {
        this.sizeOfDataInLeaf = sizeOfDataInLeaf;
        this.left = null;
        this.right = null;
        this.pathLength = pathLength;
        this.isLeaf = true;
        this.dataPoints = dataPoints;
    }

    // This method is here to test if splitting works correctly.
    public void printNode() {
        if (isLeaf) {
            System.out.println("Leaf Node: Size of data = " + sizeOfDataInLeaf + ", Path Length = " + pathLength + ", Data Points = " + dataPoints);
        } else {
            System.out.println("Split Node: Attribute Index = " + attributeIndex + ", Split Value = " + splitValue);
        }
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
    public int getAttributeIndex() {
        return attributeIndex;
    }

    public void setAttributeIndex(int attributeIndex) {
        this.attributeIndex = attributeIndex;
    }

    public double getSplitValue() {
        return splitValue;
    }

    public void setSplitValue(double splitValue) {
        this.splitValue = splitValue;
    }

    public boolean isItLeaf() {
        return isLeaf;
    }

    public int getSizeOfDataInLeaf() {
        return sizeOfDataInLeaf;
    }

    public int getPathLength() {
        return pathLength;
    }

    public void setPathLength(int pathLength) {
        this.pathLength = pathLength;
    }

    public List<List<String>> getDataPoints() {
        return dataPoints;
    }

}
