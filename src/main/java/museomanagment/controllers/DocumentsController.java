package museomanagment.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * Controller related to the insertion of a new document.
 */
public class DocumentsController {
    @FXML
    private TextField numberDocument;
    @FXML
    private DatePicker issuingDate;
    @FXML
    private DatePicker expirationDate;
    @FXML
    private ChoiceBox<String> user;
    @FXML
    private ChoiceBox<String> userType;

    /**
     * Method that calls the query that takes care of picking up the
     * users from the database and inserting them in the related ChoiceBox.
     */
    public void setUser() {
       //TODO 
    }

    /**
     * Method that calls the query that takes care of taking the categories 
     * of users from the database and inserting them in the relative ChoiceBox.
     */
    public void setUserType() {
        //TODO
    }

    /**
     * Method that calls the query that inserts a new document into the 
     * database.
     */
    public void insertDocument() {
        //TODO
    }
}
