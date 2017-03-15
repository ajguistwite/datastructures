import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class MazeSolverStack {

	private static String hexMap = "0123456789ABCDEF"; //used to convert input into binary num
	private static int row; 
	private static int col; 
	private static int rowStart; 
	private static int colStart;
	private static int rowEnd;
	private static int colEnd;
	private static boolean[][] visited; //returns true if you have been to a set of coordinates

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		//reads in input, size of maze, start and end point
		row = scan.nextInt();
		col = scan.nextInt();
		rowStart = scan.nextInt();
		colStart = scan.nextInt();
		rowEnd = scan.nextInt();
		colEnd = scan.nextInt();
		
		//new arrayList of type stack
		Stack<ArrayList<Integer>> stack = new Stack();
		Integer[][] items = new Integer[rowEnd][colEnd];
		
		
		visited = new boolean[row][col];
		ArrayList<Integer> path = new ArrayList();
		int currentrow = rowEnd; //where you are currently in the maze
		int currentcol = colEnd;
		scan.nextLine();
		int[][] map = new int[row][col];
		for (int r = 0; r < row; r++) {
			String line = scan.nextLine();
			for (int c = 0; c < col; c++) {
				visited[r][c] = false;
				char ch = line.charAt(c);
				map[r][c] = hexMap.indexOf(ch);
				
			}
			
		}
		path.add(rowEnd);
		path.add(colEnd);
		stack.push(path);
		visited[currentrow][currentcol] = true;
		
		while (currentrow != rowStart || currentcol != colStart) {
			ArrayList<Integer> tempPath = new ArrayList<Integer>();
			
			//west
			if ((map[currentrow][currentcol] & 8) == 0 //checks for wall
					&& visited[currentrow][currentcol - 1] != true) { //checks if you have been there
																		
				--currentcol;
				visited[currentrow][currentcol] = true;
				tempPath.add(currentrow);
				tempPath.add(currentcol);
				stack.push(tempPath); //add this coordinate to the stack
				
				//north
			} else if ((map[currentrow][currentcol] & 1) == 0
					&& visited[currentrow - 1][currentcol] != true) { 
																		
				--currentrow;
				visited[currentrow][currentcol] = true;
				tempPath.add(currentrow);
				tempPath.add(currentcol);
				stack.push(tempPath);
				
				//east
			} else if ((map[currentrow][currentcol] & 4) == 0
					&& visited[currentrow][currentcol + 1] != true) { 
																		
				++currentcol;
				visited[currentrow][currentcol] = true;
				tempPath.add(currentrow);
				tempPath.add(currentcol);
				stack.push(tempPath);
				
				//south
			} else if ((map[currentrow][currentcol] & 2) == 0
					&& visited[currentrow + 1][currentcol] != true) {
																		
				++currentrow;
				visited[currentrow][currentcol] = true;
				tempPath.add(currentrow);
				tempPath.add(currentcol);
				stack.push(tempPath);
				
				//pop off stack to receive path to end
			} else {
				stack.pop();
				currentrow = stack.peek().get(0);
				currentcol = stack.peek().get(1);
			}
		}
		//if the stack is not empty continue to pop off the path
		while (stack.isEmpty() == false) {
			System.out.println(stack.peek().get(0) + "  " + stack.peek().get(1));
			stack.pop();
		}
	}
}
