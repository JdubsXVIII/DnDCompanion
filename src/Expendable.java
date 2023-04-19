
public class Expendable {
    private String name; // the expendable's name
    private int maxUses; // the expendable's maximum number of uses
    private int usesLeft; // the expendable's current number of uses
    private boolean refresh; // the expendable's refresh period. true is short rest, false is long rest

    public Expendable(String name, int maxUses, boolean refresh){}

    /**
     * Subtracts one from the current number of uses. Describes its effects, but does NOT implement them.
     * @return The effect of the expendable, and the amount of uses remaining.
     */
    public String use(){
        return null;
    }

    /**
     * Gets the number of uses remaining. Used in use().
     * @return The number of uses this Expendable has remaining.
     */
    public int getUsesLeft(){
        return 0;
    }

    /**
     * Sets the number of uses remaining. Used by some class features.
     * @param newLeft The new number of uses remaining.
     */
    public void setUsesLeft(int newLeft){}

    /**
     * Gets the maximum number of uses. Used in use().
     * @return The maximum number of uses.
     */
    public int getMaxUses(){
        return 0;
    }

    /**
     * Sets the maximum number of uses.
     * @param newMax The new maximum number of uses.
     */
    public void setMaxUses(int newMax){}

    /**
     * Gets the refresh period. Used in Character.shortRest() and Character.longRest()
     * @return
     */
    public boolean getRefresh(){
        return false;
    }
}
