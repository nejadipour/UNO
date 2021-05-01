/**
 * this class is used to handle card A
 * if player decides this card
 * next player can't play in this round.
 * @author Alireza Nejadipour
 * @version 1
 */

public class A extends Action
{
    /**
     * create a new card of A
     * @param name the name of card
     * @param point the point this card has
     * @param color the color of the card
     */
    public A(String name, int point, String color)
    {
        super(name, point, color);
        this.explainAction = "next player can't play in this round.";

    }


    /**
     * the action of the card is done here
     * @param runGame access to running system
     */
    @Override
    public void runAction(RunGame runGame)
    {
        runGame.nextRound();

    }

}
