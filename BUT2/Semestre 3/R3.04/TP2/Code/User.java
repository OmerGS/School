public class User {
    private String name;
    
    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void borrowBook(Book book, Library library) {
        if (library.isBookAvailable(book)) {
            library.lendBook(book);
            System.out.println(name + " successfully borrowed the book: " + book.getTitle());
        } else {
            System.out.println("Sorry, " + name + ". The book is not available.");
        }
    }
}