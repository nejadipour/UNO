/**
 * subclass of Player
 * methods related to human are stored here
 * @author Alireza Nehadipour
 * @version 3.2
 */

public class Human extends Player
{
    /**
     * create a new human
     * @param name name of the human
     */
    public Human(String name)
    {
        super(name);

    }


    /**
     * player decides a card from valid ones
     * @param runGame access to running system
     */
    @Override
    public void chooseCard(RunGame runGame)
    {
        userChoice();
        Card chosen = cards.get(choice - 1);
        invalidHandler(chosen, runGame);

    }


    /**
     * used after deciding a card
     * if it is valid or not related messages will be displayed
     * @param chosenCard card that is decided
     * @param runGame access to running system
     */
    public void invalidHandler(Card chosenCard, RunGame runGame)
    {
        if (!validCard(chosenCard))
        {
            System.out.println("You can't choose this card! try again.");
            chooseCard(runGame);

        }
        else
        {
            askToContinue(chosenCard, runGame);

        }

    }


    /**
     * asks to continue or not
     * if player wants to change the decision before run the next round
     * @param chosen the decided card
     * @param runGame access to running system
     */
    public void askToContinue(Card chosen, RunGame runGame)
    {
        System.out.println(chosen);

        System.out.println("""
                1. Continue
                2. Choose another card""");

        userChoice();

        if (choice == 1)
        {
            runGame.getAllCards().add(roundCard);
            runGame.roundPlayer.removeCard(chosen);
            runGame.setRoundCard(chosen);

        }

        else if (choice == 2)
        {
            chooseCard(runGame);

        }

    }

}
