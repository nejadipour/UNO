import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * super class that whole program details are stored in
 * like allCards, players,...
 * @author Alireza Nejadipour
 * @version 5
 */

public class Game
{
    protected ArrayList<Card> allCards;
    protected Random random;
    protected Scanner scanner;
    protected ArrayList<Player> players;
    protected ArrayList<String> colors;
    protected int choice;

    /**
     * create a new Game
     */
    public Game()
    {
        this.allCards = new ArrayList<>();
        this.random = new Random();
        this.players = new ArrayList<>();
        this.colors = new ArrayList<>();
        this.scanner = new Scanner(System.in).useDelimiter("\n");

        this.colors.add("blue");
        this.colors.add("black");
        this.colors.add("red");
        this.colors.add("green");

    }


    /**
     * generate a random number less than upperbound
     * @param upperbound the max of random number
     * @return generated number
     */
    public int randomGenerator(int upperbound)
    {
        return random.nextInt(upperbound);

    }

    /**
     * used to get a card from list by random
     * @param cards the list to get card from
     * @return the random card
     */
    public Card randomCard(ArrayList<Card> cards)
    {
        int index = randomGenerator(cards.size());

        return cards.get(index);

    }


    /**
     * used in times player should press enter to continue
     */
    public void pressEnter()
    {
        System.out.print("\nPress Enter...");
        scanner.next();

    }


    /**
     * gets players inputs
     */
    public void userChoice()
    {
        System.out.print("Your choice : ");
        this.choice = scanner.nextInt();

    }


    /**
     * after invalid inputs it is used
     */
    public void invalidInput()
    {
        System.out.println("Invalid input.");

    }

    /**
     * gets all the cards in the game
     * @return allCards field
     */
    public ArrayList<Card> getAllCards()
    {
        return allCards;

    }

    /**
     * gets all the players in the game
     * @return players field
     */
    public ArrayList<Player> getPlayers()
    {
        return players;

    }

    /**
     * gets all the colors in the game
     * @return colors field
     */
    public ArrayList<String> getColors()
    {
        return colors;

    }

    /**
     * set all cards
     * @param allCards all cards to be set
     */
    public void setAllCards(ArrayList<Card> allCards)
    {
        this.allCards = allCards;

    }

}
