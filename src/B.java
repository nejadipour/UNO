import java.awt.*;
import java.util.ArrayList;

public class B extends Action
{
    private String decidedColor;
    public B(String name, int point, String color)
    {
        super(name, point, color);
        this.explainAction = "player should decide the color of next round.";

    }


    public void askColor(ArrayList<String> colorsToDecide)
    {
        int number = 1;
        for (String colorToDecide : colorsToDecide)
        {
            System.out.print(number + ")");
            System.out.println(colorToDecide);
            number++;

        }

        userChoice();

        setDecidedColor(colorsToDecide.get(choice - 1));

    }


    public void autoSelectColor(ArrayList<String> colorsToDecide)
    {
        int index = randomGenerator(colorsToDecide.size());
        String selected = colors.get(index);

        System.out.println(this.getPlayer().getName() + " decided color " + selected);
        setDecidedColor(selected);
    }


    public void updateColor(ArrayList<Player> players)
    {
        int index = players.indexOf(this.getPlayer());

        Player nextPlayer;

        if (index == players.size() - 1)
            nextPlayer = players.get(0);
        else
            nextPlayer = players.get(index + 1);

        nextPlayer.setColorToTake(decidedColor);

    }

    @Override
    public void runAction(RunGame runGame)
    {
        if (this.getPlayer() instanceof Human)
            askColor(runGame.getColors());
        else
            autoSelectColor(runGame.colors);

        updateColor(runGame.getPlayers());

    }


    public void setDecidedColor(String decidedColor)
    {
        this.decidedColor = decidedColor;

    }

}
