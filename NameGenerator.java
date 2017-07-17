import java.util.*;
import java.io.*;

public final class NameGenerator{

  private NameGenerator(){}

  public static String getRandomName(){
    ArrayList<String> names = new ArrayList<String>();
    Random rand = new Random();
    Scanner s;
    try{
      s = new Scanner(new File("names.txt"));

      while (s.hasNext()){
          names.add(s.next());
      }
      s.close();
    }catch(Exception e){
      System.out.println("File not found");
    }
    int index = rand.nextInt(names.size());
    return names.get(index);

  }


}
