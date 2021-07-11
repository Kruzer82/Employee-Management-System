package com.jabbour.ems.ui.view.register;

import com.jabbour.ems.backend.entity.User;
import com.jabbour.ems.backend.repository.UserRepository;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterView extends Composite<Div> {

    public RegisterView(UserRepository userRepository, PasswordEncoder encoder) {

        TextField l = new TextField("Login");
        PasswordField p = new PasswordField("Password");
        Button r = new Button("Register");
        r.addClickListener(e -> {
            User user = new User(l.getValue(), encoder.encode(p.getValue()));
            User save = userRepository.save(user);
            if(user.getId() != null) {

            }
        });
        getContent().add(new VerticalLayout(l,p,r));
    }


}
