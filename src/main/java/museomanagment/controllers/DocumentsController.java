package museomanagment.controllers;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import museomanagment.model.MuseoManagement;
import museomanagment.model.MuseoManager;

/**
 * Controller related to the insertion of a new document.
 */
public class DocumentsController {
    private final MuseoManagement museoManagment = new MuseoManager();
    private final Alert alertError = new Alert(AlertType.ERROR,
                                               "Errore nell'inserimento del documento");
    private final Alert alertSuccess = new Alert(AlertType.CONFIRMATION, 
                                                 "Documento inserito con successo");
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
       this.user.getItems().addAll(this.museoManagment.getUsers());
    }

    /**
     * Method that calls the query that takes care of taking the categories 
     * of users from the database and inserting them in the relative ChoiceBox.
     */
    public void setUserType() {
        this.userType.getItems().addAll(this.museoManagment.getUserTypes());
    }

    /**
     * Method that calls the query that inserts a new document into the 
     * database.
     */
    public void insertDocument() {
        if (numberDocument.getText().isBlank() 
                || issuingDate.getValue().toString().isBlank() 
                || expirationDate.getValue().toString().isBlank() 
                || user.getValue().isBlank() 
                || userType.getValue().isBlank()) {
            this.alertError.show();
        }

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ITALY);
        final String issuingDateFormatted = issuingDate.getValue().format(formatter);
        final String expirationDateFormatted = issuingDate.getValue().format(formatter);
        this.museoManagment.documentRegistration(numberDocument.getText().trim(),
                                                 issuingDateFormatted.trim(), 
                                                 expirationDateFormatted.trim(), 
                                                 user.getValue(),
                                                 userType.getValue());
        this.alertSuccess.show();
    }
}
