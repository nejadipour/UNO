/**
 * super class used for displaying cards
 * @author Alireza Nejadipour
 * @version 1
 */

public class Display
{
    /**
     * gets color ANSI code
     * @param color the color that we need its ANSI code
     * @return the related ANSI code
     */
    public String getColorANSICode(String color)
    {
        String ANSICode = "";
        switch (color)
        {
            case "red" -> ANSICode = "\u001B[31m";
            case "blue" -> ANSICode = "\u001B[34m";
            case "black", "reset" -> ANSICode = "\u001B[0m";
            case "green" -> ANSICode = "\u001B[32m";
            case "gray" -> ANSICode = "\u001B[37m";
            case "yellow" -> ANSICode = "\u001B[33m";

        }

        return ANSICode;

    }


    /**
     * cards count (or index) are in format of '# " or '##'
     * method converts number to valid format
     * @param count the count that should convert to valid format
     * @return the string generated
     */
    public String formatCardCount(int count)
    {
        String cardCount = Integer.toString(count);
        if (cardCount.length() == 2)
            return cardCount;

        cardCount = cardCount + " ";
        return cardCount;

    }

}
