
import java.util.*;

public class CPU extends CribbagePlayer{

  public static final String[] names = {"Kyle","Emma","Megan","Christian","Bob","Karla","Tabatha","Robert"};

  public CPU(){
    super("placeholder");
    Random rand = new Random();
    this.name = this.names[rand.nextInt(this.names.length)];
  }

}
