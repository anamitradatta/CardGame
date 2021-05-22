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

    private boolean jokerCheck()
    {
        if(rank == Rank.JOKER)
        {
            if(suit == Suit.NONE)
            {
                return true;
            }
            else
            {
                throw new IllegalArgumentException("Joker must have a NONE Suit type");
            }
        }
        else
        {
            return true;
        }
    }

    public static boolean jokerCheck(Card c)
    {
        if(c.rank() == Rank.JOKER)
        {
            if(c.suit() == Suit.NONE)
            {
                return true;
            }
            else
            {
                throw new IllegalArgumentException("Joker must have a NONE Suit type");
            }
        }
        else
        {
            return true;
        }
    }

    public static boolean jokerCheck(Rank r, Suit s)
    {
        if(r == Rank.JOKER)
        {
            if(s == Suit.NONE)
            {
                return true;
            }
            else
            {
                throw new IllegalArgumentException("Joker must have a NONE Suit type");
            }
        }
        else
        {
            return true;
        }
    }

    public Card(Rank rank, Suit suit, boolean fu, boolean fd)
    {
        if(rank != null && suit != null)
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

            jokerCheck();
        }
        else
        {
            throw new IllegalArgumentException("Rank: " + rank + " and/or Suit: " + suit + " is null. Invalid Card input arguments");
        }
    }

    public Card(Rank rank, Suit suit)
    {
        if(rank != null && suit != null)
        {
            this.rank = rank;
            this.suit = suit;
            facedown = true;
            faceup = false;

            jokerCheck();
        }
        else
        {
            throw new IllegalArgumentException("Rank: " + rank + " and/or Suit: " + suit + " is null. Invalid Card input arguments");
        }
    }

    public Card(Rank rank)
    {
        if(rank!=null)
        {
            this.rank = rank;
            this.suit = Card.Suit.NONE;
            facedown = true;
            faceup = false;

            jokerCheck();
        }
        else
        {
            throw new IllegalArgumentException("Card rank cannot be null. Invalid Card input arguments."); 
        }
    }

}
