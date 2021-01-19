package com.test.estudos.resources;

import com.test.estudos.model.User;
import com.test.estudos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crud")
public class UserController {

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
        return userRepository.findById(id)
                .map(record -> {
                    record.setIdade(user.getIdade());
                    record.setEmail(user.getEmail());
                    User updated = userRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/delete/{id}"})
    public ResponseEntity<?> delete(@PathVariable int id) {
        return userRepository.findById(id)
                .map(record -> {
                    userRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}

