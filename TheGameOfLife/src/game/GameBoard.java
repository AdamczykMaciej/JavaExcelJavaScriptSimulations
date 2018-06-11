package game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameBoard {
	private int[][] ar;
	private final int numOfRows = 40;
	private final int numOfColumns = 40;
	private ArrayList<Integer> deadWillBeAlive;
	private ArrayList<Integer> aliveIsStillAlive;
	// public GameBoard(int numOfRows, int numOfColumns) {
	// ar = new int[numOfRows][numOfColumns];
	// }

	public void init() { // pars = rules
		deadWillBeAlive = new ArrayList<>();
		aliveIsStillAlive = new ArrayList<>();
		ar = new int[numOfRows][numOfColumns];
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter rules. How many neighbours so that a dead cell to become alive?");
		while (scan.hasNextInt()) {
			int cond = 0;
			if ((cond = scan.nextInt()) == -1)
				break;
			deadWillBeAlive.add(cond);
		}
		System.out.println("How many neighbours so that an alive cell is still alive?");
		while (scan.hasNextInt()) {
			int cond = 0;
			if ((cond = scan.nextInt()) == -1)
				break;
			aliveIsStillAlive.add(cond);
		}
		System.out.println("Range " + numOfRows + " x " + numOfColumns + ".\n"
				+ "Enter coordinates (row, column; indices start at 0) of a cell that You'd like to fill in.\n"
				+ "Enter '-1' to finish entering coordinates");
		while (true) {
			int row = scan.nextInt();
			if (row == -1)
				break;
			int column = scan.nextInt();
			if (column == -1)
				break;

			for (int i = 0; i < ar.length; i++) {
				for (int j = 0; j < ar[0].length; j++) {
					ar[row][column] = 1;
				}
			}
		}
	}

	public int[][] generate() {
		int numOfRows = ar.length;
		int numOfColumns = ar[0].length;
		int[][] ar_after = new int[numOfRows][numOfColumns];
		int filledNeighbours = 0;
		int row = 0, column = 0;
		for (int i = 0; i < ar.length; i++) {
			for (int j = 0; j < ar[0].length; j++) {
				filledNeighbours = 0;
				// if neighbours out of bound ( we have to wrap them)

				// first check if one of the 4 exceptional cases
				if (i == 0 && j == 0) {
					// 5 special (wrapped up cases)
					if (ar[ar.length - 1][ar[0].length - 1] == 1)
						filledNeighbours++;
					if (ar[ar.length - 1][j] == 1)
						filledNeighbours++;
					if (ar[ar.length - 1][j + 1] == 1)
						filledNeighbours++;
					if (ar[i][ar[0].length - 1] == 1)
						filledNeighbours++;
					if (ar[i + 1][ar[0].length - 1] == 1)
						filledNeighbours++;
					// regular cases
					if (ar[i][j + 1] == 1)
						filledNeighbours++;
					if (ar[i + 1][j + 1] == 1)
						filledNeighbours++;
					if (ar[i + 1][j] == 1)
						filledNeighbours++;
				} else if (i == 0 && j == ar[0].length - 1) {
					if (ar[ar.length - 1][j - 1] == 1)
						filledNeighbours++;
					if (ar[ar.length - 1][j] == 1)
						filledNeighbours++;
					if (ar[ar.length - 1][0] == 1)
						filledNeighbours++;
					if (ar[i + 1][0] == 1)
						filledNeighbours++;
					if (ar[i][0] == 1)
						filledNeighbours++;
					// regular
					if (ar[i + 1][j - 1] == 1)
						filledNeighbours++;
					if (ar[i + 1][j] == 1)
						filledNeighbours++;
					if (ar[i][j - 1] == 1)
						filledNeighbours++;
				} else if (i == ar.length - 1 && j == ar[0].length - 1) {
					if (ar[i - 1][0] == 1)
						filledNeighbours++;
					if (ar[i][0] == 1)
						filledNeighbours++;
					if (ar[0][0] == 1)
						filledNeighbours++;
					if (ar[0][j] == 1)
						filledNeighbours++;
					if (ar[0][j - 1] == 1)
						filledNeighbours++;
					// regular
					if (ar[i - 1][j - 1] == 1)
						filledNeighbours++;
					if (ar[i - 1][j] == 1)
						filledNeighbours++;
					if (ar[i][j - 1] == 1)
						filledNeighbours++;
				} else if (i == ar.length - 1 && j == 0) {
					if (ar[0][j + 1] == 1)
						filledNeighbours++;
					if (ar[0][j] == 1)
						filledNeighbours++;
					if (ar[0][ar[0].length - 1] == 1)
						filledNeighbours++;
					if (ar[i][ar[0].length - 1] == 1)
						filledNeighbours++;
					if (ar[i - 1][ar[0].length - 1] == 1)
						filledNeighbours++;
					// regular
					if (ar[i - 1][j] == 1)
						filledNeighbours++;
					if (ar[i - 1][j + 1] == 1)
						filledNeighbours++;
					if (ar[i][j + 1] == 1)
						filledNeighbours++;
				} else if (i == 0) {
					// regular cases
					if (ar[i + 1][j - 1] == 1)
						filledNeighbours++;
					if (ar[i + 1][j] == 1)
						filledNeighbours++;
					if (ar[i + 1][j + 1] == 1)
						filledNeighbours++;
					// wrap up 3 cases
					if (ar[ar.length - 1][j - 1] == 1)
						filledNeighbours++;
					if (ar[ar.length - 1][j] == 1)
						filledNeighbours++;
					if (ar[ar.length - 1][j + 1] == 1)
						filledNeighbours++;
					// regular cases
					if (ar[i][j - 1] == 1)
						filledNeighbours++;
					if (ar[i][j + 1] == 1)
						filledNeighbours++;
				} else if (i == ar.length - 1) {
					// wrap up 3 cases
					if (ar[0][j - 1] == 1)
						filledNeighbours++;
					if (ar[0][j] == 1)
						filledNeighbours++;
					if (ar[0][j + 1] == 1)
						filledNeighbours++;
					// regular cases
					if (ar[i - 1][j - 1] == 1)
						filledNeighbours++;
					if (ar[i - 1][j] == 1)
						filledNeighbours++;
					if (ar[i - 1][j + 1] == 1)
						filledNeighbours++;
					if (ar[i][j - 1] == 1)
						filledNeighbours++;
					if (ar[i][j + 1] == 1)
						filledNeighbours++;
				} else if (j == 0) {
					// ar[0].length-1 exceptional cases (wrap up)
					if (ar[i + 1][ar[0].length - 1] == 1)
						filledNeighbours++;
					if (ar[i + 1][j] == 1)
						filledNeighbours++;
					if (ar[i + 1][j + 1] == 1)
						filledNeighbours++;
					if (ar[i - 1][ar[0].length - 1] == 1)
						filledNeighbours++;
					if (ar[i - 1][j] == 1)
						filledNeighbours++;
					if (ar[i - 1][j + 1] == 1)
						filledNeighbours++;
					if (ar[i][ar[0].length - 1] == 1)
						filledNeighbours++;
					if (ar[i][j + 1] == 1)
						filledNeighbours++;
				} else if (j == ar[0].length - 1) {
					// 0 - exceptional cases (Wrap up)
					if (ar[i + 1][j - 1] == 1)
						filledNeighbours++;
					if (ar[i + 1][j] == 1)
						filledNeighbours++;
					if (ar[i + 1][0] == 1)
						filledNeighbours++;
					if (ar[i - 1][j - 1] == 1)
						filledNeighbours++;
					if (ar[i - 1][j] == 1)
						filledNeighbours++;
					if (ar[i - 1][0] == 1)
						filledNeighbours++;
					if (ar[i][j - 1] == 1)
						filledNeighbours++;
					if (ar[i][0] == 1)
						filledNeighbours++;
				} else {
					if (ar[i + 1][j - 1] == 1)
						filledNeighbours++;
					if (ar[i + 1][j] == 1)
						filledNeighbours++;
					if (ar[i + 1][j + 1] == 1)
						filledNeighbours++;
					if (ar[i - 1][j - 1] == 1)
						filledNeighbours++;
					if (ar[i - 1][j] == 1)
						filledNeighbours++;
					if (ar[i - 1][j + 1] == 1)
						filledNeighbours++;
					if (ar[i][j - 1] == 1)
						filledNeighbours++;
					if (ar[i][j + 1] == 1)
						filledNeighbours++;
				}
				// filling in a new array according to specified rules 
				boolean flag = false;
				for (int in = 0; in < deadWillBeAlive.size(); in++) {
					if (filledNeighbours == deadWillBeAlive.get(in)) {
						flag = true;
						break;
					}
				}

				if (flag == true) {
					ar_after[i][j] = 1;
				} else if (ar[i][j] == 1) {

					flag = false;
					for (int in = 0; in < aliveIsStillAlive.size(); in++) {
						if (filledNeighbours == aliveIsStillAlive.get(in)) {
							flag = true;
							break;
						}
					}

					if (flag == true) {
						ar_after[i][j] = 1;
					} else {
						ar_after[i][j] = 0;
					}
				}
				// if (filledNeighbours == 3)
				// ar_after[i][j] = 1;
				// else if (filledNeighbours == 2 && ar[i][j] == 1)
				// ar_after[i][j] = 1;
				// else
				// ar_after[i][j] = 0;
			}
		}
		ar = ar_after;
		return ar_after;
	}

	public String printBoard() throws IOException {
		// PrintWriter out = new PrintWriter("TheGameOfLife.txt");
		String output = "";
		StringBuilder sb = new StringBuilder(output);
		for (int i = 0; i < ar.length; i++) {
			for (int j = 0; j < ar[0].length; j++) {
				// out.print(ar[i][j]);
				sb.append(Integer.toString(ar[i][j]));
			}
			// out.println();
			sb.append("<br>");
		}
		output = sb.toString();
		// out.close();
		return "<html>" + output + "</html>";
	}
}
