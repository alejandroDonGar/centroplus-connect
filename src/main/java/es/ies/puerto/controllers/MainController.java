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

    /**
     * Constructor de la clase MainController
     */
    public MainController() {
        actividadService = new ActividadService();

        root = new BorderPane();
        root.getStyleClass().add("root-mobile");

        mostrarInicio();
        root.setBottom(crearNavegacionInferior());
    }

    /**
     * Metodo getView que devuelve el nodo raiz del escenario
        * @return El nodo raiz del escenario
     */
    public Parent getView() {
        return root;
    }

    /**
     * Metodo mostrarInicio que muestra el inicio del escenario
     */
    private void mostrarInicio() {
        VBox contenido = crearContenedorPantalla();

        Label titulo = new Label("CentroPlus Connect"); // Título del escenario
        titulo.getStyleClass().add("titulo");

        Label descripcion = new Label("Consulta actividades, reserva plazas y comunica incidencias."); // Descripción del escenario
        descripcion.getStyleClass().add("texto");
        descripcion.setWrapText(true);

        Button btnActividades = new Button("Ver actividades"); // Botón para ver las actividades
        btnActividades.getStyleClass().add("boton-principal");
        btnActividades.setOnAction(event -> mostrarActividades());

        contenido.getChildren().addAll(titulo, descripcion, btnActividades);
        root.setCenter(contenido);
    }

    /**
     * Metodo mostrarActividades que muestra las actividades del escenario
     */
    private void mostrarActividades() {
        VBox contenido = crearContenedorPantalla();

        Label titulo = new Label("Actividades"); // Título de la lista de actividades
        titulo.getStyleClass().add("titulo");

        ListView<Actividades> listaActividades = new ListView<>(); // Lista de actividades
        listaActividades.getStyleClass().add("lista");
        listaActividades.setItems(FXCollections.observableArrayList(actividadService.findAll()));
        listaActividades.setCellFactory(param -> new ListCell<>() {
            /**
             * Metodo updateItem que actualiza el item de la lista de actividades   
             * @param actividad La actividad a mostrar
             * @param empty
             */
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

        /**
         * Metodo setMouseClicked que agrega un evento de clic a la lista de actividades
         */
        listaActividades.setOnMouseClicked(event -> {
            Actividades actividadSeleccionada = listaActividades.getSelectionModel().getSelectedItem();

            if (actividadSeleccionada != null) {
                mostrarDetalleActividad(actividadSeleccionada);
            }
        });
        contenido.getChildren().addAll(titulo, listaActividades);
        root.setCenter(contenido);
    }

    /**
     * Metodo mostrarDetalleActividad que muestra el detalle de una actividad del escenario
     * @param actividad La actividad a mostrar
     */
    private void mostrarDetalleActividad(Actividades actividad) {
        VBox contenido = crearContenedorPantalla();

        Label titulo = new Label(actividad.getNombre()); // Título de la actividad
        titulo.getStyleClass().add("titulo");
        titulo.setWrapText(true);

        int plazasDisponibles = actividad.getPlazasMaximas() - actividad.getPlazasOcupadas();

        Label tipo = new Label("Tipo: " + actividad.getTipoActividad()); // Tipo de actividad
        tipo.getStyleClass().add("texto");

        Label duracion = new Label("Duración: " + actividad.getDuracion() + " minutos"); // Duración de la actividad
        duracion.getStyleClass().add("texto");

        Label precio = new Label("Precio: " + actividad.getPrecio() + "€"); // Precio de la actividad
        precio.getStyleClass().add("texto");

        Label plazas = new Label("Plazas: " + plazasDisponibles); // Plazas disponibles
        plazas.getStyleClass().add("texto");

        Button btnReservar = new Button("Reservar plaza"); // Botón de reservar plaza
        btnReservar.getStyleClass().add("boton-principal");
        btnReservar.setDisable(plazasDisponibles <= 0);

        Button btnVolver = new Button("Volver a actividades"); // Botón de volver a actividades
        btnVolver.getStyleClass().add("boton-secundario");
        btnVolver.setOnAction(event -> mostrarActividades());

        contenido.getChildren().addAll(titulo, tipo, duracion, precio, plazas, btnReservar, btnVolver);

        root.setCenter(contenido);
    }

    /**
     * Metodo mostrarReservas que muestra las reservas del escenario
     */
    private void mostrarReservas() {
        VBox contenido = crearContenedorPantalla();

        Label titulo = new Label("Mis reservas"); // Título de reservas
        titulo.getStyleClass().add("titulo");

        Label texto = new Label("Aquí se mostrarán las reservas activas del usuario."); // Texto de reservas
        texto.getStyleClass().add("texto");
        texto.setWrapText(true);

        contenido.getChildren().addAll(titulo, texto);
        root.setCenter(contenido);
    }

    /**
     * Metodo mostrarIncidencias que muestra las incidencias del escenario
     */
    private void mostrarIncidencias() {
        VBox contenido = crearContenedorPantalla();

        Label titulo = new Label("Incidencias"); // Título de incidencias
        titulo.getStyleClass().add("titulo");

        Label texto = new Label("Aquí se podrá enviar una incidencia."); // Texto de incidencias
        texto.getStyleClass().add("texto");
        texto.setWrapText(true);

        contenido.getChildren().addAll(titulo, texto);
        root.setCenter(contenido);
    }

    /**
     * Metodo crearContenedorPantalla que crea un contenedor VBox con un espacio entre los elementos
     * @return El contenedor VBox
     */
    private VBox crearContenedorPantalla() {
        VBox contenedor = new VBox(20);
        contenedor.setPadding(new Insets(25));
        contenedor.setAlignment(Pos.TOP_CENTER);
        return contenedor;
    }

    /**
     * Metodo crearNavegacionInferior que crea una barra de navegación inferior con botones
     * @return La barra de navegación inferior HBox
     */
    private HBox crearNavegacionInferior() {
        HBox navegacion = new HBox(8);
        navegacion.setPadding(new Insets(12));
        navegacion.setAlignment(Pos.CENTER);
        navegacion.getStyleClass().add("nav-inferior");

        Button btnInicio = crearBotonNav("Inicio"); // Botón de inicio
        btnInicio.setOnAction(event -> mostrarInicio());

        Button btnActividades = crearBotonNav("Actividades"); // Botón de actividades
        btnActividades.setOnAction(event -> mostrarActividades());

        Button btnReservas = crearBotonNav("Reservas"); // Botón de reservas
        btnReservas.setOnAction(event -> mostrarReservas());

        Button btnIncidencias = crearBotonNav("Incidencias"); // Botón de incidencias
        btnIncidencias.setOnAction(event -> mostrarIncidencias());

        navegacion.getChildren().addAll(btnInicio, btnActividades, btnReservas, btnIncidencias);
        return navegacion;
    }

    /**
     * Metodo crearBotonNav que crea un botón de navegación con un texto
     * @param texto El texto del botón
     * @return El botón de navegación Button
     */
    private Button crearBotonNav(String texto) {
        Button boton = new Button(texto); // Botón de navegación
        boton.getStyleClass().add("boton-nav");
        return boton;
    }
}