import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class App {
    public static void setIO() throws FileNotFoundException {
        PrintStream consoleOUT = System.out;
        PrintStream fileOut = new PrintStream(new FileOutputStream("user.log", true));
        PrintStream dualOut = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                consoleOUT.write(b);
                fileOut.write(b);
            }
        }, true);
        System.setOut(dualOut);

        InputStream consoleIN = System.in;
        InputStream input = new InputStream() {
            @Override
            public int read() throws IOException {
                int b = consoleIN.read();
                if (b != -1) {
                    fileOut.write(b);
                }
                return b;
            }

            @Override
            public int read(byte[] b, int off, int len) throws IOException {
                int n = consoleIN.read(b, off, len);
                if (n > 0) {
                    fileOut.write(b, off, n);
                }
                return n;
            }

            @Override
            public void close() throws IOException {
                fileOut.close();
                consoleIN.close();
            }
        };
        System.setIn(input);
    }

    public static void main(String[] args) throws Exception {
        setIO();
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        System.out.println("\nWelcome to Library app\n");
        while (true) {
            switch (scanner.next()) {
                case "add":
                    handleAdd(scanner.next(), scanner.next(), scanner.nextInt(), scanner.next(), library);
                    break;
                case "remove":
                    handleRemove(scanner.nextInt(), library);
                    break;
                case "edit":
                    handleEdit(scanner.nextInt(), scanner.next(), scanner.next(), scanner.nextInt(), scanner.next(),
                            library);
                    break;
                case "search":
                    switch (scanner.next()) {
                        case "author":
                            handleSearchAuthor(scanner.next(), library);
                            break;
                        case "title":
                            handleSearchTitle(scanner.next(), library);
                            break;
                        default:
                            handleDefault();
                    }
                    break;
                case "read":
                    handleRead(scanner.next(), library);
                    break;
                case "write":
                    handleWrite(scanner.next(), library);
                    break;
                case "printall":
                    handlePrintAll(library);
                    break;
                case "--help":
                    handleHelp();
                    break;
                case "exit":
                    handleExit();
                    break;
                default:
                    handleDefault();

            }
        }

    }

    private static void handleAdd(String title, String author, int releaseDate, String status, Library library) {
        library.addBook(new Book(title, author, releaseDate, BookStatus.valueOf(status.toUpperCase())));
    }

    private static void handleRemove(int index, Library library) {
        library.removeBook(index);
    }

    private static void handleEdit(int index, String title, String author, int releaseDate, String status,
            Library library) {
        library.getBook(index).setTitle(title).setAuthor(author).setReleaseDate(releaseDate)
                .setStatus(BookStatus.valueOf(status.toUpperCase()));
    }

    private static void handleSearchTitle(String text, Library library) {
        var bookList = library.getBooks();
        for (var i : library.searchBooksByAuthorIndexes(text)) {
            System.out.println(i + ": " + bookList.get(i));
        }
    }

    private static void handleSearchAuthor(String text, Library library) {
        var bookList = library.getBooks();
        for (var i : library.searchBooksByTitleIndexes(text)) {
            System.out.println(i + ": " + bookList.get(i));
        }
    }

    private static void handleRead(String filename, Library library) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("File not found: " + filename);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            library.getBooks().clear();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 0);
                if (parts.length == 4) {
                    library.addBook(new Book(parts[0].trim(), parts[1].trim(), Integer.valueOf(parts[2].trim()),
                            BookStatus.valueOf(parts[3].trim())));
                }
            }
            System.out.println("Books loaded from " + filename);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static void handleWrite(String filename, Library library) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Book book : library.getBooks()) {
                bw.write(book.getTitle() + "," + book.getAuthor() + "," + book.getReleaseDate() + ","
                        + book.getStatus());
                bw.newLine();
            }
            System.out.println("Books saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    private static void handlePrintAll(Library library) {
        System.out.println(library);
    }

    private static void handleHelp() {
        System.out
                .println("add [Title] [Author] [Release Year] [Status: EXIST|BORROWED|BANNED]: add a book to library");
        System.out.println("remove [Index]: add a book to library");
        System.out.println(
                "edit [Index] [Title] [Author] [Release Year] [Status: EXIST|BORROWED|BANNED]: add a book to library");
        System.out.println("search author [text]: search books by its author name");
        System.out.println("search title [text]: search  bookd by its title");
        System.out.println("read [File Name]: read library books from a file");
        System.out.println("write [File Name]: write library books to a file");
        System.out.println("printall: print library books");
        System.out.println("exit: close the proigram");
        System.out.println();
    }

    private static void handleExit() {
        System.exit(0);
    }

    private static void handleDefault() {
        System.out.println("Unknown command: --help for getting help about commands");
    }
}
