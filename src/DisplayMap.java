import java.lang.reflect.Array;
import java.util.ArrayList;

public class DisplayMap
{
    private String format1; // for 3 or 4 players
    private String format2; // for 5 players
    private String map;

    public DisplayMap(int size)
    {
        this.format1 = """
                                  AAAAAAA
                                  aaCards
                                 Score 111
                                
                                ____________
                               |#           |
                               |            |
                               |            |
                 DDDDDDD       |            |      BBBBBBB
                 ddCards       |            |      bbCards
                Score 444      |            |     Score 222
                               |          # |
                               |____________|
                                
                                  CCCCCCC
                                  ccCards
                                 Score 333""";


        this.format2 = """
                                  AAAAAAA
                                  aaCards
                                 Score 111
                                
                                ____________
                               |#           |
                               |            |
                               |            |
                 EEEEEEE       |            |      BBBBBBB
                 eeCards       |            |      bbCards
                Score 555      |            |     Score 222
                               |          # |
                               |____________|
                                
                         DDDDDDD              CCCCCCC
                         ddCards              ccCards
                        Score 444            Score 333""";



        if (size == 5)
            this.map = format2;
        else
            this.map = format1;

    }


    public void updateMap(ArrayList<Player> players, Player roundPlayer, Card roundCard)
    {
        String grayANSICode = "\u001B[37m";
        String yellowANSICode = "\u001B[33m";
        String resetANSICode = "\u001B[0m";

        this.map = grayANSICode + map + resetANSICode;

        String cardColor = roundCard.getColor();

        this.map = map.replace(" ____________", getColorANSICode(cardColor) + " ____________" + grayANSICode);
        this.map = map.replace("|#           |", getColorANSICode(cardColor) + "|#           |" + grayANSICode);
        this.map = map.replace("|            |", getColorANSICode(cardColor) + "|            |" + grayANSICode);
        this.map = map.replace("|          # |", getColorANSICode(cardColor) + "|          # |" + grayANSICode);
        this.map = map.replace("|____________|", getColorANSICode(cardColor) + "|____________|" + grayANSICode);

        this.map = map.replace("# ", roundCard.getName());


        int nameAscii = 'A';
        int cardAscii = 'a';
        int pointAscii = '1';
        for (Player player : players)
        {
            String nameInMap = Character.toString(nameAscii).repeat(7);
            String cardNumInMap = Character.toString(cardAscii).repeat(2);
            String pointInMap = Character.toString(pointAscii).repeat(3);

            String playerName = formatName(player.getName());
            String cardCount = formatCardCount(player.getCards().size());
            String point = formatPoint(player.calculatePoint());

            if (player.equals(roundPlayer))
            {
                this.map = map.replace(nameInMap, yellowANSICode + playerName + grayANSICode);
                this.map = map.replace(cardNumInMap + "Cards", yellowANSICode + cardCount + "Cards" + grayANSICode);
                this.map = map.replace("Score " + pointInMap, yellowANSICode + "Score " + point + grayANSICode);

            }
            else
            {
                this.map = map.replace(nameInMap, playerName);
                this.map = map.replace(cardNumInMap + "Cards", cardCount + "Cards");
                this.map = map.replace("Score " + pointInMap, "Score " + point);

            }

            nameAscii++;
            cardAscii++;
            pointAscii++;

        }

    }


    public String formatName(String name)
    {
        StringBuilder convertedName = new StringBuilder();
        if (name.length() > 7)
        {

            for (int i = 0 ; i < 4 ; i++)
            {
                convertedName.append(name.toCharArray()[i]);

            }

            convertedName.append("...");

        }
        else if (name.length() < 7)
        {
            int diff = 7 - name.length();
            convertedName.append(name);
            String space = " ".repeat(diff);
            convertedName.append(space);

        }
        else
            return name;


        return convertedName.toString();

    }


    public String formatCardCount(int count)
    {
        String cardCount = Integer.toString(count);
        if (cardCount.length() == 2)
            return cardCount;

        cardCount = cardCount + " ";
        return cardCount;

    }


    public String formatPoint(int point)
    {
        String playerPoint = Integer.toString(point);
        int len = playerPoint.length();

        switch (len)
        {
            case 1 -> playerPoint = "  " + playerPoint;
            case 2 -> playerPoint = " " + playerPoint;

        }

        return playerPoint;
    }


    public String getColorANSICode(String color)
    {
        String ANSICode = "";
        switch (color)
        {
            case "red" -> ANSICode = "\u001B[31m";
            case "blue" -> ANSICode = "\u001B[34m";
            case "black" -> ANSICode = "";
            case "green" -> ANSICode = "\u001B[32m";

        }

        return ANSICode;

    }


    public void printMap(ArrayList<Player> players, Player roundPlayer, Card roundCard, String direction)
    {
        updateMap(players, roundPlayer, roundCard);

        if (direction.equals("clockwise"))
            System.out.println("                 clockwise:.");
        else
            System.out.println("               .:anticlockwise");
        System.out.println();

        System.out.println(this.map);

    }

}
