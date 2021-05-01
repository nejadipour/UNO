import java.util.ArrayList;
import java.util.Collections;

/**
 * subclass of Display
 * used each round to print the map
 * it shows the status of all players, roundPlayer, roundCard and the direction
 * @author Alireza Nejadipour
 * @version 3.8
 */

public class DisplayMap extends Display
{
    private final String format1; // for 3 players
    private final String format2; // for 4 players
    private final String format3; // for 5 players
    private String map;

    /**
     * different formats of map based on number of players
     */
    public DisplayMap()
    {
        this.format1 = """
                                  AAAAAAA
                                  aaCards
                                 Score 111
                                
                                ____________
                               |#           |
                               |            |
                               |            |
                 CCCCCCC       |            |      BBBBBBB
                 ccCards       |            |      bbCards
                Score 333      |            |     Score 222
                               |          # |
                               |____________|
                                
                                  """;

        this.format2 = """
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


        this.format3 = """
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


    }


    /**
     * decides which format to be used
     * @param size the number od players
     */
    public void initMap(int size)
    {
        switch (size)
        {
            case 3 -> this.map = format1;
            case 4 -> this.map = format2;
            case 5 -> this.map = format3;

        }

    }


    /**
     * updates the map based on parameters passed
     * @param players all players of the game
     * @param roundPlayer the roundPlayer whose turn is it
     * @param roundCard the card that is in the middle
     */
    public void updateMap(ArrayList<Player> players, Player roundPlayer, Card roundCard)
    {
        String grayANSICode = getColorANSICode("gray");
        String yellowANSICode = getColorANSICode("yellow");
        String resetANSICode = getColorANSICode("reset");

        this.map = grayANSICode + this.map + resetANSICode;

        String cardColor = roundCard.getColor();

        // update the roundCard
        this.map = this.map.replace(" ____________", getColorANSICode(cardColor) + " ____________" + grayANSICode);
        this.map = this.map.replace("|#           |", getColorANSICode(cardColor) + "|#           |" + grayANSICode);
        this.map = this.map.replace("|            |", getColorANSICode(cardColor) + "|            |" + grayANSICode);
        this.map = this.map.replace("|          # |", getColorANSICode(cardColor) + "|          # |" + grayANSICode);
        this.map = this.map.replace("|____________|", getColorANSICode(cardColor) + "|____________|" + grayANSICode);

        this.map = this.map.replace("# ", roundCard.getName());

        // we use ASCII codes and update each player's info
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
                this.map = this.map.replace(nameInMap, yellowANSICode + playerName + grayANSICode);
                this.map = this.map.replace(cardNumInMap + "Cards", yellowANSICode + cardCount + "Cards" + grayANSICode);
                this.map = this.map.replace("Score " + pointInMap, yellowANSICode + "Score " + point + grayANSICode);

            }
            else
            {
                this.map = this.map.replace(nameInMap, playerName);
                this.map = this.map.replace(cardNumInMap + "Cards", cardCount + "Cards");
                this.map = this.map.replace("Score " + pointInMap, "Score " + point);

            }

            nameAscii++;
            cardAscii++;
            pointAscii++;

        }

    }


    /**
     * names are in length of 7
     * if it is more we use '...' at the end
     * @param name the name of player
     * @return the converted name
     */
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


    /**
     * points are in format of '###'
     * @param point the point that should be converted
     * @return valid format
     */
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


    /**
     * prints the map based on parameters passed
     * @param players all players
     * @param roundPlayer the player whose turn is it
     * @param roundCard card in the middle
     * @param direction direction of the game
     */
    public void printMap(ArrayList<Player> players, Player roundPlayer, Card roundCard, String direction)
    {
        System.out.println("____________________________________________________");
        initMap(players.size());

        if (direction.equals("anticlockwise"))
            Collections.reverse(players);

        updateMap(players, roundPlayer, roundCard);

        if (direction.equals("clockwise"))
            System.out.println("                 clockwise:.");
        else
            System.out.println("               .:anticlockwise");
        System.out.println();

        System.out.println(this.map);

        if (direction.equals("anticlockwise"))
            Collections.reverse(players);

    }

}
