public class App {
    public static void main(String[] args) throws Exception {
        App.test();
    }

    public static void test() {
        Library library = new Library();

        library.addBook(new Book("The Silent Star", "Ali Rezaei", 1999, BookStatus.EXIST));
        library.addBook(new Book("Ocean's Tale", "Mina Jafari", 2005, BookStatus.BORROWED));
        library.addBook(new Book("Desert Moon", "Sara Karimi", 2012, BookStatus.BANNED));
        library.addBook(new Book("Mountain Echo", "Hossein Asadi", 1995, BookStatus.EXIST));
        library.addBook(new Book("Code Unknown", "Reza Mohammadi", 2020, BookStatus.BORROWED));
        library.addBook(new Book("Light and Shadow", "Narges Zarei", 2010, BookStatus.EXIST));
        library.addBook(new Book("Final Algorithm", "Amir Jalali", 2018, BookStatus.BANNED));
        library.addBook(new Book("Broken Lines", "Leila Hashemi", 2003, BookStatus.EXIST));
        library.addBook(new Book("Digital Maze", "Mohammad Farhadi", 2015, BookStatus.BORROWED));
        library.addBook(new Book("Dream Weaver", "Fatemeh Azizi", 2022, BookStatus.EXIST));

        library.printBooks();
        library.sortBooksByYear();
        library.printBooks();
        System.out.println(library.searchBooksByAuthor("hos"));
        System.out.println(library.searchBooksByTitle("mo"));

        System.out.println();
        library.removeBook(library.searchBooksByAuthor("hos").get(0));
        library.printBooks();

        System.out.println();
        library.searchBooksByTitle("mo").get(0).setTitle("new title");
        library.printBooks();
    }
}
