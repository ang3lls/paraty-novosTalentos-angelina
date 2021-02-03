package Templates;

import com.test.estudos.model.User;

public class UserBuilder{

  /**
   * Essa é a classe que te falei para ser template.
   * tudo que vc precisar sobre a classe User vc cria aqui dentro
   * forma mais limpa de escrever pois se vc precisar alterar algo ta tudo aqui dentro.
   */

  public static User getUser(){
    return new User( 1, "Ana", 25, "ana@cadmus.com.br");
  }

  public static List<User> getUsers(){
    return Arrays.asList(
        new User( 3, "rafaela", 20, "rafaela@cadmus.com.br"),
        new User(4, "Rafa", 20, "rafa@cadmus.com.br"));
  }
}