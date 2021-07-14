package com.jabbour.ems.ui.view.generic;

import com.jabbour.ems.backend.entity.User;
import com.jabbour.ems.backend.service.renderer.ClassConfig;
import com.jabbour.ems.backend.service.renderer.RendererConfig;
import com.jabbour.ems.ui.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.Route;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

@Route(value="generic", layout = MainLayout.class)
public class genericView extends VerticalLayout {
    Binder binder = new BeanValidationBinder<>(User.class);

    Class clazz = null;
    public genericView() {
        super();
        User user = new User("Login", "Password");


        Set<ClassConfig> classConfigs = RendererConfig.getUserClass();
        classConfigs.forEach(e -> {
            Label label = new Label(e.getLabel());
            TextField value = new TextField();
//            binder.bind(e.getField(),
//                    val -> e.getGetter(),
//                    (val, street) -> e.);
            binder.forField(value).bind(e::getGetter, e::getSetter);
            add(
                    new HorizontalLayout(
                            label,
                            value
                    )
            );

        });
        binder.bindInstanceFields(user);
        Button button = new Button("show User");
        button.addClickListener(e -> System.out.println(user));
        add(button);
    }
}
