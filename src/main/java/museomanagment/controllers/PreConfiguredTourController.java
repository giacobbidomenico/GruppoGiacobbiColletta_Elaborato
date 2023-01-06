package museomanagment.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * Controller for inserting a new pre-configured tour.
 */
public class PreConfiguredTourController {
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
        //TODO
    }

    /**
     * Method that calls the query that inserts a new pre-configured tour, 
     * fetching the data from the application.
     */
    public void insertPreConfiguredTour() {
        //TODO
    }
}
