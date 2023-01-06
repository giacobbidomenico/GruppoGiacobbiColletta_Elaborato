package museomanagment.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Controller related to the insertion of a new client.
 */
public class ClientsController {
    @FXML
    private TextField mail;
    @FXML
    private TextField password;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;

    /**
     * Method that calls the query that inserts the clients data, 
     * taken from the application, in the database.
     */
    @FXML
    public void insertClient() {
        System.out.println("he");
    }
}
