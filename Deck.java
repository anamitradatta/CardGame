import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    
    private ArrayList<Card> cards;
    private String deckName;

    public void setName(String name)
    {
        deckName = name;
    }

    public String getName()
    {
        return deckName;
    }

    public ArrayList<Card> cards()
    {
        return cards;
    }

    public void printAllCards()
    {
        for(Card card: cards)
        {
            System.out.println(card.toString());
        }
    }

    public static void printAllCards(ArrayList<Card> deck)
    {
        for(Card card: deck)
        {
            System.out.println(card.toString());
        }
    }

    public int getNumberOfCards()
    {
        return cards.size();
    }

    public void printNumberOfCards()
    {
        System.out.println(cards.size());
    }

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
        if(card == null)
        {
            throw new IllegalArgumentException("Cannot set card to null in deck"); 
        }
        else
        {
            cards.add(card);
        }
    }

    public void addCard(Card.Rank rank, Card.Suit suit)
    {
        if(rank != null && suit != null)
        {
            cards.add(new Card(rank,suit));
        }
        else
        {
            throw new IllegalArgumentException("Rank: " + rank + " and/or Suit: " + suit + " is null. Invalid Card input arguments");
        }
    }

    public void addCard(Card.Rank rank, Card.Suit suit, boolean fu, boolean fd)
    {
        if(rank != null && suit != null)
        {
            cards.add(new Card(rank,suit,fu,fd));
        }
        else
        {
            throw new IllegalArgumentException("Rank: " + rank + " and/or Suit: " + suit + " is null. Invalid Card input arguments");
        }
    }

    public void addCards(ArrayList<Card> cardsToAdd)
    {
        if(cardsToAdd.size() == 0)
        {
            throw new NullPointerException("Input card list is empty");
        }
        for(Card card: cardsToAdd)
        {
            cards.add(card);
        }
        return;
    }

    public void stackDeck(Deck d)
    {
        if(d.cards().size() == 0)
        {
            throw new NullPointerException("Input deck: + " + d.getName() + " is empty");
        }
        else
        {
            addCards(d.cards);
            d.removeAllCards();
        }
    }

    public void stackDecks(ArrayList<Deck> decks)
    {
        for(Deck d: decks)
        {
            if(d.cards().size() == 0)
            {
                throw new NullPointerException("Input deck: + " + d.getName() + " is empty");
            }
            else
            {
                addCards(d.cards);
                d.removeAllCards();
            }
        }   
    }

    public Deck combineDecks(Deck d)
    {
        Deck newDeck = emptyDeck();
        newDeck.addCards(cards);
        newDeck.addCards(d.cards);
        return newDeck;
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

    public ArrayList<Card> drawCards(int num)
    {
        if(num<1)
        {
            throw new IllegalArgumentException("Number of cards to draw input cannot be less than 1");
        }

        if(cards.size() == 0)
        {
            throw new IndexOutOfBoundsException("Deck is empty, cannot draw cards");
        }

        if(cards.size() < num)
        {
            throw new IndexOutOfBoundsException("Deck size is only " + cards.size() + ", cannot draw " + String.valueOf(num) + " cards");
        }

        ArrayList<Card> cardsDrawn = new ArrayList<Card>();

        for(int i = 0; i < num; i++)
        {
            cardsDrawn.add(cards.get(0));
            cards.remove(0);
        }

        return cardsDrawn;
    }

    public Card removeCard(Card cardToRemove)
    {
        Card.jokerCheck(cardToRemove);
        
        if(cards.size() == 0)
        {
            throw new IndexOutOfBoundsException("Deck is empty, cannot remove card");
        }

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

    public Card removeCard(Card.Rank rank, Card.Suit suit)
    {
        Card.jokerCheck(rank, suit);

        if(cards.size() == 0)
        {
            throw new IndexOutOfBoundsException("Deck is empty, cannot draw cards");
        }

        for(Card currentCard: cards)
        {
            if(rank == currentCard.rank() && suit == currentCard.suit())
            {
                cards.remove(currentCard);
                return currentCard;
            }
        }
        
        throw new NullPointerException("Could not find card with rank " +  rank.name() + " and suit " + suit.name() + " in the deck");
    }

    public Card removeCard(int index)
    {
        if(cards.size() == 0)
        {
            throw new IndexOutOfBoundsException("Deck is empty, cannot draw cards");
        }

        try
        {
            return cards.remove(index);
        }
        catch(IndexOutOfBoundsException e)
        {
            throw new IndexOutOfBoundsException("Cannot removed specified card index: " + index);
        }
    }

    public ArrayList<Card> removeCards(int[] indexes)
    {
        if(cards.size() == 0)
        {
            throw new NullPointerException("Deck size is empty, cards cannot be removed");
        }

        if(indexes.length > cards.size())
        {
            throw new IndexOutOfBoundsException("Number of cards: " + indexes.length +  " to remove was greater than the deck size: " + cards.size());
        }
        ArrayList<Card> cardsRemoved = new ArrayList<Card>();
        for(int i: indexes)
        {
            try
            {
                cardsRemoved.add(cards.get(i));
            }
            catch(IndexOutOfBoundsException e)
            {
                throw new IndexOutOfBoundsException("Index " + i + " was out of bounds of the deck size: " + cards.size());
            }
        }
        for(int j=0;j<indexes.length;j++)
        {
            cards.remove(cardsRemoved.get(j));
        }
        return cardsRemoved;
    }

    public ArrayList<Card> removeCards(ArrayList<Card> cardsToRemove)
    {
        if(cards.size() == 0)
        {
            throw new IndexOutOfBoundsException("Deck is empty, cannot draw cards");
        }

        if(cardsToRemove.size() > cards.size())
        {
            throw new IndexOutOfBoundsException("Number of cards "+ cardsToRemove.size() + " to remove was greater than the deck size: " + cards.size());
        }
        ArrayList<Card> cardsRemoved = new ArrayList<Card>();

        for(Card card: cardsToRemove)
        {
            Card.jokerCheck(card);
            if(isInDeck(card))
            {
                cardsRemoved.add(card);
                removeCard(card);
            }
            else
            {
                throw new NullPointerException("Card: "+ card.toString() + " does not exist in deck");
            }
        }
        return cardsRemoved;
    }

    public ArrayList<Card> removeAllCards()
    {
        ArrayList<Card> allCardsInDeck = new ArrayList<Card>(cards);
        cards.clear();
        return allCardsInDeck;
    }

    public void shuffle()
    {
        Collections.shuffle(cards, new Random());
    }

    public static Deck emptyDeck()
    {
        Deck emptyDeck = new Deck();
        emptyDeck.removeAllCards();
        return emptyDeck;
    }

    public Deck() //sets default deck of 52 cards
    {
        cards = defaultCardsList();
        deckName = "";
    }

    public Deck(String name) //sets default deck of 52 cards
    {
        cards = defaultCardsList();
        deckName = name;
    }

    public Deck(ArrayList<Card> cardsList)
    {
        cards = cardsList;
        deckName = "";
    }

    public Deck(ArrayList<Card> cardsList, String name)
    {
        cards = cardsList;
        deckName = name;
    }

    public static void main(String[] args) 
    {
    }

}
