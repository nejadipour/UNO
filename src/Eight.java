public class Eight extends Action
{

    public Eight(String name, int point, String color)
    {
        super(name, point, color);
        this.explainAction = "U have prize to play another round.";

    }


    @Override
    public void runAction(RunGame runGame)
    {
        runGame.playRound();

    }




}
