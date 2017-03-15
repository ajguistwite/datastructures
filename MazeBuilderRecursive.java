import java.util.ArrayList;

public class MazeBuilderRecursive implements MazeBuilderInterface {
	
	// Maze height and width
	private int height = 0;
	private int width = 0;
	
	// Array of values for maze walls
	private byte[][] walls = null; //make bytes ints
	
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
	
	// Basic constructor
	public MazeBuilderRecursive(int h, int w) {
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
	
	// Method to set the starting position and build the maze
	@Override
	public void buildMaze() {
		// Set start of maze
		start = new int[2];
		start[0] = (int) (Math.random() * height);
		start[1] = (int) (Math.random() * width);
		buildMazeInternal(start);
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
	
	// Recursive method to build maze from position
	private void buildMazeInternal(int[] pos) {
		
		// Visit current position
		visited[pos[0]][pos[1]] = true;

		// Randomize directions
		ArrayList<Integer> dirs = getPermutation();

		// Recursively try all directions
		while (!dirs.isEmpty()) {
			int val = dirs.remove(0);
			int[] newPos = { pos[0] + adds[val][0], pos[1] + adds[val][1] };
			// Try direction only if legal
			if (isLegal(newPos)) {
				walls[pos[0]][pos[1]] &= fromMask[val];
				walls[newPos[0]][newPos[1]] &= toMask[val];
				buildMazeInternal(newPos);
			}
		}

	}
	
	// Method to determine if new position is legal
	private boolean isLegal(int[] p) {
		return (p[0] >= 0 && p[0] < height && p[1] >= 0 && p[1] < width && !visited[p[0]][p[1]]);
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
	
	// Get the walls of the maze
	@Override
	public byte[][] getWalls() {
		return walls;
	}
	
}
