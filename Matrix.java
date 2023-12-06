public class Matrix {
    private int numRows; // Instance Variable
    private int numCols; // Instance Variable
    private double[][] data; // Instance Variable for array

    public Matrix(int r, int c) { // Matrix constructor for numrow and numcol
        numRows = r; //initilizer
        numCols = c; // initilizer
        data = new double[r][c]; //initilize array

    }

    public Matrix(int r, int c, double[] linArr) { // Matrix constructor for numrow, num col and 1d array
        numRows = r;
        numCols = c;
        data = new double[r][c];
        int value = 0;
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[0].length; col++) {
                data[row][col] = linArr[value++]; // loop through matrix and append each value in 1d array going from left to right
            }
        }
    }


    public int getNumRows() { //getter to return numrows
        return numRows;
    }

    public int getNumCols() { //getter for numcols
        return numCols;
    }

    public double[][] getData() { // getdata for 2d array
        return data;
    }

    public double getElement(int r, int c) { //get element at r row and c col
        return data[r][c];
    }

    public void setElement(int r, int c, double value) {
        data[r][c] = value; //set the element at r row and c col equal to the value
    }

    public void transpose() {
        double transposeMatrix[][] = new double[numCols][numRows]; // create local tranpose matrix object with same length of numrows and numcols
        for (int row = 0; row < data.length; row++) { // loop through the matrix
            for (int col = 0; col < data[row].length; col++) {
                transposeMatrix[col][row] = data[row][col]; // transpose the matrix so row and col  = col and row
            }
        }
        numRows = transposeMatrix.length; //update the numrow instance variable
        numCols = transposeMatrix[0].length; //update numcol instance vraible
        data = transposeMatrix; //now data is the tranposed matrix
    }

    public Matrix multiply(double scalar) {
        Matrix matrixMultiplied = new Matrix(numRows, numCols);
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[row].length; col++) { //loop through matrix
                matrixMultiplied.data[row][col] = data[row][col] * scalar; // the new matrix is just the old one multiplied by a scalar value
            }
        }
        return matrixMultiplied;
    }

    public Matrix multiply(Matrix other) {
        Matrix matrixMultiplied = new Matrix(data.length, other.data[0].length);
        if (data[0].length == other.data.length) {
            for (int i = 0; i < data.length; i++) {
                for (int z = 0; z < other.data.length; z++) {
                    for (int x = 0; x < other.data[0].length; x++) // when multiply the matrix, the new matrix will inherit the row length of 1st matrix and the col length of 2nd
                        matrixMultiplied.data[i][z] += data[i][x] * other.data[x][z]; // follow the matrix multiplication process
                }
            }
            return matrixMultiplied; // return matrix
        }
        return null; // return null if cannot be multiplied
    }

    public String toString() {
        String s1 = "";
        int counter = -1;
        if (data.length != 0 && data[0].length != 0) { // make sure matrix is not empty
            for (int row = 0; row < data.length; row++) {
                counter++;
                String S = "";
                for (int col = 0; col < data[0].length; col++) {
                    S += String.format("%8.3f", data[row][col]); // 8 spaces for each element in matrix
                }
                if (counter != (data.length - 1)) { //if not on last row then we can add new space
                    s1 += (S + "\n");
                } else {
                    s1 += (S); // since its in last row, no need for new space, that is what the counter was for
                }
            }
            return s1; //return the formatted matrix
        }
        return "Empty matrix"; // return empty matrix since theres nothing
    }
    }
