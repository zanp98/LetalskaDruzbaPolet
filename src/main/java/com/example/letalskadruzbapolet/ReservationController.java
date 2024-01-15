package com.example.letalskadruzbapolet;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.spreadsheet.Grid;


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
    private CheckBox fixedDepartureDateCheckbox;
    @FXML
    private CheckBox fixedReturnDateCheckbox;
    @FXML
    private VBox preferencesContainer;
    @FXML
    private Label departureLabel, returnLabel;
    @FXML
    private CheckBox oneWayFlightCheckbox, returnFlightCheckbox;
    @FXML
    private ProgressIndicator searchLoader;
    @FXML
    private ProgressIndicator departureLoader;
    @FXML
    private ProgressIndicator returnLoader;

    @FXML
    private void initialize() {
        fixedDepartureDateCheckbox.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            departureDateTo.setVisible(!isSelected);
        });

        fixedReturnDateCheckbox.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            returnDateTo.setVisible(!isSelected);
        });

        departureCities.getItems().addAll("Benetke", "Trst", "Zagreb", "Dunaj");
        destinationCity.getItems().addAll("Sri Lanka", "Kanarski otoki", "Havaji", "New York");


        createFirstPassengerForm();

        oneWayFlightCheckbox.selectedProperty().addListener((obs, oldVal, newVal) -> {
            returnDateFrom.setVisible(!newVal);
            returnDateTo.setVisible(!newVal);
            returnLabel.setVisible(!newVal);
            fixedReturnDateCheckbox.setVisible(!newVal);
            fixedReturnDateCheckbox.setSelected(false);

            if (!newVal) {
                returnDateFrom.setValue(null);
                returnDateTo.setValue(null);
            }
        });

        // Initialize with the return date enabled
        oneWayFlightCheckbox.setSelected(false);
    }

    private void createFirstPassengerForm() {
        int firstPassengerNumber = passengerDataContainer.getChildren().size();
        HBox firstPassengerPreferences = createPassengerPreferencesControls(firstPassengerNumber);
        preferencesContainer.getChildren().add(firstPassengerPreferences);
    }

    @FXML
    private void searchFlights(ActionEvent event) {
        departureFlightsList.getItems().clear();
        returnFlightsList.getItems().clear();

        // Show the loader
        searchLoader.setVisible(true);
        departureLoader.setVisible(true);
        returnLoader.setVisible(true);

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                // Simulate a long-running task
                Thread.sleep(2000);

                // Update UI after search is done, you need to run this on the JavaFX Application Thread
                Platform.runLater(() -> {
                    departureLoader.setVisible(false);
                    returnLoader.setVisible(false);
                    searchLoader.setVisible(false);

                    // Populate the lists with results
                    departureFlightsList.getItems().addAll(/* ... flight data ... */);
                    returnFlightsList.getItems().addAll(/* ... flight data ... */);
                });

                return null;
            }
        };

        // Run the task on a background thread
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();

        departureFlightsList.getItems().addAll(
                "Benetke - New York, 12.10.2024 08:00 - Cena: $300",
                "Trst - New York, 13.10.2024 09:30 - Cena: $350",
                "Trst - New York, 11.10.2024 09:30 - Cena: $360"
        );

        returnFlightsList.getItems().addAll(
                "New York - Benetke, 17.10.2024 at 19:00 - Cena: $280",
                "New York - Trst, 18.10.2024 20:30 - Cena: $330",
                "New York - Trst, 16.10.2024 20:30 - Cena: $230",
                "New York - Trst, 19.10.2024 20:30 - Cena: $365"
        );
    }
    private HBox createPassengerPreferencesControls(int passengerNumber) {
        HBox hbox = new HBox(10); // Horizontal spacing between elements

        hbox.getChildren().add(new Label("Potnik " + passengerNumber + ":"));

        ComboBox<String> seatSelection = new ComboBox<>();
        seatSelection.getItems().addAll("1A", "1B", "1C", "2A", "2B", "2C", "3A", "3B", "3C");
        hbox.getChildren().add(new Label("Sedež:"));
        hbox.getChildren().add(seatSelection);

        ComboBox<String> foodSelection = new ComboBox<>();
        foodSelection.getItems().addAll("Brez omejitev", "Vegetarijanska", "Veganska");
        hbox.getChildren().add(new Label("Hrana:"));
        hbox.getChildren().add(foodSelection);

        CheckBox extraLuggageCheckbox = new CheckBox("Dodatna Prtljaga");
        hbox.getChildren().add(extraLuggageCheckbox);

        return hbox;
    }



    @FXML
    private void addNewPassengerForm(ActionEvent event) {
        GridPane passengerForm = createPassengerForm();
        passengerDataContainer.getChildren().add(passengerForm);
        int passengerNumber = passengerDataContainer.getChildren().size();
        HBox passengerPreferences = createPassengerPreferencesControls(passengerNumber);
        preferencesContainer.getChildren().add(passengerPreferences);
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
        String bookingSummary = "Podatki o potnikih:\n" + passengerDetails
                + "\n\nPodatki o letih:\n" + flightDetails
                + "\n\nPlačilo:\n" + paymentDetails;

        // Show confirmation dialog
        showConfirmationDialog(bookingSummary);
    }

    private String collectPassengerDetails() {
        // Implement logic to collect passenger details
        return """
                Ime in priimek: Janko Kajuh
                Št. potnega lista: PB0050052
                Naslov: Srednjekranjski cesti 31, 2463 Modrozob

                Ime in priimek: Janja Ratko
                Št. potnega lista: PB0050052
                Naslov: Srednjekranjski cesti 31, 2463 Modrozob"""; // Placeholder
    }

    private String collectFlightDetails() {
        // Implement logic to collect flight details
        return "Let: Benetke - New York, 12.10.2024 08:00";
    }

    private String collectPaymentDetails() {
        // Implement logic to collect payment details
        return "Kreditna kartica, ki se konča z 1234, Potek veljavnosti: 12/24";
    }

    private void showConfirmationDialog(String bookingSummary) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potrditev rezervacije");
        alert.setHeaderText("Prosim potrdite rezervacijo:");
        alert.setContentText(bookingSummary);

        // Show the dialog and wait for response
        alert.showAndWait();
        // You can add more logic based on user response
    }

    @FXML
    private void cancelBooking() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Preklic");
        alert.setHeaderText("Vpisani se ne bodo shranili. Ste prepričani, da želite preklicati?");

        // Show the dialog and wait for response
        alert.showAndWait();
        // You can add more logic based on user response
    }
}
