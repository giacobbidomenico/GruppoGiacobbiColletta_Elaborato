package museomanagment.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import museomanagment.controllers.TicketsController.Ticket;
import museomanagment.model.MuseoManagement;
import museomanagment.model.MuseoManager;

/**
 * Controller related to the calculation of the average spend made by clients.
 */
public class AverageSpendController implements Initializable {
    private final MuseoManagement museoManagment = new MuseoManager();

    @FXML
    private TableView<AvgRow> average;
    @FXML
    private TableColumn<AvgRow, String> email;
    @FXML
    private TableColumn<AvgRow, String> pw;
    @FXML
    private TableColumn<AvgRow, String> nome;
    @FXML
    private TableColumn<AvgRow, String> cognome;
    @FXML
    private TableColumn<AvgRow, String> avg;
    @FXML
    private TableColumn<AvgRow, String> code;

    /**
     * 
     */
    public class AvgRow {
        private final String email;
        private final String pw;
        private final String nome;
        private final String cognome;
        private final String avg;
        private final String code;

        /**
         * @param email
         * @param pw
         * @param nome
         * @param cognome
         * @param avg
         * @param code
         */
        public AvgRow(final String code, final String email, final String pw, final String nome, final String cognome, final String avg) {
            this.email = email;
            this.pw = pw;
            this.nome = nome;
            this.cognome = cognome;
            this.avg = avg;
            this.code = code;
        }

        public String getEmail() {
            return email;
        }

        public String getPw() {
            return pw;
        }

        public String getNome() {
            return nome;
        }

        public String getCognome() {
            return cognome;
        }

        public String getAvg() {
            return avg;
        }

        public String getCode() {
            return code;
        }


    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.updateAverageSpend();
    }

    /**
     * Method that calls the query that calculates the average spend 
     * for each user and inserts the result in the relative table.
     */
    public void updateAverageSpend() {
        this.email.setCellValueFactory(new PropertyValueFactory<AvgRow, String>("email"));
        code.setCellValueFactory(new PropertyValueFactory<AvgRow, String>("code"));
        this.pw.setCellValueFactory(new PropertyValueFactory<AvgRow, String>("pw"));
        this.nome.setCellValueFactory(new PropertyValueFactory<AvgRow, String>("nome"));
        this.cognome.setCellValueFactory(new PropertyValueFactory<AvgRow, String>("cognome"));
        this.avg.setCellValueFactory(new PropertyValueFactory<AvgRow, String>("avg"));


        this.average.getItems().clear();
        final var table = this.museoManagment.usersExpenseAvg();
        table.forEach(e -> {
            final AvgRow row = new AvgRow(e.get(0), 
                                             e.get(1),
                                             e.get(2),
                                             e.get(3),
                                             e.get(4),
                                             e.get(5));
            final ObservableList<AvgRow> list = this.average.getItems();
            list.add(row);
            this.average.setItems(list);
        });
    }


}
