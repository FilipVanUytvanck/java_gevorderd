package be.ucll.springboot.opdracht.service;

import be.ucll.springboot.opdracht.model.Todo;
import be.ucll.springboot.opdracht.model.User;
import be.ucll.springboot.opdracht.repository.TodoRepository;
import be.ucll.springboot.opdracht.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Todo> getTodosForCurrentUser(String email) {
        return todoRepository.findByUserEmail(email);
    }

    public void addTodoForUser(User user, String title, String comment, LocalDate expiryDate) {
        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setComment(comment);
        todo.setStatus(false);
        todo.setExpiryDate(expiryDate);
        todo.setUser(user);
        todoRepository.save(todo);
    }
}
