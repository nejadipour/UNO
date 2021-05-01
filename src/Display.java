public class Display
{
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


    public String formatCardCount(int count)
    {
        String cardCount = Integer.toString(count);
        if (cardCount.length() == 2)
            return cardCount;

        cardCount = cardCount + " ";
        return cardCount;

    }


}
