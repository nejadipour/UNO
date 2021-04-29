import java.util.ArrayList;

public class DisplayCard extends Display
{
    private StringBuilder playerCards;
    private ArrayList<String> ANSICodes;

    public DisplayCard()
    {
        super();
        this.playerCards = new StringBuilder();
        this.ANSICodes = new ArrayList<>();

    }


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


    public void printCards(ArrayList<Card> cards, Player player)
    {
        updateColors(cards, player);
        updateBody(" ______", " ____________");
        updateName(cards, "|#     ", "|#           |");
        for (int line = 1 ; line <= 5 ; line++)
            updateBody("|      ", "|            |");
        updateName(cards, "|      ", "|          # |");
        updateBody("|______", "|____________|");

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


 ______ ______ _____________
|A     |4     |B            |
|      |      |             |
|      |      |             |
|      |      |             |
|      |      |             |
|      |      |             |
|      |      |           B |
|______|______|_____________|


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
