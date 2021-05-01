public class A extends Action
{
    public A(String name, int point, String color)
    {
        super(name, point, color);
        this.explainAction = "next player can't play in this round.";

    }


    @Override
    public void runAction(RunGame runGame)
    {
        runGame.nextRound();

    }

}
