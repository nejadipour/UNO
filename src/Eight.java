/**
 * this class is used to handle card 8
 * if player decides this card
 * player has prize to play another round.
 * @author Alireza Nejadipour
 * @version 1.2
 */

public class Eight extends Action
{
    /**
     * create a new card of 8
     * @param name the name of card
     * @param point the point this card has
     * @param color the color of the card
     */
    public Eight(String name, int point, String color)
    {
        super(name, point, color);
        this.explainAction = "player has prize to play another round.";

    }


    /**
     * the action of the card is done here
     * @param runGame access to running system
     */
    @Override
    public void runAction(RunGame runGame)
    {
        runGame.playRound();

    }

}
