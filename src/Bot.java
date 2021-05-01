public class Bot extends Player
{
    public Bot(String name)
    {
        super(name);

    }

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
