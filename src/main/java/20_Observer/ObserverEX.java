import java.util.ArrayList;

interface Observer {
    void update();
}

class Student implements Observer {
    private String name;
    Student(String name){
        this.name=name;
    }
    public void update() {
        System.out.println("Book is now available!");
    }
}

class Book{
    private ArrayList<Observer>observers=new ArrayList<>();

    private String title;

    Book(String title){
        this.title=title;
    }

    void addObserver(Observer observer){
        observers.add(observer);
    }

    void removeObserver(Observer observer){
        observers.remove(observer);
    }

    void notifyObservers(){
        for(Observer observer:observers){
            observer.update();
        }
    }

    void makeAvailabe(){
        System.out.println("book "+title+" is now available");
        notifyObservers();
    }
}


public class ObserverEX{
    public static void main(String[]args){
        Book book = new Book("Clean Code");

        Student s1 = new Student("Shivam");
        Student s2 = new Student("Rahul");
        Student s3 = new Student("Amit");

        // Students subscribe to the book
        book.addObserver(s1);
        book.addObserver(s2);
        book.addObserver(s3);

        // Book becomes available
        book.makeAvailabe();

        // Rahul no longer wants notification
        book.removeObserver(s2);

        // Book becomes available again
        book.makeAvailabe();
    }
}