import java.util.ArrayList;

/**
 * Class Name : MatchSimulator
 * Purpose : This class creates objects that represents a match and all related attributes for
 * a match between two given countries
 * @author Arnav Singh Sethi
 * @Version ver1.0.0
 */
public class MatchSimulator
{
    public final Team COUNTRY1;
    public final Team COUNTRY2;
    public static int matchNumber;
    private int minutesPlayed;
    private int scoreCOUNTRY1;
    private int scoreCOUNTRY2;

    /**
     * Non-Default constructor which creates the object of the class MatchSimulator.
     * @param COUNTRY1 : the first country of the two in the match
     * @param COUNTRY2 : the second country of the two in the match
     */
    public MatchSimulator(Team COUNTRY1, Team COUNTRY2)
    {
        this.COUNTRY1 = COUNTRY1;
        this.COUNTRY2 = COUNTRY2;
        int scoreCOUNTRY1 = 0;
        int scoreCOUNTRY2 = 0;
    }

    /**
     * Default constructor which creates the object of the class MatchSimulator.
     */
    public MatchSimulator()
    {
        this.COUNTRY2 = new Team();
        this.COUNTRY1 = new Team();
        int scoreCOUNTRY1 = 0;
        int scoreCOUNTRY2 = 0;
    }

    /**
     * It displays the total number of minutes played in the match.
     */
    private void displayMinutesPlayed()
    {
        if (minutesPlayed == 90) {
            System.out.println("Minutes Played: " + minutesPlayed + "'");
        } else {
            System.out.println("Minutes Played: " + 90 + "'" + "[" + (minutesPlayed - 90) + "]'");
        }
    }

    /**
     * Accessor method to get the number of minutes played in the match
     * @return number of minutes played in the match
     */
    public int getMinutesPlayed()
    {
        return minutesPlayed;
    }

    /**
     * Accessor method to get the goals scored by country 1 in the match
     * @return goals scored by country 1 in the match
     */
    public int getScoreCOUNTRY1()
    {
        return scoreCOUNTRY1;
    }

    /**
     * Accessor method to get the goals scored by country 2 in the match
     * @return goals scored by country 2 in the match
     */
    public int getScoreCOUNTRY2()
    {
        return scoreCOUNTRY2;
    }

    /**
     * This method runs the game simulation for the match between the two countries based on their
     * probability to score a goals, saves the times at which the goals were scored and calls the
     * methods to update the team stats and print the match result
     */
    public void playGame()
    {
        System.out.println("\nMatch "+ matchNumber+" now commencing...");
        ArrayList<String> timesCOUNTRY1 = new ArrayList<>();
        ArrayList<String> timesCOUNTRY2 = new ArrayList<>();
        int probCOUNTRY1 = 20 - COUNTRY1.getRanking();
        int probCOUNTRY2 = 20 - COUNTRY2.getRanking();

        for (int i = 0; i < 90; i += 5)
        {
            // the random variable gives the team an opportunity to score
            int randNum1 = (int) (Math.random() * (100 - 1 + 1) + 1);
            if ((1 <= randNum1) & (randNum1 <= probCOUNTRY1))
            {
                // sets a random time at which the goal is score within the 5 minutes range
                int randNum11 = (int) (Math.random() * ((i + 5) - (i + 1) + 1) + i + 1);
                String randNum = "";

                if (1<=randNum11 && randNum11<=9)
                {
                    randNum = "0"+randNum11 ;
                }
                else
                {
                    randNum = randNum11 + "";
                }

                timesCOUNTRY1.add(randNum);
                scoreCOUNTRY1 += 1;
            }

            int randNum2 = (int) (Math.random() * (100 - 1 + 1) + 1);
            if ((1 <= randNum2) & (randNum2 <= probCOUNTRY2))
            {
                int randNum22 = (int) (Math.random() * ((i + 5) - (i + 1) + 1) + i + 1);
                String randNum = "";
                if (1<=randNum22 && randNum22<=9)
                {
                    randNum = "0"+randNum22 ;
                }
                else
                {
                    randNum = randNum22 + "";
                }
                timesCOUNTRY2.add(randNum);
                scoreCOUNTRY2 += 1;
            }
        }

        // Checking which team has a higher score
        minutesPlayed = 90;
        if (scoreCOUNTRY2 > scoreCOUNTRY1)
        {
            printWinner(scoreCOUNTRY1, scoreCOUNTRY2, timesCOUNTRY1, timesCOUNTRY2);
            displayMinutesPlayed();
            System.out.println("\033[0;92m"+"Winner of this Game is : "+COUNTRY2.COUNTRY_NAME+"\033[0m");
        }
        else if (scoreCOUNTRY2 < scoreCOUNTRY1)
        {
            printWinner(scoreCOUNTRY1, scoreCOUNTRY2, timesCOUNTRY1, timesCOUNTRY2);
            displayMinutesPlayed();
            System.out.println("\033[0;92m"+"Winner of this Game is : "+COUNTRY1.COUNTRY_NAME+"\033[0m");
        }
        else
        {
            System.out.println("Game is tied. Going to Extra Time.");
            printWinner(scoreCOUNTRY1, scoreCOUNTRY2, timesCOUNTRY1, timesCOUNTRY2);
            String winner = runTieSimulation();
            displayMinutesPlayed();
            System.out.println("\033[0;92m"+"After Penalty: Winner of this Game is "+winner+"\033[0m");
        }
        updateCountryStats(scoreCOUNTRY1, scoreCOUNTRY2);
    }

    /**
     * This method prints the winner of the match in a formatted way with the time at which the goals were scored
     * @param scoreCOUNTRY1 : Integer storing the score of the country 1
     * @param scoreCOUNTRY2 : Integer storing the score of the country 2
     * @param timesCOUNTRY1 : ArrayList storing the times at the which the goals were scored by country 1
     * @param timesCOUNTRY2 : ArrayList storing the times at the which the goals were scored by country 2
     */
    private void printWinner(int scoreCOUNTRY1, int scoreCOUNTRY2, ArrayList<String> timesCOUNTRY1, ArrayList<String>
            timesCOUNTRY2)
    {
        System.out.println(COUNTRY1.COUNTRY_NAME +" "+ scoreCOUNTRY1 + "   -   " + scoreCOUNTRY2 + " "+
                COUNTRY2.COUNTRY_NAME + "\n");
        int timesLen = 0;
        int flag = 0;

        if (timesCOUNTRY1.size() >= timesCOUNTRY2.size())
        {
            timesLen = timesCOUNTRY1.size();
        }
        else
        {
            flag = 1;
            timesLen = timesCOUNTRY2.size();
        }

        for (int i = 0; i < timesLen; i += 1)
        {
            // formatting the times at which the goals are scored
            try
            {
                System.out.println(timesCOUNTRY1.get(i) + "'      |   " + timesCOUNTRY2.get(i) + "'" + "\n");
            }
            catch (Exception IndexOutOfBoundsException)
            {
                if (flag == 0)
                {
                    System.out.println(timesCOUNTRY1.get(i) + "'      |   " + " " + "\n");
                }
                else
                {
                    System.out.println("         |   " + timesCOUNTRY2.get(i) + "'" + "\n");
                }
            }
        }
    }

    /**
     * This method runs the simulation in the case of a tie after 90 minutes.
     * @return : A String containing the name of the team that won the penalty shootout.
     */
    private String runTieSimulation()
    {
        // a random number is generated for each team and the team with the higher number wins the penalty shootout
        int randNum1 = (int) (Math.random() * (100 - 1 + 1) + 1);
        int randNum2 = (int) (Math.random() * (100 - 1 + 1) + 1);
        if (randNum1 > randNum2)
        {
            minutesPlayed += 5;
            scoreCOUNTRY1 += 1;
            return COUNTRY1.COUNTRY_NAME;
        }
        else if (randNum1<randNum2)
        {
            scoreCOUNTRY2 += 1;
            minutesPlayed += 5;
            return COUNTRY2.COUNTRY_NAME;
        }
        else
        {
            scoreCOUNTRY1 += 1;
            scoreCOUNTRY2 += 1;
            minutesPlayed += 5;
            runTieSimulation();
        }
        return null;
    }

    /**
     * Mutator method to change the number of minutes the match ran for
     * @param minutesPlayed : The number of minutes the match ran for
     */
    public void setMinutesPlayed(int minutesPlayed)
    {
        this.minutesPlayed = minutesPlayed;
    }

    /**
     * Mutator method to change the score of country 1
     * @param scoreCOUNTRY1 : The match score of country 1
     */
    public void setScoreCOUNTRY1(int scoreCOUNTRY1)
    {
        this.scoreCOUNTRY1 = scoreCOUNTRY1;
    }

    /**
     * Mutator method to change the score of country 2
     * @param scoreCOUNTRY2 : The match score of country 2
     */
    public void setScoreCOUNTRY2(int scoreCOUNTRY2)
    {
        this.scoreCOUNTRY2 = scoreCOUNTRY2;
    }

    /**
     * The toString method of the class that returns the details about the match
     * @return String containing information about the match
     */
    public String toString()
    {
        return "The match no. " + matchNumber + " is being played by" + "COUNTRY1= " +
                COUNTRY1.COUNTRY_NAME + ", COUNTRY2= " + COUNTRY2.COUNTRY_NAME;
    }

    /**
     * The method that updates the stats of the country that's a team object
     * @param scoreCOUNTRY1 : The match score of country 1
     * @param scoreCOUNTRY2 : The match score of country 2
     */
    private void updateCountryStats(int scoreCOUNTRY1, int scoreCOUNTRY2)
    {
        // Checking if the rankings of the teams need to be changed or not
        if (COUNTRY1.getRanking() < COUNTRY2.getRanking() && scoreCOUNTRY2 > scoreCOUNTRY1)
        {
            int temp = COUNTRY2.getRanking();
            COUNTRY2.setRanking(COUNTRY1.getRanking());
            COUNTRY1.setRanking(temp);
        }

        if (COUNTRY2.getRanking() < COUNTRY1.getRanking() && scoreCOUNTRY1 > scoreCOUNTRY2)
        {
            int temp = COUNTRY1.getRanking();
            COUNTRY1.setRanking(COUNTRY2.getRanking());
            COUNTRY2.setRanking(temp);
        }

        COUNTRY1.setTotalGoals(COUNTRY1.getTotalGoals() + scoreCOUNTRY1);
        COUNTRY1.setGoalsConceded(COUNTRY1.getGoalsConceded() + scoreCOUNTRY2);
        COUNTRY2.setTotalGoals(COUNTRY2.getTotalGoals() + scoreCOUNTRY2);
        COUNTRY2.setGoalsConceded(COUNTRY2.getGoalsConceded() + scoreCOUNTRY1);
    }
}
