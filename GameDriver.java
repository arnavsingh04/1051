import java.util.Scanner;

/**
 * Class Name : GameDriver
 * Purpose : This class simulates a football tournament between eight countries bu calling methods from Team and
 * MatchSimulator class
 * @author Arnav Singh Sethi
 * @Version ver1.0.0
*/
public class GameDriver
{
    /**
     * The main method of the class
     * It creates the objects of the different teams playing the tournament and uses the PlayMatchRounds method
     * to play matches, keep track of score and prints the winners.
     */
    public static void main(String[] args)
    {
        // Creating Team objects representing various countries
        System.out.println("Welcome to Olympics 2024:");
        System.out.println("==========================================");
        Team Spain = new Team("Spain", 1);
        Team USA = new Team("USA", 2);
        Team France = new Team("France", 3);
        Team Japan = new Team("Japan", 4);
        Team Canada = new Team("Canada", 5);
        Team Brazil = new Team("Brazil", 6);
        Team Australia = new Team("Australia", 7);
        Team Colombia = new Team("Colombia", 8);
        System.out.println("The teams competing in the Women's Football are (in rank-order)");

        for (Team i : Team.countries)
        {
            System.out.println(i.getRanking() + "." + i.COUNTRY_NAME);
        }

        System.out.println("\nStarting from Quarterfinals...");
        System.out.println("Press Enter to start Olympics Women Football...");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.isEmpty())
            {
                MatchSimulator.matchNumber = 0;
                int counterOuter = 4;
                playMatchRounds(4, 0);
                System.out.println("\nStarting Semifinals...");
                playMatchRounds(2, 4);
                System.out.println("\nStarting the tournament Finals...");
                playMatchRounds(1, 6);
                break;
            }
            else
            {
                System.out.println("Just hit Enter to start");
            }
        }

        // Displays the winner and runners-up for the tournament
        System.out.println("Press Enter to Proceed to Awards Ceremony...");
        while (true)
        {
            String input = sc.nextLine();
            if (input.isEmpty())
            {
                System.out.println("Congratulations to the winners of the Women's Football Tournament");
                System.out.println("=======================================================");
                System.out.println("🥇" + " Gold Medal   : " + Team.countries.get(0).COUNTRY_NAME +
                        " (GF: "+Team.countries.get(0).getTotalGoals()+", GA: "+Team.countries.get(0).getGoalsConceded()
                +", GD: "+(Team.countries.get(0).getTotalGoals()-Team.countries.get(0).getGoalsConceded())+")");
                System.out.println("🥈" + " Silver Medal : " + Team.countries.get(1).COUNTRY_NAME+
                        " (GF: "+Team.countries.get(1).getTotalGoals()+", GA: "+Team.countries.get(1).getGoalsConceded()
                        +", GD: "+(Team.countries.get(1).getTotalGoals()-Team.countries.get(1).getGoalsConceded())+")");
                break;
            }
            else
            {
                System.out.println("Just hit Enter to Proceed to Awards Ceremony");
            }
        }

    }

    /**
     * Class method named PlayMatchRounds that
     * It uses the MatchSimulator class to play matches in a given round or stage and shifts the rankings of the
     * countries based on the match results
     * @param totalMatches : It tells the number of matches that are to be played in a given round of the tournament.
     * @param factor : This Adjusts the combinations of teams from the countries list that are to play
     *               a match with each other
     */
    private static void playMatchRounds(int totalMatches, int factor){

        Scanner sc = new Scanner(System.in);
        int counter = 0;

        while (counter<totalMatches)
        {
            System.out.println("Press Enter to start the match...");
            String input = sc.nextLine();

            if (input.isEmpty())
            {
                int rankBefore = Team.countries.get(counter).getRanking();
                System.out.println("\033[0;93m"+Team.countries.get(counter).COUNTRY_NAME +" vs "+ Team.countries.get(
                        Team.countries.size()-counter-1-factor).COUNTRY_NAME+"\033[0m");
                System.out.print("------------------------------");
                MatchSimulator.matchNumber += 1;

                // create a match simulator object for the two countries that are to play with each other.
                MatchSimulator match = new MatchSimulator(Team.countries.get(counter), Team.countries.get(
                        Team.countries.size()-counter-1-factor));
                match.playGame();

                // swaps the rankings in the countries list based on the outcome of the match
                if (Team.countries.get(counter).getRanking()!=rankBefore)
                {
                    Team temp = Team.countries.get(counter);
                    int tempIndex = Team.countries.get(counter).getRanking()-1;
                    Team.countries.set(Team.countries.get(Team.countries.size()-counter-1-factor).getRanking()-1,
                            Team.countries.get(Team.countries.size()-counter-1-factor));
                    Team.countries.set(tempIndex, temp);
                }
                counter+=1;
            }
            else
            {
                System.out.println("Just hit Enter to Start");
            }
        }
    }
}