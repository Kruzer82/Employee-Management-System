package com.jabbour.ems.ui.view.login;

import com.jabbour.ems.backend.entity.User;
import com.jabbour.ems.backend.repository.UserRepository;
import com.jabbour.ems.ui.view.register.RegisterView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("serial")
@Route("login")
@PageTitle("Login | Slick EMS")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {
	
	private LoginForm login = new LoginForm();
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder encoder;

	public LoginView() {
		
		Notification notification = new Notification();

		addClassName("login-view");
		Button button = new Button("Register!");
		button.addClassName("lumo-button");
		button.getElement().setAttribute("aria-label", "Click me");
		button.addClickListener(click -> {
			Dialog dialog = new Dialog();
			dialog.setResizable(true);
			dialog.add(new RegisterView(userRepository, encoder));
			dialog.open();
		});

		setSizeFull();
		setAlignItems(Alignment.CENTER);
		setJustifyContentMode(JustifyContentMode.CENTER);

		login.setAction("login");
		add(new H1("Slick EMS"), login, button);


	}
	
	
	@Override
	public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
		
		if(beforeEnterEvent.getLocation()
		.getQueryParameters()
		.getParameters()
		.containsKey("error")) {
			login.setError(true);
		}
	}
}
