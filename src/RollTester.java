import org.junit.jupiter.api.Test;
public class RollTester {
    @Test
    public void rollProbabilityTester(){
        Roll r = new Roll(20);
        int numOnes = 0;
        int numTwos = 0;
        int numThrees = 0;
        int numFours = 0;
        int numFives = 0;
        int numSixes = 0;
        int numSevens = 0;
        int numEights = 0;
        int numNines = 0;
        int numTens = 0;
        int numElevens = 0;
        int numTwelves = 0;
        int numThirteens = 0;
        int numFourteens = 0;
        int numFifteens = 0;
        int numSixteens = 0;
        int numSeventeens = 0;
        int numEighteens = 0;
        int numNineteens = 0;
        int numTwenties = 0;
        int i = 0;
        while(i <= 100000){
            int thisRoll = r.nextRoll(1,0);
            if(thisRoll == 20){
                numTwenties++;
            } else if(thisRoll == 1){
                numOnes++;
            } else if(thisRoll == 2){
                numTwos++;
            } else if(thisRoll == 3){
                numThrees++;
            } else if(thisRoll == 4){
                numFours++;
            } else if(thisRoll == 5){
                numFives++;
            } else if(thisRoll == 6){
                numSixes++;
            } else if(thisRoll == 7){
                numSevens++;
            } else if(thisRoll == 8){
                numEights++;
            } else if(thisRoll == 9){
                numNines++;
            } else if(thisRoll == 10){
                numTens++;
            } else if(thisRoll ==11){
                numElevens++;
            } else if(thisRoll == 12){
                numTwelves++;
            } else if(thisRoll == 13){
                numThirteens++;
            } else if(thisRoll == 14){
                numFourteens++;
            } else if(thisRoll == 15){
                numFifteens++;
            } else if(thisRoll == 16){
                numSixteens++;
            } else if(thisRoll == 17){
                numSeventeens++;
            } else if(thisRoll == 18){
                numEighteens++;
            } else {
                numNineteens++;
            }
            i++;
        }
        System.out.printf("%d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d",
                numOnes, numTwos, numThrees, numFours, numFives, numSixes, numSevens, numEights,numNines,
                numTens, numElevens, numTwelves, numThirteens, numFourteens, numFifteens, numSixteens,
                numSeventeens, numEighteens, numNineteens, numTwenties);

    }
    @Test
    public void rollMultipleTester(){
        Roll r = new Roll(6);
        int newRoll = 0;
        int numRolls = 0;
        while(newRoll < 48){
            newRoll = r.nextRoll(8,0);
            numRolls++;
        }
        System.out.println(numRolls);
    }
}
