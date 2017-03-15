public interface MazeBuilderInterface {

	// Method to initialize building process (can rebuild if needed)
	void init();

	// Method to set the starting position and build the maze
	void buildMaze();

	// Get the walls of the maze
	byte[][] getWalls();

}
