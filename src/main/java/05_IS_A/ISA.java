// Without inheritance:

// Dog → eat()
// Cat → eat()

// same code baar-baar likhna padta.

// With inheritance:

// Animal
//   |
//  eat()
//   |
//   ├── Dog
//   └── Cat

Code reuse + common structure.
class Animal{
    private String name;
    Animal(String name){
        this.name=name;
    }
    void eat(){
        System.out.println(name+" is eating..");
    }
}

class Dog extends Animal{
    Dog(String name){
        super(name);
    }
    void bark(){
        System.out.println("Dog is barking...");
    }
}
class Cat extends Animal{
    Cat(String name){
        super(name);
    }
    void meow(){
        System.out.println("Cat is mew mew...");
    }
}
class ISA{
    public static void main(String[]args){
        Dog d=new Dog("Toomyy");
        d.eat();
        d.bark();
        Cat c=new Cat("kitty");
        c.eat();
        c.meow();

    }
}