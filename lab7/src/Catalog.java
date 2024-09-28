import java.util.*;

class Book {
    private String name;
    private int pages;

    public Book(String name, int pages) {
        this.name = name;
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public int getPages() {
        return pages;
    }
}

public class Catalog {
    private Map<String, Book> booksByName;
    private TreeSet<Book> booksByPages;

    public Catalog() {
        booksByName = new HashMap<>();
        booksByPages = new TreeSet<>(Comparator.comparing(Book::getPages));
    }

    public void addBook(Book book) {
        booksByName.put(book.getName(), book);
        booksByPages.add(book);
    }

    public Book searchByName(String name) {
        return booksByName.get(name);
    }

    public Book searchByPages(int pages) {
        Book searchBook = new Book("", pages);
        Book foundBook = booksByPages.floor(searchBook);
        return (foundBook != null && foundBook.getPages() == pages) ? foundBook : null;
    }

    public static void main(String[] args) {
        Catalog library = new Catalog();
        Book book1 = new Book("A", 30);
        Book book2 = new Book("B", 25);
        Book book3 = new Book("C", 40);
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Хайх номны нэр : ");
        String bookName = scanner.nextLine();
        Book searchedBook = library.searchByName(bookName);
        if (searchedBook != null) {
            System.out.println(searchedBook.getName() + " ном " + searchedBook.getPages() + " хуудастай.");
        } else {
            System.out.println("Олдсонгүй.");
        }

        System.out.print("Хайх номны хуудас : ");
        int pagesToSearch = scanner.nextInt();
        searchedBook = library.searchByPages(pagesToSearch);
        if (searchedBook != null) {
            System.out.println(searchedBook.getName() + " ном " + searchedBook.getPages() + " хуудастай.");
        } else {
            System.out.println("Олдсонгүй.");
        }
        scanner.close();
    }
}
