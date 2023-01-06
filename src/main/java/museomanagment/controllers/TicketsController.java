package museomanagment.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import museomanagment.model.MuseoManagement;
import museomanagment.model.MuseoManager;

/**
 * Controller for the section of tickets a customer has bought.
 */
public class TicketsController implements Initializable{
    private final MuseoManagement museoManagment = new MuseoManager();
    private final Alert alertError = new Alert(AlertType.ERROR, 
                                               "Errore nella ricerca dello storico di biglietti acquistati dall'utente");

    @FXML
    private TextField user;
    @FXML
    private TableView<Ticket> tickets;
    @FXML
    private TableColumn<Ticket, String> code;
    @FXML
    private TableColumn<Ticket, String> buyDate;
    @FXML
    private TableColumn<Ticket, String> buyTime;
    @FXML
    private TableColumn<Ticket, String> numberSeats;
    @FXML
    private TableColumn<Ticket, String> userCode;

    /**
     *
     */
    private final class Ticket {
        private final String code;
        private final String buyDate;
        private final String buyTime;
        private final String numberSeats;
        private final String userCode;

        private Ticket(final String code, 
                       final String buyDate,
                       final String buyTime,
                       final String numberSeats,
                       final String userCode) {
            this.code = code;
            this.buyDate = buyDate;
            this.buyTime = buyDate;
            this.numberSeats = numberSeats;
            this.userCode = userCode;
        }

        private String getCode() {
            return this.code;
        }

        private String getBuyDate() {
            return this.buyDate;
        }
 
        private String getBuyTime() {
            return this.buyTime;
        }
        private String getNumberSeats() {
            return this.numberSeats;
        }
        private String getUserCode() {
            return this.userCode;
        }
    }

    /**
     * Method that calls the query that takes care of seeing 
     * which tickets a client has bought and inserts the data 
     * in the related table.
     */
    public void searchTickets() {
        if (user.getText() == null
                || user.getText().isBlank()) {
            this.alertError.show();
        }
        final var table = this.museoManagment.getUserTicketHistory(user.getText());
        table.forEach(e -> {
            final Ticket ticket = new Ticket(e.get(0), 
                                             e.get(1),
                                             e.get(2),
                                             e.get(3),
                                             e.get(4));
            final ObservableList<Ticket> listTicket = this.tickets.getItems();
            listTicket.add(ticket);
            this.tickets.setItems(listTicket);
        });

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        code.setCellValueFactory(new PropertyValueFactory<Ticket, String>("code"));
        buyDate.setCellValueFactory(new PropertyValueFactory<Ticket, String>("buyDate"));
        buyTime.setCellValueFactory(new PropertyValueFactory<Ticket, String>("buyTime"));
        numberSeats.setCellValueFactory(new PropertyValueFactory<Ticket, String>("numberSeats"));
        userCode.setCellValueFactory(new PropertyValueFactory<Ticket, String>("userCode"));
    }
}
