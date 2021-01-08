package com.novostalentos.demo.repository;

import com.novostalentos.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Integer>{
}
