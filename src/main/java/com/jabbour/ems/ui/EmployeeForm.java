package com.jabbour.ems.ui;


import com.jabbour.ems.backend.entities.Department;
import com.jabbour.ems.backend.entities.Employee;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;

/**
 * Employee form component that extends FormLayout Vaadin for form display
 * @author chris
 *
 */
public class EmployeeForm extends FormLayout {
	
	TextField firstName = new TextField("First name");
	TextField lastName = new TextField("Last name");
	EmailField email = new EmailField("Email");
	
	ComboBox<Employee.Status> status = new ComboBox<>("Status");
	ComboBox<Department> department = new ComboBox<>("Department");
	
	Button save = new Button("Save");
	Button delete = new Button("Delete");
	Button close = new Button("Cancel");
	
	//Constructor
	public EmployeeForm(){
		addClassName("employee-form");
		add(firstName,lastName,email, department, status, createButtonsLayout());
	}
	
	
	private HorizontalLayout createButtonsLayout() {
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
		close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		
		save.addClickShortcut(Key.ENTER);
		close.addClickShortcut(Key.ESCAPE);
		
		return new HorizontalLayout(save, delete, close); 
	}
}