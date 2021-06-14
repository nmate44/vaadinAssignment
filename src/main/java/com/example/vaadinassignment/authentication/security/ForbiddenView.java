package com.example.vaadinassignment.authentication.security;

import com.example.vaadinassignment.navbar.NavbarComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("403")
public class ForbiddenView extends VerticalLayout {

    public ForbiddenView(){
        add(new NavbarComponent());
        add("Access Denied");
    }
}
