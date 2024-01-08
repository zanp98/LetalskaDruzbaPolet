package com.example.letalskadruzbapolet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import org.controlsfx.control.CheckComboBox;


public class ReservationController {
    @FXML
    private HBox passengerDataContainer;
    @FXML
    private CheckComboBox<String> departureCities;
    @FXML
    private ListView<String> departureCitiesList;
    @FXML
    private ComboBox<String> destinationCity;
    @FXML
    private DatePicker departureDateFrom, departureDateTo, returnDateFrom, returnDateTo;
    @FXML
    private ListView<String> departureFlightsList;
    @FXML
    private ListView<String> returnFlightsList;
    @FXML
    private CheckComboBox<String> seatSelectionList;
    @FXML
    private TextField selectedSeats;
    @FXML
    private ComboBox<String> foodSelection;
    @FXML
    private TextField emailField;
    @FXML
    private TextField creditCardField;
    @FXML
    private DatePicker creditCardExpiryDate;
    @FXML
    private PasswordField securityCodeField;

    @FXML
    private void initialize() {
        destinationCity.getItems().addAll("Benetke", "Trst", "Zagreb", "Dunaj");
        departureCities.getItems().addAll("Sri Lanka", "Kanarski otoki", "Havaji", "New York");
        seatSelectionList.getItems().addAll("1A", "1B", "1C", "2A", "2B", "2C", "3A", "3B", "3C");
    }

    @FXML
    private void searchFlights(ActionEvent event) {
        departureFlightsList.getItems().clear();
        returnFlightsList.getItems().clear();

        departureFlightsList.getItems().addAll(
                "Benetke - New York, 12.10.2024 at 08:00 - Cena: $300",
                "Trst - New York, 13.10.2024 at 09:30 - Cena: $350",
                "Trst - New York, 11.10.2024 at 09:30 - Cena: $360"
        );

        returnFlightsList.getItems().addAll(
                "New York - Benetke, 17.10.2024 at 19:00 - Cena: $280",
                "New York - Trst, 18.10.2024 at 20:30 - Cena: $330",
                "New York - Trst, 16.10.2024 at 20:30 - Cena: $230",
                "New York - Trst, 19.10.2024 at 20:30 - Cena: $365"
        );
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

    @FXML
    private void handleCreditCardInput() {
        String cardNumber = creditCardField.getText();
        if (cardNumber.length() > 4) {
            String maskedNumber = "XXXX-XXXX-XXXX-" + cardNumber.substring(cardNumber.length() - 4);
            creditCardField.setText(maskedNumber);
        }
    }

    @FXML
    private void finalizeBooking() {
        // Example: Retrieve values from new UI elements
//        String seats = selectedSeats.getText();
//        String foodChoice = foodSelection.getValue();
//        String email = emailField.getText();
//        String creditCard = creditCardField.getText();

        String passengerDetails = collectPassengerDetails();
        String flightDetails = collectFlightDetails();
        String paymentDetails = collectPaymentDetails();

        // Combine details into a summary
        String bookingSummary = "Passenger Details:\n" + passengerDetails
                + "\n\nFlight Details:\n" + flightDetails
                + "\n\nPayment Details:\n" + paymentDetails;

        // Show confirmation dialog
        showConfirmationDialog(bookingSummary);
    }

    private String collectPassengerDetails() {
        // Implement logic to collect passenger details
        return "John Doe, ID: 12345"; // Placeholder
    }

    private String collectFlightDetails() {
        // Implement logic to collect flight details
        return "Flight: XYZ123, Departure: City A, Arrival: City B"; // Placeholder
    }

    private String collectPaymentDetails() {
        // Implement logic to collect payment details
        return "Card ending in 1234, Expiry: 12/24"; // Placeholder
    }

    private void showConfirmationDialog(String bookingSummary) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Booking Confirmation");
        alert.setHeaderText("Please confirm your booking details:");
        alert.setContentText(bookingSummary);

        // Show the dialog and wait for response
        alert.showAndWait();
        // You can add more logic based on user response
    }
}
