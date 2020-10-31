import java.util.*;
/**
 * This program creates a 2D Creature world. It then chooses random coordinates for a randomly chosen 
 * Creature object (either Rock Paper or Scissors) and places them in the world. In the main game loop the method
 * move(world) randomly selects a valid direction to move in the world for a Creature object. The move method also stores
 * the new coordinates of the Creature in a 1D int array. The method update uses the cords from the move method 
 * and updates the Creatures current location. Creatures can only be moved and updated if the method getAlive equals
 * true. Otherwise the Creature is considered dead and will not be moved or updated. The main game loop simulates a 
 * dynamical system because each iteration of the loop simulates one time step. This program works for all values 
 * of ROWS and COLS. 
 * 
 * @author Kyle Lynch
 * @version 11/6/2018
 */
public class Driver{ 
	// more rows and cols will make the world bigger
    public static final int ROWS = 5; 
    public static final int COLS = 5; 
    
    // do not change
    public static final int NUM_CREATURES_SELECTION = 3;
    public static final int ROCK = 0;
    public static final int PAPER = 1;
    public static final int SCISSORS = 2;
    
    // this will add more creatures
    public static final int NUM_CREATURES_IN_WORLD = 5;
   
    public static void main(String args[]){ 
        System.out.println("!! Welcome to project 3 !!");
        Creature[][] world;
        Creature[] creatures = new Creature[NUM_CREATURES_IN_WORLD];
        world = new Creature[ROWS][COLS];
        setWorldNull(world);
        giveWorldCreatures(world, creatures);
        Scanner in = new Scanner(System.in); 
        String pressEnter;
        printWorld(world);
        while(true){
            pressEnter = in.nextLine();
            if(pressEnter.equals("exit")){
                break;
            }

            for(int i = 0; i < NUM_CREATURES_IN_WORLD; i++){
                if(creatures[i].getAlive() == true){
                    int[] arr = creatures[i].move(world); 
                    creatures[i].update(arr[0], arr[1]);
                }
            }
            printWorld(world);
        } 
        System.out.println("Done. Normal termination.");
    }

    /**
     * This method sets all of the rows and columns in the 2D Creature object to null.
     * @param inWorld 2D Creature object that represents the board or playing field.
     */
    private static void setWorldNull(Creature[][] inWorld){
        for(int r = 0; r < inWorld.length; r++){
            for(int c = 0; c < inWorld[0].length; c++){
                inWorld[r][c] = null;
            }
        }        
    }

    /**
     * This method creates an amount of Creatures based on the int NUM_CREATURES_IN_WORLD.
     * It randomly chooses a number 0, 1, or 2 to create either a Rock, Paper, or Scissors
     * Creature. It uses the method getEmptyRowCol to find an empty row and column in the 2D
     * Creature world. It uses the cords from getEmptyRowCol to create a new Creature in the
     * world.
     * @param inWorld 2D Creature object that represents the board or playing field.
     * @param creatures 1D Creature object that represents either a Rock Paper or Scissors.
     */
    private static void giveWorldCreatures(Creature[][] inWorld, Creature[] creatures){
        for(int i = 0; i < NUM_CREATURES_IN_WORLD; i++){
            int rint = new java.util.Random().nextInt(NUM_CREATURES_SELECTION); 
            int[] cords = getEmptyRowCol(inWorld);
            if(rint == ROCK){
                creatures[i] = new Rock("R", cords[0], cords[1]);
                inWorld[cords[0]][cords[1]] = creatures[i];
            }
            if(rint == PAPER){
                creatures[i] = new Paper("P", cords[0], cords[1]);
                inWorld[cords[0]][cords[1]] = creatures[i];
            }
            if(rint == SCISSORS){
                creatures[i] = new Scissors("S", cords[0], cords[1]);
                inWorld[cords[0]][cords[1]] = creatures[i];
            }
        }
    }

    /**
     * This method finds an empty row and empty column in the 2D Creature object. It then
     * returns a 1D int array with the coordinates of the empty row and column.
     * @param inWorld 2D Creature object that represents the board or playing field.
     * @return 1D int array that contains the coordinates of the empty row and column.
     */
    private static int[] getEmptyRowCol(Creature[][] inWorld){ 
        Random rand = new Random(); 
        int rrow = rand.nextInt(inWorld.length), rcol = rand.nextInt(inWorld[0].length);
        while(inWorld[rrow][rcol] != null){ 
            rrow = rand.nextInt(inWorld.length); 
            rcol = rand.nextInt(inWorld[0].length); 
        } 
        return new int[]{rrow, rcol}; 
    }

    /**
     * This method prints the world.
     * @param inWorld 2D Creature object that represents the board or playing field.
     */
    private static void printWorld(Creature[][] inWorld){ 
        System.out.print("+");
        for(int i = 0; i < inWorld[0].length; i++){
            System.out.print("-"); 
        }
        System.out.println("+"); 
        for(int row = 0; row < inWorld.length; row++){ 
        
            System.out.print("|"); 
            for(int col = 0; col < inWorld[row].length; col++){ 
                if(inWorld[row][col] == null){ 
                    System.out.print(" "); 
                }else{
                    System.out.print(inWorld[row][col]); 
                }
            }
            System.out.println("|"); 
        } 
        System.out.print("+"); 
        for(int i = 0; i < inWorld[0].length; i++){ 
            System.out.print("-"); 
        }
        System.out.println("+\n"); 
    }
}