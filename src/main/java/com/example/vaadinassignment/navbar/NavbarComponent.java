package com.example.vaadinassignment.navbar;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class NavbarComponent extends HorizontalLayout {
    public NavbarComponent() {
        Anchor home = new Anchor();
        home.setText("Home");
        home.setHref("/");
        add(home);

        Anchor vehicles = new Anchor();
        vehicles.setText("Vehicles");
        vehicles.setHref("/vehicle");
        add(vehicles);

        Anchor manufacturers = new Anchor();
        manufacturers.setText("Manufacturers");
        manufacturers.setHref("/manufacturer");
        add(manufacturers);
    }
}
