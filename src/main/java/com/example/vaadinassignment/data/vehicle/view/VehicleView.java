package com.example.vaadinassignment.data.vehicle.view;

import com.example.vaadinassignment.data.manufacturer.entity.ManufacturerEntity;
import com.example.vaadinassignment.data.vehicle.entity.VehicleEntity;
import com.example.vaadinassignment.navbar.NavbarComponent;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route
@PageTitle("Vehicles")
public class VehicleView extends VerticalLayout {
    public VehicleView() {
        Label title = new Label("Vehicles Page");
        title.setHeight("1em");
        add(title);
        add(new NavbarComponent());
        Grid<VehicleEntity> vehicleGrid = new Grid<>();
        List<VehicleEntity> vehicleList = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            VehicleEntity vehicle = new VehicleEntity();
            //vehicle.setId(i);
            vehicle.setType("Vehicle" + i);
            vehicle.setManufacturer("Manufacturer" + i);
            vehicle.setDoors(4);
            vehicle.setProductionYear(1996);
            vehicleList.add(vehicle);
        }
        vehicleGrid.setItems(vehicleList);
        vehicleGrid.addColumn(VehicleEntity::getId).setHeader("Id");
        vehicleGrid.addColumn(VehicleEntity::getType).setHeader("Type");
        vehicleGrid.addColumn(VehicleEntity::getManufacturer).setHeader("Manufacturer");
        vehicleGrid.addColumn(VehicleEntity::getDoors).setHeader("Doors");
        vehicleGrid.addColumn(VehicleEntity::getProductionYear).setHeader("ProductionYr");
        add(vehicleGrid);
    }
}
