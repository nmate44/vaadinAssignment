package com.example.vaadinassignment.data.manufacturer.view;

import com.example.vaadinassignment.data.manufacturer.entity.ManufacturerEntity;
import com.example.vaadinassignment.data.manufacturer.service.ManufacturerService;
import com.example.vaadinassignment.navbar.NavbarComponent;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route
@PageTitle("Manufacturers")
public class ManufacturerView extends VerticalLayout {

    private VerticalLayout form;
    private TextField name;
    private Button deleteBtn = new Button("Delete", VaadinIcon.TRASH.create());

    ManufacturerEntity selectedManufacturer;
    private Binder<ManufacturerEntity> binder;

    @Autowired
    ManufacturerService service;

    @PostConstruct
    public void init() {
        Label title = new Label("Manufacturers Page");
        title.setHeight("1em");
        add(title);
        add(new NavbarComponent());

        Grid<ManufacturerEntity> manufacturerGrid = new Grid<>();
        manufacturerGrid.setItems(service.getAll());
        manufacturerGrid.addColumn(ManufacturerEntity::getId).setHeader("Id");
        manufacturerGrid.addColumn(ManufacturerEntity::getName).setHeader("Name");
        manufacturerGrid.asSingleSelect().addValueChangeListener(event -> {
            selectedManufacturer = event.getValue();
            binder.setBean(selectedManufacturer);
            form.setVisible(selectedManufacturer != null);
            deleteBtn.setEnabled(selectedManufacturer != null);
        });

        addButtonBar(manufacturerGrid);
        add(manufacturerGrid);
        addForm(manufacturerGrid);
    }

    private void addForm(Grid<ManufacturerEntity> grid) {
        form = new VerticalLayout();
        binder = new Binder<>(ManufacturerEntity.class);
        HorizontalLayout nameField = new HorizontalLayout();
        name = new TextField();
        nameField.add(new Text("Manufacturer's name"), name);
        form.add(nameField, addSaveBtn(grid));
        add(form);
        form.setVisible(false);
        binder.bindInstanceFields(this);
    }

    private Button addSaveBtn(Grid<ManufacturerEntity> grid) {
        Button saveBtn = new Button("Save", VaadinIcon.SAFE.create());
        saveBtn.addClickListener(buttonClickEvent -> {
            if (selectedManufacturer.getId() == null) {
                ManufacturerEntity manufacturerEntity = new ManufacturerEntity();
                manufacturerEntity.setName(selectedManufacturer.getName());
                service.add(manufacturerEntity);
                grid.setItems(service.getAll());
                selectedManufacturer = null;
                Notification.show("Sikeres mentés");
            } else {
                service.update(selectedManufacturer);
                grid.setItems(service.getAll());
                Notification.show("Sikeres módosítás");
            }
            form.setVisible(false);
        });
        return saveBtn;
    }

    private void addButtonBar(Grid<ManufacturerEntity> grid) {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        deleteBtn.addClickListener(buttonClickEvent -> {
            service.remove(selectedManufacturer);
            Notification.show("Sikeres törlés");
            selectedManufacturer = null;
            grid.setItems(service.getAll());
            form.setVisible(false);
        });
        deleteBtn.setEnabled(false);

        Button addBtn = new Button("Add", VaadinIcon.PLUS.create());
        addBtn.addClickListener(buttonClickEvent -> {
            selectedManufacturer = new ManufacturerEntity();
            binder.setBean(selectedManufacturer);
            form.setVisible(true);
        });

        horizontalLayout.add(deleteBtn);
        horizontalLayout.add(addBtn);
        add(horizontalLayout);
    }

}
