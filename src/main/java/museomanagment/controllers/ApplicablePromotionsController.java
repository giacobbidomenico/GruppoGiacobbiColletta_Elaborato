package museomanagment.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import museomanagment.model.MuseoManagement;
import museomanagment.model.MuseoManager;

/**
 * Controller related to the display of promotions applicable to a specific client.
 */
public class ApplicablePromotionsController implements Initializable {
    private final MuseoManagement museoManagment = new MuseoManager();
    private final Alert alertError = new Alert(AlertType.ERROR, 
                                               "Errore, nessuma promozione corrispondente ai seguenti parametri");

    @FXML
    private TextField userCode;
    @FXML
    private DatePicker buyDate;
    @FXML
    private DatePicker tourDate;
    @FXML
    private TableView<Promotion> promotions;
    @FXML
    private TableColumn<Promotion, String> code;
    @FXML
    private TableColumn<Promotion, String>  name;
    @FXML
    private TableColumn<Promotion, String>  discount;
    @FXML
    private TableColumn<Promotion, String>  numberSeats;

    /**
     * Promotion class.
     */
    public final class Promotion {
        private final String code;
        private final String name;
        private final String discount;
        private final String numberSeats;

        /**
         * Build new {@link Promotion}.

         * @param code
         * @param name
         * @param discount
         * @param numberSeats
         */
        Promotion(final String code, 
                  final String name, 
                  final String discount,
                  final String numberSeats) {
            this.code = code;
            this.name = name;
            this.discount = discount;
            this.numberSeats = numberSeats;
        }

        /**
         * @return promotion code.
         */
        public String getCode() {
            return code;
        }

        /**
         * @return promotion name.
         */
        public String getName() {
            return name;
        }

        /**
         * @return discount of the promotion
         */
        public String getDiscount() {
            return discount;
        }

        /**
         * @return number of seats.
         */
        public String getNumberSeats() {
            return numberSeats;
        }
    }

    /**
     * Method that calls the query that takes care of picking up the
     * applicable promotions from the database and inserting them in the related table.
     */
    public void searchPromotions() {
        if (userCode.getText() == null
                || userCode.getText().isBlank()
                || buyDate.getValue() == null
                || tourDate.getValue() == null) {
            alertError.show();
        }

        this.promotions.getItems().clear();

        final List<List<String>> prom = this.museoManagment.checkAvailablePromotions(userCode.getText().trim(),
                                                                                           buyDate.getValue().toString(), 
                                                                                           tourDate.getValue().toString());
        prom.stream().forEach(e -> {
            final Promotion promotion = new Promotion(e.get(0), 
                                                      e.get(1), 
                                                      e.get(2), 
                                                      e.size() > 3 ? e.get(3) : "");
            final ObservableList<Promotion> listPromotions = this.promotions.getItems();
            listPromotions.add(promotion);
            this.promotions.setItems(listPromotions);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        code.setCellValueFactory(new  PropertyValueFactory<Promotion, String>("code"));
        name.setCellValueFactory(new  PropertyValueFactory<>("name"));
        discount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        numberSeats.setCellValueFactory(new PropertyValueFactory<>("numberSeats"));
    }
}
