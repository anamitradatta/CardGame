public class Card {

    public enum Rank 
    {
       ACE,
       TWO,
       THREE,
       FOUR,
       FIVE,
       SIX,
       SEVEN,
       EIGHT,
       NINE,
       TEN,
       JACK,
       QUEEN,
       KING,
       JOKER; 
    }

    public enum Suit
    {
        CLUBS,
        HEARTS,
        SPADES,
        CLOVES,
        NONE; //only use with joker
    }

    private final Rank rank;
    private final Suit suit;
    private boolean faceup;
    private boolean facedown;

    public Rank rank() 
    { 
        return rank; 
    }

    public Suit suit() 
    { 
        return suit; 
    }

    public boolean isFaceup()
    {
        return faceup;
    }

    public boolean isFacedown()
    {
        return facedown;
    }

    public void flip()
    {
        faceup = !faceup;
        facedown = !facedown;
    }

    public boolean equals(Card c)
    {
        if((c.rank() == this.rank) && (c.suit() == this.suit()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public String toString() 
    { 
        return rank + " of " + suit; 
    }

    public Card(Rank rank, Suit suit, boolean fu, boolean fd)
    {
        this.rank = rank;
        this.suit = suit;

        if(fu==fd)
        {
            throw new IllegalArgumentException("faceup and facedown cannot be same for a card");
        }
        else
        {
            facedown = fu;
            faceup = fd;
        }
    }

    public Card(Rank rank, Suit suit)
    {
        this.rank = rank;
        this.suit = suit;
        facedown = true;
        faceup = false;
    }

    public Card(Rank rank)
    {
        this.rank = rank;
        this.suit = Card.Suit.NONE;
        facedown = true;
        faceup = false;
    }

}
