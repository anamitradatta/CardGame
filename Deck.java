import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    
    private ArrayList<Card> cards;

    public static ArrayList<Card> defaultCardsList()
    {
        ArrayList<Card> tempCardsList = new ArrayList<Card>();
        for(Card.Suit suit: Card.Suit.values())
        {
            for(Card.Rank rank: Card.Rank.values())
            {
                if(rank == Card.Rank.JOKER)
                {
                    continue;
                }
                else if(suit == Card.Suit.NONE)
                {
                    continue;
                }
                else
                {
                    tempCardsList.add(new Card(rank,suit));
                }
            }
        }
        return tempCardsList;
    }

    public boolean isInDeck(Card card)
    {
        for(Card currentCard: cards)
        {
            if(card.equals(currentCard))
            {
                return true;
            }
        }
        return false;
    }

    public void addCard(Card card)
    {
        if(card.rank() == Card.Rank.JOKER)
        {
            if(card.suit() == Card.Suit.NONE)
            {
                cards.add(card);
            }
            else
            {
                throw new IllegalArgumentException("JOKER needs a NONE suit type");
            }
        }
        else
        {
            cards.add(card);
        }
        
    }

    public Card drawCard()
    {
        if(cards.size() == 0)
        {
            throw new IndexOutOfBoundsException("Deck is empty, cannot draw card");
        }
        else
        {
            Card topCard = cards.get(0);
            cards.remove(0);
            return topCard;
        }
    }

    public Card removeCard(Card cardToRemove)
    {
        for(Card currentCard: cards)
        {
            if(cardToRemove.equals(currentCard))
            {
                cards.remove(currentCard);
                return currentCard;
            }
        }
        throw new NullPointerException("Could not find card " + cardToRemove.toString() + " in the deck");
    }

    public Card removeCard(int index)
    {
        try
        {
            return cards.remove(index);
        }
        catch(IndexOutOfBoundsException e)
        {
            throw new IndexOutOfBoundsException("Cannot removed specified card index: " + index);
        }
    }

    public void shuffle()
    {
        Collections.shuffle(cards, new Random());
    }

    public Deck() //sets default deck of 52 cards
    {
        cards = defaultCardsList();
    }

    public Deck(ArrayList<Card> cardsList)
    {
        cards = cardsList;
    }

    public static void main(String[] args) 
    {
        Deck deck = new Deck();
        int count = 0;

        /*
        //deck.addCard(new Card(Card.Rank.JOKER));
        for(Card card: deck.cards)
        {
            System.out.println(card.toString());
            count++;
        }

        System.out.println(count);

        System.out.println("------");
        */

        deck.shuffle();

        for(Card card: deck.cards)
        {
            System.out.println(card.toString());
        }

        
        Card cardRemoved = deck.removeCard(new Card(Card.Rank.KING,Card.Suit.CLOVES));
        System.out.println(cardRemoved.toString() + " has been removed");

        for(Card card: deck.cards)
        {
            System.out.println(card.toString());
        }

        Card cardRemoved2 = deck.removeCard(82);
        

        /*
        Card drawnCard = deck.drawCard();

        System.out.println("Card Drawn = " + drawnCard.toString());

        for(Card card: deck.cards)
        {
            System.out.println(card.toString());
        }
        */
    }

}
