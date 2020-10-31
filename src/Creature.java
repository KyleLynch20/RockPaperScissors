/**
 * The purpose of this class is to define common behaviors among Creatures that
 * can be inherited by multiple subclasses.
 */
public abstract class Creature{
    public final static int NORTH = 0;
    public final static int SOUTH = 1;
    public final static int WEST = 2;
    public final static int EAST = 3;
    public final static int NUM_DIRECTIONS = 4;
    protected final String name;
    private boolean isAlive;

    /**
     * This is the Creature constructor it gives Creatures a name. 
     * It also gives Creatures an alive status of true.
     * @param inName A Creatures name either R, P, or S.
     */
    public Creature(String inName){
        name = inName;
        this.isAlive = true;
    }

    /**
     * This method creates the foundation for movement for Creature objects.
     * @param inWorld 2D Creature object that represents the board or playing field.
     * @return A 1D int array that contains new random coordinates for a Creature. 
     */
    public abstract int[] move(Creature[][] inWorld);

    /**
     * This method can be used to display meaningful details about the object.
     * @return A String that contains details about the object.
     */
    public abstract String toString();

    /**
     * This method allows a Creature to update its current coordinates.
     * @param newStartRow updated row cords.
     * @param newStartCol updated col cords.
     */
    public abstract void update(int newStartRow, int newStartCol);

    /**
     * This method can be used for a Creature to kill itself. 
     * It can also be used for a Creature to kill another Creature.
     */
    public void kill(){
        this.isAlive = false;
    }

    /**
     * This method returns a Creatures life status.
     * @return True means that the Creature is still alive. 
     * False means that the Creature is dead.
     */
    public boolean getAlive(){
        return this.isAlive;
    }
}
