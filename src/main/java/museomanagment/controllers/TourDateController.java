package museomanagment.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * Controller related to the definition of tour dates.
 */
public class TourDateController {
    @FXML
    private DatePicker date;
    @FXML
    private TextField  beginTime;
    @FXML
    private TextField  endTime;
    @FXML
    private ComboBox<String> typeTour;
    @FXML
    private ComboBox<String> guide;
    @FXML
    private ComboBox<String> language;
    @FXML
    private CheckBox guidedTour;

    /**
     * Method that calls the query that takes care of picking up the
     * type of tours from the database and inserting them in the related ComboBox.
     */
    public void setTypeTour() {
        //TODO
    }

    /**
     * Method that calls the query that takes care of picking up the
     * guides from the database and inserting them in the related ComboBox.
     */
    public void setGuide() {
        //TODO
    }

    /**
     * Method that calls the query that takes care of picking up the
     * languages from the database and inserting them in the related ComboBox.
     */
    public void setLanguage() {
        //TODO
    }

    /**
     * 
     */
    public void insertTour() {
        //TODO
    }

    /**
     * 
     */
    public void isGuidedTour() {
        //TODO
    }
}
