package museomanagment.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;

/**
 * Controller related to the search for tours available in a given period.
 */
public class AvailableTourController {
    @FXML
    private DatePicker beginDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private TableView<String> tours;

    /**
     *  Method that calls the query that searches for the tours available in 
     *  a certain period and inserts the result in the relative table.
     */
    public void searchTours() {
        //TODO
    }
}
