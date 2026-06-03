package es.ies.puerto.controllers;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import es.ies.puerto.modelos.Actividades;
import es.ies.puerto.services.ActividadService;
import javafx.collections.FXCollections;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

/**
 * @author AlejandroDonGar y JavierReyPer
 * @version 1.0.0
 *
 * Clase MainController
 */
public class MainController {

    private BorderPane root;
    private ActividadService actividadService;

    public MainController() {
        actividadService = new ActividadService();

        root = new BorderPane();
        root.getStyleClass().add("root-mobile");

        mostrarInicio();
        root.setBottom(crearNavegacionInferior());
    }

    public Parent getView() {
        return root;
    }

    private void mostrarInicio() {
        VBox contenido = crearContenedorPantalla();

        Label titulo = new Label("CentroPlus Connect");
        titulo.getStyleClass().add("titulo");

        Label descripcion = new Label("Consulta actividades, reserva plazas y comunica incidencias.");
        descripcion.getStyleClass().add("texto");
        descripcion.setWrapText(true);

        Button btnActividades = new Button("Ver actividades");
        btnActividades.getStyleClass().add("boton-principal");
        btnActividades.setOnAction(event -> mostrarActividades());

        contenido.getChildren().addAll(titulo, descripcion, btnActividades);
        root.setCenter(contenido);
    }

    private void mostrarActividades() {
        VBox contenido = crearContenedorPantalla();

        Label titulo = new Label("Actividades");
        titulo.getStyleClass().add("titulo");
        
        ListView<Actividades> listaActividades = new ListView<>();
        listaActividades.setItems(FXCollections.observableArrayList(actividadService.findAll()));
        listaActividades.getStyleClass().add("lista");
        listaActividades.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Actividades actividad, boolean empty) {
                super.updateItem(actividad, empty);
                if (empty || actividad == null) {
                    setText(null);
                } else {
                    int plazasDisponibles = actividad.getPlazasMaximas() - actividad.getPlazasOcupadas();
                    setText(
                        actividad.getNombre() + "\n" +
                        actividad.getTipoActividad() + " · " +
                        actividad.getDuracion() + " min\n" +
                        "Precio: " + actividad.getPrecio() + " € · " +
                        "Plazas libres: " + plazasDisponibles
                    );
                }
            }
        });
        contenido.getChildren().addAll(titulo, listaActividades);
        root.setCenter(contenido);
    }

    private void mostrarReservas() {
        VBox contenido = crearContenedorPantalla();

        Label titulo = new Label("Mis reservas");
        titulo.getStyleClass().add("titulo");

        Label texto = new Label("Aquí se mostrarán las reservas activas del usuario.");
        texto.getStyleClass().add("texto");
        texto.setWrapText(true);

        contenido.getChildren().addAll(titulo, texto);
        root.setCenter(contenido);
    }

    private void mostrarIncidencias() {
        VBox contenido = crearContenedorPantalla();

        Label titulo = new Label("Incidencias");
        titulo.getStyleClass().add("titulo");

        Label texto = new Label("Aquí se podrá enviar una incidencia.");
        texto.getStyleClass().add("texto");
        texto.setWrapText(true);

        contenido.getChildren().addAll(titulo, texto);
        root.setCenter(contenido);
    }

    private VBox crearContenedorPantalla() {
        VBox contenedor = new VBox(20);
        contenedor.setPadding(new Insets(25));
        contenedor.setAlignment(Pos.TOP_CENTER);
        return contenedor;
    }

    private HBox crearNavegacionInferior() {
        HBox navegacion = new HBox(8);
        navegacion.setPadding(new Insets(12));
        navegacion.setAlignment(Pos.CENTER);
        navegacion.getStyleClass().add("nav-inferior");

        Button btnInicio = crearBotonNav("Inicio");
        btnInicio.setOnAction(event -> mostrarInicio());

        Button btnActividades = crearBotonNav("Actividades");
        btnActividades.setOnAction(event -> mostrarActividades());

        Button btnReservas = crearBotonNav("Reservas");
        btnReservas.setOnAction(event -> mostrarReservas());

        Button btnIncidencias = crearBotonNav("Incidencias");
        btnIncidencias.setOnAction(event -> mostrarIncidencias());

        navegacion.getChildren().addAll(btnInicio, btnActividades, btnReservas, btnIncidencias);
        return navegacion;
    }

    private Button crearBotonNav(String texto) {
        Button boton = new Button(texto);
        boton.getStyleClass().add("boton-nav");
        return boton;
    }
}