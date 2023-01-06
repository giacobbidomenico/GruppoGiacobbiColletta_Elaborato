package museomanagment.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

/**
 * Controller related to the calculation of the average spend made by clients.
 */
public class AverageSpendController implements Initializable {
    @FXML
    private TableView<String> averageSpend;

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
        //TODO
    }


}
