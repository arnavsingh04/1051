import java.util.ArrayList;

/**
 * Class Name : Team
 * Purpose : This class creates objects that represent a country as a team participating in the football tournament
 * @author Arnav Singh Sethi
 * @Version ver1.0.0
 */
public class Team {
    public static ArrayList<Team> countries = new ArrayList<>();
    public final String COUNTRY_NAME;
    private int goalsConceded;
    private int ranking;
    private int totalGoals;

    /**
     * Non-Default constructor which creates the object of the class Team.
     * @param ranking : Integer representing the ranking of the country among all teams
     * @param teamCountry : Name of the team saved as the name of the country
     * @param goalsConceded : Integer representing the number of goals scored against the team
     * @param totalGoals : Integer representing the number of goals scored by the team
     */
    public Team(String teamCountry, int ranking, int totalGoals, int goalsConceded)
    {
        this.COUNTRY_NAME = teamCountry;
        this.ranking = ranking;
        this.totalGoals = totalGoals;
        this.goalsConceded = goalsConceded;
        countries.add(this);
    }

    /**
     * Non-Default constructor which creates the object of the class Team.
     * @param ranking : Integer representing the ranking of the country among all teams
     * @param teamCountry : Name of the team saved as the name of the country
     */
    public Team(String teamCountry, int ranking)
    {
        this.COUNTRY_NAME = teamCountry;
        this.ranking = ranking;
        this.totalGoals = 0;
        this.goalsConceded = 0;
        countries.add(this);
    }

    /**
     * Default constructor which creates the object of the class Team.
     */
    public Team()
    {
        this.COUNTRY_NAME = "";
        this.ranking = 0;
        this.totalGoals = 0;
        this.goalsConceded = 0;
        countries.add(this);
    }

    /**
     * Accessor method to get the goals conceded by the team
     * @return goals conceded by the team
     */
    public int getGoalsConceded()
    {
        return goalsConceded;
    }

    /**
     * Accessor method to get the ranking of the team
     * @return The ranking of the team
     */
    public int getRanking()
    {
        return ranking;
    }

    /**
     * Accessor method to get the goals scored by the team
     * @return The goals scored by the team
     */
    public int getTotalGoals()
    {
        return totalGoals;
    }

    /**
     * Mutator method to set Goals conceded by the team
     * @param goalsConceded : Goals conceded by the team
     */
    public void setGoalsConceded(int goalsConceded)
    {
        this.goalsConceded = goalsConceded;
    }

    /**
     * Mutator method to set Goals ranking of the team
     * @param ranking : ranking of the team
     */
    public void setRanking(int ranking)
    {
        this.ranking = ranking;
    }

    /**
     * Mutator method to set Goals scored by the team
     * @param totalGoals : Goals scored by the team
     */
    public void setTotalGoals(int totalGoals)
    {
        this.totalGoals = totalGoals;
    }

    /**
     * The toString method of the class that returns the details about the team
     * @return String containing information about the team
     */
    public String toString()
    {
        String teamInfo = "Country Name : " + COUNTRY_NAME +"\n";
        teamInfo += "Ranking : " + ranking + "\n";
        teamInfo += "Total goals Scored : " + totalGoals + "\n";
        teamInfo += "Total goals Conceded : " + goalsConceded + "\n";
        return teamInfo;
    }
}
