import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class StartGame extends Game
{
    private String welcomeMessage;
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


    public void startMessage()
    {
        System.out.println(welcomeMessage);

    }


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

        if (humanNUm > num)
        {
            invalidInput();
            getStartInfo();

        }


        for (int playerNum = 1 ; playerNum <= humanNUm ; playerNum++)
        {
            System.out.print("Enter the name of player " + playerNum + " : ");
            String name = scanner.next();
            Human human = new Human(name);
            players.add(human);

        }

        for (int playerNum = 1 ; playerNum <= num - humanNUm ; playerNum++)
        {
            String name = "Bot" + playerNum;
            Bot bot = new Bot(name);
            players.add(bot);

        }


    }


    public void generateCards()
    {
        for (String color : colors)
        {
            allCards.addAll(generateColorCards(color));

        }

        spreadCards();

    }


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


    public void spreadCards()
    {
        // how many cards should be spread at all
        int count = players.size() * 7;


        while (count > 0)
        {
            for (Player player : players)
            {
                Card card = randomCard();
                player.addCard(card);
                allCards.remove(card);

            }

            count = count - players.size();

        }

        System.out.println("Cards are passed to players. Each player has 7 cards now.");

    }


    public Player whoStarts()
    {
        int index = random.nextInt(players.size());

        System.out.println(players.get(index).getName() + " will start the game.");
        pressEnter();

        return players.get(index);

    }




}
