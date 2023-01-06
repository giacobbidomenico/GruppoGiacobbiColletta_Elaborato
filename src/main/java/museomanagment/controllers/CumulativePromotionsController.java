package museomanagment.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * Controller related to the entry of a new cumulative promotion.
 */
public class CumulativePromotionsController {
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

    /**
     * Method that calls the query relating to the insertion of a new 
     * cumulative promotion, inserting the data fetched from the application.
     */
    public void insertCumulativePromotion() {
        //TODO
    }
}
