

public class User extends CribbagePlayer{
  private String realName;

  public User(String name){
    super(name + " (You)");
    this.realName = name;
  }

  public String getRealName(){
    return this.realName;
  }

}
