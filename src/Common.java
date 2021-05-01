/**
 * subclass of Card
 * @author Alireza Nejadipour
 * @version 2
 */

public class Common extends Card
{
    /**
     * create a new Common card
     * @param name the name of card
     * @param point the point this card has
     * @param color the color of the card
     */
    public Common(String name, int point, String color)
    {
        super(name, point, color);

    }


    /**
     * explains what has happened by the bot
     * @return the report of what bot has done
     */
    @Override
    public String botExplain()
    {
        return "\n" + this.getPlayer().getName() + " decided card " + this.getName();

    }

}
