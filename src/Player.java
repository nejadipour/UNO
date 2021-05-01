import java.util.ArrayList;
import java.util.Objects;

/**
 * all information about player is stored here
 * like name, cards, color or extra cards that player should decide...
 * @author Alireza Nejadipour
 * @version 3.6
 */

public class Player extends RunGame
{
    private String name;
    protected ArrayList<Card> cards;
    private int cardsToTake;                // by deciding card 7 this field will be updated for next player
    private String colorToTake;             // by deciding card B this field will be updated for next player
    protected DisplayCard displayCard;
    protected int noValidCounter;           // used to see if it's the first time that user has no valid card or not
    private boolean validExists;            // says there is any valid card available or not

    /**
     * create a new player
     * @param name the name of the new player
     */
    public Player(String name)
    {
        super();
        this.cards = new ArrayList<>();
        this.name = name;
        this.cardsToTake = 0;
        this.colorToTake = "";
        this.displayCard = new DisplayCard();
        this.noValidCounter = 1;
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
        this.validExists = true;

    }


    /**
     * if no valid card exists
     * prints related messages based on the noValidCounter field
     */
    public void beforeChoose()
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

                /* if the selected card is 8 player should put it in middle again:)
                but the noValidCounter should be reset*/
                if (newCard instanceof Eight)
                    setNoValidCounter(1);

                addCard(newCard);
                allCards.remove(newCard);
                this.beforeChoose(); // to check if player can choose a card this time or not

            }

            else
            {
                System.out.println("Again there is no valid card to use.");
                System.out.println("This turn is finished");
                setNoValidCounter(1); // for next round
                pressEnter();
                setValidExists(false);

            }

        }

    }


    /**
     * player decides a card from valid ones
     * @param runGame access to running system
     */
    public void chooseCard(RunGame runGame)
    {

    }


    /**
     * method is called each time its player's turn
     * @param runGame access to running system
     */
    public void play(RunGame runGame)
    {
        setValidExists(true);

        beforeChoose();

        if (validExists)
            chooseCard(runGame);

    }


    /**
     * iterates through the cards to check if there is any valid card available or not
     * @return if one valid card exists returns false
     */
    public boolean noValid()
    {
        for (Card card : cards)
            if (validCard(card))
                return false;

        return true;

    }


    /**
     * at the end of the game players will be displayed based on the points
     * that is calculated here
     * based on cards
     * @return the total of points
     */
    public int calculatePoint()
    {
        int point = 0;
        for (Card card : this.cards)
        {
            point += card.getPoint();

        }

        return point;

    }


    /**
     * checks if selected card is valid or not
     * it should has same name with roundCard or
     * same color with roundCard(if the player before hasn't selected card)
     * or it should be an Action card
     * @param chosenCard the card player has decided
     * @return true if it is valid
     */
    public boolean validCard(Card chosenCard)
    {
        // if the player before hasn't selected card B
        if (colorToTake.equals(""))
            return roundCard.getColor().equals(chosenCard.getColor()) ||
                   roundCard.getName().equals(chosenCard.getName()) ||
                   chosenCard instanceof Action;
        else
            return colorToTake.equals(chosenCard.getColor()) ||
                   (roundCard.getName().equals(chosenCard.getName()) ||
                   chosenCard instanceof Action);

    }


    /**
     * adds a new card to list
     * @param card the card that should be added
     */
    public void addCard(Card card)
    {
        cards.add(card);
        card.setPlayer(this);

    }


    /**
     * remove a card from list
     * @param card the card that sould be removed
     */
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


    // this method is used in card 2
    @Override
    public String toString()
    {
        return getName() + "\t\t" + getCards().size() + " cards";

    }


    /**
     * gets cards that player has
     * @return cards field is returned
     */
    public ArrayList<Card> getCards()
    {
        return cards;

    }

    /**
     * gets name of the player
     * @return name field is returned
     */
    public String getName()
    {
        return name;

    }

    /**
     * sets the cards that player should take more
     * method is used by using card 7
     * @param cardsToTake cards that player should take based on color of card 7
     */
    public void setCardsToTake(int cardsToTake)
    {
        this.cardsToTake = cardsToTake;

    }

    /**
     * set the color player should take
     * method is used by using card B
     * @param colorToTake color that player should take in the round
     */
    public void setColorToTake(String colorToTake)
    {
        this.colorToTake = colorToTake;

    }

    /**
     * sets the validExists field
     * @param validExists its either true or false
     */
    public void setValidExists(boolean validExists)
    {
        this.validExists = validExists;

    }

    /**
     * gets the count of cards player should take from store
     * @return cardsToTake field is returned
     */
    public int getCardsToTake()
    {
        return cardsToTake;

    }

    /**
     * gets the color of card player should use
     * @return colorToTake field is returned
     */
    public String getColorToTake()
    {
        return colorToTake;

    }

    /**
     * sets the noValidCounter field
     * @param noValidCounter its either 1 or 2
     */
    public void setNoValidCounter(int noValidCounter)
    {
        this.noValidCounter = noValidCounter;

    }

}
