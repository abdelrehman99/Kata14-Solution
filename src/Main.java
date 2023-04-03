public class Main {
    public static void main(String[] args)
    {
        bookReader book = new bookReader("Tom Swift Under the Milkwood.txt");
        book.readBook();
        book.trigram();
    }
}