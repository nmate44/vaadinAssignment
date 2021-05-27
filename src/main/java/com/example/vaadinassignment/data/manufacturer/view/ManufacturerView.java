package com.example.vaadinassignment.data.manufacturer.view;

import com.example.vaadinassignment.data.manufacturer.entity.ManufacturerEntity;
import com.example.vaadinassignment.navbar.NavbarComponent;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route
@PageTitle("Manufacturers")
public class ManufacturerView extends VerticalLayout {
    public ManufacturerView() {
        Label title = new Label("Manufacturers Page");
        title.setHeight("1em");
        add(title);
        add(new NavbarComponent());
        Grid<ManufacturerEntity> manufacturerGrid = new Grid<>();
        manufacturerGrid.addColumn(ManufacturerEntity::getId).setHeader("Id");
        manufacturerGrid.addColumn(ManufacturerEntity::getName).setHeader("Name");
        add(manufacturerGrid);
    }
}
