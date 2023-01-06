package museomanagment.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * Controller related to the sale of a ticket and the application 
 * of available promotions.
 */
public class SellController {
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

    /**
     * Method that calls the query that takes care of picking up the
     * user promotions from the database and inserting them in the related ComboBox.
     */
    public void setUserPromotions() {
        //TODO
    }

    /**
     * Method that calls the query that takes care of picking up the
     * cumulative promotions from the database and inserting them in 
     * the related ComboBox.
     */
    public void setCumulativePromotions() {
        //TODO
    }

    /**
     * Method that calls the query that takes care of picking up the
     * type tours from the database and inserting them in 
     * the related ComboBox.
     */
    public void setTypeTour() {
        //TODO
    }

    /**
     * Method that calls the query that takes care of picking up the
     * guides from the database and inserting them in 
     * the related ComboBox.
     */
    public void setGuide() {
        //TODO
    }

    /**
     * Determines whether the promotion is cumulative or not, 
     * by making the appropriate changes in the application.
     */
    public void isCumulative() {
        //TODO
    }

    /**
     * Method that calls the query relating to the entry of a new sale, 
     * inserting the data taken from the application.
     */
    public void insertSell() {
        //TODO
    }
}
