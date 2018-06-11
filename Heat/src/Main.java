public class Main {
	public static int[][] initHeatMatrix(int rows, int cols, int tUp, int tDown, int tLeft, int tRight) {
		// generate edges
		// up, down
		int[][] heatMatrix = new int[rows][cols];
		int assign = 0;
		for (int i = 0; i < heatMatrix.length; i = i + heatMatrix.length - 1) {

			if (i == 0)
				assign = tUp;
			else
				assign = tDown;
			for (int j = 1; j < heatMatrix[0].length - 1; j++) {
				heatMatrix[i][j] = assign;
			}
		}
		// left, right

		for (int i = 0; i < heatMatrix[0].length; i = i + heatMatrix[0].length - 1) {
			if (i == 0)
				assign = tLeft;
			else
				assign = tRight;
			for (int j = 1; j < heatMatrix.length - 1; j++) {
				heatMatrix[j][i] = assign;
			}
		}

		// corners

		heatMatrix[0][0] = (heatMatrix[0][1] + heatMatrix[1][0]) / 2;
		heatMatrix[0][cols - 1] = (heatMatrix[0][cols - 2] + heatMatrix[1][cols - 1]) / 2;
		heatMatrix[rows - 1][0] = (heatMatrix[rows - 1][1] + heatMatrix[rows - 2][0]) / 2;
		heatMatrix[rows - 1][cols - 1] = (heatMatrix[rows - 2][cols - 1] + heatMatrix[rows - 1][cols - 2])
				/ 2;

		return heatMatrix;
	}

	public static int[][][][] computeEqsMatrix(int[][] heatMatrix) {
		int rows = heatMatrix.length;
		int cols = heatMatrix[0].length;
		// it contains rows * columns arrays which contain arrays of all elements
		// a (rows * columns) * (rows * columns) matrix
		int[][][][] eqs = new int[rows][cols][rows][cols];
		for (int i = 1; i < heatMatrix.length - 1; i++) {

			for (int j = 1; j < heatMatrix[0].length - 1; j++) {
				int result = 0;

				if (heatMatrix[i][j - 1] != 0)
					result += heatMatrix[i][j - 1];
				else
					eqs[i][j][i][j - 1] = 1;

				if (heatMatrix[i][j + 1] != 0)
					result += heatMatrix[i][j + 1];
				else
					eqs[i][j][i][j + 1] = 1;

				// heatMatrix[i][j] always is unknown, don't have to check it
				eqs[i][j][i][j] = (-4);

				if (heatMatrix[i - 1][j] != 0)
					result += heatMatrix[i - 1][j];
				else
					eqs[i][j][i - 1][j] = 1;

				if (heatMatrix[i + 1][j] != 0)
					result += heatMatrix[i + 1][j];
				else
					eqs[i][j][i + 1][j] = 1;

				eqs[i][j][rows - 1][cols - 1] = -result; // we move the result to the other side of equation

			}
		}
		return eqs;
	}
	
	public static void print2DMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.format("%5s", matrix[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void printAndFormatFinalMatrix(int[][][][] eqs) {
		int rows = eqs.length;
		int cols = eqs[0].length;
		for (int i = 1; i < eqs.length - 1; i++) {
			for (int j = 1; j < eqs[0].length - 1; j++) {
				// displays row, column (from the top!), it is not x,y axis!
				// System.out.println("row: "+i+" column: "+j);
				for (int k = 1; k < eqs.length - 1; k++) {
					for (int l = 1; l < eqs[0].length - 1; l++) {
						// System.out.format("%10s", eqs[i][j][k][l]);

						System.out.print(eqs[i][j][k][l] + ",");
						if (k == rows - 2 && l == cols - 2) {
							System.out.print(" ," + eqs[i][j][rows - 1][cols - 1]);
						}
					}
				}
				System.out.println();
			}
		}
	}
	public static void main(String[] args) {
		// the size of our matrix
		final int ROWS = 10;
		final int COLS = 10;
		// temperatures
		int tUp = 200, tDown = 150, tLeft = 100, tRight = 50;

		int[][] heatMatrix = new int[ROWS][COLS];
		heatMatrix = initHeatMatrix(ROWS, COLS, tUp, tDown, tLeft, tRight);
		
		// contains A matrix (equations of all unknowns)
		int[][][][] eqs = computeEqsMatrix(heatMatrix);
		
		// beware that everything which is printed will be saved to output.txt, it is mainly for checking
		print2DMatrix(heatMatrix); 
		
		// displays the final matrix
		printAndFormatFinalMatrix(eqs);
		
		/* after that we use Gaussian Eliminations.
		 * Excel provides nice such functionalities.
		 * So we copy the printAndFormatFinalMatrix output and paste into Excel ( use the pasting creator in Excel; that's why we used commas ',') 
		 * Copy the output without print2DMatrix!
		 */	
	}
}
