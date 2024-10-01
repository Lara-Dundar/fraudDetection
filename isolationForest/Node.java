package isolationForest;

public class Node {
    private Node left;
    private Node right;
    private int attributeIndex;
    private double splitValue;
    private boolean isLeaf;
    private int sizeOfDataInLeaf;

    public Node(int attributeIndex, double splitValue) {
        this. attributeIndex = attributeIndex;
        this.splitValue = splitValue;
        left = null;
        right = null;
        this.isLeaf = false;
    }

    public Node(int sizeOfDataInLeaf) {
        this.sizeOfDataInLeaf = sizeOfDataInLeaf;
        this.isLeaf = true;
        this.left = null;
        this.right = null;
    }

    // This method is here to test if splitting works correctly.
    public void printNode() {
        if (isLeaf) {
            System.out.println("Leaf Node: Size of data = " + sizeOfDataInLeaf);
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

}
