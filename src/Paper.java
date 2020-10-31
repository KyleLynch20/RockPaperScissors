/**
 * This class is a subclass of Creature. It Overrides the move, update and toString methods in Creature.
 * The move method contains a Paper Creatures algorithm for movement. The update method updates a Paper Creatures 
 * current location. The toString method returns a String of a Paper Creatures contents. 
 */
public class Paper extends Creature{
    private int startRow, startCol;

    /**
     * This is the Paper constructor it gives Paper Creatures a name. 
     * It also gives Paper Creatures a starting row and starting column.
     * @param name A Paper Creatures name.
     * @param inStartRow The starting row for a Paper Creature.
     * @param inStartCol The starting column for a Paper Creature.
     */
    public Paper(String name, int inStartRow, int inStartCol){
        super(name);
        startRow = inStartRow;
        startCol = inStartCol;
    }

    /**
     * This method chooses a random vaild direction for Paper to move. Valid meaning it will not run into 
     * another paper object or off the world. If the first random direction is not a "valid" move then it will
     * continue to choose a random direction until it can move. Unless of course it is blocked by all Paper objects.
     * If a valid direction is chosen and the creature runs into a different creature object it will kill the other creature
     * object or kill itself accordinely.
     * @param inWorld 2d Creature object that represents the board or playing field.
     * @return A 1D int array that contains a Paper Creatures current cords.
     */
    @Override
    public int[] move(Creature[][] inWorld){ 
        int row = startRow,col = startCol;
        int prevRow = row, prevCol = col;
        boolean canMove = false;

        do{
            int rint = new java.util.Random().nextInt(NUM_DIRECTIONS);
            if(rint == NORTH && row > 0 && !(inWorld[row - 1][col] instanceof Paper)){
                row--;
                canMove = true;
            }else if(rint == SOUTH && row < inWorld.length - 1 && !(inWorld[row + 1][col] instanceof Paper)){
                row++; 
                canMove = true;
            }else if(rint == WEST && col > 0 && !(inWorld[row][col - 1] instanceof Paper)){
                col--; 
                canMove = true;
            }else if(rint == EAST && col < inWorld[0].length - 1 && !(inWorld[row][col + 1] instanceof Paper)){ 
                col++; 
                canMove = true;
            }else{
                canMove = false;
            }
        }while(canMove == false);

        if(canMove == true){ 
            System.out.println("Paper moved from " + "(" + prevRow + "," + prevCol + ") " + "to " + "(" + row + "," + col + ").");
            if(inWorld[row][col] == null){ 
                inWorld[row][col] = this;
            }else{ 
                Creature other = inWorld[row][col];
                if(other instanceof Scissors){ 
                    kill();
                    inWorld[row][col] = new Scissors("S", row, col);
                    System.out.println("Paper killed by Scissors at " + "(" + row + "," + col + ")."); 
                }else if(other instanceof Rock){ 
                    other.kill();
                    inWorld[row][col] = this;
                    System.out.println("Paper killed Rock at " + "(" + row + "," + col + ")."); 
                }
            } 
            inWorld[prevRow][prevCol] = null;
        }else{
            System.out.println("Paper at " + "(" + row + "," + col + ") " + "could not move.");
        }
        return new int[]{row, col};  
    }

    /**
     * This method if called will return a String of the name of a Paper Creature.
     * @return A String that contains the name of the Creature.
     */
    @Override
    public String toString(){
        return name;
    }

    /**
     * This method updates a Paper object current coordinents.
     * @param newStartRow updated row cords.
     * @param newStartCol updated col cords.
     */
    @Override
    public void update(int newStartRow, int newStartCol){
        startRow = newStartRow;
        startCol = newStartCol;
    }
}