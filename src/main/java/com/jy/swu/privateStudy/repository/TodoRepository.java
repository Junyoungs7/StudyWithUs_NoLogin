package com.jy.swu.privateStudy.repository;

import com.jy.swu.privateStudy.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    Optional<Todo> findById(Long id);
}
