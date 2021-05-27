package com.example.vaadinassignment.data.manufacturer.view;

import com.example.vaadinassignment.data.manufacturer.entity.ManufacturerEntity;
import com.example.vaadinassignment.navbar.NavbarComponent;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route
@PageTitle("Manufacturers")
public class ManufacturerView extends VerticalLayout {
    public ManufacturerView() {
        Label title = new Label("Manufacturers Page");
        title.setHeight("1em");
        add(title);
        add(new NavbarComponent());
        Grid<ManufacturerEntity> manufacturerGrid = new Grid<>();
        List<ManufacturerEntity> manufacturerList = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            ManufacturerEntity manufacturer = new ManufacturerEntity();
            manufacturer.setId(i);
            manufacturer.setName("Manufacturer" + i);
            manufacturerList.add(manufacturer);
        }
        manufacturerGrid.setItems(manufacturerList);
        manufacturerGrid.addColumn(ManufacturerEntity::getId).setHeader("Id");
        manufacturerGrid.addColumn(ManufacturerEntity::getName).setHeader("Name");
        add(manufacturerGrid);
    }
}
