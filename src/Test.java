//                     import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Iterator;
//
//public class Test
//{
//
//    public static void main(String[] args)
//    {
//        System.out.println("\u001B[36m" + "fuck");
//        System.out.println("\u001B[37m" + "fuck" + "\u001B[0m");
//        System.out.println("fuck");
//        System.out.println("\u001B[35m" + "fuck");
//        System.out.println("\u001B[34m" + "fuck");
//        System.out.println("\u001B[33m" + "fuck");
//        System.out.println("\u001B[32m" + "fuck");
//        System.out.println("\u001B[31m" + "fuck");
//        System.out.println("\u001B[30m" + "fuck");
////
////
////
////        ArrayList<String> notes = new ArrayList<>();
////
////        notes.add("hie");
////        notes.add("how");
////        notes.add("are");
////        notes.add("you");
////
////        Iterator<String> iterator = notes.iterator();
////
////        int count = 1;
////        while (iterator.hasNext())
////        {
////            if (count == 3)
////            {
////                break;
////
////            }
////            System.out.println(iterator.next());
////
////            if (!iterator.hasNext())
////            {
////                iterator = notes.iterator();
////                count++;
////            }
////
////        }
////
////        Collections.reverse(notes);
////
////        iterator = notes.iterator();
////
////        count = 1;
////        while (iterator.hasNext())
////        {
////            if (count == 3)
////            {
////                break;
////
////            }
////            System.out.println(iterator.next());
////
////            if (!iterator.hasNext())
////            {
////                iterator = notes.iterator();
////                count++;
////            }
////
////        }
//
////        ArrayList<Card> cardsStore = new ArrayList<>();
////
////        Player player1 = new Player("alireza");
////        Player player2 = new Player("hamed");
////        Player player3 = new Player("mahdi");
////        Player player4 = new Player("amir");
////
////        ArrayList<Player> players = new ArrayList<>();
////
////        players.add(player1);
////        players.add(player2);
////        players.add(player3);
////        players.add(player4);
////
////        ArrayList<String> colors = new ArrayList<>();
////
////        colors.add("blue");
////        colors.add("black");
////        colors.add("red");
////        colors.add("green");
////
////        StartGame startGame = new StartGame();
////
////        cardsStore = startGame.generateCards(colors, players);
////
////        for (Player player : players)
////        {
////            System.out.println(player.getName());
////            for (Card card : player.getCards())
////            {
////                System.out.println(card.toString());
////
////            }
////
////        }
////
////
////        for (Card card : cardsStore)
////        {
////            System.out.println(card);
////
////        }
//
//
//
//        String name = "|     ";
//        name = name.replace("# ", "4 ");
//
//        System.out.println(name);
//
//
//
//
//
//
//
//
//
//
//
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
///*
//
//
//
//                 clockwise:.
//
//                  Player1
//                  12Cards
//                 Score 150
//
//                ____________
//               |A           |
//               |            |
//               |            |
// Player5       |            |      Player2
// 12Cards       |            |      12Cards
//Score 150      |            |     Score 150
//               |          A |
//               |____________|
//
//                  Player1
//                  12Cards
//                 Score 150
//
//____________________________________________________
//
//               .:anticlockwise
//
//                  Player1
//                  12Cards
//                 Score 150
//
//                ____________
//               |A           |
//               |            |
//               |            |
// Player5       |            |      Player2
// 12Cards       |            |      12Cards
//Score 150      |            |     Score 150
//               |          A |
//               |____________|
//
//         Player4              Player3
//         12Cards              12Cards
//        Score 150            Score 150
//
//
//
//it's player1's turn.
//player 1 decided card B.
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
// */
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//


import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static java.lang.System.exit;


class ActionClass {

    public static void main(String[] args)
    {

        Scanner input = new Scanner(System.in).useDelimiter("\n");


        //The program starts with the number of Strings
        int numberOfStrings = input.nextInt();

        //Input check
        if(numberOfStrings < 1 || numberOfStrings > 20)
            exit(0);

        //Getting the Strings
        String[] strings = new String[numberOfStrings];

        for (int i = 0; i < numberOfStrings; i++)
        {
            strings[i] = input.next();
            //Input check
            if(strings[i].length() > 20)
                exit(0);
        }


        //Now we find the shortest string to make sure we check all
        //possible substrings
        String min = strings[0];
        for (int i = 0; i < numberOfStrings; i++)
        {
            if (strings[i].length() < min.length())
                min = strings[i];

        }
        //Now we got our min

        //This array list is gonna store our final subStrings
        ArrayList<String> subStrings = new ArrayList<>();


        for (int i = 0; i < min.length() - 1; i++)
            for (int j = i + 1; j <= min.length(); j++)
            {
                if (subString.subStringCheck(strings, numberOfStrings, min, i, j))
                    subStrings.add(min.substring(i, j));

            }



        //Well everything is done now it's time to find the longest mutual substring
        String minSubString = "";
        for (String subString : subStrings)
        {
            if (subString.length() > minSubString.length())
                minSubString = subString;

        }

        //now we got everything we wanted
        if(subStrings.size() == 1)
            exit(0);
        System.out.println(minSubString);

    }
}


//This class is made for checking the substrings
class subString {

    //This method finds mutual Substrings
    public static boolean subStringCheck(String[] strings, int numberOfStrings,
                                         String min, int i, int j) {
        //StringBuffer reverse = new StringBuffer();
        int flag = 0;

        // A nested loop for checking the substring of each member of array
        for (int s = 0; s < numberOfStrings; s++) {
            if (strings[s].contains(min.substring(i, j)) ||
                    strings[s].contains(subString.reverser(min.substring(i,j))))
                flag++;
        }

        if(flag == numberOfStrings)
            return true;
        return false;


    }


    //we need a method to make strings backward
    public static String reverser(String word)
    {
        StringBuilder stringBuilder = new StringBuilder(word);

        return stringBuilder.reverse().toString();

    }
}
