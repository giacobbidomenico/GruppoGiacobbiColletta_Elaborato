package museomanagment.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import museomanagment.model.MuseoManagement;
import museomanagment.model.MuseoManager;

/**
 * Controller for inserting a new pre-configured tour.
 */
public class PreConfiguredTourController {
    private final MuseoManagement museoManagment = new MuseoManager();
    private final Alert alertError = new Alert(AlertType.ERROR, 
                                               "Errore nell'inserimento di un tour pre configurato");
    private final Alert alertSuccess = new Alert(AlertType.CONFIRMATION, 
                                                 "Tour pre configurato inserito con successo");
    @FXML
    private TextField numberSeats;
    @FXML
    private TextField price;
    @FXML
    private ComboBox<String> area;

    /**
     * Method that calls the query that takes care of picking up the
     * museum areas from the database and inserting them in the related table.
     */
    public void setArea() {
        this.area.getItems().setAll(this.museoManagment.getAreas());
    }

    /**
     * Method that calls the query that inserts a new pre-configured tour, 
     * fetching the data from the application.
     */
    public void insertPreConfiguredTour() {
        if (numberSeats.getText() == null
                || numberSeats.getText().isBlank()
                || price.getText() == null
                || price.getText().isBlank()
                || this.area.getItems().isEmpty()) {
            alertError.show();
            return;
        }
        this.museoManagment.tourStandardRegistration(numberSeats.getText().trim(), 
                                                     price.getText().trim(),
                                                     this.area.getValue());
        alertSuccess.show();
    }
}
