import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    /**
     * Creates a character, and allows several actions to be undertaken with that character. Actions that can happen include:
     *  Rolling a die.
     *  Printing the character sheet.
     *  Using an expendable resource.
     *  Taking a long or short rest.
     *  Modifying the HP value.
     * @param args
     */
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Greetings, adventurer! Let's make a character.");
        Character c = new Character();
        System.out.println("Let's start with stats.");
        ArrayList<String> statNames = new ArrayList<>();
        System.out.println("Which stat do you want to be highest? Make sure your spelling is correct! ");
        statNames.add(console.next());
        System.out.println("Which stat do you want to be second highest? Don't pick a stat you've already selected! ");
        statNames.add(console.next());
        System.out.println("Which stat do you want to be third highest? ");
        statNames.add(console.next());
        System.out.println("Which stat do you want to be third lowest? ");
        statNames.add(console.next());
        System.out.println("Which stat do you want to be second lowest?" );
        statNames.add(console.next());
        System.out.println("Which stat do you want to be lowest?");
        statNames.add(console.next());
        c.setStats(statNames);
        System.out.println("Next, select your character's race: ");
        c.setRace(console.next());
        System.out.println("Now, select you character's class: ");
        c.setClass(console.next());
        System.out.println("Now, select your character's background: ");
        c.setBackground(console.next());
        System.out.println("Now, select your character's skills: ");
        c.setSkills();
        System.out.println("We're almost done! Now pick your character's alignment, gender, and name.");
        //c.setMisc(console.next(), console.next(), console.next());
        String input = "";
        while(!input.equals("end")){
            System.out.print("What would you like do now?\n-Type print to print out your character sheet.\n-Type roll to make a roll.\nType use to use one of your expendable resources, like spells.\n-Type rest to take a rest.\n-Type hp to modify your current HP.\n-Type end to end the program. Note that your character will be lost.\n");
            input = console.next();
            if(input.equals("print")){
                System.out.print(c.toString());
            } else if(input.equals("roll")){
                System.out.println("Which die would you like to roll?");
                Roll thisRoll = new Roll(console.nextInt());
                System.out.println("How many dice would you like to roll?");
                int numDice = console.nextInt();
                System.out.println("What is the modifier for this roll, if any?");
                int mod = console.nextInt();
                int result = thisRoll.nextRoll(numDice, mod);
                System.out.printf("The result of your roll is %d.\n", result);
            } else if(input.equals("use")){
                System.out.println("What expendable would you like to use?");
                // TODO: Implement expendable usage
            } else if(input.equals("rest")){
                System.out.print("Input short to take a short rest and long to take a long rest.");
                input = console.next();
                if(input.equals("short")){
                    c.shortRest();
                } else if(input.equals("long")){
                    c.longRest();
                }
            } else if(input.equals("hp")){
                System.out.println("By what number should your HP be modified?");
                c.modifyHP(console.nextInt());
            } else {
                System.out.println("Please check your formatting, or enter a valid input.");
            }
        }
        console.close();
    }
}