import java.util.ArrayList;

public class Action extends Card
{
    protected String explainAction;

    public Action(String name, int point, String color)
    {
        super(name, point, color);
        this.explainAction = "";

    }


    public void runAction(RunGame runGame)
    {

    }


    public String getExplainAction()
    {
        return "\u001B[37m" + this.explainAction + "\u001B[0m";

    }


    @Override
    public String botExplain()
    {
        return "\n" + this.getPlayer().getName() + " decided card " + this.getName() + "\n" + this.getExplainAction();

    }


}
