package be.ucll.springboot.opdracht.ui;

import be.ucll.springboot.opdracht.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("register")
public class RegisterView extends VerticalLayout {

    private final UserService userService;

    public RegisterView(UserService userService) {
        this.userService = userService;

        TextField email = new TextField("Email");
        TextField firstName = new TextField("First Name");
        TextField lastName = new TextField("Last Name");
        PasswordField password = new PasswordField("Password");
        Button registerButton = new Button("Register", event -> {
            userService.registerUser(email.getValue(), firstName.getValue(), lastName.getValue(), password.getValue());
            Notification.show("User registered");
        });

        add(email, firstName, lastName, password, registerButton);
    }
}
