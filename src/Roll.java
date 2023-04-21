import java.util.Random;
public class Roll {
    int die;
    /**
     * Creates a new die type to roll.
     * @param die The kind of die rolled.
     */
    public Roll(int die){
        this.die = die;
    }

    /**
     * Calculates a roll.
     * @param diceNum The number of dice being rolled.
     * @param mod The modifer being added to the toll.
     * @return The total rolled.
     */
    public int nextRoll(int diceNum, int mod){
        Random roller = new Random();
        int total = 0;
        for(int i = 0; i < diceNum; i++){
            total += roller.nextInt(1, die+1);
        }
        return total+mod;
    }
}
