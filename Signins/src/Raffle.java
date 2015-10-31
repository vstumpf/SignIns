import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Raffle {
   public static final String FILENAME = "points4.txt";
   public static PersonList persons;
   public static int maxPoints = 0;
   
   public static ArrayList<String> randlist;
   
   public static void onStartup() throws IOException, ClassNotFoundException {
      randlist = new ArrayList<String>();
      File file = new File(FILENAME);
      boolean exist = true;
      try {
         if (file.createNewFile()) exist = false;
      } catch (IOException e) {}
      
      if (exist) {
         FileInputStream fis = new FileInputStream(file);
         try {
            ObjectInputStream ois = new ObjectInputStream(fis);
            persons = (PersonList)ois.readObject();
            ois.close();
         } catch (EOFException e) {
            System.out.println("no list in file");
            persons = new PersonList();
         } catch (InvalidClassException e) {
            System.out.println("Class is invalid, undo edits.");
            fis.close();
            System.exit(1);
         }
         fis.close();
      } else {
         persons = new PersonList();
      }
   }
   
   public static void fillList () {
      for (Person p : persons) {
         int points = p.getPoints();
         for (int i = 0; i < points; i++) {
            randlist.add(p.getName());
            maxPoints++;
            System.out.println(p.getName() + " " + i + " " + randlist.size());
         }
      }
   }
   
   public static String getRand() {
      String name = null;
      Random rand = new Random();
      int index = (int) (rand.nextDouble() * maxPoints);
      name = randlist.get(index);
      System.out.print(index + "   ");
      return name;
   }
   
   public static void main(String args[]) {
      try {
         onStartup();
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
      fillList();
      
      Scanner sc = new Scanner(System.in);
      String s = null;
      while (((s = sc.nextLine()) != null) && (!s.equalsIgnoreCase("q") && !s.equalsIgnoreCase("quit"))) {
         if (!s.equalsIgnoreCase("next"))
            continue;
         System.out.println("Generating random name...");
         System.out.println(getRand());
      }
      sc.close();
   }
   
}
