package museomanagment.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 */
public class View extends Application {

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        final Parent root = FXMLLoader.load(getClass().getResource("/view.fxml"));
        final Scene scene = new Scene(root);
        primaryStage.setTitle("Museo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
