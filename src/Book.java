public class Book {
    private String title;
    private String author;
    private int releaseDate;
    private BookStatus status;

    public Book(String title, String author, int releaseDate, BookStatus status) {
        this.title = title;
        this.author = author;
        this.releaseDate = releaseDate;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Book [title: " + title +
                ", author: " + author +
                ", releaseDate: " + releaseDate +
                ", status: " + status + "]";
    }
}