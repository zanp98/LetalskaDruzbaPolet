package com.example.letalskadruzbapolet;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import org.controlsfx.control.CheckComboBox;


public class ReservationController {
    @FXML
    private HBox passengerDataContainer;
    private CheckComboBox<String> checkComboBox;

    @FXML
    private ListView<String> departureCitiesList;
    @FXML
    private ComboBox<String> destinationCity;
    @FXML
    private DatePicker departureDateFrom, departureDateTo, returnDateFrom, returnDateTo;

    @FXML
    private void initialize() {
        destinationCity.getItems().addAll("Benetke", "Trst", "Zagreb", "Dunaj");
    }

    @FXML
    private void addDepartureCity(ActionEvent event) {
        // Logic to add a new departure city
        // This might be a new window or dialog to select a city and then add it to the list
    }

    @FXML
    private void addNewPassengerForm(ActionEvent event) {
        GridPane passengerForm = createPassengerForm();
        passengerDataContainer.getChildren().add(passengerForm);
    }

    private GridPane createPassengerForm() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        TextField nameField = new TextField();
        nameField.setPromptText("Ime");
        TextField surnameField = new TextField();
        surnameField.setPromptText("Priimek");
        TextField idNumberField = new TextField();
        idNumberField.setPromptText("Št. osebnega dokumenta");
        TextField addressField = new TextField();
        addressField.setPromptText("Naslov");
        TextField postalField = new TextField();
        postalField.setPromptText("Pošta");
        TextField cityField = new TextField();
        cityField.setPromptText("Mesto");

        Button addButton = new Button("Dodaj");
        addButton.setOnAction(this::addNewPassengerForm);

        Button deleteButton = new Button("Zbriši");
        deleteButton.setOnAction(e -> passengerDataContainer.getChildren().remove(gridPane));

        gridPane.add(nameField, 0, 0);
        gridPane.add(surnameField, 0, 1);
        gridPane.add(idNumberField, 0, 2);
        gridPane.add(addressField, 0, 3);
        gridPane.add(postalField, 0, 4);
        gridPane.add(cityField, 0, 5);
        gridPane.add(addButton, 1, 2);
        gridPane.add(deleteButton, 1, 3);

        return gridPane;
    }
}
