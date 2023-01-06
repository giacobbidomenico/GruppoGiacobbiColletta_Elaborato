package museomanagment.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Controller related to the display of promotions applicable to a specific client.
 */
public class ApplicablePromotionsController {
    @FXML
    private TextField userCode;
    @FXML
    private DatePicker buyDate;
    @FXML
    private DatePicker tourDate;
    @FXML
    private TableView<String> userPromotions;
    @FXML
    private TableView<String> cumulativePromotions;

    /**
     * Method that calls the query that takes care of picking up the
     * applicable promotions from the database and inserting them in the related table.
     */
    public void searchPromotions() {
        //TODO
    }
}
