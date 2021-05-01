import java.util.ArrayList;

/**
 * this class is used to handle card 2
 * if player decides this card
 * player has to choose one of the players.
 * then one of the cards will be passed to that player by random.
 * @author Alireza Nejadipour
 * @version 2.6
 */

public class Two extends Action
{
    // the player that the card will be passed to
    private Player other;

    /**
     * create a new card of 2
     * @param name the name of card
     * @param point the point this card has
     * @param color the color of the card
     */
    public Two(String name, int point, String color)
    {
        super(name, point, color);
        this.explainAction = "player has to choose one of the players.\nthen one of the cards will be passed to that player by random.";

    }


    /**
     * prints all players
     * @param players all players
     */
    public void displayPlayers(ArrayList<Player> players)
    {
        int number = 1;
        for (Player player : players)
        {
            if (!player.equals(this.getPlayer()))
            {
                System.out.print(number + ")");
                System.out.println(player);

                number++;

            }

        }

    }


    /**
     * decides one of the players
     * @param players all players
     */
    public void choosePlayer(ArrayList<Player> players)
    {
        userChoice();

        int number = 1;
        for (Player player : players)
        {
            if (number == choice)
            {
                setOther(player);

            }

            if (!player.equals(this.getPlayer()))
                number++;

        }

    }


    /**
     * used to decide a player by random for bot player
     * @param players all players
     */
    public void autoChoosePlayer(ArrayList<Player> players)
    {
        do
        {
            int index;

            index = randomGenerator(players.size());

            setOther(players.get(index));

        } while (other.equals(this.getPlayer()));

        System.out.println(this.getPlayer().getName() + " decided player " + other.getName());

    }


    /**
     * removes card from the player and gives it to other one
     */
    public void giveCard()
    {
        Card cardToMove = randomCard(this.getPlayer().getCards());

        this.getPlayer().removeCard(cardToMove);

        other.addCard(cardToMove);

    }


    /**
     * the action of the card is done here
     * @param runGame access to running system
     */
    @Override
    public void runAction(RunGame runGame)
    {
        if (this.getPlayer() instanceof Human)
        {
            displayPlayers(runGame.getPlayers());
            choosePlayer(runGame.getPlayers());

        }
        else
            autoChoosePlayer(runGame.getPlayers());

        giveCard();

    }

    /**
     * sets player that will get the card
     * @param other is player that will get the card
     */
    public void setOther(Player other)
    {
        this.other = other;

    }
}