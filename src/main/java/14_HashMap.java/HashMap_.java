import java.util.*;
// freq.put(x, freq.getOrDefault(x, 0) + 1);
//agar x ki value hei to vo return karo 0 ignore
//agar x ki value nahi hei to 0 return kro 

// Break karo:

// int oldFrequency = freq.getOrDefault(x, 0);

// int newFrequency = oldFrequency + 1;

// freq.put(x, newFrequency);

// Function	Kaam	Example
//  put()	insert/update	mp.put(1, "A");
//  get()	value nikalna	mp.get(1);
//  remove()	key delete	mp.remove(1);
//  containsKey()	key exist?	mp.containsKey(1);
// containsValue()	value exist?	mp.containsValue("A");
//  size()	number of entries	mp.size();
// isEmpty()	empty hai?	mp.isEmpty();
// clear()	pura map empty	mp.clear();

public class HashMap_{
    public static void main(String[] args) {

    HashMap<Integer,String>mp=new HashMap<>();
    mp.put(1,"shivam");
    mp.put(2,"rahul");
    mp.put(3,"Aman");
    mp.put(1,"SKSKSKSKSKSKSKSKSK_REPLACED");
    
    // System.out.println(mp);    

    // System.out.println(mp.get(2));

    // mp.remove(2);

    // System.out.println(mp);    

        
    System.out.println(mp.containsKey(2));
    System.out.println(mp.containsKey(10));

    System.out.println(mp.containsValue("rahul"));

    for (int key : mp.keySet()) {
        System.out.println(key);
    }

    for(String value:mp.values()){
        System.out.println(value);
    }

    for (Map.Entry<Integer, String> entry : mp.entrySet()) {

        System.out.println(
            entry.getKey() + " -> " + entry.getValue()
        );
    }
}
}