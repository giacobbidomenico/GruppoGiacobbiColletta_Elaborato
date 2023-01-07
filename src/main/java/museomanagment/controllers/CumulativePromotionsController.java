package museomanagment.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import museomanagment.model.MuseoManagement;
import museomanagment.model.MuseoManager;

/**
 * Controller related to the entry of a new cumulative promotion.
 */
public class CumulativePromotionsController {
    private final MuseoManagement museoManagment = new MuseoManager();
    private final Alert alertError = new Alert(AlertType.ERROR, 
                                               "Errore nell'inserimento della promozione cumulativa");
    private final Alert alertSuccess = new Alert(AlertType.CONFIRMATION, 
                                                 "Promozione cumulativa inserita correttamente");

    @FXML
    private TextField namePromotion;
    @FXML
    private TextField discount;
    @FXML
    private ComboBox<String> activityPeriod; 
    @FXML
    private ComboBox<String> validityPeriod;
    @FXML
    private TextField peopleNumber;

    private List<String> getPeriods() {
        return this.museoManagment.getPeriods()
                                  .stream()
                                  .map(e -> e.toString())
                                  .collect(Collectors.toList());
    }

    /**
     * Method that calls the query that takes care of picking up the
     * activity period from the database and inserting them in the related ComboBox.
     */
    public void setActivityPeriod() {
        this.activityPeriod.getItems().clear();
        this.activityPeriod.getItems().addAll(this.getPeriods());
    }

    /**
     * Method that calls the query that takes care of picking up the
     * validity period from the database and inserting them in the related ComboBox.
     */
    public void setValidityPeriod() {
        this.validityPeriod.getItems().clear();
        this.validityPeriod.getItems().addAll(this.getPeriods());
    }

    /**
     * Method that calls the query relating to the insertion of a new 
     * cumulative promotion, inserting the data fetched from the application.
     */
    public void insertCumulativePromotion() {
        if (namePromotion.getText() == null
                || namePromotion.getText().isBlank()
                || discount.getText() == null
                || discount.getText().isBlank()
                || peopleNumber.getText() == null
                || peopleNumber.getText().isBlank()) {
            alertError.show();
            return;
        }
        this.museoManagment.ticketsNumberPromotionRegistration(namePromotion.getText().trim(),
                                                               discount.getText().trim(),
                                                               activityPeriod.getSelectionModel().getSelectedIndex(),
                                                               validityPeriod.getSelectionModel().getSelectedIndex(),
                                                               peopleNumber.getText().trim());
        alertSuccess.show();
    }
}
