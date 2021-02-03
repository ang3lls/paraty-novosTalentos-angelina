package templates;

import com.test.estudos.model.User;

import java.util.Arrays;
import java.util.List;

public class UserBuilder{

  public static User getUser(){
    return new User( 1, "Ana", 25, "ana@cadmus.com.br");
  }

  public static List<User> getUsers(){
    return Arrays.asList(
        new User( 3, "rafaela", 20, "rafaela@cadmus.com.br"),
        new User(4, "Rafa", 20, "rafa@cadmus.com.br"));
  }
}