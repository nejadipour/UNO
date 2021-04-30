import java.util.DoubleSummaryStatistics;

public class Human extends Player
{
    public Human(String name)
    {
        super(name);

    }


    @Override
    public void chooseCard()
    {
        userChoice();
        Card chosen = cards.get(choice - 1);
        invalidHandler(chosen);

    }


    public void invalidHandler(Card chosenCard)
    {
        if (!validCard(chosenCard))
        {
            System.out.println("You can't choose this card! try again.");
            play();

        }
        else
        {
            setCardToPutMiddle(chosenCard);
            askToContinue();

        }

    }


    public void askToContinue()
    {
        System.out.println(cardToPutMiddle);

        System.out.println("""
                1. Continue
                2. Choose another card""");

        userChoice();

        if (choice == 2)
            play();

    }

}
