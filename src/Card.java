import java.util.ArrayList;

public class Card extends RunGame
{
    private String name;
    private int point;
    private String color;
    private Player player;

    public Card(String name, int point, String color)
    {
        this.name = name;
        this.point = point;
        this.color = color;

    }

    public Card()
    {

    }


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


    public String botExplain()
    {
        return "";

    }


    public void setPlayer(Player player)
    {
        this.player = player;

    }


    public Player getPlayer()
    {
        return player;

    }


    public String getColor()
    {
        return color;

    }


    public int getPoint()
    {
        return point;

    }

    public String getName()
    {
        return name;

    }

}
