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
    public Character() {
        stats = new HashMap<>();
        statMods = new HashMap<>();
        skills = new ArrayList<>();
        savingThrows = new HashMap<>();
        spells = new ArrayList<>();
        features = new HashMap<>();
    }

    /**
     * Sets the stats of the character.
     * @param statList The order the player assigns values to their stats, from highest to lowest.
     */
    /**
     * Sets the stats of the character.
     * @param statList The order the player assigns values to their stats, from highest to lowest.
     */
    public void setStats(ArrayList<String> statList) {
        int[] statValues = new int[6];

        // Prompt the user to enter stat values
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < statList.size(); i++) {
            String statName = statList.get(i);
            System.out.print("Enter value for " + statName + ": ");
            int value = scanner.nextInt();
            statValues[i] = value;
        }

        // Set the stats and calculate stat modifiers
        stats.put("Strength", statValues[0]);
        stats.put("Dexterity", statValues[1]);
        stats.put("Constitution", statValues[2]);
        stats.put("Intelligence", statValues[3]);
        stats.put("Wisdom", statValues[4]);
        stats.put("Charisma", statValues[5]);

        calculateStatModifiers();

        // Prompt the user to confirm stat values
        System.out.println("Stats set to: " + stats);
    }

    /**
     * Calculates the stat modifiers based on the current stat values.
     */
    private void calculateStatModifiers() {
        for (String stat : stats.keySet()) {
            int value = stats.get(stat);
            int modifier = (value - 10) / 2;
            statMods.put(stat, modifier);
        }
    }

    /**
     * Sets the race of the character.
     * @param r The desired race.
     */
    public void setRace(String r) {
        race = r;

        // Autofill features based on race
        if (race.equals("Dwarf")) {
            // Autofill features for Dwarf race
            features.put("Darkvision", "You can see in dim light within 60 feet of you as if it were bright light, and in darkness as if it were dim light.");
            features.put("Dwarven Resilience", "You have advantage on saving throws against poison, and you have resistance against poison damage.");
            features.put("Stonecunning", "Whenever you make an Intelligence (History) check related to the origin of stonework, you are considered proficient in the History skill and add double your proficiency bonus to the check.");
        } else if (race.equals("Elf")) {
            // Autofill features for Elf race
            features.put("Darkvision", "You can see in dim light within 60 feet of you as if it were bright light, and in darkness as if it were dim light.");
            features.put("Fey Ancestry", "You have advantage on saving throws against being charmed, and magic can't put you to sleep.");
            features.put("Trance", "Elves don't need to sleep. Instead, they meditate deeply, remaining semiconscious, for 4 hours a day. After resting in this way, you gain the same benefit that a human does from 8 hours of sleep.");
        }

        // Add additional race-specific features here
        //if needed/ wanted

        // Prompt the user to confirm race choice
        System.out.println("Race set to: " + race);
    }
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
