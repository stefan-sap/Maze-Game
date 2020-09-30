package solution;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * A maze game.
 * 
 * @author Stefan Saponja
 * @version 1.23.2019
 *
 */
public class MazeGame
{
    /**
     * The size of each side of the game map.
     */
    private final static int HEIGHT = 19;
    private final static int WIDTH = 39;

    /**
     * The game map, as a 2D array of ints.
     */
    private boolean[][] blocked;

    /**
     * The game map of breadcrumbs from previous player moves.
     */
    private boolean[][] breadcrumbs;
    /**
     * The current location of the player vertically.
     */
    // TODO: add field here.
    private int userRow;

    /**
     * The current location of the player horizontally.
     */
    // TODO: add field here.
    private int userCol;
    /**
     * The scanner from which each move is read.
     */
    // TODO: add field here.
    Scanner moveScanner = new Scanner(System.in);
    /**
     * The row and column of the goal.
     */
    // TODO: add fields here.
    private int goalRow;
    private int goalCol;
    /**
     * The row and column of the start.
     */
    // TODO: add fields here.
    private int startRow;
    private int startCol;
    /**
     * Constructor initializes the maze with the data in 'mazeFile'.
     * @param mazeFile the input file for the maze
     * 
     */
    public MazeGame(String mazeFile) 
    {

        // TOD0
        moveScanner = new Scanner(System.in);
        loadMaze(mazeFile);

    }
    /**
     * @param mazeFile the input file for the maze
     * @param moveScanner the scanner object from which to read user moves
     *
     */
    public MazeGame(String mazeFile, Scanner moveScanner)
    {
        // TODO
        loadMaze(mazeFile);
        this.moveScanner = moveScanner;
    }

    /**
     * getMaze returns a copy of the current maze for testing purposes.
     * 
     * @return the grid
     */
    public boolean[][] getMaze()
    {
        if (blocked == null)
        {
            return null;
        }
        boolean[][] copy = new boolean[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++)
        {
            for (int j = 0; j < WIDTH; j++)
            {
                copy[i][j] = blocked[i][j];
            }
        }
        return copy;
    }

    /**
     * setMaze sets the current map for testing purposes.
     * 
     * @param maze
     *            another maze.
     */
    public void setMaze(boolean[][] maze)
    {
        this.blocked = maze;
    }

    /**
     * getUserRow gets the user's vertical position.
     * 
     * @return userRow
     */
    public int getUserRow()
    {
        return userRow;
    }

    /**
     * setUserRow sets the user's vertical position.
     * 
     * @param userRow is the new pos of the user.
     */
    public void setUserRow(int userRow)
    {
        this.userRow = userRow;
    }

    /**
     * getUserCol gets the user's horizontal position.
     * 
     * @return userCol is the horizontal pos of the user.
     */
    public int getUserCol()
    {
        return userCol;
    }

    /**
     *setUserCol sets the user's horizontal position.
     *
     *@param userCol is the new pos.
     */
    public void setUserCol(int userCol)
    {
        this.userCol = userCol;
    }

    /**
     * getGoalRow gets the goal's vert pos
     * 
     * @return goalRow is the pos.
     */
    public int getGoalRow()
    {
        return goalRow;
    }

    /**
     * setGoalRow sets the goals vert pos 
     * @param goalRow is the new pos.
     */
    public void setGoalRow(int goalRow)
    {
        this.goalRow = goalRow;
    }

    /**
     * getGoalCol gets the goal's horz pos.
     * @return goalCol is the pos.
     */
    public int getGoalCol()
    {
        return goalCol;
    }

    /**
     * setGoalCol set the goal's horz pos.
     * @param goalCol is the new pos.
     */
    public void setGoalCol(int goalCol)
    {
        this.goalCol = goalCol;
    }

    /**
     * getStartRow gets the starts vert pos.
     * @return startRow is the start pos.
     */
    public int getStartRow()
    {
        return startRow;
    }

    /**
     * setStartRow sets the starts vert pos.
     * @param startRow is the new vert pos.
     */
    public void setStartRow(int startRow)
    {
        this.startRow = startRow;
    }

    /**
     * getStartCol is the start's horz pos.
     * @return startCol is the horz pos.
     */
    public int getStartCol()
    {
        return startCol;
    }

    /**
     * setStartCol is the start's new horz pos.
     * @param startCol is the new horz pos.
     */
    public void setStartCol(int startCol)
    {
        this.startCol = startCol;
    }
    /**
     * getMoveScanner gets the scanner used to detect moves.
     * 
     * @return moveScanner is the user's next move.
     */
    public Scanner getMoveScanner()
    {
        return moveScanner;
    }

    /**
     * setMoveScanner set's the scanner use to detect moves.
     * 
     * @param moveScanner is the designated scanner.
     */
    public void setMoveScanner(Scanner moveScanner)
    {
        this.moveScanner = moveScanner;
    }

    /**
     * Function loads the data from the maze file and creates the 'blocked' 
     * 2D array.
     *  
     * @param mazeFile the input maze file.
     * @throws IOException for missing file.
     */
    // TODO: private void loadMaze(String mazeFile)
    private void loadMaze(String mazeFile)
    {  

        try {
            //Create a file, and fileScanner
            File maze = new File(mazeFile);
            Scanner fileScanner = new Scanner(maze);

            setUserRow(getStartRow());
            setUserCol(getStartCol());
            /*Iterate through each String object delimited by white space
             * in the maze file.
             */
            this.blocked = new boolean[HEIGHT][WIDTH];
            
            //Breadcrumbs
            this.breadcrumbs = new boolean[HEIGHT][WIDTH];


            for (int i = 0; i < blocked.length; i++)
            {
                for (int j = 0; j < blocked[i].length; j++)
                {
                    if (fileScanner.hasNext())
                    {
                        String current = fileScanner.next();
                        if (current.equals("1"))
                        {
                            blocked[i][j] = true;
                        }
                        else if (current.equals("S"))
                        {
                            startRow = i;
                            startCol = j;
                        }
                        else if (current.equals("G"))
                        {
                            goalRow = i;
                            goalCol = j;                   
                        }
                        else
                        {
                            blocked[i][j] = false;
                        }

                    }
                }
            }
            //Close the fileScanner
            fileScanner.close();
        }
        catch(Exception iOException)
        {
            System.out.println("File Error");
        }

    }
    /**
     * Actually plays the game.
     */
    public void playGame()
    {

        boolean gameOver = false;
        
        while (gameOver == false)
        {
            if (playerAtGoal() == false) {
                printMaze();
                playerAtGoal();
                System.out.println("What's your next move?");
                String move = moveScanner.next();
                if (move.equals("quit"))
                {
                    gameOver = true;
                }
                else
                {
                    makeMove(move);
                }
            }
            else
            {
                printMaze();
                System.out.println("You won!");
                gameOver = true;
                
            }
           
        }
    }


    /**
     * Checks to see if the player has won the game.
     * @return true if the player has won.
     */
    // TODO: public boolean playerAtGoal()
    public boolean playerAtGoal()
    {
        if (getUserRow() == getGoalRow() && getUserCol() == getGoalCol())
        {
            return true;
        }
        return false;
    }
    /**
     * Makes a move based on the String.
     * 
     * @param move
     *            the direction to make a move in.
     * @return whether the move was valid.
     */
    public boolean makeMove(String move)
    {
        // TODO
        if (move.toLowerCase().charAt(0) == 'u')
        {
            //Move up
            if (getUserRow() - 1 >= 0 
                && blocked[getUserRow() - 1][getUserCol()] != true)
            {
                this.breadcrumbs[getUserRow()][getUserCol()] = true;
                setUserRow(getUserRow() - 1);
                return true;
            }
            else
            {
                return false;
            }
        }
        else if (move.toLowerCase().charAt(0) == 'd')
        {
            //Move down
            if (getUserRow() + 1 <= HEIGHT - 1 
                && blocked[getUserRow() + 1][getUserCol()] != true)
            {
                this.breadcrumbs[getUserRow()][getUserCol()] = true;
                setUserRow(getUserRow() + 1);
                return true;
            }
            else
            {
                return false;
            }
        }
        else if (move.toLowerCase().charAt(0) == 'r')
        {
            //Move right
            if (getUserCol() + 1 <= WIDTH - 1 
                && blocked[getUserRow()][getUserCol() + 1] != true)
            {
                this.breadcrumbs[getUserRow()][getUserCol()] = true;
                setUserCol(getUserCol() + 1);
                return true;
            }
            else
            {
                return false;
            }
        }
        else if (move.toLowerCase().charAt(0) == 'l')
        {
            //Move left
            if (getUserCol() - 1 >= 0 
                && blocked[getUserRow()][getUserCol() - 1] != true)
            {
                this.breadcrumbs[getUserRow()][getUserCol()] = true;
                setUserCol(getUserCol() - 1);
                return true;
            }
            else
            {
                return false;
            }
        }
        else if (move.equals("quit"))
        {
            System.out.println("Quitting...");
            System.exit(0);
        }
        else
        {
            System.out.println("Enter 'u' for up, 'd' for down, 'l' "
                + "for left and 'r' for right.");
        }
        return false;
    }

    /**
     * Prints the map of the maze.
     */
    public void printMaze()
    {
        // TODO
        //Breadcrumb trail.
        //Prints the top border.
        String hBorder = "*" 
            + String.format("%39s", "").replace(' ', '-') + "*";

        System.out.print(hBorder + '\n');

        for (int i = 0; i < HEIGHT; i++)
        {
            //Print the left border
            System.out.print("|");

            for (int j = 0; j < WIDTH; j++)
            {
                /*First check if the user is in the current position of the
                 * array.
                 */
                if (i == getUserRow() && j == getUserCol())
                {
                    System.out.print("@");
                }
                /*
                 * Otherwise print the other parts of the maze.
                 */
                else 
                {
                    if (i == startRow && j == startCol)
                    {
                        System.out.print("S");
                    }
                    else if (i == goalRow && j == goalCol)
                    {
                        System.out.print("G");
                    }
                    else if (this.blocked[i][j] == true)
                    {
                        System.out.print("X");
                    }
                    else if (this.breadcrumbs[i][j] == true)
                    {
                        System.out.print(".");
                    }
                    else
                    {
                        System.out.print(" ");
                    }

                }

            }
            //Prints the right border.
            System.out.print("|\n");
        }
        System.out.print(hBorder + "\n");
    }

    /**
     * Creates a new game, using a command line argument file name, if one is
     * provided.
     * 
     * @param args the command line arguments
     * @throws IOException for missing file.
     */

    public static void main(String[] args)
    {

        String mapFile = "data/easy.txt";
        Scanner scan = new Scanner(System.in);
        MazeGame game = new MazeGame(mapFile, scan);
        game.playGame();
    }
}
