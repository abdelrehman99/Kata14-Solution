import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        int option;
        String[] options = {"Tom Swift Under the Milkwood", "The boys in white", "The awakening by Henry Bordeaux", "Middlemarch by George Eliot"};
        System.out.println("Hi, Welcome to my project." + "\n" + "This is a small project to trigram a book");
        while (true)
        {
            for (int i = 0; i < options.length; i++)
            {
                System.out.println("Type " + (i + 1) + " to trigram " + options[i]);
            }
            System.out.println("Type 0 to exit the program");
            option = input.nextInt();
            if (option > options.length || option < 0)
            {
                System.out.println("Invalid input please try again");
                continue;
            }
            if (option == 0)
            {
                System.out.println("Thank you for your time, and I hope you enjoyed this experience.");
            }
            else
            {
                bookReader book = new bookReader(options[option - 1] + ".txt");
                book.readBook();
                book.trigram();
            }
            break;
        }
        if (option != 0)
            System.out.println("You can see the results in the results folder with the name of the book you typed.");
        input.close();
    }
}