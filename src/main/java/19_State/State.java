interface BookState {
    void borrow(Book book);
}


class AvailableState implements BookState {

    public void borrow(Book book) {

        System.out.println("Book borrowed successfully");

        // State changes:
        // AVAILABLE → BORROWED

        book.setState(new BorrowedState());
    }
}

class BorrowedState implements BookState {

    public void borrow(Book book) {

        System.out.println("Book is already borrowed");
    }
}

class Book {

    private BookState state;

    Book() {

        // Initial state
        state = new AvailableState();
    }

    void setState(BookState state) {

        // Change current state
        this.state = state;
    }
    
    void borrow() {

        // Delegate behavior to current state
        state.borrow(this);
    }

}

public class State {

    public static void main(String[] args) {

        Book book = new Book();

        // Current state = AVAILABLE
        book.borrow();

        // Current state = BORROWED
        book.borrow();
    }
}