package museomanagment.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * Controller related to the entry of a new user promotion.
 */
public class UserPromotionsController {
    @FXML
    private TextField namePromotion;
    @FXML
    private TextField discount;
    @FXML
    private ComboBox<String> activityPeriod; 
    @FXML
    private ComboBox<String> validityPeriod;
    @FXML
    private ComboBox<String> userType;

    /**
     * Method that calls the query relating to the insertion of a new 
     * user promotion, inserting the data fetched from the application.
     */
    public void insertUserPromotion() {
        //TODO
    }
}
