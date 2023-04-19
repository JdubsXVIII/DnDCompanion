import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class Character {
    private String name; // the character's name
    private String gender; // the character's gender
    private String alignment; // the character's alignment
    private HashMap<String, Integer> stats; // the characters stats
    private HashMap<String, Integer> statMods; // the character's stat modifiers
    private String race; // the character's race
    private String playerClass; // the character's class
    private int maxHP; // the character's max HP
    private int currentHP; // the character's current HP
    private int hitDie; // the character's hit die value
    private int hitDiceNum; // the number of hit dice the character has
    private int ac; // the character's armor class
    private String background; // the character's background
    private ArrayList<String> skills; // the character's skill proficiencies
    private HashMap<String, Boolean> savingThrows; // the character's saving throw proficiencies
    private ArrayList<String> spells; // the character's spells
    private HashMap<Object, String> features; // the character's features, as determined by their previous choices, and their descriptions.

    /**
     * Creates a new Character object.
     */
    public Character(){}

    /**
     * Sets the stats of the character.
     * @param statList The order the player assigns values to their stats, from highest to lowest.
     */
    public void setStats(ArrayList<String> statList){}

    /**
     * Sets the race of the character.
     * @param r The desired race.
     */
    public void setRace(String r){}

    /**
     * Sets the class of the character.
     * @param c The desired class.
     */
    public void setClass(String c){}

    /**
     * Sets the background of the character.
     * @param bg The desired background.
     */
    public void setBackground(String bg){}

    /**
     * Sets the skills of the character.
     */
    public void setSkills(){}

    /**
     * Sets the spells of the character.
     */
    public void setSpells(){}

    /**
     * Modifies the character's HP.
     * @param hpChange The number added or subtracted from the character's HP.
     */
    public void modifyHP(int hpChange){}
    /**
     * Applies the effects of a short rest to the character.
     */
    public void shortRest(){}

    /**
     * Applies the effects of a long rest to the character.
     */
    public void longRest(){}

    /**
     * Prints the character's information.
     * @return The character information.
     */
    @Override
    public String toString(){
        return null;
    }
}
