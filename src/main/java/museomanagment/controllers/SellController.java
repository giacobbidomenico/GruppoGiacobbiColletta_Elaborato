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
    @FXML
    private CheckBox guidated;

    private final Alert alertError = new Alert(AlertType.ERROR,
                                               "Errore, campi mancanti.");
    /**
     * Method that calls the query that takes care of picking up the
     * user promotions from the database and inserting them in the related ComboBox.
     */
    public void setUserPromotions() {
        this.userPromotions.getItems().setAll(this.museoManagment.getUserPromotions());
    }

    /**
     * Method that calls the query that takes care of picking up the
     * cumulative promotions from the database and inserting them in 
     * the related ComboBox.
     */
    public void setCumulativePromotions() {
        this.cumulativePromotions.getItems().setAll(this.museoManagment.getCumulativePromotions());
    }

    /**
     * Method that calls the query that takes care of picking up the
     * type tours from the database and inserting them in 
     * the related ComboBox.
     */
    public void setTypeTour() {
        this.typeTour.getItems().setAll(this.museoManagment.getTourStandard());
    }

    /**
     * Method that calls the query that takes care of picking up the
     * guides from the database and inserting them in 
     * the related ComboBox.
     */
    public void setGuide() {
        this.guide.getItems().setAll(this.museoManagment.getConductors());
    }

    /**
     * Determines whether the promotion is cumulative or the tour is guidated, 
     * by making the appropriate changes in the application.
     */
    public void isChecked() {
        if (this.cumulative.isSelected()) {
            this.userPromotions.setDisable(true);
            this.cumulativePromotions.setDisable(false);
        } else {
            this.userPromotions.setDisable(false);
            this.cumulativePromotions.setDisable(true);
        }
 
        if (this.guidated.isSelected()) {
            this.guide.setDisable(false);
        } else {
            this.guide.setDisable(true);
        }
    }

    /**
     * Method that calls the query relating to the entry of a new sale, 
     * inserting the data taken from the application.
     */
    public void insertSell() {
        if (ticketNumbers.getText() == null
                || ticketNumbers.getText().isBlank()
                || userCode.getText() == null
                || userCode.getText().isBlank()
                || date.getValue() == null
                || beginTime.getText() == null
                || beginTime.getText().isBlank()
                || endTime.getText() == null
                || endTime.getText().isBlank()
                || typeTour.getValue() == null) {
            this.alertError.show();
            return;
        }

        Optional<String> oGuide = Optional.empty();

        if (this.guidated.isSelected() && this.guide.getValue() != null) {
                oGuide = Optional.of(this.guide.getValue());
        }

        Optional<String> cPromotion = Optional.empty();
        Optional<String> uPromotion = Optional.empty();
        if (this.cumulative.isSelected()) {
            if (cumulativePromotions.getValue() != null) {
                cPromotion = Optional.of(cumulativePromotions.getValue());
            }
        } else {
            if (this.userPromotions.getValue() != null) {
                uPromotion = Optional.of(this.userPromotions.getValue());
            }
        }
        this.museoManagment.ticketRegistration(ticketNumbers.getText().trim(), 
                                               userCode.getText().trim(),
                                               date.getValue().toString(),
                                               beginTime.getText(),
                                               endTime.getText(),
                                               oGuide,
                                               typeTour.getValue(),
                                               cPromotion.isEmpty() ? uPromotion : cPromotion,
                                               this.cumulative.isSelected(),
                                               this.guidated.isSelected());

    }
}
