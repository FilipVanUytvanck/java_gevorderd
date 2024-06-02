package be.ucll.springboot.opdracht.soap;

import be.ucll.springboot.opdracht.service.TodoService;
import be.ucll.springboot.opdracht.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.time.LocalDate;

@Endpoint
public class TodoEndpoint {

    private static final String NAMESPACE_URI = "http://ucll.be/todo";

    @Autowired
    private TodoService todoService;

    @Autowired
    private UserService userService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AddTodoRequest")
    @ResponsePayload
    public AddTodoResponse addTodo(@RequestPayload AddTodoRequest request) {
        AddTodoResponse response = new AddTodoResponse();
        var user = userService.findByEmail(request.getEmail());

        if (user == null) {
            response.setStatus("User not found");
        } else {
            todoService.addTodoForUser(user, request.getTitle(), request.getComment(), LocalDate.parse(request.getExpiryDate()));
            response.setStatus("Todo added");
        }

        return response;
    }
}
