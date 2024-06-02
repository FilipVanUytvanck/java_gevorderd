package be.ucll.springboot.opdracht.jms;

import be.ucll.springboot.opdracht.model.Todo;
import be.ucll.springboot.opdracht.service.TodoService;
import be.ucll.springboot.opdracht.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TodoListener {

    @Autowired
    private TodoService todoService;

    @Autowired
    private UserService userService;

    @JmsListener(destination = "todoQueue")
    public void receiveMessage(String message) {
        String[] parts = message.split(",");
        String email = parts[0];
        String title = parts[1];
        String comment = parts[2];
        LocalDate expiryDate = LocalDate.parse(parts[3]);

        var user = userService.findByEmail(email);
        if (user != null) {
            todoService.addTodoForUser(user, title, comment, expiryDate);
        }
    }
}
