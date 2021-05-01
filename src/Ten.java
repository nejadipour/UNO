import java.util.Collections;

public class Ten extends Action
{
    public Ten(String name, int point, String color)
    {
        super(name, point, color);
        this.explainAction = "The direction of the players will change.";

    }


    public void changeDirection(RunGame runGame, String direction)
    {
        if (direction.equals("clockwise"))
            runGame.setDirection("anticlockwise");
        else
            runGame.setDirection("clockwise");

        Collections.reverse(runGame.getPlayers());

    }


    @Override
    public void runAction(RunGame runGame)
    {
        changeDirection(runGame, runGame.getDirection());

    }

}
