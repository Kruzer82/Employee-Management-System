package com.jabbour.ems.ui.view.register;

import com.jabbour.ems.backend.entity.User;
import com.jabbour.ems.backend.repository.UserRepository;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterView extends Dialog {

    // fields
    TextField username = new TextField("Login");
    PasswordField password = new PasswordField("Password");
    Button r = new Button("Register");

    // models
    User user = new User();


    public RegisterView(UserRepository userRepository, PasswordEncoder encoder) {
        super();
        BeanValidationBinder<User> binder = new BeanValidationBinder<>(User.class);
        r.addClickListener(e -> {
            user = new User(username.getValue(), encoder.encode(password.getValue()));
            user = userRepository.save(user);

            if(user.getId() != null) {
                add(Notification.show("User created"));
            } else {
                add(Notification.show("User not created"));
            }
        });
        add(new VerticalLayout(username,password,r));

        binder.bindInstanceFields(this);

    }


}
