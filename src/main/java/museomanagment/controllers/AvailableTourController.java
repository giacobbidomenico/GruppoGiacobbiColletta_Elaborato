package museomanagment.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import museomanagment.model.MuseoManagement;
import museomanagment.model.MuseoManager;

/**
 * Controller related to the search for tours available in a given period.
 */
public class AvailableTourController implements Initializable {
    private final MuseoManagement museoManagment = new MuseoManager();
    private final Alert alertError = new Alert(AlertType.ERROR, 
                                               "Errore nel reperire i tour disponibili");

    @FXML
    private DatePicker beginDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private TableView<Tour> tours;
    @FXML
    private TableColumn<Tour, String> date;
    @FXML
    private TableColumn<Tour, String> beginTime;
    @FXML
    private TableColumn<Tour, String> endTime;
    @FXML
    private TableColumn<Tour, String> typeTour;
    @FXML
    private TableColumn<Tour, String> guide;

    /**
     * Tour class.
     */
    public final class Tour {
        private final String date;
        private final String beginTime;
        private final String  endTime;
        private final String typeTour;
        private final String guide;

        /**
         * Build a new {@link Tour}.

         * @param date
         * @param beginTime
         * @param endTime
         * @param typeTour
         * @param guide
         */
        Tour(final String date,
             final String beginTime,
             final String  endTime,
             final String typeTour,
             final String guide) {
            this.date = date;
            this.beginTime =  beginTime;
            this.endTime = endTime;
            this.typeTour = typeTour;
            this.guide = guide;
        }

        /**
         * @return the tour date.
         */
        public String getDate() {
            return date;
        }

        /**
         * @return tour begin time.
         */
        public String getBeginTime() {
            return beginTime;
        }

        /**
         * @return tour end time.
         */
        public String getEndTime() {
            return endTime;
        }

        /**
         * @return the type of tour.
         */
        public String getTypeTour() {
            return typeTour;
        }

        /**
         * @return the guide of the tour.
         */
        public String getGuide() {
            return guide;
        }
    }

    /**
     *  Method that calls the query that searches for the tours available in 
     *  a certain period and inserts the result in the relative table.
     */
    public void searchTours() {
        if (this.beginDate.getValue() == null
                || this.endDate.getValue() == null) {
            alertError.show();
            return;
        }

        final var table =  this.museoManagment.tourHistory(this.beginDate.getValue().toString(), 
                                                           this.endDate.getValue().toString());
        System.out.println(table);
        table.forEach(e -> {
            final Tour tour = new Tour(e.get(0), 
                                         e.get(1),
                                         e.get(2),
                                         e.get(3),
                                         e.size() > 5 ? e.get(4) : "");
            final ObservableList<Tour> listTour = this.tours.getItems();
            listTour.add(tour);
            this.tours.setItems(listTour);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        beginTime.setCellValueFactory(new PropertyValueFactory<>("beginTime"));
        endTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        typeTour.setCellValueFactory(new PropertyValueFactory<>("typeTour"));
        guide.setCellValueFactory(new PropertyValueFactory<>("guide"));
    }
}
