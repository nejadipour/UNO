import java.util.ArrayList;

/**
 * subclass of Display
 * used each round for human to decide from cards
 * @author Alireza Nejadipour
 * @version 1.8
 */

public class DisplayCard extends Display
{
    private StringBuilder playerCards;
    private ArrayList<String> ANSICodes;

    /**
     * create a new displayCard
     */
    public DisplayCard()
    {
        super();
        this.playerCards = new StringBuilder();
        this.ANSICodes = new ArrayList<>();

    }


    /**
     * updates colors based on the color field
     * if it is not valid it will be gray
     * @param cards the cards player has
     * @param player the owner of the cards
     */
    public void updateColors(ArrayList<Card> cards, Player player)
    {
        for (Card card : cards)
        {
            String ANSICode = "";

            if (player.validCard(card))
                ANSICode = getColorANSICode(card.getColor());

            else
                ANSICode = getColorANSICode("gray");

            this.ANSICodes.add(ANSICode);

        }

    }


    /**
     * updates body of the cards
     * @param smallFormat cards that will display before last one
     * @param largeFormat the last card format
     */
    public void updateBody(String smallFormat, String largeFormat)
    {
        int lastIndex = ANSICodes.size() - 1;

        for (int index = 0 ; index < lastIndex ; index++)
        {
            this.playerCards.append(ANSICodes.get(index)).append(smallFormat);

        }

        this.playerCards.append(ANSICodes.get(lastIndex)).append(largeFormat);

        this.playerCards.append("\n");

    }


    /**
     * updates cards names based on the player's cards
     * @param cards all cards player has
     * @param smallFormat cards that will display before last one
     * @param largeFormat the last card format
     */
    public void updateName(ArrayList<Card> cards, String smallFormat, String largeFormat)
    {
        int lastIndex = ANSICodes.size() - 1;
        String name = "";

        for (int index = 0 ; index < lastIndex ; index++)
        {
            name = cards.get(index).getName();
            this.playerCards.append(ANSICodes.get(index));
            this.playerCards.append(smallFormat.replace("# ", name));

        }

        name = cards.get(lastIndex).getName();
        this.playerCards.append(ANSICodes.get(lastIndex));
        this.playerCards.append(largeFormat.replace("# ", name));

        this.playerCards.append("\n");

    }


    /**
     * numbers will be added to bottom of the card
     * @param cards all cards player has
     * @param smallFormat for cards that will display before last one
     * @param largeFormat for the last card format
     */
    public void updateIndex(ArrayList<Card> cards, String smallFormat, String largeFormat)
    {
        this.playerCards.append("\n");
        int lastIndex = cards.size() - 1;

        for (int index = 0 ; index < lastIndex ; index++)
        {
            this.playerCards.append(smallFormat.replace("# ", formatCardCount(index + 1)));

        }

        this.playerCards.append(largeFormat.replace("# ", formatCardCount(lastIndex + 1)));

    }


    /**
     * updates cards view and prints it
     * @param cards all cards player has
     * @param player the owner of the cards
     */
    public void printCards(ArrayList<Card> cards, Player player)
    {
        this.playerCards = new StringBuilder();
        this.ANSICodes = new ArrayList<>();

        updateColors(cards, player);
        updateBody(" ______", " ____________");
        updateName(cards, "|#     ", "|#           |");
        for (int line = 1 ; line <= 5 ; line++)
            updateBody("|      ", "|            |");
        updateName(cards, "|      ", "|          # |");
        updateBody("|______", "|____________|");
        this.playerCards.append(getColorANSICode("reset"));
        updateIndex(cards, "   #   ", "      #      ");

        System.out.println(playerCards);

    }

}


/*

 ____________
|A           |
|            |
|            |
|            |
|            |
|            |
|           A|
|____________|


 ______ ______ ____________
|A     |4     |B           |
|      |      |            |
|      |      |            |
|      |      |            |
|      |      |            |
|      |      |            |
|      |      |          B |
|______|______|____________|

   1      2         3

"   #   ".... "      #      "    ---> '# ' should change with the index number
head:
" ______" ______" _____________"

name:
"|#     "|#     "|#            |"

body(5 lines)
"|      "|      "|             |"

lastName:
"|      "|      "|           # |"

bottom:
"|______"|______"|_____________|"


 */
