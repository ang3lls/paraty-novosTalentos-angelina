package com.test.estudos.resources;

import com.test.estudos.model.User;
import com.test.estudos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/crud")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/save")
    public ResponseEntity<User>  save(@Valid @RequestBody User user) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.salvar(user));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity <User> findUserID(@PathVariable("id") int id) throws Exception {
        User user = userService.buscarID(id);
        return new ResponseEntity(userService.buscarID(id), HttpStatus.OK);
    }

    @GetMapping("/find-all")
    public List<User> findAll(){return userService.buscarAll();}

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable("id") int id, @RequestBody User user) throws Exception {
        return ResponseEntity.ok(userService.alteracao(id, user));
    }

    @DeleteMapping(path ={"/delete/{id}"})
    public ResponseEntity<Void> delete(@PathVariable int id){
        userService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

