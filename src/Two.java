import java.util.ArrayList;

public class Two extends Action
{
    private Player other;

    public Two(String name, int point, String color)
    {
        super(name, point, color);
        this.explainAction = "You have to choose one of the players.\nthen one of your cards will be passed to that player by random.";

    }

    public void displayPlayers(ArrayList<Player> players)
    {
        int number = 1;
        for (Player player : players)
        {
            if (!player.equals(this.getPlayer()))
            {
                System.out.print(number + ")");
                System.out.println(player);

                number++;

            }

        }

    }


    public void choosePlayer(ArrayList<Player> players)
    {
        userChoice();

        int number = 1;
        for (Player player : players)
        {
            if (number == choice)
            {
                setOther(player);

            }

            if (!player.equals(this.getPlayer()))
                number++;

        }

    }


    public void autoChoosePlayer(ArrayList<Player> players)
    {
        do
        {
            int index;

            index = randomGenerator(players.size());

            setOther(players.get(index));

        } while (other.equals(this.getPlayer()));

        System.out.println(this.getPlayer().getName() + " decided player " + other.getName());

    }


    public void giveCard()
    {
        Card cardToMove = randomCard(this.getPlayer().getCards());

        this.getPlayer().removeCard(cardToMove);

        other.addCard(cardToMove);

    }


    @Override
    public void runAction(RunGame runGame)
    {
        if (this.getPlayer() instanceof Human)
        {
            displayPlayers(runGame.getPlayers());
            choosePlayer(runGame.getPlayers());

        }
        else
            autoChoosePlayer(runGame.getPlayers());

        giveCard();

    }


    public void setOther(Player other)
    {
        this.other = other;

    }
}