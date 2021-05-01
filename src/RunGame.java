import java.util.ArrayList;
import java.util.Collections;

/**
 * subclass of game
 * running information and data will be handled here
 * @author Alireza Nejadipour
 * @version 6.8
 */

public class RunGame extends Game
{
    protected Player roundPlayer;
    protected Card roundCard;
    private String direction;
    private final DisplayMap displayMap;

    /**
     * create a new runGame
     */
    public RunGame()
    {
        super();
        this.displayMap = new DisplayMap();

    }

    public RunGame(StartGame startGame)
    {
        super();

        players = startGame.getPlayers();
        allCards = startGame.getAllCards();

        this.roundCard = startGame.randomCard(allCards);

        while (roundCard instanceof Action)
        {
            this.roundCard = startGame.randomCard(allCards);

        }
        allCards.remove(roundCard);

        this.roundPlayer = startGame.whoStarts();

        this.direction = "clockwise";

        this.displayMap = new DisplayMap();

    }


    /**
     * changes the current roundPlayer
     */
    public void nextRound()
    {
        int index = players.indexOf(roundPlayer);

        if (index == players.size() - 1)
            setRoundPlayer(this.players.get(0));
        else
            setRoundPlayer(this.players.get(index + 1));

    }


    /**
     * play one round based on card and player's identity
     */
    public void playRound()
    {
        displayMap.printMap(players, roundPlayer, roundCard, direction);
        roundPlayer.setRoundCard(roundCard);
        roundPlayer.setAllCards(allCards);

        roundPlayer.play(this);

        if (roundCard instanceof Common)
        {
            sevenHandler();
            colorHandler();

            nextRound();

        }

        else if (roundCard instanceof Eight)
        {
            sevenHandler();
            colorHandler();
            while (roundCard instanceof Eight)
                ((Eight) roundCard).runAction(this);

        }

        else if (roundCard instanceof Action)
        {
            ((Action) roundCard).runAction(this);
            sevenHandler();
            colorHandler();

            nextRound();

        }

    }


    /**
     * checks if the game is finished
     * the game is finished when one of the players has no card
     * @return true if game is finished
     */
    public boolean isFinished()
    {
        for (Player player : players)
            if (player.calculatePoint() == 0)
                return true;

        return false;

    }


    /**
     * sorts players and prints them
     */
    public void gameBoard()
    {
        System.out.println("Game is finished!");

        ArrayList<Integer> points = new ArrayList<>();
        for (Player player : players)
        {
            points.add(player.calculatePoint());
            if (player.calculatePoint() == 0)
                System.out.println(player.getName() + " won the game!");

        }

        Collections.sort(points);
        int index = 0;

        System.out.println(".:Game Board:.");

        while (true)
        {
            for (Player player : players)
            {
                if (index == players.size())
                    return;

                if (player.calculatePoint() == points.get(index))
                {
                    System.out.println(player.getName() + "\t\t" + player.calculatePoint());
                    index++;

                }

            }

        }

    }


    /**
     * sets roundPlayer
     * @param roundPlayer whose turn is it
     */
    public void setRoundPlayer(Player roundPlayer)
    {
        this.roundPlayer = roundPlayer;

    }


    /**
     * checks if the player has used 7 or not when the round card has been 7
     * if not the player will receive extra cards
     */
    public void sevenHandler()
    {
        if (roundPlayer.getCardsToTake() > 0)
        {
            for (int cardNum = 0 ; cardNum < roundPlayer.getCardsToTake() ; cardNum++)
            {
                Card newCard = randomCard(allCards);
                roundPlayer.addCard(newCard);
                allCards.remove(newCard);

            }

            roundPlayer.setCardsToTake(0);

        }

    }


    /**
     * cahnges the colorToTake after its related turn is done
     */
    public void colorHandler()
    {
        if (!roundPlayer.getColorToTake().equals(""))
            roundPlayer.setColorToTake("");

    }

    /**
     * sets roundCard
     * @param roundCard the card that is in the middle
     */
    public void setRoundCard(Card roundCard)
    {
        this.roundCard = roundCard;

    }

    /**
     * set the direction of the game
     * @param direction its either clockwise or anticlockwise
     */
    public void setDirection(String direction)
    {
        this.direction = direction;

    }

    /**
     * gets the direction of running game
     * @return direction field
     */
    public String getDirection()
    {
        return direction;

    }


    public static void main(String[] args)
    {
        // init the game needs...
        StartGame startGame = new StartGame();
        startGame.startMessage();
        startGame.getStartInfo();
        startGame.generateCards();

        RunGame runGame = new RunGame(startGame);

        while (!runGame.isFinished())
            runGame.playRound();

        runGame.gameBoard();

    }

}
