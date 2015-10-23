import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;


public class SignIns {

   
   public static final String FILENAME = "points.txt";
   public static PersonList persons;
   public static void onStartup() throws IOException, ClassNotFoundException {
      
      File file = new File(FILENAME);
      boolean exist = true;
      try {
         if (file.createNewFile()) exist = true;
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
         }
         fis.close();
      } else {
         persons = new PersonList();
      }
   }
   
   public static void onClose() throws IOException {
      File file = new File(FILENAME);
      FileOutputStream fos = new FileOutputStream(file);
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(persons);
      oos.close();
      fos.close();
   }
   
   public static void main(String[] args) {
      try {
         onStartup();
      } catch (Exception e) {
         System.out.println("exception on startup: " + e);
         e.printStackTrace();
      }
      scanSignins();
      try {
         onClose();
      } catch (IOException e) {
         System.out.println("exception on close: " + e);
         e.printStackTrace();
      }
   }
   
   public static void main2(String[] args) {
      String s = "%2015001341086^STUDENT?;6278561535502085?";
      int i = whichInput(s);
      System.out.println(i);
      System.out.println(whichInput("Vincent Stumpf"));
      System.out.println(Long.parseLong(s.split("\\;|\\?")[2]));

            
   }
   
   public static int scanPoints(Scanner sc) {
      System.out.println("HOW MANY POINTS IS THIS EVENT WORTH?");
      while (!sc.hasNextInt()) {
         System.out.println("Not an integer. Enter an integer");
         sc.nextLine();
      }
      int p = sc.nextInt();
      return p;
   }
   
   public static int whichInput(String s) {
      if (s.matches("^[\\w[\\s]]*$"))
         return 1; // NAME
      if (s.matches("^%.*\\?\\;\\d*\\??"))
         return 2; // ID
      return -1; //WRONG INPUT
   }
   
   public static void scanSignins() {
      Scanner sc = new Scanner(System.in);
      int points = scanPoints(sc);
      sc.nextLine();
      System.out.println("You can now scan cards OR type names");
      String temp;
      long ISO;
      String name;
      Person p = null;
      while (!(temp = sc.nextLine()).equalsIgnoreCase("q") && !temp.equalsIgnoreCase("quit")) {
         int type = whichInput(temp);
         if (type < 0) {
            // ERROR
            System.out.println("Bad Read, try again");
            continue;
         }
         
         if (type == 2) {
            ISO = Long.parseLong(temp.split("\\;|\\?")[2]);
            if (persons.hasISO(ISO)) {
               p = persons.getPerson(ISO);
            } else {
               System.out.println("New User Found!");
               System.out.println("Type in your name (Ex. Vincent Stumpf)");
               name = sc.nextLine();
               if (persons.hasName(name)) {
                  System.out.println("ISO Updated");
                  p = persons.getPerson(name);
                  p.updateISO(ISO);
               } else {
                  p = new Person(name, ISO);
                  persons.add(p);
               }
            }
         } else if (type == 1) {
            name = temp;
            if (persons.hasName(name)) {
               p = persons.getPerson(name);
            } else {
               System.out.println("New User Found!");
               p = new Person(name, persons.getTempISO());
               persons.add(p);
            }
         }
         
         p.addPoints(points);
         System.out.println("Thanks " + p.getName() + "! You now have " + p.getPoints() + " points!");
      }
         
      System.out.println("BYE!!");
   }
}
