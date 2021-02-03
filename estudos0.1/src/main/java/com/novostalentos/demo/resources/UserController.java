package com.novostalentos.demo.resources;

import com.novostalentos.demo.model.User;
import com.novostalentos.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crud")
public class UserController {

  /**
   * Toodos esses meu comentários é pra vc entender o que eu penso sobre... vc pode deletar depois tá!
   */

  @Autowired
    UserRepository userRepository;

    @PostMapping("/save")
    public ResponseEntity<User>  save(@RequestBody User user){
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity <User> findUserID(@PathVariable("id") int id){
        return new ResponseEntity(userRepository.findById(id), HttpStatus.OK);

    }

    @GetMapping("/find-all")
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable("id") int id, @RequestBody User user) {
      /**
       *  O mesmo se aplica aqui no update o assunto do DELETE. Não está errado, porém existe varias situações e diferentes formas de fazer.
       */
        return userRepository.findById(id)
                .map(record -> {
                    record.setIdade(user.getIdade());
                    record.setEmail(user.getEmail());
                    User updated = userRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }


  /**
   * Esse tipo de bloco de código só necessário quando o metodo tem uma regra muito complexa ai vc precisa explicar cada parâmetro
   * Na real o padrão clean code o nome tanto dos parametros quanto do metodo deve ser auto explicativo.
   * @param id id sequencia registrado no branco
   * @return returno do metodo para qualquer eventualidade: sucesso ou erro!
   */
  @DeleteMapping(path ={"/delete/{id}"})
    public ResponseEntity<?> delete(@PathVariable int id) {
    /**
     * Não tem problema a forma como está é mais uma garantia que irá existir o registro, porém não há necessidade de buscar
     * o registro no banco de dados para efetuar o delete. Se você já está chamando o metodo para deletar é porque já
     * existe a informação no banco.
     */
        return userRepository.findById(id)
                .map(record -> {
                    userRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
