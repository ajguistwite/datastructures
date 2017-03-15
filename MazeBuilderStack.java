import java.util.ArrayList;

import stack.*;

public class MazeBuilderStack implements MazeBuilderInterface {

	// Maze height and width
	private int height = 0;
	private int width = 0;

	// Array of values for maze walls
	private byte[][] walls = null;

	// Array of visited nodes
	private boolean[][] visited = null;

	// Starting position to build maze
	private int[] start = null;

	// Masks to remove from and to walls
	private byte[] fromMask = { 14, 13, 11, 7 };
	private byte[] toMask = { 13, 14, 7, 11 };

	// Array of position additions for direction
	private int[][] adds = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	// Map integers to hex digits
	private String hexMap = "0123456789ABCDEF";

	// Internal stack
	private StackInterface<int[]> stack = null;

	// Create the maze builder
	public MazeBuilderStack(int h, int w) {
		stack = new StackDynamic<int[]>();
		height = h;
		width = w;
		init();
	}

	// Method to initialize building process (can rebuild if needed)
	@Override
	public void init() {
		// Create walls and visited arrays
		walls = new byte[height][width];
		visited = new boolean[height][width];
		for (int r = 0; r < height; r++)
			for (int c = 0; c < width; c++) {
				walls[r][c] = 15;
				visited[r][c] = false;
			}
	}

	private ArrayList<Integer> getPermutation() {
		// Randomize directions
		ArrayList<Integer> dirs = new ArrayList<Integer>();
		dirs.add(0);
		dirs.add(1);
		dirs.add(2);
		dirs.add(3);
		java.util.Collections.shuffle(dirs);
		return dirs;
	}
	
	@Override
	public void buildMaze() {
		// Set start of maze
		start = new int[2];
		start[0] = (int) (Math.random() * height);
		start[1] = (int) (Math.random() * width);
		visited[start[0]][start[1]] = true;
		try {
			// Push starting moves onto stack
			ArrayList<Integer> dirs = getPermutation();
			while (!dirs.isEmpty()) {
				int val = dirs.remove(0);
				int[] newPos = { start[0] + adds[val][0], start[1] + adds[val][1] };
				// Stack holds 5-element int arrays (fromR, fromC, toR, toC, dir)
				if (isLegal(newPos)) {
					int[] toPush = new int[5];
					toPush[0] = start[0];
					toPush[1] = start[1];
					toPush[2] = newPos[0];
					toPush[3] = newPos[1];
					toPush[4] = val;
					stack.push(toPush);
				}
			}

			// Keep processing until there are no more elements in the stack
			while (!stack.isEmpty()) {
				// Get top of stack
				int[] cur = stack.pop();
				int[] from = new int[2];
				int[] to = new int[2];
				from[0] = cur[0];
				from[1] = cur[1];
				to[0] = cur[2];
				to[1] = cur[3];
				// If move is (still) legal
				if (isLegal(to)) {
					// Make move
					walls[from[0]][from[1]] &= fromMask[cur[4]];
					walls[to[0]][to[1]] &= toMask[cur[4]];
					visited[to[0]][to[1]] = true;
					// Push all new legal moves onto stack
					dirs = getPermutation();
					while (!dirs.isEmpty()) {
						int val = dirs.remove(0);
						int[] newPos = { to[0] + adds[val][0], to[1] + adds[val][1] };
						if (isLegal(newPos)) {
							int[] toPush = new int[5];
							toPush[0] = to[0];
							toPush[1] = to[1];
							toPush[2] = newPos[0];
							toPush[3] = newPos[1];
							toPush[4] = val;
							stack.push(toPush);
						}
					}

				}
			}
		} catch (StackSizeException e) {
			System.out.println("STACK SIZE PROBLEMS!");
		}
	}
	
	// Method to determine if new position is legal
	private boolean isLegal(int[] p) {
		return (p[0] >= 0 && p[0] < height && p[1] >= 0 && p[1] < width && !visited[p[0]][p[1]]);
	}

	@Override
	public byte[][] getWalls() {
		return walls;
	}
	
	// Override toString from parent to return string of maze
	public String toString() {
		String ret = "";
		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c++)
				ret += hexMap.charAt(walls[r][c]);
			if (r < height - 1)
				ret += "\n";
		}
		return ret;
	}

}