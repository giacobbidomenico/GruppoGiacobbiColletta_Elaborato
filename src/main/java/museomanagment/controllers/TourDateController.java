package museomanagment.controllers;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import museomanagment.model.MuseoManagement;
import museomanagment.model.MuseoManager;

/**
 * Controller related to the definition of tour dates.
 */
public class TourDateController {
    private final MuseoManagement museoManagment = new MuseoManager();
    private final Alert alertError = new Alert(AlertType.ERROR, 
                                               "Errore nell'inserimento di un tour");
    private final Alert alertSuccess = new Alert(AlertType.CONFIRMATION, 
                                                 "Tour inserito con successo");
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
       this.typeTour.getItems().setAll(this.museoManagment.getTourStandard());
    }

    /**
     * Method that calls the query that takes care of picking up the
     * guides from the database and inserting them in the related ComboBox.
     */
    public void setGuide() {
        this.guide.getItems().setAll(this.museoManagment.getConductors());
    }

    /**
     * Method that calls the query that takes care of picking up the
     * languages from the database and inserting them in the related ComboBox.
     */
    public void setLanguage() {
        this.language.getItems().setAll(this.museoManagment.getLanguages());
    }

    /**
     * 
     */
    public void insertTour() {
        if (date.getValue() == null
                || beginTime.getText() == null
                || beginTime.getText().isBlank()
                || endTime.getText() == null
                || endTime.getText().isBlank()
                || typeTour.getItems().isEmpty()) {
            alertError.show();
            return;
        }
        if (guidedTour.isSelected()) {
            if (guide.getItems().isEmpty()
                    || language.getItems().isEmpty()
                    || this.museoManagment.checkGuidedTourExists(date.getValue().toString(),
                                                                 beginTime.getText().trim(),
                                                                 endTime.getText().trim(),
                                                                 guide.getValue())) {
                alertError.show();
                return;
            }
            this.museoManagment.tourRegistration(date.getValue().toString(), 
                                                 beginTime.getText().trim(),
                                                 endTime.getText().trim(),
                                                 typeTour.getValue(),
                                                 Optional.of(guide.getValue()),
                                                 Optional.of(language.getValue()));
            alertSuccess.show();
            return;
        }

        if (this.museoManagment.checkAutonomousTourExists(date.getValue().toString(),
                                                          beginTime.getText().trim(),
                                                          endTime.getText().trim(),
                                                          typeTour.getValue())) {
            this.museoManagment.tourRegistration(date.getValue().toString(), 
                                                 beginTime.getText().trim(),
                                                 endTime.getText().trim(),
                                                 typeTour.getValue(),
                                                 Optional.empty(),
                                                 Optional.empty());
            alertSuccess.show();
            return;
        }
        alertError.show();
    }

    /**
     * 
     */
    public void isGuidedTour() {
        if (!guidedTour.isSelected()) {
            this.guide.setDisable(true);
            this.language.setDisable(true);
        } else {
            this.guide.setDisable(false);
            this.language.setDisable(false);
        }
    }
}
