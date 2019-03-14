package approximation.DoneClasses;

public class Range {
    double left;
    double right;

    public Range(double left, double right) {
        this.left = left;
        this.right = right;
    }

    public double getLeft() {
        return left;
    }

    public void setLeft(double left) {
        this.left = left;
    }

    public double getRight() {
        return right;
    }

    public void setRight(double right) {
        this.right = right;
    }

    public void setRange(double left, double right){
        this.left = left;
        this.right = right;
    }

    public void setRange(Range range){
        this.left = range.getLeft();
        this.right = range.getRight();
    }

    public double getLength(){
        return this.right-this.left;
    }

    @Override
    public String toString() {
        return "[" + left +"," + right + "] ";
    }
}
