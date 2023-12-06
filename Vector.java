public class Vector extends Matrix { // inherit instance variables and methods from matrix
    public Vector (int c) {
        super(1, c); // super calls the matrix constructor

    }
    public Vector (int c, double[] linArr) { // for 1d array and int c
        super (1, c, linArr);
    }
    public double getElement(int c) { // get element at 0,c since its a 1d array
        return getElement(0,c);
    }
}