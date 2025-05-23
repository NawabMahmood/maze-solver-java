package project3;
import java.util.ArrayList;
import java.util.Random;

/**
 * Labyrinth class represents a 2D rectangular maze.
 *
 * @author Joanna Klukowska
 *
 */
public class Labyrinth {

    private SquareType [][] maze;
    private int height;
    private int width;
    private Random rand = new Random();
    private Location start;

    /**
     * Construct a maze object given a valid 2D character array.
     * A valid character array has to satisfy the following requirements
     * - it has to be a rectangle (i.e., each row has the same number of columns)
     * - it has to have at least 3 rows and at least 3 columns
     * - it has to consist of only valid characters:
     * 		- 'x' indicating a solid wall,
     *      - ' ' (space) indicating a corridor that can be used to travel through the maze
     *      - 'o' indicating exist from the maze (these should only be located around the
     *          outer walls of the maze)
     *
     * @param charMaze 2D character array representing a maze
     *    (array has to be a valid maze).
     * @throws IllegalArgumentException when the parameter does not represent a valid maze
     */
    public Labyrinth (char [][] charMaze ) throws IllegalArgumentException {

        if (!isValid(charMaze) )
            throw new IllegalArgumentException("Parameter does not represent a valid maze.");

        int row, column;

        //convert character array to SquareType object array
        SquareType [][] squareMazeFromFile =
            new SquareType[charMaze.length][charMaze[0].length];

        for (row = 0; row < squareMazeFromFile.length; row++)
            for (column = 0; column < squareMazeFromFile[0].length; column++) {
                switch (charMaze[row][column]) {
                case 'x':
                    squareMazeFromFile[row][column] = SquareType.WALL;
                    break;
                case ' ':
                    squareMazeFromFile[row][column] = SquareType.CORRIDOR;
                    break;
                case 'o':
                    squareMazeFromFile[row][column] = SquareType.WAYOUT;
                    break;
                default:
                    System.err.printf("Error: Incorrect maze element.%n");
                    System.exit (1);
                }
            }

        this.maze = squareMazeFromFile;
        this.width = maze[0].length;
        this.height = maze.length;
    }

    /**
     * Returns a SquareType object at a particular position in the maze.
     * @param loc position (row, column)
     * @return SquareType object at a given position or null if the location is invalid
     */
    public SquareType getSquareType( Location loc) {
        if (loc.getRow() >=0 && loc.getRow() < height
                && loc.getColumn() >= 0 && loc.getColumn() < width )
            return maze[loc.getRow()][loc.getColumn()];
        return null;
    }

    /**
     * Generates a random start position for maze exploration.
     * @return an object with information about the start position
     */
    public Location generateStartPosition ( ) {
        //pick coordinates at random
        int col = rand.nextInt(width);
        int row = rand.nextInt(height);
        Location loc = new Location(row,col);
        //keep trying until valid start position coordinates are found
        //(we cannot start from the wall, for example)
        while (!getSquareType(loc).canBeSet()) {
            col = rand.nextInt(width);
            row = rand.nextInt(height);
            loc = new Location(row,col);
        }
        //store and return the start position selected
        start = new Location(row, col) ;
        markSquareToStart();
        return loc;
    }

    /**
     * Sets the start position for this maze to (col,row).
     * @param col  the zero-based column number for the start position
     * @param row  the zero-based row number for the start position
     * @return  the SquarePosition object corresponding to the set start
     * position or null if the (col,row) parameters do not correspond to
     * a valid position for a start position (for example, they are
     * coordinates of a wall)
     */
    public Location setStartPosition (int col, int row ) {
        Location loc = new Location(row,col);
        //keep trying until valid start position coordinates are found
        //(we cannot start from the wall, for example)
        if (!getSquareType(loc).canBeSet()) {
            return null;
        }
        //store and return the start position selected
        start = new Location(row, col) ;
        markSquareToStart();
        return loc;
    }

    /**
     * Marks position as visited.
     * @param loc  position of a square that should be set to visited
     */
    public void setSquareToVisited ( Location loc ) {
        if ((loc.getRow() >=0 && loc.getRow() < height 					//verify row
                && loc.getColumn() >= 0 && loc.getColumn() < width )    //verify column
                && maze[loc.getRow()][loc.getColumn()].canBeSet() )     //check if it is a corridor

            maze[loc.getRow()][loc.getColumn()] = SquareType.VISITED;
    }

    /**
     * Marks position as an exit.
     * @param loc position of a square that should be set to exit
     */
    public void setSquareToExit ( Location loc ) {
        if ((loc.getRow() >=0 && loc.getRow() < height
                && loc.getColumn() >= 0 && loc.getColumn() < width )
                && maze[loc.getRow()][loc.getColumn()].isWayOut() )
            maze[loc.getRow()][loc.getColumn()] = SquareType.EXIT;
    }


    /**
     * Marks position as the start position.
     */
    private void markSquareToStart ( ) {
        if (start != null )
            maze[start.getRow()][start.getColumn()] = SquareType.START;
    }

    /**
     * Returns, a possibly empty, list of neighbors that are not walls
     * of a square at a specified position.
     * @param loc  a Location object representing a position of a square
     * @return  list of non-wall neighbors
     */
    public ArrayList<Location> getNeighbors ( Location loc ) {
        ArrayList<Location> list = new ArrayList<Location>();
        int row = loc.getRow();
        int column = loc.getColumn();
        Location newSquarePostion = null;
        if (row >=0 && row < height && column >= 0 && column < width ) {

            if ( row > 0  ) {	//check only if not at the top
                newSquarePostion = new Location (row-1, column);
                if ( ! getSquareType(newSquarePostion).isWall() )
                    if (rand.nextBoolean())
                        list.add(newSquarePostion);
                    else list.add(0,newSquarePostion);
            }

            if ( column < width-1  ) {	// check only if not rightmost column
                newSquarePostion = new Location (row, column+1);
                if ( ! getSquareType(newSquarePostion).isWall() )

                    if (rand.nextBoolean())
                        list.add(newSquarePostion);
                    else list.add(0,newSquarePostion);
            }

            if ( row < height-1  ) {  // check only if not at the bottom
                newSquarePostion = new Location (row+1, column);
                if ( ! getSquareType(newSquarePostion).isWall() )
                    if (rand.nextBoolean())
                        list.add(newSquarePostion);
                    else list.add(0,newSquarePostion);
            }

            if ( column > 0  ) {	//check only if not leftmost
                newSquarePostion = new Location (row, column-1);
                if ( ! getSquareType(newSquarePostion).isWall() )
                    if (rand.nextBoolean())
                        list.add(newSquarePostion);
                    else list.add(0,newSquarePostion);
            }
        }
        return list;
    }

    /**
     * Returns the heights of this maze.
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the width of this maze.
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Generates 2D string representation of this maze indicating the
     * position of walls, corridors, visited spaces, starting space and
     * the exit location (for completed mazes).
     * @return the string representation of this maze
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        String output = "";
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                output = output + maze[row][column].toString();
            }
            output = output + "\n";
        }
        return output;
    }

    /**
     * Determines if the character array representing a maze is valid
     * maze representation or not.
     * @param maze  2D array representing a maze
     * @return
     *    true, if the array represents a valid maze
     *    false, otherwise
     */
    private boolean isValid (char [][] maze ) {
        int row, column;

        //verify that the maze matrix is at least 3x3
        if (maze.length < 3 || maze[0].length < 3)
            return false;

        //verify that each row has the same number of elements
        int rowLength = maze[0].length;
        for (row = 1; row < maze.length; row++) {
            if (maze[row].length != rowLength )
                return false;
        }

        //verify that only valid characters are present in the matrix
        SquareType s;
        for (row = 0; row < maze.length; row++) {
            for (column = 0; column < rowLength; column++) {
                //s will be null if character does not represent a valid maze square
                s = SquareType.fromChar(maze[row][column]);
                if ( s == null ) {
                    return false;
                }
                //verify that WAYOUT squares are only on the boundary
                if (s.equals(SquareType.WAYOUT)) {
                    //should be either first or last row or column
                    if (!( row ==0 || column == 0 ||
                            row == maze.length-1 || column == rowLength - 1))
                        return false;
                }
            }
        }
        //if we got here, the maze is valid
        return true;
    }

}
