import java.util.Collections;

/**
 * this class is used to handle card 10
 * if player decides this card
 * The direction of the players will change.
 * @author Alireza Nejadipour
 * @version 1.2
 */

public class Ten extends Action
{
    /**
     * create a new card of 10
     * @param name the name of card
     * @param point the point this card has
     * @param color the color of the card
     */
    public Ten(String name, int point, String color)
    {
        super(name, point, color);
        this.explainAction = "The direction of the players will change.";

    }


    /**
     * changes the direction of the game
     * @param runGame access to running system
     * @param direction the current direction
     */
    public void changeDirection(RunGame runGame, String direction)
    {
        if (direction.equals("clockwise"))
            runGame.setDirection("anticlockwise");
        else
            runGame.setDirection("clockwise");

        Collections.reverse(runGame.getPlayers());

    }


    /**
     * the action of the card is done here
     * @param runGame access to running system
     */
    @Override
    public void runAction(RunGame runGame)
    {
        changeDirection(runGame, runGame.getDirection());

    }

}
