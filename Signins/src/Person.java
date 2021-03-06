import java.io.Serializable;


public class Person implements Serializable{
   private static final long serialVersionUID = 1L;
   private long ISO;
   private int points = 0;
   private String name;
   
   public Person(String s, long l) {
      name = s;
      ISO = l;
   }
   
   public void updateISO(long l) {
      ISO = l;
   }
   
   public void addPoints(int p) {
      points += p;
   }
   
   public String getName() {
      return name;
   }
   
   public long getISO() {
      return ISO;
   }
   
   public int getPoints() {
      return points;
   }
   
   public String toString() {
      return name + ": " + points;
   }
   
}
