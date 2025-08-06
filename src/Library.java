import java.util.LinkedList;
import java.util.Comparator;
import java.util.List;

public class Library {
    private List<Book> books;

    public Library() {
        books = new LinkedList<>();
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public void removeBook(Book book) {
        this.books.remove(book);
    }

    public void printBooks() {
        System.out.println(this.toString());
    }

    public List<Book> searchBooksByTitle(String title) {
        title = title.toLowerCase();
        List<Book> books = new LinkedList<>();
        for (Book book : this.books) {
            if (book.getTitle().toLowerCase().contains(title))
                books.add(book);

        }
        return books;
    }

    public List<Book> searchBooksByAuthor(String author) {
        author = author.toLowerCase();
        List<Book> books = new LinkedList<>();
        for (Book book : this.books) {
            if (book.getAuthor().toLowerCase().contains(author))
                books.add(book);

        }
        return books;
    }

    public void sortBooksByYear() {
        books.sort(Comparator.comparingInt(Book::getReleaseDate));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Book book : books) {
            builder.append(book);
            builder.append("\n");
        }
        return builder.toString();
    }
}