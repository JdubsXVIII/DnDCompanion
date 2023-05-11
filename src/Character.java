import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class Character {
    private String name; // the character's name
    private String gender; // the character's gender
    private String alignment; // the character's alignment
    private HashMap<String, Integer> stats; // the characters stats
    private HashMap<String, Integer> statMods; // the character's stat modifiers
    private int profBonus; // the character's proficiency bonus
    private String race; // the character's race
    private String playerClass; // the character's class
    private int maxHP; // the character's max HP
    private int currentHP; // the character's current HP
    private int hitDie; // the character's hit die value
    private int hitDiceNum; // the number of hit dice the character has
    private int usedHitDice; // the number of hit dice the character has used so far
    private int ac; // the character's armor class
    private ArrayList<String> skills; // the character's skill proficiencies
    private ArrayList<String> savingThrows; // the character's saving throw proficiencies
    private ArrayList<String> spells; // the character's spells
    private HashMap<Object, String> features; // the character's features, as determined by their previous choices, and their descriptions.
    private ArrayList<Expendable> expFeatures; // the character's expendable features.

    /**
     * Creates a new Character object.
     */
    public Character() {
        profBonus = 2;
        stats = new HashMap<>();
        statMods = new HashMap<>();
        skills = new ArrayList<>();
        savingThrows = new ArrayList<>();
        spells = new ArrayList<>();
        features = new HashMap<>();
        expFeatures = new ArrayList<>();
    }

    /**
     * Sets the stats of the character.
     * @param statList The order the player assigns values to their stats, from highest to lowest.
     */
    public void setStats(ArrayList<String> statList) {
        int[] statArray = new int[6];
        Roll statRoller = new Roll(6);
        for(int i = 0; i < 6; i++){
            statArray[i] = statRoller.nextRoll(3,0);
        }
        for(int i = 0; i < statArray.length; i++){
            for(int j = i + 1; j < statArray.length; j++){
                if(statArray[j] > statArray[i]){
                    int temp = statArray[i];
                    statArray[i] = statArray[j];
                    statArray[j] = temp;
                }
            }
        }
        ArrayList<Integer> statNums = new ArrayList<>();
        for(int i: statArray){
            statNums.add(i);
        }
        for(int i = 0; i < 6; i++){
            statNums.add(statRoller.nextRoll(3, 0));
        }

        for(int i = 0; i < 6; i++){
            stats.put(statList.get(i), statNums.get(i));
            System.out.println(statList.get(i));
            System.out.println(statNums.get(i));
        }
        for(String e: stats.keySet()){
            statMods.put(e, stats.get(e)/2 - 5);
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
        //expFeatures.add("");

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
            playerClass = c;
            maxHP = 12 + statMods.get("Constitution");
            hitDie = 12;
            hitDiceNum = 1;
            ac = 10 + statMods.get("Dexterity") + statMods.get("Constitution");
            savingThrows.add("Strength");
            savingThrows.add("Constitution");
            features.put("Unarmored Defense", "When not wearing armor, add your Constitution modifier to your armor class.");
            expFeatures.add(new Expendable("Rage", 2, false, "Rage lasts for one minute and grants:\n- Advantage on all Strength checks and saving throws\n- A +2 damage bonus on melee weapon attacks using Strength\n- Resistance to bludgeoning, piercing, and slashing damage\nRage lasts for 1 minute."));
        } else if(c.equals("Bard")){
            playerClass = c;
            maxHP = 8 + statMods.get("Constitution");
            hitDie = 8;
            hitDiceNum = 1;
            ac = 11 + statMods.get("Dexterity");
            savingThrows.add("Dexterity");
            savingThrows.add("Charisma");
            features.put("Cantrips", "0th-level spells.");
            expFeatures.add(new Expendable("1st-level spellcasting", 2, false, "Spell slots for 1st-level spells."));
            expFeatures.add(new Expendable("Bardic Inspiration", statMods.get("Charisma"), false, "Give any ally a d6 to add to any ability check, attack roll, or saving throw."));
        } else if(c.equals("Cleric")){
            playerClass = c;
            maxHP = 8 + statMods.get("Constitution");
            hitDie = 8;
            hitDiceNum = 1;
            if(statMods.get("Dexterity") > 2){
                ac = 16;
            } else {
                ac = 14 + statMods.get("Dexterity");
            }
            savingThrows.add("Wisdom");
            savingThrows.add("Charisma");
            features.put("Cantrips", "0th-level spells.");
            expFeatures.add(new Expendable("1st-level spellcasting",2, false, "Spell slots for 1st-level spells"));
            System.out.println("Which domain is your cleric?");
            String subclass = console.next();
            if(subclass.equals("Knowledge")){
                features.put("Knowledge Domain","Your domain is knowledge.");
                features.put("Blessings of Knowledge", "You gain proficiency in 2 additional languages.");
            } else if(subclass.equals("Life")){
                features.put("Life Domain","Your domain is life.");
                features.put("Disciple of Life", "Whenever you use a spell 1st level or higher to restore another creature's HP, their HP restored is increased by 2 + the spell's level.");
            } else if(subclass.equals("Light")){
                features.put("Light Domain","Your domain is light.");
                expFeatures.add(new Expendable("Warding Flare", statMods.get("Wisdom"), false, "You may use your reaction to impose disadvantage on a creature who is attacking you."));
            } else if(subclass.equals("Nature")){
                features.put("Nature Domain","Your domain is nature.");
                features.put("Acolyte of Nature", "You gain a druid cantrip and proficiency in one of Animal Handling, Nature, or Survival.");
            } else if(subclass.equals("Tempest")){
                features.put("Tempest Domain","Your domain is tempest.");
                expFeatures.add(new Expendable("Wrath of the Storm",statMods.get("Wisdom"), false, "When a creature within 5 feet of you hits you with an attack, you may make it roll a Dex saving throw for 2d8 damage, or half as much on a success."));
            } else if(subclass.equals("Trickery")){
                features.put("Trickery Domain","Your domain is trickery.");
                features.put("Blessing of the Trickster", "You may touch a creature to give it advantage on Stealth checks for an hour.");
            } else {
                features.put("War Domain","Your domain is war.");
                expFeatures.add(new Expendable("War Priest", statMods.get("Wisdom"), false, "You may make an attack as a bonus action whenever you make a weapon attack."));
            }
        } else if(c.equals("Druid")){
            playerClass = c;
            maxHP = 8 + statMods.get("Constitution");
            hitDie = 8;
            hitDiceNum = 1;
            ac = 13 + statMods.get("Dexterity");
            savingThrows.add("Intelligence");
            savingThrows.add("Wisdom");
            features.put("Druidic", "You know the secret druidic language.");
            features.put("Cantrips", "0th-level spell slots.");
            expFeatures.add(new Expendable("1st-level spellcasting", 2, false, "Spell slots for 1st-level spells."));
        } else if(c.equals("Fighter")){
            playerClass = c;
            maxHP = 10 + statMods.get("Constitution");
            hitDie = 10;
            hitDiceNum = 1;
            ac = 16;
            savingThrows.add("Strength");
            savingThrows.add("Constitution");
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
            playerClass = c;
            maxHP = 8 + statMods.get("Constitution");
            hitDie = 8;
            hitDiceNum = 1;
            ac = 10 + statMods.get("Wisdom") + statMods.get("Constitution");
            savingThrows.add("Strength");
            savingThrows.add("Dexterity");
            features.put("Unarmored Defense", "When not wearing armor, add your Wisdom modifier to your armor class.");
            features.put("Martial Arts", "When unarmed or only wielding monk weapons, you may:\n- Use Dex instead of Str when attacking with monk weapons or unarmed strikes\n- Use a d4 for your unarmed strikes\n- Make an unarmed strike as a bonus action whenever you attack");
        } else if(c.equals("Paladin")){
            playerClass = c;
            maxHP = 10 + statMods.get("Constitution");
            hitDie = 10;
            hitDiceNum = 1;
            ac = 18;
            savingThrows.add("Wisdom");
            savingThrows.add("Charisma");
            expFeatures.add(new Expendable("Divine Sense", 1+statMods.get("Charisma"), false, "You the location and type of any fiend, celestial, or undead within 60 ft. of you."));
            features.put("Lay on Hands", "You have a pool of 5 HP to use in healing anyone you touch. You gain all the HP back after a long rest.");
        } else if(c.equals("Ranger")){ //Ranger uses the reworked ranger rules, in order to be more competitive with the other classes.
            playerClass = c;
            maxHP = 10 + statMods.get("Constitution");
            hitDie = 10;
            hitDiceNum = 1;
            savingThrows.add("Strength");
            savingThrows.add("Dexterity");
            if(statMods.get("Dexterity") > 2){
                ac = 16;
            } else {
                ac = 14 + statMods.get("Dexterity");
            }
            expFeatures.add(new Expendable("Favored Foe", profBonus, false,"When you hit an enemy with a weapon attack, you may mark that enemy. The first time a turn you damage the marked enemy, including the turn you mark it, increase your damage by 1d4."));
            features.put("Deft Explorer", "You may double one of your skill proficiencies. You also learn 2 additional languages.");
        } else if(c.equals("Rogue")){
            playerClass = c;
            maxHP = 8 + statMods.get("Constitution");
            hitDie = 8;
            hitDiceNum = 1;
            ac = 11 + statMods.get("Dexterity");
            savingThrows.add("Dexterity");
            savingThrows.add("Intelligence");
            features.put("Expertise", "Two skills of your choice may use double their proficiency bonus when making related checks.");
            features.put("Sneak Attack", "Once per turn, you may deal an extra 1d6 damage to the enemy if you have advantage or if another enemy of the enemy is within 5 feet of it.");
        } else if(c.equals("Sorcerer")){
            playerClass = c;
            maxHP = 6 + statMods.get("Constitution");
            hitDie = 6;
            hitDiceNum = 1;
            ac = 10 + statMods.get("Dexterity");
            savingThrows.add("Constitution");
            savingThrows.add("Charisma");
            System.out.println("Please choose your sorcerous bloodline.");
            String subclass = console.next();
            if(subclass.equals("Draconic")){
                features.put("Draconic Resilience", "Your HP increases by 1 per level in this class. Additionally, your unarmored AC is equal to 13 + Dex mod");
                maxHP++;
                ac = 13 + statMods.get("Dexterity");
            } else {
                features.put("Wild Magic Surge", "After casting a 1st-level or higher spell, roll a d20. On a 1, roll a d% on the Wild Magic table.");
                expFeatures.add(new Expendable("Tides of Chaos", 1, false, "You may gain advantage on an attack roll, saving throw, or ability check."));
            }
            features.put("Cantrips", "0th-level spells.");
            expFeatures.add(new Expendable("1st-level spellcasting", 2, false, "1st-level spell slots."));
        } else if(c.equals("Warlock")){
            playerClass = c;
            maxHP = 8 + statMods.get("Constitution");
            hitDie = 8;
            hitDiceNum = 1;
            ac = 11 + statMods.get("Dexterity");
            savingThrows.add("Wisdom");
            savingThrows.add("Charisma");
            features.put("Cantrips", "0th-level spellcasting");
            expFeatures.add(new Expendable("Pact Magic", 1, true, "Spell slots"));
            System.out.println("Enter your Otherworldly Patron.");
            String subclass = console.next();
            if(subclass.equals("Archfey")){
                expFeatures.add(new Expendable("Fey Presence", 1,true,"You may cause other creatures within 10 ft. to make a Wisdom saving throw or be charmed/frightened by you."));
            } else if(subclass.equals("Fiend")){
                features.put("Dark One's Blessing", "Whenever you reduce a creature to 0 HP, gain temporary HP equal to your Cha mod + your warlock level.");
            } else {
                features.put("Awakened Mind", "You may speak telepathically to any creature within 30 ft. that can understand at least 1 language.");
            }
        } else { // the only remaining class is Wizard.
            playerClass = c;
            maxHP = 6 + statMods.get("Constitution");
            hitDie = 6;
            hitDiceNum = 1;
            ac = 10 + statMods.get("Dexterity");
            savingThrows.add("Intelligence");
            savingThrows.add("Wisdom");
            features.put("Cantrips", "0th-level spells.");
            expFeatures.add(new Expendable("1st-level spellcasting", 2,false,"Spell slots for 1st-level spells."));
        }
        currentHP = maxHP;
        usedHitDice = 0;
    }

    /**
     * Sets the skills of the character.
     */
    public void setSkills() {
        // List of available skills based on class and background
        ArrayList<String> availableSkills = new ArrayList<>();

        // Add skills based on class
        if(playerClass.equals("Barbarian")){
            availableSkills.add("Animal Handling");
            availableSkills.add("Athletics");
            availableSkills.add("Intimidation");
            availableSkills.add("Nature");
            availableSkills.add("Perception");
            availableSkills.add("Survival");
        } else if (playerClass.equals("Bard")){
            availableSkills.add("Athletics");
            availableSkills.add("Acrobatics");
            availableSkills.add("Sleight of Hand");
            availableSkills.add("Stealth");
            availableSkills.add("Arcana");
            availableSkills.add("History");
            availableSkills.add("Investigation");
            availableSkills.add("Nature");
            availableSkills.add("Religion");
            availableSkills.add("Animal Handling");
            availableSkills.add("Insight");
            availableSkills.add("Medicine");
            availableSkills.add("Perception");
            availableSkills.add("Survival");
            availableSkills.add("Deception");
            availableSkills.add("Intimidation");
            availableSkills.add("Performance");
            availableSkills.add("Persuasion");
        } else if (playerClass.equals("Cleric")){
            availableSkills.add("History");
            availableSkills.add("Insight");
            availableSkills.add("Medicine");
            availableSkills.add("Persuasion");
            availableSkills.add("Religion");
        } else if (playerClass.equals("Druid")){
            availableSkills.add("Animal Handling");
            availableSkills.add("Arcana");
            availableSkills.add("Insight");
            availableSkills.add("Medicine");
            availableSkills.add("Nature");
            availableSkills.add("Perception");
            availableSkills.add("Religion");
            availableSkills.add("Survival");
        } else if (playerClass.equals("Fighter")){

        } else if (playerClass.equals("Monk")){

        } else if (playerClass.equals("Paladin")){

        } else if (playerClass.equals("Ranger")){

        } else if (playerClass.equals("Rogue")) {
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
        } else if (playerClass.equals("Sorcerer")){

        } else if (playerClass.equals("Warlock")){

        }else if (playerClass.equals("Wizard")) {
            availableSkills.add("Arcana");
            availableSkills.add("History");
            availableSkills.add("Insight");
            availableSkills.add("Investigation");
            availableSkills.add("Medicine");
            availableSkills.add("Perception");
            availableSkills.add("Religion");
            availableSkills.add("Survival");
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
        Roll hitDieRoller = new Roll(hitDie);
        modifyHP(hitDieRoller.nextRoll(1,statMods.get("Constitution")));
        usedHitDice++;

        // Restore spell slots or other expendable resources
        for(Expendable e : expFeatures){
            if(e.getRefresh()){
                e.setUsesLeft(e.getMaxUses());
            }
        }

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
        for(Expendable e : expFeatures){
            e.setUsesLeft(e.getMaxUses());
        }

        // Print the results of the long rest
        System.out.println("Long rest completed.");
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
