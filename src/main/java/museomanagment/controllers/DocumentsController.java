package museomanagment.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
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
    private final Alert alertUserTypeCorrelationError = new Alert(AlertType.ERROR,
            "Errore, l'utente appartiene già a questa categoria.");
    private final Alert alertSuccess = new Alert(AlertType.CONFIRMATION, 
                                                 "Documento inserito con successo");
    @FXML
    private TextField documentNumber;
    @FXML
    private DatePicker issuingDate;
    @FXML
    private DatePicker expirationDate;
    @FXML
    private ComboBox<String> user;
    @FXML
    private ComboBox<String> userType;

    /**
     * Method that calls the query that takes care of picking up the
     * users from the database and inserting them in the related ChoiceBox.
     */
    public void setUser() {
       this.user.getItems().clear();
       this.user.getItems().addAll(this.museoManagment.getUsers());
    }

    /**
     * Method that calls the query that takes care of taking the categories 
     * of users from the database and inserting them in the relative ChoiceBox.
     */
    public void setUserType() {
        this.userType.getItems().clear();
        this.userType.getItems().addAll(this.museoManagment.getUserTypes());
    }

    /**
     * Method that calls the query that inserts a new document into the 
     * database.
     */
    public void insertDocument() {
        if (documentNumber.getText().isBlank()
                || issuingDate.getValue() == null
                || issuingDate.getValue().toString().isBlank()
                || expirationDate.getValue() == null
                || expirationDate.getValue().toString().isBlank()) {
            this.alertError.show();
            return;
        }
        if (this.museoManagment.checkUserType(user.getValue(), userType.getValue())) {
            this.alertUserTypeCorrelationError.show();
        } else {
            this.museoManagment.documentRegistration(documentNumber.getText().trim(),
                    issuingDate.getValue().toString(), 
                    expirationDate.getValue().toString(), 
                    user.getValue() == null ? "" : user.getValue().trim(),
                    userType.getValue() == null ? "" : userType.getValue().trim());
            this.alertSuccess.show();
        }
    }
}
