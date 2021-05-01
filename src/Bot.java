/**
 * subclass of Player
 * methods related to bot are stored here
 * @author Alireza Nehadipour
 * @version 2.2
 */

public class Bot extends Player
{
    /**
     * create a new bot
     * @param name name of the bot
     */
    public Bot(String name)
    {
        super(name);

    }


    /**
     * choose card is randomly done by system
     * @param runGame access to running system
     */
    @Override
    public void chooseCard(RunGame runGame)
    {
        int index;
        Card chosen;

        while (true)
        {
            index = randomGenerator(cards.size());

            chosen = cards.get(index);

            if (validCard(chosen))
            {
                System.out.println(chosen.botExplain());
                runGame.getAllCards().add(roundCard);
                runGame.roundPlayer.removeCard(chosen);
                runGame.setRoundCard(chosen);

                break;

            }

        }

    }

}
