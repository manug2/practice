package epi.hackathon;

public class Ch10BinaryTree {
    private final char item;
    private Ch10BinaryTree left, right;

    public Ch10BinaryTree(char item) {
        this.item = item;
    }

    public void setRight(Ch10BinaryTree right) {
        this.right = right;
    }

    public void setLeft(Ch10BinaryTree left) {
        this.left = left;
    }

    public int height() {
        return height(0);
    }

    private int height(int max) {
        int hl = max;
        if (left!=null)
            hl = left.height(max+1);

        int hr = max;
        if (right!=null)
            hr = right.height(max+1);

        return (hl>hr?hl:hr);
    }

    public boolean isBalanced() {
        if (left == null) {
            if (right==null || right.height(0) ==0)
                return true;
            else return false;
        } else if (right==null) {
            if (left.height(0) ==0)
                return true;
            else return false;
        }

        final int hl = left.height(0);
        final int hr = right.height(0);
        final int diff = hl -hr;
        return diff <= 1 && diff >= -1;
    }

}
