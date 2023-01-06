package museomanagment.controllers;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import museomanagment.model.MuseoManagement;
import museomanagment.model.MuseoManager;

/**
 * Controller related to the insertion of a new client.
 */
public class ClientsController {
    private final MuseoManagement museoManagment = new MuseoManager();
    private final Alert alert = new Alert(AlertType.ERROR, 
                                          "Errore nell'inserimento dei dati");

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
        if (mail.getText().isBlank()
                || password.getText().isBlank()) {
            alert.show();
        }
        museoManagment.clientSubscription(mail.getText().trim(), 
                                          password.getText().trim(), 
                                          name.getText().trim(), 
                                          surname.getText().trim());
    }
}
