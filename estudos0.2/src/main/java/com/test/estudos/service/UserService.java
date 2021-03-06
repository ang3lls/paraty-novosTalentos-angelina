package com.test.estudos.service;

import com.test.estudos.model.User;
import com.test.estudos.repository.UserRepository;
import com.test.estudos.services.exceptions.UserNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User salvar(User user){
        return userRepository.save(user);
    }


    public User buscarID(int id) {
        verificaExistencia(id);
        User user = userRepository.getOne(id);
        return user;
    }

    public List<User> buscarAll (){
        return userRepository.findAll();
    }

    public User alteracao(int id, User user) {
        User userAtualizado = verificaExistencia(id);
        userAtualizado.setIdade(user.getIdade());
        userAtualizado.setEmail(user.getEmail());
        return salvar(userAtualizado);
    }

    public void deletar(int id){
        verificaExistencia(id);
        userRepository.deleteById(id);
    }

    public User verificaExistencia(int id){
        try (User user = userRepository.getOne(id)){
            return user;
        }catch(Exception e){
            throw new UserNaoEncontradoException("O usuário não pôde ser encontrado");
        }
    }
}
