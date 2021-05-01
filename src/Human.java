import java.util.DoubleSummaryStatistics;

public class Human extends Player
{
    public Human(String name)
    {
        super(name);

    }


    @Override
    public void chooseCard(RunGame runGame)
    {
        userChoice();
        Card chosen = cards.get(choice - 1);
        invalidHandler(chosen, runGame);

    }


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
//            setCardToPutMiddle(null);

        }

    }

}
