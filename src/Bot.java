public class Bot extends Player
{
    public Bot(String name)
    {
        super(name);

    }

    @Override
    public void chooseCard()
    {
        int index;
        Card chosen;

        while (true)
        {
            index = randomGenerator(cards.size());

            chosen = cards.get(index);

            if (validCard(chosen))
            {
                setCardToPutMiddle(chosen);
                break;

            }

        }

    }

}
