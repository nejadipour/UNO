import java.util.ArrayList;

public class Seven extends Action
{
    public Seven(String name, int point, String color)
    {
        super(name, point, color);
        if (this.getColor().equals("black"))
            this.explainAction = "next player should take 4 cards by random.";
        else
            this.explainAction = "next player should take 2 cards by random.";

    }


    public void updateCount(ArrayList<Player> players)
    {
        int index = players.indexOf(this.getPlayer());

        Player nextPlayer;

        if (index == players.size() - 1)
            nextPlayer = players.get(0);
        else
            nextPlayer = players.get(index + 1);

        if (this.getColor().equals("black"))
            nextPlayer.setCardsToTake(this.getPlayer().getCardsToTake() + 4);
        else
            nextPlayer.setCardsToTake(this.getPlayer().getCardsToTake() + 2);

        this.getPlayer().setCardsToTake(0);

    }


    @Override
    public void runAction(RunGame runGame)
    {
        updateCount(runGame.getPlayers());

    }

}
