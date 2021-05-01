import java.util.ArrayList;

/**
 * this class is used to handle card B
 * if player decides this card
 * player should decide the color of next round.
 * @author Alireza Nejadipour
 * @version 2.4
 */

public class B extends Action
{
    // the color that will be chosen
    private String decidedColor;

    /**
     * create a new card of B
     * @param name the name of card
     * @param point the point this card has
     * @param color the color of the card
     */
    public B(String name, int point, String color)
    {
        super(name, point, color);
        this.explainAction = "player should decide the color of next round.";

    }


    /**
     * prints available colors
     * the user will decide one of them
     * @param colorsToDecide list of colors
     */
    public void askColor(ArrayList<String> colorsToDecide)
    {
        int number = 1;
        for (String colorToDecide : colorsToDecide)
        {
            System.out.print(number + ")");
            System.out.println(colorToDecide);
            number++;

        }

        userChoice();

        setDecidedColor(colorsToDecide.get(choice - 1));

    }


    /**
     * if the player is bot, colors won't be printed
     * the method will decide a color by random
     * @param colorsToDecide available colors to decide from
     */
    public void autoSelectColor(ArrayList<String> colorsToDecide)
    {
        int index = randomGenerator(colorsToDecide.size());
        String selected = colors.get(index);

        // tells which color has been selected
        System.out.println(this.getPlayer().getName() + " decided color " + selected);
        setDecidedColor(selected);

    }


    /**
     * sets the color that next player should decide
     * @param players list of all players
     */
    public void updateColor(ArrayList<Player> players)
    {
        int index = players.indexOf(this.getPlayer());

        Player nextPlayer;

        // check last element
        if (index == players.size() - 1)
            nextPlayer = players.get(0);
        else
            nextPlayer = players.get(index + 1);

        nextPlayer.setColorToTake(decidedColor);

    }

    /**
     * the action of the card is done here
     * @param runGame access to running system
     */
    @Override
    public void runAction(RunGame runGame)
    {
        // check player identity
        if (this.getPlayer() instanceof Human)
            askColor(runGame.getColors());
        else
            autoSelectColor(runGame.colors);

        updateColor(runGame.getPlayers());

    }


    /**
     * sets the chosen color
     * @param decidedColor the color user has decided
     */
    public void setDecidedColor(String decidedColor)
    {
        this.decidedColor = decidedColor;

    }

}
