/**
 * This class is a subclass of Creature. It Overrides the move, update and toString methods in Creature.
 * The move method contains a Scissors Creatures algorithm for movement. The update method updates a Scissors Creatures 
 * current location. The toString method returns a String of a Scissors Creatures contents.
 */
public class Scissors extends Creature
{
    private int startRow, startCol;

    /**
     * This is the Scissors constructor it gives Scissors Creatures a name. 
     * It also gives Scissors Creatures a starting row and starting column.
     * @param name A Scissors Creatures name.
     * @param inStartRow The starting row for a Scissors Creature.
     * @param inStartCol The starting column for a Scissors Creature.
     */
    public Scissors(String name, int inStartRow, int inStartCol)
    {
        super(name);
        startRow = inStartRow;
        startCol = inStartCol;
    }

    /**
     * This method chooses a random vaild direction for Scissors to move. Valid meaning it will not run into 
     * another Scissors object or off the world. If the first random direction is not a "valid" move then it will
     * continue to choose a random direction until it can move. Unless of course it is blocked by all Scissors objects.
     * If a valid direction is chosen and the creature runs into a different creature object it will kill the other creature
     * object or kill itself accordinely.
     * @param inWorld 2d Creature object that represents the board or playing field.
     * @return A 1D int array that contains a Scissors Creature current cords.
     */
    @Override
    public int[] move(Creature[][] inWorld){
        int row = startRow,col = startCol;
        int prevRow = row, prevCol = col;
        boolean canMove = false;
        
        do{
            int rint = new java.util.Random().nextInt(NUM_DIRECTIONS);
            if(rint == NORTH && row > 0 && col > 0 && !(inWorld[row - 1][col - 1] instanceof Scissors)){
                row--;
                col--;
                canMove = true;
            }else if(rint == SOUTH && row < inWorld.length - 1 && col < inWorld.length - 1 && !(inWorld[row + 1][col + 1] instanceof Scissors)){
                row++; 
                col++;
                canMove = true;
            }else if(rint == WEST && col > 0 && row < inWorld.length - 1 && !(inWorld[row + 1][col - 1] instanceof Scissors)){
                col--; 
                row++;
                canMove = true;
            }else if(rint == EAST && col < inWorld[0].length - 1 && row > 0 && !(inWorld[row - 1][col + 1] instanceof Scissors)){ 
                col++; 
                row--;
                canMove = true;
            }else{
                canMove = false;
            }
        }while(canMove == false);

        if(canMove == true){ 
            System.out.println("Scissors moved from " + "(" + prevRow + "," + prevCol + ") " + "to " + "(" + row + "," + col + ").");
            if(inWorld[row][col] == null){ 
                inWorld[row][col] = this;
            }else{ 
                Creature other = inWorld[row][col];
                if(other instanceof Paper){    
                    other.kill();
                    inWorld[row][col] = this;
                    System.out.println("Scissors killed Paper at " + "(" + row + "," + col + ")."); 
                }else if(other instanceof Rock){    
                    kill();
                    inWorld[row][col] = new Rock("R", row, col);              
                    System.out.println("Scissors killed by Rock at " + "(" + row + "," + col + ")."); 
                }
            } 
            inWorld[prevRow][prevCol] = null;
        }else{
            System.out.println("Scissors at " + "(" + row + "," + col + ") " + "could not move.");
        }
        return new int[]{row, col};   
    }

    /**
     * This method if called will return a String of the name of a Scissors Creature.
     * @return A String that contains the name of the Creature.
     */
    public String toString(){
        return name;
    }

    /**
     * This method updates a Scissors object current coordinents.
     * @param newStartRow updated row cords.
     * @param newStartCol updated col cords.
     */
    @Override
    public void update(int newStartRow, int newStartCol){
        startRow = newStartRow;
        startCol = newStartCol;
    }
}
