// ArrayList<Integer> v = new ArrayList<>();

// v.add(10);              // push_back
// v.add(1, 50);           // insert at index

// v.get(1);               // access
// v.set(1, 100);          // update

// v.remove(1);            // remove by index
// v.remove(Integer.valueOf(20)); // remove by value

// v.size();               // size
// v.isEmpty();            // empty?
// v.contains(20);         // search
// v.indexOf(20);          // first index
// v.clear();              // remove everything
import java.util.*;
public class ArrayList_EX{
    public static void main(String[]args){
        ArrayList<Integer>v=new ArrayList<>();
        v.add(10);
        v.add(20);
        v.add(30);
        System.out.println(v);

        System.out.println(v.get(2));

        v.set(1,50);

        System.out.println(v);

        System.out.println(v.size());

        v.remove(v.size()-1);

        System.out.println(v);

        for(int x:v){
            System.out.println(x);
        }

        System.out.println(v.contains(100));

        System.out.println(v.indexOf(10));
    }
}