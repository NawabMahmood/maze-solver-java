# Labyrinth Solver

## Overview

This project implements a maze (labyrinth) solver in Java that finds a way out of a 2D maze using multiple search strategies. The maze is represented as a rectangular grid of squares with walls, corridors, and exits. The solver supports three search methods: a recursive search, a stack-based search (depth-first), and a queue-based search (breadth-first).

## Key Components

### Labyrinth.java
- **Purpose:** Represents the maze as a 2D array of squares.
- **Functionality:**
  - Converts a 2D character array into a maze with various square types (walls, corridors, exits, etc.).
  - Validates the maze ensuring it is rectangular, has the minimum required dimensions, and contains only valid characters.
  - Provides methods to get and set square types, generate a random start position, and retrieve non-wall neighbor locations.
  
### SquareType.java
- **Purpose:** Enumerates the different types of squares in the maze.
- **Functionality:**
  - Defines constants for WALL, CORRIDOR, WAYOUT, VISITED, START, and EXIT.
  - Provides helper methods to check if a square can be modified, is a wall, is visited, or represents an exit.
  - Maps characters to corresponding square types and defines custom string representations for display.

### Location.java
- **Purpose:** Encapsulates a position in the maze with row and column coordinates.
- **Functionality:**
  - Provides getter methods for the row and column.
  - Implements equality checks and string representations for debugging and logging.

### PossibleLocations Interface & Implementations
- **Purpose:** Represents a collection of maze positions (locations) to be explored.
- **Implementations:**
  - **PossibleLocationsQueue.java:** Uses an array-based queue to perform breadth-first search.
  - **PossibleLocationsStack.java:** Uses a linked-list stack to perform depth-first search.
- **Functionality:** Both implementations provide methods to add, remove, check if empty, and generate a string representation of the locations to be explored.

### Simulation.java
- **Purpose:** Acts as the entry point of the application and runs the maze exploration.
- **Functionality:**
  - Reads the maze from an input file.
  - Validates command-line arguments to choose the search method (stack, queue, or recursive).
  - Creates the maze object and displays it.
  - Depending on the specified method, it calls the appropriate search algorithm:
    - **Recursive Search:** Explores the maze using recursion.
    - **Stack-Based Search:** Uses a depth-first search strategy.
    - **Queue-Based Search:** Uses a breadth-first search strategy.
  - Provides visual feedback by clearing the screen and printing the current maze state at each step.

### LabyrinthSearchException.java
- **Purpose:** Custom exception class for signaling errors during the maze exploration process.
- **Functionality:** Throws runtime exceptions if the search process encounters an unexpected error (e.g., null locations).

## How the Maze Solving Works

1. **Maze Initialization:**  
   The maze is read from a file, validated, and converted into a 2D array of `SquareType` objects. Walls, corridors, and exits are defined based on the input characters.

2. **Starting Point:**  
   A random starting position is generated (or manually set) provided the square is valid (not a wall).

3. **Search Strategies:**  
   - **Recursive Approach:** The algorithm explores each neighbor recursively, marking visited squares and checking for an exit.
   - **Stack-Based Approach (DFS):** Uses a stack (LIFO) to explore as deep as possible along one branch before backtracking.
   - **Queue-Based Approach (BFS):** Uses a queue (FIFO) to explore all neighbors at the current depth before moving to the next level.
   
4. **Visual Feedback:**  
   The simulation clears the screen and prints the current state of the maze after each move, simulating an animation. Delays are added between moves for a clearer visual progression.

5. **Completion:**  
   Once the exit is found or all possible paths are explored, the simulation prints the final maze and an appropriate message indicating success or failure.

## Running the Project

1. **Compile the Code:**  
   Navigate to the project directory and compile using:
   ```bash
   javac project3/*.java
2. **Run the Simulation:**  
   java project3.Simulation inputMaze.txt stack
   Replace inputMaze.txt with the maze file and choose one of stack, queue, or rec as the search method.
