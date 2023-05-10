
public class Expendable {
    private String name; // the expendable's name
    private int maxUses; // the expendable's maximum number of uses
    private int usesLeft; // the expendable's current number of uses
    private boolean refresh; // the expendable's refresh period. true is short rest, false is long rest
    private String desc; // the expendable's effects, described in String form

    public Expendable(String name, int maxUses, boolean refresh, String desc){
        this.name = name;
        this.maxUses = maxUses;
        this.usesLeft = maxUses;
        this.refresh = refresh;
        this.desc = desc;
    }

    /**
     * Subtracts one from the current number of uses. Describes its effects, but does NOT implement them.
     * @return The effect of the expendable, and the amount of uses remaining.
     */
    public String use(){
        if(usesLeft > 0){
            usesLeft--;
            return desc + " You have " + usesLeft + " uses remaining.";
        } else {
            return "You have no uses of this left!";
        }
    }

    /**
     * Getter for the name
     * @return The expendable's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the number of uses remaining. Used in use().
     * @return The number of uses this Expendable has remaining.
     */
    public int getUsesLeft(){
        return usesLeft;
    }

    /**
     * Sets the number of uses remaining. Used by some class features.
     * @param newLeft The new number of uses remaining.
     */
    public void setUsesLeft(int newLeft){
        usesLeft = newLeft;
    }

    /**
     * Gets the maximum number of uses. Used in use().
     * @return The maximum number of uses.
     */
    public int getMaxUses(){
        return maxUses;
    }

    /**
     * Sets the maximum number of uses.
     * @param newMax The new maximum number of uses.
     */
    public void setMaxUses(int newMax){
        maxUses = newMax;
    }

    /**
     * Gets the refresh period. Used in Character.shortRest() and Character.longRest()
     * @return
     */
    public boolean getRefresh(){
        return refresh;
    }

    /**
     * Gets the description.
     * @return The expendable's description.
     */
    public String getDesc(){
        return desc;
    }
}
