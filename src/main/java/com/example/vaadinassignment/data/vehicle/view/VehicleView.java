package com.example.vaadinassignment.data.vehicle.view;

import com.example.vaadinassignment.data.manufacturer.entity.ManufacturerEntity;
import com.example.vaadinassignment.data.manufacturer.service.ManufacturerService;
import com.example.vaadinassignment.data.vehicle.entity.VehicleEntity;
import com.example.vaadinassignment.data.vehicle.service.VehicleService;
import com.example.vaadinassignment.navbar.NavbarComponent;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToLongConverter;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route
@PageTitle("Vehicles")
public class VehicleView extends VerticalLayout {

    private VerticalLayout form;
    private ComboBox<ManufacturerEntity> manufacturer;
    private TextField type;
    private TextField production;
    private TextField doors;
    private Button deleteBtn = new Button("Delete", VaadinIcon.TRASH.create());

    private TextField nameFilter;
    private TextField prodYrFilter;

    private VehicleEntity selectedVehicle;
    private Binder<VehicleEntity> binder;

    @Autowired
    private VehicleService service;

    @Autowired
    private ManufacturerService manufacturerService;

    @PostConstruct
    public void init() {
        Label title = new Label("Vehicles Page");
        title.setHeight("1em");
        add(title);
        add(new NavbarComponent());

        Grid<VehicleEntity> grid = new Grid<>();
        grid.setItems(service.getAll());
        grid.addColumn(VehicleEntity::getId).setHeader("Id").setSortable(true);
        grid.addColumn(VehicleEntity::getType).setHeader("Type").setSortable(true);
        grid.addColumn(vehicleEntity -> {
                    if (vehicleEntity.getManufacturer() != null) {
                        return vehicleEntity.getManufacturer().getName();
                    }
                    return "";
                }
        ).setHeader("Manufacturer").setSortable(true);
        grid.addColumn(VehicleEntity::getProductionYear).setHeader("Production Yr").setSortable(true);
        grid.addColumn(VehicleEntity::getDoors).setHeader("Doors").setSortable(true);
        grid.asSingleSelect().addValueChangeListener(event -> {
            selectedVehicle = event.getValue();
            binder.setBean(selectedVehicle);
            form.setVisible(selectedVehicle != null);
            deleteBtn.setEnabled(selectedVehicle != null);

        });

        addButtonBar(grid);
        add(grid);
        addForm(grid);
    }

    private void addForm(Grid<VehicleEntity> grid) {
        form = new VerticalLayout();
        binder = new Binder<>(VehicleEntity.class);

        HorizontalLayout typeField = new HorizontalLayout();
        type = new TextField();
        typeField.add(new Text("Name"), type);

        HorizontalLayout manufacturerField = new HorizontalLayout();
        manufacturer = new ComboBox<>();
        manufacturer.setItems(manufacturerService.getAll());
        manufacturer.setItemLabelGenerator(manufacturerEntity -> {
            return manufacturerEntity.getName();
        });
        manufacturerField.add(new Text("Manufacturer"), manufacturer);

        HorizontalLayout productionField = new HorizontalLayout();
        production = new TextField();
        productionField.add(new Text("Production"), production);

        HorizontalLayout doorsField = new HorizontalLayout();
        doors = new TextField();
        doorsField.add(new Text("Doors"), doors);

        form.add(typeField, manufacturerField, productionField, doorsField, addSaveBtn(grid));
        add(form);
        form.setVisible(false);

        binder.forField(production)
                .withNullRepresentation( "" )
                .withConverter(
                        new StringToLongConverter( Long.valueOf( 0 ), "Long only" ) )
                .bind( VehicleEntity::getProductionYear, VehicleEntity::setProductionYear );

        binder.forField(doors)
                .withNullRepresentation( "" )
                .withConverter(
                        new StringToLongConverter( Long.valueOf( 0 ), "Long only" ) )
                .bind( VehicleEntity::getDoors, VehicleEntity::setDoors );

        binder.bindInstanceFields( this );
    }

    private Button addSaveBtn(Grid<VehicleEntity> grid) {
        Button saveBtn = new Button("Save", VaadinIcon.SAFE.create());
        saveBtn.addClickListener(buttonClickEvent -> {
            if (selectedVehicle.getId() == null) {
                VehicleEntity vehicleEntity = new VehicleEntity();
                vehicleEntity.setType(selectedVehicle.getType());
                vehicleEntity.setManufacturer(selectedVehicle.getManufacturer());
                vehicleEntity.setProductionYear(selectedVehicle.getProductionYear());
                vehicleEntity.setDoors(selectedVehicle.getDoors());
                service.add(vehicleEntity);
                grid.setItems(service.getAll());
                selectedVehicle = null;
                Notification.show("Sikeres ment??s");
            } else {
                service.update(selectedVehicle);
                grid.setItems(service.getAll());
                Notification.show("Sikeres m??dos??t??s");
            }
            form.setVisible(false);
        });
        return saveBtn;
    }

    private void addButtonBar(Grid<VehicleEntity> grid) {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        deleteBtn.addClickListener(buttonClickEvent -> {
            service.remove(selectedVehicle);
            Notification.show("Sikeres t??rl??s");
            selectedVehicle = null;
            grid.setItems(service.getAll());
            form.setVisible(false);
        });
        deleteBtn.setEnabled(false);

        Button addBtn = new Button("Add", VaadinIcon.PLUS.create());
        addBtn.addClickListener(buttonClickEvent -> {
            selectedVehicle = new VehicleEntity();
            binder.setBean(selectedVehicle);
            form.setVisible(true);
        });

        horizontalLayout.add(deleteBtn);
        horizontalLayout.add(addBtn);
        horizontalLayout.add(addNameFilter(grid));
        horizontalLayout.add(addProdYrFilter(grid));
        horizontalLayout.add(addFilterSwitchBtn(grid));
        add(horizontalLayout);
    }

    private Button addFilterSwitchBtn(Grid<VehicleEntity> grid) {
        Button filterSwitchBtn = new Button("Change Filter", VaadinIcon.FILTER.create());
        filterSwitchBtn.addClickListener(buttonClickEvent -> {
            if(nameFilter.isVisible()) {
                nameFilter.setVisible(false);
                prodYrFilter.setVisible(true);
            } else if(prodYrFilter.isVisible()) {
                prodYrFilter.setVisible(false);
                nameFilter.setVisible(true);
            }
            nameFilter.setValue("");
            prodYrFilter.setValue("");
            grid.setItems(service.getAll());
        });
        return filterSwitchBtn;
    }

    private TextField addNameFilter(Grid<VehicleEntity> grid) {
        nameFilter = new TextField();
        nameFilter.setPlaceholder("Filter by Name");
        nameFilter.setClearButtonVisible(true);
        nameFilter.setValueChangeMode(ValueChangeMode.LAZY);
        nameFilter.addValueChangeListener(e ->
                grid.setItems(service.getAllByNameFilter(nameFilter.getValue()))
        );
        nameFilter.setVisible(true);
        return nameFilter;
    }

    private TextField addProdYrFilter(Grid<VehicleEntity> grid) {
        prodYrFilter = new TextField();
        prodYrFilter.setPlaceholder("Filter by Production Yr");
        prodYrFilter.setClearButtonVisible(true);
        prodYrFilter.setValueChangeMode(ValueChangeMode.LAZY);
        prodYrFilter.addValueChangeListener(e ->
                grid.setItems(service.getAllByProdYrFilter(prodYrFilter.getValue()))
        );
        prodYrFilter.setVisible(false);
        return prodYrFilter;
    }
}
