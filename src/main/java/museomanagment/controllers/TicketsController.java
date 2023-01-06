package museomanagment.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Controller related to the section of tickets sold.
 */
public class TicketsController {
    @FXML
    private TextField user;
    @FXML
    private TableView<String> tickets;

    /**
     * Method that calls the query that takes care of seeing 
     * which tickets a client has bought and inserts the data 
     * in the related table.
     */
    public void searchTickets() {
        //TODO
    }
}
