package museomanagment;

import javafx.application.Application;
import museomanagment.view.View;

/**
 * Main class.
 */
public final class App {


    private App() {

    }

    /**
     * @param args
     */
    public static void main(final String[] args) {
        Application.launch(View.class, args);
    }

}
