import java.util.Scanner;
import java.util.ArrayList;

public class CardGameMain {

    //Define all card games for user input selection
    public static ArrayList<String> cardGamesList = new ArrayList<String>();

    public static void setListOfCardGames()
    {
        //set list of card games
        cardGamesList.add("go fish");
        cardGamesList.add("rummy");
        cardGamesList.add("blackjack");
    }

    public static void printAllCardGames()
    {
        //Print out all card games
        for(int i = 0; i< cardGamesList.size(); i++)
        {
            System.out.println(i + " : " + cardGamesList.get(i));
        }
        System.out.print("\n");
    }

    public static void main(String[] args) 
    {
        setListOfCardGames();

        boolean validInput = false;

        Scanner inputCardGameSelection = new Scanner(System.in);

        String cardGameSelection = "";

        while(!validInput)
        {
            //Ask user for input for card game selection
            
            System.out.println("Which card game do you want to play? Please input a String or a number: \n");
            printAllCardGames();

            if(inputCardGameSelection.hasNextInt())
            {
                int inputCardGameSelectionInt = inputCardGameSelection.nextInt();
                try
                {
                    //System.out.println("You have chosen to play : " + cardGamesList.get(inputCardGameSelectionInt));
                    cardGameSelection = cardGamesList.get(inputCardGameSelectionInt);
                    validInput = true;
                    break;
                }
                catch(IndexOutOfBoundsException e)
                {
                    System.out.println("Number is not in Card Games List. Please choose a number in the card games list. \n");
                    continue;
                }
            }

            if(inputCardGameSelection.hasNextLine())
            {
                String inputCardGameSelectionString = inputCardGameSelection.nextLine().toLowerCase().trim();
                if(cardGamesList.contains(inputCardGameSelectionString))
                {
                    //System.out.println("You have chosen to play : " + inputCardGameSelectionString);
                    cardGameSelection = inputCardGameSelectionString;
                    validInput = true;
                    break;
                }
                else
                {
                    System.out.println("Game is not in Card Games List. Please choose a game in the card games list. \n");
                    continue;
                }
            }
        } //end check for validInput

        System.out.println("You have chosen to play : " + cardGameSelection);

        inputCardGameSelection.close();
    } //end main
}