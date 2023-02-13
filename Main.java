import java.util.Scanner;

class Main {
  static int[][] grid = new int[9][9];
  static boolean isSafe(int row, int col, int num) {
    // Check row
    for (int d = 0; d < 9; d++) {
      if (grid[row][d] == num) {
        return false;
      }
    }
    // Check column
    for (int r = 0; r < 9; r++) {
      if (grid[r][col] == num) {
        return false;
      }
    }
    // Check subgrid
    int x = row - row % 3;
    int y = col - col % 3;
    for (int r = 0; r < 3; r++) {
      for (int d = 0; d < 3; d++) {
        if (grid[x + r][y + d] == num) {
          return false;
        }
      }
    }
    return true;
  }
  static boolean solveSudoku() {
    int row = -1;
    int col = -1;
    boolean isEmpty = true;
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (grid[i][j] == 0) {
          row = i;
          col = j;
          isEmpty = false;
          break;
        }
      }
      if (!isEmpty) {
        break;
      }
    }
    if (isEmpty) {
      return true;
    }
    for (int num = 1; num <= 9; num++) {
      if (isSafe(row, col, num)) {
        grid[row][col] = num;
        if (solveSudoku()) {
          return true;
        } else {
          grid[row][col] = 0;
        }
      }
    }
    return false;
  }
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the values of the grid, 0 for empty cells:");
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        grid[i][j] = sc.nextInt();
      }
    }
    if (solveSudoku()) {
      System.out.println("Solution:");
      for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
          System.out.print(grid[i][j] + " ");
        }
        System.out.println();
      }
    } else {
      System.out.println("No solution exists");
    }
    sc.close();
  }
}