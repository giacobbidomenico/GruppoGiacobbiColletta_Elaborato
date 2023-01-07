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
 * Controller related to the sale of a ticket and the application 
 * of available promotions.
 */
public class SellController {
    private final MuseoManagement museoManagment = new MuseoManager();
    @FXML
    private TextField ticketNumbers;
    @FXML
    private TextField userCode;
    @FXML
    private ComboBox<String> userPromotions;
    @FXML
    private ComboBox<String> cumulativePromotions;
    @FXML
    private CheckBox cumulative;
    @FXML
    private DatePicker date;
    @FXML
    private TextField beginTime;
    @FXML
    private TextField endTime;
    @FXML
    private ComboBox<String> typeTour;
    @FXML
    private ComboBox<String> guide;
    private boolean isCumulative = false;
    private final Alert alertUserPError = new Alert(AlertType.ERROR,
            "Errore, compilare le sezioni 'promozioni utenti' e 'Guida'.");
    private final Alert alertCumulativePError = new Alert(AlertType.ERROR,
            "Errore, compilare le sezioni 'promozioni cumulative' e 'tour standard'.");
    private final Alert alertError = new Alert(AlertType.ERROR,
            "Errore, campi mancanti.");
    /**
     * Method that calls the query that takes care of picking up the
     * user promotions from the database and inserting them in the related ComboBox.
     */
    public void setUserPromotions() {
//        this.userPromotions.getItems().clear();
//        this.userPromotions.getItems().addAll(this.museoManagment.get);
    }

    /**
     * Method that calls the query that takes care of picking up the
     * cumulative promotions from the database and inserting them in 
     * the related ComboBox.
     */
    public void setCumulativePromotions() {
//        this.cumulativePromotions.getItems().clear();
//        this.cumulativePromotions.getItems().addAll(this.museoManagment.getUsers());
    }

    /**
     * Method that calls the query that takes care of picking up the
     * type tours from the database and inserting them in 
     * the related ComboBox.
     */
    public void setTypeTour() {
        this.typeTour.getItems().clear();
        this.typeTour.getItems().addAll(this.museoManagment.getTourStandard());
    }

    /**
     * Method that calls the query that takes care of picking up the
     * guides from the database and inserting them in 
     * the related ComboBox.
     */
    public void setGuide() {
        this.guide.getItems().clear();
        this.guide.getItems().addAll(this.museoManagment.getConductors());
    }

    /**
     * Determines whether the promotion is cumulative or not, 
     * by making the appropriate changes in the application.
     */
    public void isCumulative() {
        this.isCumulative =  this.cumulative.isFocused();
    }

    /**
     * Method that calls the query relating to the entry of a new sale, 
     * inserting the data taken from the application.
     */
    public void insertSell() {
        if (ticketNumbers.getText().isBlank()
                || userCode.getText().isBlank()
                || date.getValue() == null
                || beginTime.getText().isBlank()
                || endTime.getText().isBlank()) {
            this.alertError.show();
            return;
        }

        if (isCumulative) {
            if (cumulativePromotions.getValue() == null) {
                this.alertCumulativePError.show();
                return;
            }
            this.museoManagment.ticketRegistration(ticketNumbers.getText(), userCode.getText(), date.getValue().toString(), beginTime.getText(), 
                    endTime.getText(), Optional.empty(), cumulativePromotions.getValue().toString(), isCumulative);
        } else {
            if (userPromotions.getValue() == null 
                    || this.guide.getValue() == null) {
                this.alertUserPError.show();
                return;
            }
            this.museoManagment.ticketRegistration(ticketNumbers.getText(), userCode.getText(), date.getValue().toString(), beginTime.getText(), 
                    endTime.getText(), Optional.ofNullable(guide.getValue().toString()), userPromotions.getValue().toString(), isCumulative);
        }

    }
}
