import java.util.*;

enum STATUS{
    AVAILABLE,
    BORROWED,
    RESERVED
}

class BookCopy{
    private STATUS s;
    private int CopyId;

    BookCopy(int id,STATUS status){
        this.s=status;
        this.CopyId=id;
    }

    public void setStatus(STATUS status){
        this.s=status;
    }

    public STATUS getStatus(){
        return s;
    }

    public int getCopyId(){
        return CopyId;
    }
}

class Book{
    private int bookId;
    private String title;
    private String author;
    private ArrayList<BookCopy> copies;

    Book(int bid,String t,String a){
        this.bookId=bid;
        this.title=t;
        this.author=a;
        this.copies=new ArrayList<>();
    }

    int getBookId(){
        return bookId;
    }

    String getBookTitle(){
        return title;
    }

    String getBookAuthor(){
        return author;
    }

    public ArrayList<BookCopy> getCopies(){
        return copies;
    }

    public void addCopy(BookCopy bc){
        copies.add(bc);
    }

    public BookCopy findAvailableCopy(){
        for(BookCopy x:copies){
            if(x.getStatus()==STATUS.AVAILABLE){
                return x;
            }
        }
        return null;
    }

    void show(){
        System.out.println(
            "Book id is.."+getBookId()+
            "\ntitle is "+getBookTitle()+
            "\nauthor is "+getBookAuthor()+
            "\nbook copies are..."+copies.size()
        );
    }
}

class MainLibrary{
    ArrayList<Book> books;

    MainLibrary(){
        books=new ArrayList<>();
    }

    void addBooks(Book b){
        books.add(b);
    }

    void getAllBooks(){
        for(Book x:books){
            System.out.println(
                "Book title is.."+x.getBookTitle()+
                "\nBook id is.."+x.getBookId()+
                "\nBook author is.."+x.getBookAuthor()
            );
        }
    }
}

class Member{
    int memId;
    String MemName;
    ArrayList<BookCopy> borrowed_books;
    ArrayList<Book> reserved_books;

    Member(int id,String name){
        this.memId=id;
        this.MemName=name;
        this.borrowed_books=new ArrayList<>();
        this.reserved_books=new ArrayList<>();
    }

    public int getMemId(){
        return memId;
    }

    public String getMemName(){
        return MemName;
    }

    void borrow_book(Book book){
        BookCopy bc=book.findAvailableCopy();

        if(bc!=null){
            bc.setStatus(STATUS.BORROWED);
            borrowed_books.add(bc);

            System.out.println(
                "Book Borrowed Successfully. Copy ID: "+
                bc.getCopyId()
            );
        }
        else{
            System.out.println(
                "No copy available. You can reserve this book."
            );
        }
    }

    void reserve_book(Book book){
        reserved_books.add(book);

        System.out.println(
            "Book reserved successfully."
        );
    }

    void returnBook(BookCopy bc){
        bc.setStatus(STATUS.AVAILABLE);
        borrowed_books.remove(bc);
    }
}

public class library{
    public static void main(String[] args){
        Book b1=new Book(1,"clean code","robert");
        Book b2=new Book(2,"Desgin Patterns","AIRTEL");

        BookCopy copy1=new BookCopy(101,STATUS.AVAILABLE);
        BookCopy copy2=new BookCopy(102,STATUS.AVAILABLE);
        BookCopy copy3=new BookCopy(103,STATUS.AVAILABLE);

        b1.addCopy(copy1);
        b1.addCopy(copy2);
        b2.addCopy(copy3);

        MainLibrary library=new MainLibrary();

        library.addBooks(b1);
        library.addBooks(b2);

        Member m1=new Member(1,"Shivam");

        library.getAllBooks();

        System.out.println("\nBefore Borrow:");

        for(BookCopy x:b1.getCopies()){
            System.out.println(
                "Copy ID: "+x.getCopyId()+
                " Status: "+x.getStatus()
            );
        }

        m1.borrow_book(b1);

        System.out.println("\nAfter Borrow:");

        for(BookCopy x:b1.getCopies()){
            System.out.println(
                "Copy ID: "+x.getCopyId()+
                " Status: "+x.getStatus()
            );
        }

        System.out.println(
            "\nShivam borrowed books: "+
            m1.borrowed_books.size()
        );
    }
}