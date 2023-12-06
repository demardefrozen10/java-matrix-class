public class MarkovChain {
    private Vector stateVector;
    private Matrix transitionMatrix;

    public MarkovChain(Vector sVector, Matrix tMatrix) {
        stateVector = sVector;
        transitionMatrix = tMatrix;
    }

    public boolean isValid() {
        double vectorSum = 0;
        if (transitionMatrix.getNumRows() == transitionMatrix.getNumCols() && transitionMatrix.getNumCols() == stateVector.getNumCols()) { //check if multiplying is compatiable
            for (int i = 0; i < stateVector.getNumCols(); i++) {
                vectorSum += stateVector.getElement(i); //get vector sum
            }
            boolean notValid = false;
            for (int row = 0; row < transitionMatrix.getNumRows(); row++) {
                double transitionMatrixSum = 0;
                for (int col = 0; col < transitionMatrix.getNumCols(); col++) {
                    transitionMatrixSum += transitionMatrix.getElement(row, col);
                }
                if (!(transitionMatrixSum >= 0.99 && transitionMatrixSum <= 1.01)) { // will keep checking each row to make sure that it adds up to one, the second it dosent, then notValid is true and code stops running
                    notValid = true;
                    break;
                }
            }
            if (vectorSum >= 0.99 && vectorSum <= 1.01 && notValid == false) {
                return true; // return true if vector sum is one and clearly each matrix row is equal to one
            }
        }
        return false;
    }

    public Matrix computeProbabilityMatrix(int numSteps) {
        Matrix newMatrixFinal;
        if (isValid()) { // make sure matrix is valid and can be multiplied
            Matrix testMatrix = transitionMatrix; // let ie be the transition matrix for now
            if (numSteps != 1) { // only do this if numsteps is > 1 since theres special instructions to juyst mutlitply statevector by testmatrix if just one step
                for (int i = 0; i < numSteps - 1; i++) { //num steps -1 since for eg 2 steps you just multiply it once
                    testMatrix = transitionMatrix.multiply(testMatrix);
                }
            }
                newMatrixFinal = stateVector.multiply(testMatrix);
                return newMatrixFinal;
        }
        return null; // invalid? return null
    }
}