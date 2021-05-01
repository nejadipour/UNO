import javax.management.timer.Timer;
import javax.swing.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class RunGame extends Game
{
    protected Player roundPlayer;
    protected Card roundCard;
    private String direction;
    private DisplayMap displayMap;

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



    public void nextRound()
    {
        int index = players.indexOf(roundPlayer);

        if (index == players.size() - 1)
            setRoundPlayer(this.players.get(0));
        else
            setRoundPlayer(this.players.get(index + 1));


    }


    public void playRound()
    {
        displayMap.printMap(players, roundPlayer, roundCard, direction);
        roundPlayer.setRoundCard(roundCard);
        roundPlayer.setAllCards(allCards);

        roundPlayer.play(this);





//        if (roundPlayer.getCardToPutMiddle() != null)
//        {
//            this.allCards.add(roundCard);
//            setRoundCard(roundPlayer.getCardToPutMiddle());
//            roundPlayer.removeCard(roundCard);
//
//        }


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


    public boolean isFinished()
    {
        for (Player player : players)
            if (player.calculatePoint() == 0)
                return true;

        return false;

    }


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
                    System.out.println(player);
                    index++;

                }

            }

        }

    }



    public void setRoundPlayer(Player roundPlayer)
    {
        this.roundPlayer = roundPlayer;

    }


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


    public void colorHandler()
    {
        if (!roundPlayer.getColorToTake().equals(""))
            roundPlayer.setColorToTake("");

    }



    public void setRoundCard(Card roundCard)
    {
        this.roundCard = roundCard;

    }

    public void setDirection(String direction)
    {
        this.direction = direction;

    }


    public String getDirection()
    {
        return direction;

    }

    public static void main(String[] args)
    {
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
