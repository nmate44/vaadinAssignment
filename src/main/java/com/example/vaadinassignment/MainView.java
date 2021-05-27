package com.example.vaadinassignment;

import com.example.vaadinassignment.navbar.NavbarComponent;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route
@PageTitle("Home")
public class MainView extends VerticalLayout {
    public MainView() {
        Label title = new Label("Main Page");
        title.setHeight("1em");
        add(title);
        add(new NavbarComponent());
    }
}
