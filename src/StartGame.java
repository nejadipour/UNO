import java.util.ArrayList;

/**
 * subclass of game
 * first information and data will be generated here before the game starts
 * @author Alireza Nejadipour
 * @version 4.4
 */


public class StartGame extends Game
{
    private final String welcomeMessage;

    /**
     * create a new start
     */
    public StartGame()
    {
        super();
        this.welcomeMessage = """
                \033[0;1m
                Welcome to UNO Game!\u001B[0m
                Hope U Enjoy it;)
                
                Author : Alireza Nejadipour
                Version: 2.2
                
                """;

    }


    /**
     * prints the start message
     */
    public void startMessage()
    {
        System.out.println(welcomeMessage);

    }


    /**
     * gets information to start the game
     * like the number of players
     * and the names
     */
    protected void getStartInfo()
    {
        System.out.print("How many players(all) ? : ");
        int num = scanner.nextInt();

        if (num > 5 || num < 3)
        {
            invalidInput();
            getStartInfo();
        }

        System.out.print("How many of the players are human? : ");
        int humanNUm = scanner.nextInt();

        if (humanNUm > num || humanNUm == 0)
        {
            invalidInput();
            getStartInfo();

        }

        else
        {
            for (int playerNum = 1 ; playerNum <= humanNUm ; playerNum++)
            {
                System.out.print("Enter the name of player " + playerNum + " : ");
                String name = scanner.next();
                Human human = new Human(name);
                players.add(human);

            }

            // generate names for bots
            for (int playerNum = 1 ; playerNum <= num - humanNUm ; playerNum++)
            {
                String name = "Bot" + playerNum;
                Bot bot = new Bot(name);
                players.add(bot);

            }

        }

    }


    /**
     * makes 52 cards
     * 13 from each color
     */
    public void generateCards()
    {
        for (String color : colors)
        {
            allCards.addAll(generateColorCards(color));

        }

        spreadCards();

    }


    /**
     * makes 13 cards for passed color
     * @param color the color of the card
     * @return the list of 13 cards
     */
    public ArrayList<Card> generateColorCards(String color)
    {
        ArrayList<Card> colorCards = new ArrayList<>();

        // generate common cards
        Common card1 = new Common("3 ", 3, color);
        Common card2 = new Common("4 ", 4, color);
        Common card3 = new Common("5 ", 5, color);
        Common card4 = new Common("6 ", 6, color);
        Common card5 = new Common("9 ", 9, color);
        Common card6 = new Common("C ", 12, color);
        Common card7 = new Common("D ", 13, color);

        // generate action cards
        Two card8 = new Two("2 ", 2, color);

        Seven card9;
        if (color.equals("black"))
            card9 = new Seven("7 ", 15, color);
        else
            card9 = new Seven("7 ", 10, color);

        Eight card10 = new Eight("8 ", 8, color);
        Ten card11 = new Ten("10", 10, color);
        A card12 = new A("A ", 11, color);
        B card13 = new B("B ", 12, color);

        colorCards.add(card1);
        colorCards.add(card2);
        colorCards.add(card3);
        colorCards.add(card4);
        colorCards.add(card5);
        colorCards.add(card6);
        colorCards.add(card7);
        colorCards.add(card8);
        colorCards.add(card9);
        colorCards.add(card10);
        colorCards.add(card11);
        colorCards.add(card12);
        colorCards.add(card13);

        return colorCards;

    }


    /**
     * each player will have 7 cards by random at the start point
     */
    public void spreadCards()
    {
        // how many cards should be spread at all
        int count = players.size() * 7;

        while (count > 0)
        {
            for (Player player : players)
            {
                Card card = randomCard(allCards);
                player.addCard(card);
                allCards.remove(card);

            }

            count = count - players.size();

        }

        System.out.println("Cards are passed to players. Each player has 7 cards now.");

    }


    /**
     * one player should start the game by random
     * @return the roundPlayer
     */
    public Player whoStarts()
    {
        int index = random.nextInt(players.size());

        System.out.println(players.get(index).getName() + " will start the game.");
        pressEnter();

        return players.get(index);

    }

}
