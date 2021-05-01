import java.util.ArrayList;

/**
 * this class is used to handle card 7
 * if player decides this card
 * next player should take 4 or 2 cards by random.
 * it depends on color
 * @author Alireza Nejadipour
 * @version 3
 */

public class Seven extends Action
{
    /**
     * create a new card of 7
     * @param name the name of card
     * @param point the point this card has
     * @param color the color of the card
     */
    public Seven(String name, int point, String color)
    {
        super(name, point, color);
        if (this.getColor().equals("black"))
            this.explainAction = "next player should take 4 cards by random.";
        else
            this.explainAction = "next player should take 2 cards by random.";

    }


    /**
     * updates cardsToTake of next player
     * @param players all players
     */
    public void updateCount(ArrayList<Player> players)
    {
        int index = players.indexOf(this.getPlayer());

        Player nextPlayer;

        if (index == players.size() - 1)
            nextPlayer = players.get(0);
        else
            nextPlayer = players.get(index + 1);

        if (this.getColor().equals("black"))
            nextPlayer.setCardsToTake(this.getPlayer().getCardsToTake() + 4);
        else
            nextPlayer.setCardsToTake(this.getPlayer().getCardsToTake() + 2);

        this.getPlayer().setCardsToTake(0);

    }


    /**
     * the action of the card is done here
     * @param runGame access to running system
     */
    @Override
    public void runAction(RunGame runGame)
    {
        updateCount(runGame.getPlayers());

    }

}
