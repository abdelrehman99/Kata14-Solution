import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class bookReader {
    public String bookName;
    public File book;
    public HashMap <String ,ArrayList<String> > map;
    public ArrayList<String> words;
    public int bookSize;
    private int firstIdx, secondIdx;
    private static final int charsPerLine = 120;

    public bookReader(String fileName)
    {
        // Getting book
        bookName = fileName;
        book = new File("Books/" + fileName);
        map = new HashMap<String, ArrayList<String>>();
        words = new ArrayList<>();
    }

    // to check if this string is a word or not
    private boolean is_valid(String word)
    {
        int notLetters = 0;

        for (int i = 0; i < word.length(); i++)
        {
            if (!Character.isLetterOrDigit(word.charAt(i)))
                notLetters++;
        }

        // if not all characters are not letters nor numbers then this is a word.
        return (notLetters < word.length());
    }

    // function to generate two random words from the book to be trigramed
    private void generate_random()
    {
        Random rand = new Random();
        firstIdx = rand.nextInt(words.size() - 1);
        secondIdx = firstIdx + 1;
    }
    public void readBook() {
        try {
            // adding every 2 adjacent words to a hashmap
            Scanner myReader = new Scanner(book);

            // getting the first two words
            while (words.size() < 2 && myReader.hasNext())
            {
                String word = myReader.next();

                if (!is_valid(word))
                    continue;

                words.add(word);
            }

            while (myReader.hasNext()) {
                // adding the concatenation of the two adjacent words to the third
                String word = myReader.next();

                // if string is not a word ignore it
                if (!is_valid(word))
                    continue;

                // getting the last two words and combining them
                String key = words.get(words.size() - 2) + words.get(words.size() - 1);
                map.computeIfAbsent(key, k -> new ArrayList<String>());
                map.get(key).add(word);
                words.add(word);

            }

            // maximum size of words in a book
            bookSize = words.size();
            // closing file
            myReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading the book");
            e.printStackTrace();
        }
    }

    public void trigram()
    {
        try
        {
            String path = "Result/" + bookName;

            // creating file
            File createTrigrammedBook = new File(path);
            createTrigrammedBook.createNewFile();

            // opening file to write to it
            FileWriter trigramedBook = new FileWriter(path);

            // Everytime randomly choose two words and trigram it
            ArrayList<String> trigrams = new ArrayList<String>();
            if (words.size() < 2)
                trigrams = words;
            else
            {
                generate_random();
                trigrams.add(words.get(firstIdx));
                trigrams.add(words.get(secondIdx));
            }

            while (trigrams.size() < words.size())
            {
                // exiting when we can't build anymore
                if (map.get(trigrams.get(trigrams.size() - 2) + trigrams.get(trigrams.size() - 1)) == null)
                    break;

                Random rand = new Random();

                // getting a random value of the arraylist
                ArrayList <String> trigram = new ArrayList<>(map.get(trigrams.get(trigrams.size() - 2) + trigrams.get(trigrams.size() - 1)));
                String result = trigram.get(rand.nextInt(trigram.size()));

                trigrams.add(result);
            }
            // writing trigrams to the trigramed book with 120 letters per line
            StringBuilder line = new StringBuilder();
            for (String i: trigrams)
            {
                i += " ";

                // making sure that the line fits.
                if (line.length() + i.length() > charsPerLine)
                {
                    trigramedBook.write(line + "\n");
                    line = new StringBuilder();
                }

                line.append(i);
            }
            trigramedBook.write(line + "\n");
            trigramedBook.close();
        }
        catch (IOException e)
        {
            System.out.println("An error occurred while writing trigramed book");
            e.printStackTrace();
        }
    }
}
