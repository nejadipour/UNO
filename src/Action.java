/**
 * subclass of Card
 * cards of this class have an action that runs by deciding them
 * action cards have an explanation that prints by deciding them
 * they also have an explanation that will print if a bot decides an action card
 * @author Alireza Nejadipour
 * @version 2
 */

public class Action extends Card
{
    // action cards have this field
    protected String explainAction;

    /**
     * create a new Action card
     * @param name the name of card
     * @param point the point this card has
     * @param color the color of the card
     */
    public Action(String name, int point, String color)
    {
        super(name, point, color);
        this.explainAction = "";

    }


    /**
     * the action of the card is done here
     * @param runGame access to running system
     */
    public void runAction(RunGame runGame)
    {

    }


    /**
     * gets the explanation of the action the card is for
     * @return explainAction field is returned
     */
    public String getExplainAction()
    {
        // it will be printed in gray
        return "\u001B[37m" + this.explainAction + "\u001B[0m";

    }


    /**
     * explains what has happened by the bot
     * @return the report of what bot has done
     */
    @Override
    public String botExplain()
    {
        return "\n" + this.getPlayer().getName() + " decided card " + this.getName() + "\n" + this.getExplainAction();

    }

}
