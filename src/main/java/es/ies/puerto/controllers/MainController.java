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
import es.ies.puerto.modelos.Reservas;
import es.ies.puerto.services.ReservaService;
import es.ies.puerto.modelos.Incidencias;
import es.ies.puerto.services.IncidenciaService;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.Date;

/**
 * @author AlejandroDonGar y JavierReyPer
 * @version 1.0.0
 *
 * Clase MainController
 */
public class MainController {

    private BorderPane root;
    private ActividadService actividadService;
    private ReservaService reservaService;
    private IncidenciaService incidenciaService;

    /**
     * Constructor de la clase MainController
     */
    public MainController() {
        actividadService = new ActividadService();
        reservaService = new ReservaService();
        incidenciaService = new IncidenciaService();

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

        Label titulo = new Label("CentroPlus Connect"); // Título del inicio
        titulo.getStyleClass().add("titulo");

        Label descripcion = new Label("Consulta actividades, reserva plazas y comunica incidencias."); // Descripción del inicio
        descripcion.getStyleClass().add("texto");
        descripcion.setWrapText(true);

        Label resumen = new Label( // Resumen del inicio
            "Actividades disponibles: " + actividadService.findAll().size() + "\n" +
            "Reservas registradas: " + reservaService.findAll().size() + "\n" +
            "Incidencias registradas: " + incidenciaService.findAll().size()
        );
        resumen.getStyleClass().add("tarjeta-resumen");

        Button btnActividades = new Button("Ver actividades"); // Botón para ver las actividades
        btnActividades.getStyleClass().add("boton-principal");
        btnActividades.setOnAction(event -> mostrarActividades());

        contenido.getChildren().addAll(titulo, descripcion, resumen, btnActividades);
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
             * @param empty Si el item está vacío
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

        btnReservar.setOnAction(event -> {
            Reservas reserva = new Reservas(generarIdReserva(), 1, actividad.getId(), new Date(), "ACTIVA");
            boolean resultado = reservaService.save(reserva);
            if (resultado) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Reserva creada", "La reserva se ha realizado correctamente.");
                mostrarActividades();
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se ha podido realizar la reserva.");
            }
        });

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

        Label titulo = new Label("Mis reservas"); // Título de la lista de reservas
        titulo.getStyleClass().add("titulo");

        ListView<Reservas> listaReservas = new ListView<>(); // Lista de reservas
        listaReservas.setItems(FXCollections.observableArrayList(reservaService.findAll()));
        listaReservas.setCellFactory(param -> new ListCell<>() {

            /**
             * Metodo updateItem que actualiza el contenido de una celda de la lista de reservas
             * @param reserva La reserva a mostrar
             * @param empty Si la celda está vacía
             */
            @Override
            protected void updateItem(Reservas reserva, boolean empty) {
                super.updateItem(reserva, empty);
                if (empty || reserva == null) {
                    setText(null);
                } else {
                    Actividades actividad = actividadService.findByID(reserva.getIdActividad());
                    String nombreActividad = "Actividad no encontrada";
                    if (actividad != null) {
                        nombreActividad = actividad.getNombre();
                    }
                    setText(
                        "Reserva #" + reserva.getId() + "\n" +
                        "Actividad: " + nombreActividad + "\n" +
                        "Fecha: " + reserva.getFecha() + "\n" +
                        "Estado: " + reserva.getEstado()
                    );
                }
            }
        });
        Button btnCancelar = new Button("Cancelar reserva");
        btnCancelar.getStyleClass().add("boton-principal");
        btnCancelar.setDisable(true);
        listaReservas.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> btnCancelar.setDisable(newVal == null));
        /**
         * Metodo cancelarReserva que cancela una reserva del escenario
         * @param event El evento de clic en el botón de cancelar
        */
        btnCancelar.setOnAction(event -> {
            Reservas reservaSeleccionada =listaReservas.getSelectionModel().getSelectedItem();
            if (reservaSeleccionada == null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Reserva no seleccionada", "Debes seleccionar una reserva para cancelarla.");
                return;
            }
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Confirmar cancelación");
            confirmacion.setHeaderText(null);
            confirmacion.setContentText("¿Seguro que quieres cancelar esta reserva?");

            if (confirmacion.showAndWait().isEmpty()
                    || confirmacion.getResult() != javafx.scene.control.ButtonType.OK) {
                return;
            }
            boolean cancelada = reservaService.delete(reservaSeleccionada.getId());
            if (cancelada) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Reserva cancelada", "La reserva se ha cancelado correctamente.");
                mostrarReservas();
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se ha podido cancelar la reserva.");
            }
        });
        contenido.getChildren().addAll(titulo, listaReservas, btnCancelar);
        root.setCenter(contenido);
    }

    /**
     * Metodo generarIdReserva que genera un nuevo ID de reserva
     * @return El nuevo ID de reserva
     */
    private Integer generarIdReserva() {
        return reservaService.findAll().size() + 1;
    }

     /**
      * Metodo mostrarIncidencias que muestra las incidencias del escenario
      */
    private void mostrarIncidencias() {
        VBox contenido = crearContenedorPantalla(); // Contenedor de la pantalla

        Label titulo = new Label("Incidencias"); // Título de incidencias
        titulo.getStyleClass().add("titulo");

        TextField campoAsunto = new TextField(); // Campo de asunto
        campoAsunto.setPromptText("Asunto");
        campoAsunto.getStyleClass().add("campo-texto");

        TextArea campoDescripcion = new TextArea(); // Campo de descripción
        campoDescripcion.setPromptText("Descripción");
        campoDescripcion.setWrapText(true);
        campoDescripcion.setPrefRowCount(4);
        campoDescripcion.getStyleClass().add("campo-texto");

        Button btnEnviar = new Button("Enviar incidencia"); // Botón de enviar incidencia
        btnEnviar.getStyleClass().add("boton-principal");

        ListView<Incidencias> listaIncidencias = new ListView<>(); // Lista de incidencias
        listaIncidencias.setItems(FXCollections.observableArrayList(incidenciaService.findAll()));
        listaIncidencias.getStyleClass().add("lista");

        listaIncidencias.setCellFactory(param -> new ListCell<>() {

            /**
             * Metodo updateItem que actualiza el item de la lista de incidencias   
             * @param incidencia La incidencia a mostrar
             * @param empty Si el item está vacío
             */
            @Override
            protected void updateItem(Incidencias incidencia, boolean empty) {
                super.updateItem(incidencia, empty);

                if (empty || incidencia == null) {
                    setText(null);
                } else {
                    setText(
                        "Incidencia #" + incidencia.getId() + "\n" +
                        incidencia.getAsunto() + "\n" +
                        incidencia.getDescripcion() + "\n" +
                        "Estado: " + incidencia.getEstado()
                    );
                }
            }
        });
        btnEnviar.setOnAction(event -> {
            String asunto = campoAsunto.getText();
            String descripcion = campoDescripcion.getText();

            if (asunto == null || asunto.trim().isEmpty()) {
                mostrarAlerta(Alert.AlertType.WARNING, "Validación", "El asunto no puede estar vacío.");
                return;
            }
            if (descripcion == null || descripcion.trim().isEmpty()) {
                mostrarAlerta(Alert.AlertType.WARNING, "Validación", "La descripción no puede estar vacía.");
                return;
            }
            Incidencias incidencia = new Incidencias(
                generarIdIncidencia(),
                1,
                asunto.trim(),
                descripcion.trim(),
                new Date(),
                "ABIERTA"
            );
            boolean guardada = incidenciaService.save(incidencia);
            if (guardada) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Incidencia enviada", "La incidencia se ha registrado correctamente.");
                campoAsunto.clear();
                campoDescripcion.clear();
                mostrarIncidencias();
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se ha podido registrar la incidencia.");
            }
        });
        contenido.getChildren().addAll(
            titulo,
            campoAsunto,
            campoDescripcion,
            btnEnviar,
            listaIncidencias
        );
        root.setCenter(contenido);
    }

    /**
     * Metodo mostrarAlerta que muestra una alerta con un tipo, un título y un mensaje
     * @param tipo El tipo de alerta
     * @param titulo El título de alerta
     * @param mensaje El mensaje de alerta
     */
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Metodo generarIdIncidencia que genera un nuevo ID de incidencia
     * @return El nuevo ID de incidencia
     */
    private Integer generarIdIncidencia() {
        return incidenciaService.findAll().size() + 1;
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