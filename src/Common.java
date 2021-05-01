public class Common extends Card
{
    public Common(String name, int point, String color)
    {
        super(name, point, color);

    }


    @Override
    public String botExplain()
    {
        return "\n" + this.getPlayer().getName() + " decided card " + this.getName();

    }

}
