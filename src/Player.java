import java.util.ArrayList;
import java.util.Objects;

public class Player extends RunGame
{
    private String name;
    protected ArrayList<Card> cards;
    private int cardsToTake;
    private String colorToTake;
    protected DisplayCard displayCard;
    protected int noValidCounter;
    protected Card cardToPutMiddle;
    private boolean validExists;

    public Player(String name)
    {
        super();
        this.cards = new ArrayList<>();
        this.name = name;
        this.cardsToTake = 0;
        this.colorToTake = "";
        this.displayCard = new DisplayCard();
        this.noValidCounter = 1;
        this.cardToPutMiddle = new Card();
        this.validExists = true;

    }

    public Player()
    {
        super();
        this.cards = new ArrayList<>();
        this.cardsToTake = 0;
        this.colorToTake = "";
        this.displayCard = new DisplayCard();
        this.noValidCounter = 1;
        this.cardToPutMiddle = new Card();
        this.validExists = true;

    }


    public void beforeChoose(RunGame runGame)
    {
        if (this instanceof Human)
            displayCard.printCards(this.cards, this);

        if (noValid())
        {
            if (noValidCounter  == 1)
            {
                System.out.println("No valid card found for " + getName());
                System.out.println("One card will be added from store.");
                pressEnter();
                setNoValidCounter(2);
                Card newCard = randomCard(allCards);
                if (newCard instanceof Eight)
                    setNoValidCounter(1);
                addCard(newCard);
                allCards.remove(newCard);
                this.beforeChoose(runGame);

            }

            else
            {
                System.out.println("Again there is no valid card to use.");
                System.out.println("This turn is finished");
                setNoValidCounter(1);
                pressEnter();
                setValidExists(false);

            }

        }


    }


    public void chooseCard(RunGame runGame)
    {

    }


    public void play(RunGame runGame)
    {
        setValidExists(true);
//        setCardToPutMiddle(null);
        beforeChoose(runGame);

        if (validExists)
            chooseCard(runGame);

    }


    public boolean noValid()
    {
        for (Card card : cards)
            if (validCard(card))
                return false;

        return true;

    }


    public int calculatePoint()
    {
        int point = 0;
        for (Card card : this.cards)
        {
            point += card.getPoint();

        }

        return point;

    }


    public boolean validCard(Card chosenCard)
    {
        if (colorToTake.equals(""))
            return roundCard.getColor().equals(chosenCard.getColor()) ||
                   roundCard.getName().equals(chosenCard.getName()) ||
                   chosenCard instanceof Action;
        else
            return colorToTake.equals(chosenCard.getColor()) ||
                   (roundCard.getName().equals(chosenCard.getName()) ||
                   chosenCard instanceof Action);

    }


    public void addCard(Card card)
    {
        cards.add(card);
        card.setPlayer(this);

    }


    public void removeCard(Card card)
    {
        cards.remove(card);

    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return cardsToTake == player.cardsToTake && Objects.equals(name, player.name) && Objects.equals(cards, player.cards) && Objects.equals(colorToTake, player.colorToTake);
    }


    @Override
    public String toString()
    {
        return getName() + "\t\t" + getCards().size() + " cards";

    }


    public ArrayList<Card> getCards()
    {
        return cards;

    }

    public String getName()
    {
        return name;

    }


    public void setCardsToTake(int cardsToTake)
    {
        this.cardsToTake = cardsToTake;

    }

    public void setColorToTake(String colorToTake)
    {
        this.colorToTake = colorToTake;

    }

//    public void setCardToPutMiddle(Card cardToPutMiddle)
//    {
//        this.cardToPutMiddle = cardToPutMiddle;
//
//    }

    public Card getCardToPutMiddle()
    {
        return cardToPutMiddle;

    }

    public void setValidExists(boolean validExists)
    {
        this.validExists = validExists;

    }

    public int getCardsToTake()
    {
        return cardsToTake;

    }

    public String getColorToTake()
    {
        return colorToTake;

    }

    public void setNoValidCounter(int noValidCounter)
    {
        this.noValidCounter = noValidCounter;

    }

}
