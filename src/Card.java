/**
 * all information related to a card are stored here
 * like name, color, point...
 * @author Alireza Nejadipour
 * @version 2.2
 */

public class Card extends RunGame
{
    private String name;
    private int point;
    private String color;
    private Player player;

    /**
     * create a new card
     * @param name the name of card
     * @param point the point this card has
     * @param color the color of the card
     */
    public Card(String name, int point, String color)
    {
        this.name = name;
        this.point = point;
        this.color = color;

    }

    public Card()
    {

    }


    /**
     * returns information of card
     * if it is Action card the explanation will be returned too
     * @return string generated in method
     */
    @Override
    public String toString()
    {
        StringBuilder cardInfo = new StringBuilder();

        cardInfo.append("Card : ");
        cardInfo.append(this.getName());
        cardInfo.append("\t");

        cardInfo.append("Color : ");
        cardInfo.append(this.getColor());
        cardInfo.append("\t");

        cardInfo.append("Point : ");
        cardInfo.append(this.getPoint());
        cardInfo.append("\t");

        if (this instanceof Action)
        {
            cardInfo.append("\n");
            cardInfo.append(((Action) this).getExplainAction());

        }

        cardInfo.append("\n");

        return cardInfo.toString();
    }


    /**
     * explains what has happened by the bot
     * @return the report of what bot has done
     */
    public String botExplain()
    {
        return "";

    }


    /**
     * sets the player of card
     * @param player the player that has this card
     */
    public void setPlayer(Player player)
    {
        this.player = player;

    }

    /**
     * gets the player that has this card
     * @return field player is returned
     */
    public Player getPlayer()
    {
        return player;

    }

    /**
     * gets the color of the card
     * @return field color is returned
     */
    public String getColor()
    {
        return color;

    }

    /**
     * gets the point of the card
     * @return point field is returned
     */
    public int getPoint()
    {
        return point;

    }

    /**
     * gets the name of the card
     * @return field name is returned
     */
    public String getName()
    {
        return name;

    }

}
