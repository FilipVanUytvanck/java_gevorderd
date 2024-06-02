package be.ucll.springboot.opdracht.ui;

import be.ucll.springboot.opdracht.model.Todo;
import be.ucll.springboot.opdracht.service.TodoService;
import be.ucll.springboot.opdracht.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.router.Route;

@Route("")
public class TodoView extends VerticalLayout {

    private final TodoService todoService;
    private final UserService userService;

    public TodoView(TodoService todoService, UserService userService) {
        this.todoService = todoService;
        this.userService = userService;

        Grid<Todo> grid = new Grid<>(Todo.class);
        grid.setColumns("title", "comment", "status", "expiryDate");
        // Replace with a valid user email for testing
        grid.setItems(todoService.getTodosForCurrentUser("test@example.com"));

        TextField title = new TextField("Title");
        TextArea comment = new TextArea("Comment");
        DatePicker expiryDate = new DatePicker("Expiry Date");
        Button addButton = new Button("Add", event -> {
            // Replace with a valid user email for testing
            var user = userService.findByEmail("test@example.com");
            if (user != null) {
                todoService.addTodoForUser(user, title.getValue(), comment.getValue(), expiryDate.getValue());
                grid.setItems(todoService.getTodosForCurrentUser("test@example.com"));
            } else {
                Notification.show("User not found");
            }
        });

        add(grid, title, comment, expiryDate, addButton);
    }
}
