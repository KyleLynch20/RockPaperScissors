/**
 * This class is a subclass of Creature. It Overrides the move, update and toString methods in Creature.
 * The move method contains a Rock Creatures algorithm for movement. The update method updates a Rock Creatures 
 * current location. The toString method returns a String of a Rock Creatures contents. 
 */
public class Rock extends Creature{ 
    private int startRow, startCol;
    
    /**
     * This is the Rock constructor it gives Rock Creatures a name. 
     * It also gives Rock Creatures a starting row and starting column.
     * @param name A Rock Creatures name.
     * @param inStartRow The starting row for a Rock Creature.
     * @param inStartCol The starting column for a Rock Creature.
     */
    public Rock(String name, int inStartRow, int inStartCol){
        super(name);
        startRow = inStartRow;
        startCol = inStartCol;
    }
    
    /**
     * This method chooses a random vaild direction for Rock to move. Valid meaning it will not run into 
     * another Rock object or off the world. If the first random direction is not a "valid" move then it will
     * stay put and not move. If a valid direction is chosen and the creature runs into a different 
     * creature object, it will kill the other creature object or kill itself accordinely.
     * @param inWorld 2d Creature object that represents the board or playing field.
     * @return A 1D int array that contains a Rock Creatures current cords.
     */
    @Override
    public int[] move(Creature[][] inWorld){ 
        int row = startRow,col = startCol;
        int prevRow = row, prevCol = col;
        int rint = new java.util.Random().nextInt(NUM_DIRECTIONS);
        
        if(rint == NORTH && row > 0 && !(inWorld[row - 1][col] instanceof Rock)){
            row--; 
        }else if(rint == SOUTH && row < inWorld.length - 1 && !(inWorld[row + 1][col] instanceof Rock)){ 
            row++; 
        }else if(rint == WEST && col > 0 && !(inWorld[row][col - 1] instanceof Rock)){
            col--; 
        }else if(rint == EAST && col < inWorld[0].length - 1 && !(inWorld[row][col + 1] instanceof Rock)){ 
            col++; 
        }

        if(prevRow != row || prevCol != col){ 
            System.out.println("Rock moved from " + "(" + prevRow + "," + prevCol + ") " + "to " + "(" + row + "," + col + ").");
            if(inWorld[row][col] == null){ 
                inWorld[row][col] = this;
            }else{ 
                Creature other = inWorld[row][col];
                if(other instanceof Scissors){                                         
                    other.kill();
                    inWorld[row][col] = this;                    
                    System.out.println("Rock killed Scissors at " + "(" + row + "," + col + ")."); 
                }else if(other instanceof Paper){ 
                    kill();
                    inWorld[row][col] = new Paper("P", row, col);   
                    System.out.println("Rock killed by Paper at " + "(" + row + "," + col + ")."); 
                }
            } 
            inWorld[prevRow][prevCol] = null;
        }else{
            System.out.println("Rock at " + "(" + row + "," + col + ") " + "could not move.");
        }
        return new int[]{row, col}; 
    }
    
    
    /**
     * This method updates a Rock object current coordinents.
     * @param newStartRow updated row cords.
     * @param newStartCol updated col cords.
     */
    @Override
    public void update(int newStartRow, int newStartCol){ 
        startRow = newStartRow;
        startCol = newStartCol;
    }
    
    /**
     * This method if called will return a String of the name of a Rock Creature.
     * @return A String that contains the name of the Creature.
     */
    @Override
    public String toString(){
        return name;
    }
}