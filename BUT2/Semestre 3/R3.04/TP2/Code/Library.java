import java.util.HashMap;
public class Library {
    private HashMap<Book, Integer> inventory = new HashMap<>();
    
    public void addBook(Book book, int quantity) {
        inventory.put(book, inventory.getOrDefault(book, 0) + quantity);
    }
    
    public boolean isBookAvailable(Book book) {
        return inventory.getOrDefault(book, 0) > 0;
    }
 
    public void lendBook(Book book) {
        if (isBookAvailable(book)) {
            inventory.put(book, inventory.get(book) - 1);
        }
    }
}