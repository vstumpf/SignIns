import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;


@SuppressWarnings("serial")
public class PersonList implements Serializable, Iterable<Person> {
   public ArrayList<Person> persons;
   public PersonList() {
      persons = new ArrayList<Person>();
   }
   
   public boolean hasName(String s) {
      for (Person p : persons) {
         if (p.getName().equalsIgnoreCase(s)) return true;
      }
      return false;
   }
   
   public boolean hasISO(long l) {
      for (Person p : persons) {
         if (p.getISO() == l) return true;
      }
      return false;
   }
   
   public Person getPerson(String s) {
      for (Person p : persons) {
         if (p.getName().equalsIgnoreCase(s)) return p;
      }
      return null;
   }
   
   public Person getPerson(long l) {
      for (Person p : persons) {
         if (p.getISO() == l) return p;
      }
      return null;
   }
   
   public Iterator<Person> iterator() {
      return persons.iterator();
   }
   
   public long getTempISO() {
      long ISO = 10000;
      for (Person p : persons) {
         if (ISO < p.getISO() && p.getISO() > 50000) {
            ISO = p.getISO();
         }
      }
      return ++ISO;
   }

   public void add(Person p) {
      persons.add(p);
   }
   
   public void printAll() {
      for (Person p : persons) {
         System.out.println(p);
      }
   }
   
}
