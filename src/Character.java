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
    private ArrayList<Expendable> expFeatures; // the character's expendable features.

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
        expFeatures = new ArrayList<>();
    }

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
        expFeatures.add(new Expendable("Spell Slots", 6,false,"Spellcasting classes have a limited number of spell slots that are expended when casting spells. These slots are typically replenished after a rest."));
        expFeatures.add(new Expendable("Hit Dice", 5,true,"During short rests, characters can spend Hit Dice to regain hit points. Hit Dice are expended until the character rests and regains them."));
        expFeatures.add(new Expendable("Channel Divinity",2,false,"Some classes, such as clerics and paladins, have access to Channel Divinity, granting them limited-use divine abilities that can be expended during encounters."));
        expFeatures.add(new Expendable("Bardic Inspiration",3,false, "Bards can expend uses of Bardic Inspiration to grant bonuses to allies' ability checks, attack rolls, or saving throws."));
        expFeatures.add(new Expendable("Superiority Dice",4,false,"Fighters with the Battle Master archetype possess Superiority Dice, which can be expended to perform combat maneuvers."));
        expFeatures.add(new Expendable("Wild Shape",4,false,"Druids have the ability to transform into animals using Wild Shape, which is limited by a certain number of uses per rest"));
        expFeatures.add(new Expendable("Rage",3,false,"Barbarians can enter a rage, granting them bonuses to damage and resistance to certain types of damage. Rage is limited by a number of uses per rest."));
        expFeatures.add(new Expendable("Ki Points",13,false,"Monks can spend Ki Points to fuel various abilities, such as Stunning Strike or Flurry of Blows. Ki Points are limited but can be replenished after a rest."));
        expFeatures.add(new Expendable("Arcane Recovery",4,true,"Wizards have the Arcane Recovery feature, allowing them to regain expended spell slots during a short rest once per day."));


        // Add additional race-specific features here
        //if needed/ wanted

        // Prompt the user to confirm race choice
        System.out.println("Race set to: " + race);
    }
    /**
     * Sets the class of the character.
     * @param c The desired class.
     */
    public void setClass(String c){
        Scanner console = new Scanner(System.in);
        if(c.equals("Barbarian")){

        } else if(c.equals("Bard")){

        } else if(c.equals("Cleric")){

        } else if(c.equals("Druid")){

        } else if(c.equals("Fighter")){
            playerClass = c;
            maxHP = 10 + statMods.get("Constitution");
            hitDie = 10;
            hitDiceNum = 1;
            ac = 16;
            expFeatures.add(new Expendable("Second Wind", 1, true, "Restore 1d10 HP."));
            System.out.println("Please type in one of the following fighting styles:");
            System.out.println("- Archery\n- Defense\n- Dueling\n- Great Weapon Fighting\n- Protection\n- Two-Weapon Fighting");
            String input = console.next();
            if(input.equals("Archery")){
                features.put("Archery", "You gain a +2 bonus to attack rolls you make with ranged weapons.");
            } else if (input.equals("Defense")){
                features.put("Defense", "While you are wearing armor, gain a +1 bonus to AC.");
                ac++;
            } else if (input.equals("Dueling")){
                features.put("Dueling", "When you are wielding a melee weapon in one hand and no other weapons, you gain a +2 bonus to damage rolls with that weapon.");
            } else if (input.equals("Great Weapon Fighting")){
                features.put("Great Weapon Fighting", "When you roll a 1 or 2 on a damage die for an attack you make with a melee weapon that you are wielding with two hands, you can reroll the die and must use the new roll, even if the new roll is a 1 or a 2. The weapon must have the two-handed or versatile property for you to gain this benefit.");
            } else if (input.equals("Protection")){
                features.put("Protection","When a creature you can see attacks a target other than you that is within 5 feet of you, you can use your reaction to impose disadvantage on the attack roll. You must be wielding a shield.");
            } else {
                features.put("Two-Weapon Fighting","When a creature you can see attacks a target other than you that is within 5 feet of you, you can use your reaction to impose disadvantage on the attack roll. You must be wielding a shield.");
            }
        } else if(c.equals("Monk")){

        } else if(c.equals("Paladin")){

        } else if(c.equals("Ranger")){

        } else if(c.equals("Rogue")){

        } else if(c.equals("Sorcerer")){

        } else if(c.equals("Warlock")){

        } else { // the only remaining class is Wizard.

        }
    }

    /**
     * Sets the background of the character.
     * @param bg The desired background.
     */
    public void setBackground(String bg) {
        background = bg;

        // Autofill features based on background
        if (background.equals("Acolyte")) {
            // Autofill features for Acolyte background
            features.put("Shelter of the Faithful", "As an acolyte, you command the respect of those who share your faith, and you can perform the religious ceremonies of your deity. You and your adventuring companions can expect to receive free healing and care at a temple, shrine, or other established presence of your faith, though you must provide any material components needed for spells. Those who share your religion will support you (but only you) at a modest lifestyle.");
            features.put("Languages", "You can speak, read, and write two additional languages of your choice.");
        } else if (background.equals("Criminal")) {
            // Autofill features for Criminal background
            features.put("Criminal Contact", "You have a reliable and trustworthy contact who acts as your liaison to a network of other criminals. You know how to get messages to and from your contact, even over great distances; specifically, you know the local messengers, corrupt caravan masters, and seedy sailors who can deliver messages for you.");
            features.put("Tool Proficiencies", "One type of gaming set, thieves' tools");
        }

        // Add additional background-specific features here

        // Prompt the user to confirm background choice
        System.out.println("Background set to: " + background);
    }

    /**
     * Sets the skills of the character.
     */
    public void setSkills() {
        // List of available skills based on class and background
        ArrayList<String> availableSkills = new ArrayList<>();

        // Add skills based on class
        if (playerClass.equals("Rogue")) {
            availableSkills.add("Acrobatics");
            availableSkills.add("Athletics");
            availableSkills.add("Deception");
            availableSkills.add("Insight");
            availableSkills.add("Intimidation");
            availableSkills.add("Investigation");
            availableSkills.add("Perception");
            availableSkills.add("Performance");
            availableSkills.add("Persuasion");
            availableSkills.add("Sleight of Hand");
            availableSkills.add("Stealth");
        } else if (playerClass.equals("Wizard")) {
            availableSkills.add("Arcana");
            availableSkills.add("History");
            availableSkills.add("Insight");
            availableSkills.add("Investigation");
            availableSkills.add("Medicine");
            availableSkills.add("Perception");
            availableSkills.add("Religion");
            availableSkills.add("Survival");
        }

        // Add skills based on background
        if (background.equals("Acolyte")) {
            availableSkills.add("Insight");
            availableSkills.add("Religion");
        } else if (background.equals("Criminal")) {
            availableSkills.add("Deception");
            availableSkills.add("Stealth");
        }

        // Prompt the user to choose skills
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose " + playerClass + " skills from the following list:");
        for (int i = 0; i < availableSkills.size(); i++) {
            System.out.println((i + 1) + ". " + availableSkills.get(i));
        }
        System.out.print("Enter the numbers of the chosen skills (comma-separated): ");
        String input = scanner.nextLine();
        String[] skillNumbers = input.split(",");

        // Add chosen skills to the character's skill list
        for (String number : skillNumbers) {
            int index = Integer.parseInt(number.trim()) - 1;
            if (index >= 0 && index < availableSkills.size()) {
                String chosenSkill = availableSkills.get(index);
                skills.add(chosenSkill);
            }
        }

        // Prompt the user to confirm chosen skills
        System.out.println("Chosen skills: " + skills);
    }


    /**
     * Sets the spells of the character.
     */
    public void setSpells() {
        // List of available spells based on class and level
        ArrayList<String> availableSpells = new ArrayList<>();

        // Add spells based on class and level
        if (playerClass.equals("Wizard")) {
            availableSpells.add("Acid Splash");
            availableSpells.add("Blade Ward");
            availableSpells.add("Chill touch");
            availableSpells.add("Dancing Lights");
            availableSpells.add("Fire Bolt");
            availableSpells.add("Friends");
            availableSpells.add("Light");
            availableSpells.add("Mage Hand");
            availableSpells.add("Mending");
            availableSpells.add("Message");
            availableSpells.add("Minor Illusion");
            availableSpells.add("Poison Spray");
            availableSpells.add("Prestidigitation");
            availableSpells.add("Ray of Frost");
            availableSpells.add("Shocking Grasp");
            availableSpells.add("True Strike");
            availableSpells.add("Alarm");
            availableSpells.add("Burning Hands");
            availableSpells.add("Charm Person");
            availableSpells.add("Chromatic Orb");
            availableSpells.add("Color Spray");
            availableSpells.add("Comprehend Languages");
            availableSpells.add("Detected Magic");
            availableSpells.add("Disguise Self");
            availableSpells.add("Expeditious Retreat");
            availableSpells.add("False Life");
            availableSpells.add("Feather Fall");
            availableSpells.add("Find Familiar");
            availableSpells.add("Fog Cloud");
            availableSpells.add("Grease");
            availableSpells.add("Identify");
            availableSpells.add("Illusory Script");
            availableSpells.add("Jump");
            availableSpells.add("Longstrider");
            availableSpells.add("Mage Armor");
            availableSpells.add("Magic Missile");
            availableSpells.add("Protection from Evil and Good");
            availableSpells.add("Ray of Sickness");
            availableSpells.add("Shield");
            availableSpells.add("Silent Image");
            availableSpells.add("Sleep");
            availableSpells.add("Sleep");
            availableSpells.add("Tasha's Floating Disk");
            availableSpells.add("Thunderwave");
            availableSpells.add("Unseen Servant");
            availableSpells.add("Witch Bolt");

        }

        // Prompt the user to choose spells
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose " + playerClass + " spells from the following list:");
        for (int i = 0; i < availableSpells.size(); i++) {
            System.out.println((i + 1) + ". " + availableSpells.get(i));
        }
        System.out.print("Enter the numbers of the chosen spells (comma-separated): ");
        String input = scanner.nextLine();
        String[] spellNumbers = input.split(",");

        // Add chosen spells to the character's spell list
        for (String number : spellNumbers) {
            int index = Integer.parseInt(number.trim()) - 1;
            if (index >= 0 && index < availableSpells.size()) {
                String chosenSpell = availableSpells.get(index);
                spells.add(chosenSpell);
            }
        }

        // Prompt the user to confirm chosen spells
        System.out.println("Chosen spells: " + spells);
    }


    /**
     * Modifies the character's HP.
     * @param hpChange The number added or subtracted from the character's HP.
     */
    public void modifyHP(int hpChange) {
        currentHP += hpChange;

        // Ensure currentHP stays within the bounds of 0 and maxHP
        currentHP = Math.max(0, currentHP);
        currentHP = Math.min(currentHP, maxHP);

        // Print the updated HP value
        System.out.println("Current HP: " + currentHP + "/" + maxHP);
    }

    /**
     * Applies the effects of a short rest to the character.
     */
    public void shortRest() {
        // Restore hit dice
        int diceToRestore = Math.min(hitDiceNum - usedHitDice);
        currentHP += diceToRestore * (int) (Math.random() * hitDie) + 1;
        usedHitDice += diceToRestore;

        // Restore spell slots or other expendable resources

        // Print the results of the short rest
        System.out.println("Short rest completed.");
        System.out.println("Current HP: " + currentHP + "/" + maxHP);
        System.out.println("Remaining Hit Dice: " + (hitDiceNum - usedHitDice) + "/" + hitDiceNum);
    }
    /**
     * Applies the effects of a long rest to the character.
     */
    public void longRest() {
        // Restore HP to maxHP
        currentHP = maxHP;

        // Restore hit dice and reset usedHitDice
        usedHitDice = 0;

        // Restore spell slots or other expendable resources

        // Print the results of the long rest
        System.out.println("Long rest completed.");
        System.out.println("Current HP: " + currentHP + "/" + maxHP);
        System.out.println("Remaining Hit Dice: " + (hitDiceNum - usedHitDice) + "/" + hitDiceNum);
    }

    /**
     * Prints the character's information.
     * @return The character information.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\n");
        sb.append("Gender: ").append(gender).append("\n");
        sb.append("Alignment: ").append(alignment).append("\n");
        sb.append("Race: ").append(race).append("\n");
        sb.append("Class: ").append(playerClass).append("\n");
        sb.append("Background: ").append(background).append("\n");
        sb.append("Stats: ").append(stats).append("\n");
        sb.append("Stat Mods: ").append(statMods).append("\n");
        sb.append("Max HP: ").append(maxHP).append("\n");
        sb.append("Current HP: ").append(currentHP).append("\n");
        sb.append("Hit Die: ").append(hitDie).append("\n");
        sb.append("Hit Dice Num: ").append(hitDiceNum).append("\n");
        sb.append("AC: ").append(ac).append("\n");
        sb.append("Skills: ").append(skills).append("\n");
        sb.append("Saving Throws: ").append(savingThrows).append("\n");
        sb.append("Spells: ").append(spells).append("\n");
        sb.append("Features: ").append(features).append("\n");
        return sb.toString();
    }

}
