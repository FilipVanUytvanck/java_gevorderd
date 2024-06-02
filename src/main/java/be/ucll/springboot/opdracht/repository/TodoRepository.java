package be.ucll.springboot.opdracht.repository;

import be.ucll.springboot.opdracht.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUserEmail(String email);
}
