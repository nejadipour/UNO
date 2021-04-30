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
        this.colorToTake = null;
        this.displayCard = new DisplayCard();
        this.noValidCounter = 0;
        this.cardToPutMiddle = new Card();
        this.validExists = true;

    }

    public Player()
    {
        super();
        this.cards = new ArrayList<>();
        this.cardsToTake = 0;
        this.colorToTake = null;
        this.displayCard = new DisplayCard();
        this.noValidCounter = 0;
        this.cardToPutMiddle = new Card();
        this.validExists = true;

    }


    public void beforeChoose()
    {
        if (this instanceof Human)
            displayCard.printCards(this.cards, this);

        if (noValid())
        {
            if (noValidCounter == 0)
            {
                System.out.println("No valid card found for " + getName());
                System.out.println("One card will be added from store.");
                pressEnter();
                noValidCounter++;
                Card newCard = randomCard();
                addCard(newCard);
                allCards.remove(newCard);
                this.beforeChoose();

            }
            else
            {
                System.out.println("Again there is no valid card to use.");
                System.out.println("This turn is finished");
                pressEnter();
                setValidExists(false);

            }

        }


    }


    public void chooseCard()
    {

    }


    public void play()
    {
        beforeChoose();

        if (validExists)
            chooseCard();

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
        if (colorToTake == null)
            return roundCard.getColor().equals(chosenCard.getColor()) ||
                   roundCard.getName().equals(chosenCard.getName()) ||
                   chosenCard instanceof Action;
        else
            return colorToTake.equals(chosenCard.getColor()) &&
                   (roundCard.getName().equals(chosenCard.getName()) ||
                   chosenCard instanceof Action);

    }


    public void addCard(Card card)
    {
        cards.add(card);
        card.setPlayer(this);

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
        return getName() + "\t\t" + calculatePoint();

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

    public void setCardToPutMiddle(Card cardToPutMiddle)
    {
        this.cardToPutMiddle = cardToPutMiddle;

    }

    public Card getCardToPutMiddle()
    {
        return cardToPutMiddle;

    }

    public void setValidExists(boolean validExists)
    {
        this.validExists = validExists;

    }

}
