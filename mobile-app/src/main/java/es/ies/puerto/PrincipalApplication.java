package es.ies.puerto;
import es.ies.puerto.controllers.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author AlejandroDonGar
 * @version 1.0.0
 *
 * Clase PrincipalApplication
 */
public class PrincipalApplication extends Application {

    @Override
    public void start(Stage stage) {
        MainController mainController = new MainController();

        Scene scene = new Scene(mainController.getView(), 390, 760);

        String css = getClass().getResource("/es/ies/puerto/css/estilos.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setTitle("CentroPlus Connect");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}