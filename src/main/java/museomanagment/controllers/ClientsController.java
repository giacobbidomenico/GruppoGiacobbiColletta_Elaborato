package museomanagment.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Class that performs the function of controller in the section 
 * where a new customer is inserted.
 */
public class ClientsController {
    @FXML
    private TextField mail;
    @FXML
    private TextField password;
    @FXML
    private TextField nome;
    @FXML
    private TextField cognome;

    /**
     * Method that takes care of recalling the query that inserts the customer 
     * data,taken from the application, into the db.
     */
    @FXML
    public void insertClient() {
        System.out.println("he");
    }
}
