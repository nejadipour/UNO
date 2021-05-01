import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game
{
    protected ArrayList<Card> allCards;
    protected Random random;
    protected Scanner scanner;
    protected ArrayList<Player> players;
    protected ArrayList<String> colors;
    protected int choice;


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


    public int randomGenerator(int upperbound)
    {
        return random.nextInt(upperbound);

    }


    public Card randomCard(ArrayList<Card> cards)
    {
        int index = randomGenerator(cards.size());

        return cards.get(index);

    }


    public Card randomCard()
    {
        int upperbound = allCards.size();
        int index = random.nextInt(upperbound);

        return allCards.get(index);

    }




    public void pressEnter()
    {
        System.out.print("\nPress Enter...");
        scanner.next();

    }


    public void userChoice()
    {
        System.out.print("Your choice : ");
        this.choice = scanner.nextInt();

    }


    public void invalidInput()
    {
        System.out.println("Invalid input.");

    }

    public ArrayList<Card> getAllCards()
    {
        return allCards;

    }

    public ArrayList<Player> getPlayers()
    {
        return players;

    }


    public ArrayList<String> getColors()
    {
        return colors;

    }


    public void setAllCards(ArrayList<Card> allCards)
    {
        this.allCards = allCards;

    }


    public void setPlayers(ArrayList<Player> players)
    {
        this.players = players;

    }
}