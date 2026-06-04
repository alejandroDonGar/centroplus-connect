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
import javafx.scene.control.Tooltip;
import java.util.Date;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Priority;

/**
 * @author AlejandroDonGar
 * @version 1.0.0
 *
 * Clase MainController
 */
public class MainController {

    private BorderPane root;
    private ActividadService actividadService;
    private ReservaService reservaService;
    private IncidenciaService incidenciaService;
    private HBox navBar;

    /**
     * Constructor de la clase MainController
     */
    public MainController() {
        actividadService = new ActividadService();
        reservaService = new ReservaService();
        incidenciaService = new IncidenciaService();

        root = new BorderPane();
        root.getStyleClass().add("root-mobile");

        navBar = crearNavegacionInferior();
        root.setBottom(navBar);
        mostrarInicio();
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
        actualizarNavActivo(0);
        VBox contenido = crearContenedorPantalla();

        Label titulo = new Label("CentroPlus\nConnect");
        titulo.getStyleClass().add("titulo");
        titulo.setAlignment(Pos.CENTER);

        Label descripcion = new Label("Gestiona tus actividades y reservas desde un solo lugar.");
        descripcion.getStyleClass().add("texto");
        descripcion.setWrapText(true);
        descripcion.setAlignment(Pos.CENTER);

        VBox tarjeta = new VBox(15);
        tarjeta.getStyleClass().add("tarjeta-resumen");
        
        Label lblActividades = new Label("Actividades: " + actividadService.findAll().size());
        lblActividades.getStyleClass().add("card-titulo");
        Label lblReservas = new Label("Reservas: " + reservaService.findAll().size());
        lblReservas.getStyleClass().add("card-titulo");
        Label lblIncidencias = new Label("Incidencias: " + incidenciaService.findAll().size());
        lblIncidencias.getStyleClass().add("card-titulo");
        
        tarjeta.getChildren().addAll(lblActividades, lblReservas, lblIncidencias);

        Button btnActividades = new Button("Explorar Actividades");
        btnActividades.getStyleClass().add("boton-principal");
        btnActividades.setMaxWidth(Double.MAX_VALUE);
        btnActividades.setOnAction(event -> mostrarActividades());

        contenido.getChildren().addAll(titulo, descripcion, tarjeta, btnActividades);
        root.setCenter(contenido);
    }

    /**
     * Metodo mostrarActividades que muestra las actividades del escenario
     */
    private void mostrarActividades() {
        actualizarNavActivo(1);
        VBox contenido = crearContenedorPantalla();

        Label titulo = new Label("Actividades");
        titulo.getStyleClass().add("titulo");

        ListView<Actividades> listaActividades = new ListView<>();
        listaActividades.getStyleClass().add("lista");
        VBox.setVgrow(listaActividades, Priority.ALWAYS);
        listaActividades.setItems(FXCollections.observableArrayList(actividadService.findAll()));
        listaActividades.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Actividades actividad, boolean empty) { 
                super.updateItem(actividad, empty);
                if (empty || actividad == null) {
                    setGraphic(null);
                } else {
                    VBox card = new VBox(5);
                    card.getStyleClass().add("card-container");
                    
                    Label nombre = new Label(actividad.getNombre());
                    nombre.getStyleClass().add("card-titulo");
                    
                    Label tipo = new Label(actividad.getTipoActividad());
                    tipo.getStyleClass().add("card-subtitulo");
                    
                    int plazasDisponibles = actividad.getPlazasMaximas() - actividad.getPlazasOcupadas();
                    Label info = new Label(actividad.getDuracion() + " min · " + actividad.getPrecio() + " € · " + plazasDisponibles + " libres");
                    info.getStyleClass().add("card-info");
                    
                    card.getChildren().addAll(nombre, tipo, info);
                    setGraphic(card);
                }
            }
        });

        listaActividades.setOnMouseClicked(event -> {
            Actividades actividadSeleccionada = listaActividades.getSelectionModel().getSelectedItem();
            if (actividadSeleccionada != null) {
                mostrarDetalleActividad(actividadSeleccionada);
            }
        });

        StackPane stack = new StackPane();
        VBox.setVgrow(stack, Priority.ALWAYS);
        
        Region fadeTop = new Region();
        fadeTop.getStyleClass().add("fade-overlay");
        fadeTop.setMouseTransparent(true);
        StackPane.setAlignment(fadeTop, Pos.TOP_CENTER);
        
        Region fadeBottom = new Region();
        fadeBottom.getStyleClass().add("fade-overlay-bottom");
        fadeBottom.setMouseTransparent(true);
        StackPane.setAlignment(fadeBottom, Pos.BOTTOM_CENTER);

        stack.getChildren().addAll(listaActividades, fadeTop, fadeBottom);
        
        contenido.getChildren().addAll(titulo, stack);
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
        actualizarNavActivo(2);
        VBox contenido = crearContenedorPantalla();

        Label titulo = new Label("Mis Reservas");
        titulo.getStyleClass().add("titulo");

        ListView<Reservas> listaReservas = new ListView<>();
        listaReservas.getStyleClass().add("lista");
        VBox.setVgrow(listaReservas, Priority.ALWAYS);
        listaReservas.setItems(FXCollections.observableArrayList(reservaService.findAll()));
        listaReservas.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Reservas reserva, boolean empty) {
                super.updateItem(reserva, empty);
                if (empty || reserva == null) {
                    setGraphic(null);
                } else {
                    VBox card = new VBox(5);
                    card.getStyleClass().add("card-container");
                    
                    Actividades actividad = actividadService.findByID(reserva.getIdActividad());
                    String nombreActividad = actividad != null ? actividad.getNombre() : "Actividad #" + reserva.getIdActividad();
                    
                    Label nombre = new Label(nombreActividad);
                    nombre.getStyleClass().add("card-titulo");
                    
                    Label estado = new Label(reserva.getEstado());
                    estado.getStyleClass().add("card-subtitulo");
                    
                    Label info = new Label("Reserva #" + reserva.getId() + " · " + reserva.getFecha());
                    info.getStyleClass().add("card-info");
                    
                    card.getChildren().addAll(nombre, estado, info);
                    setGraphic(card);
                }
            }
        });
        Button btnCancelar = new Button("Cancelar reserva");
        btnCancelar.getStyleClass().add("boton-principal");
        btnCancelar.setDisable(true);
        listaReservas.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> btnCancelar.setDisable(newVal == null));

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

        StackPane stack = new StackPane();
        VBox.setVgrow(stack, Priority.ALWAYS);
        
        Region fadeTop = new Region();
        fadeTop.getStyleClass().add("fade-overlay");
        fadeTop.setMouseTransparent(true);
        StackPane.setAlignment(fadeTop, Pos.TOP_CENTER);
        
        Region fadeBottom = new Region();
        fadeBottom.getStyleClass().add("fade-overlay-bottom");
        fadeBottom.setMouseTransparent(true);
        StackPane.setAlignment(fadeBottom, Pos.BOTTOM_CENTER);

        stack.getChildren().addAll(listaReservas, fadeTop, fadeBottom);

        contenido.getChildren().addAll(titulo, stack, btnCancelar);
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
        actualizarNavActivo(3);
        VBox contenido = crearContenedorPantalla();

        Label titulo = new Label("Incidencias");
        titulo.getStyleClass().add("titulo");

        VBox form = new VBox(12);
        form.getStyleClass().add("tarjeta-resumen");
        
        TextField campoAsunto = new TextField();
        campoAsunto.setPromptText("Asunto de la incidencia");
        campoAsunto.getStyleClass().add("campo-texto");

        TextArea campoDescripcion = new TextArea();
        campoDescripcion.setPromptText("Cuéntanos qué ha pasado...");
        campoDescripcion.setWrapText(true);
        campoDescripcion.setPrefRowCount(3);
        campoDescripcion.getStyleClass().add("campo-texto");

        Button btnEnviar = new Button("Enviar Incidencia");
        btnEnviar.getStyleClass().add("boton-principal");
        btnEnviar.setMaxWidth(Double.MAX_VALUE);
        
        form.getChildren().addAll(campoAsunto, campoDescripcion, btnEnviar);

        ListView<Incidencias> listaIncidencias = new ListView<>();
        listaIncidencias.getStyleClass().add("lista");
        VBox.setVgrow(listaIncidencias, Priority.ALWAYS);
        listaIncidencias.setItems(FXCollections.observableArrayList(incidenciaService.findAll()));

        listaIncidencias.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Incidencias incidencia, boolean empty) {
                super.updateItem(incidencia, empty);
                if (empty || incidencia == null) {
                    setGraphic(null);
                } else {
                    VBox card = new VBox(5);
                    card.getStyleClass().add("card-container");
                    
                    Label asunto = new Label(incidencia.getAsunto());
                    asunto.getStyleClass().add("card-titulo");
                    
                    Label estado = new Label(incidencia.getEstado());
                    estado.getStyleClass().add("card-subtitulo");
                    
                    Label desc = new Label(incidencia.getDescripcion());
                    desc.getStyleClass().add("card-info");
                    desc.setWrapText(true);
                    
                    card.getChildren().addAll(asunto, estado, desc);
                    setGraphic(card);
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

        StackPane stack = new StackPane();
        VBox.setVgrow(stack, Priority.ALWAYS);
        
        Region fadeTop = new Region();
        fadeTop.getStyleClass().add("fade-overlay");
        fadeTop.setMouseTransparent(true);
        StackPane.setAlignment(fadeTop, Pos.TOP_CENTER);
        
        Region fadeBottom = new Region();
        fadeBottom.getStyleClass().add("fade-overlay-bottom");
        fadeBottom.setMouseTransparent(true);
        StackPane.setAlignment(fadeBottom, Pos.BOTTOM_CENTER);

        stack.getChildren().addAll(listaIncidencias, fadeTop, fadeBottom);

        contenido.getChildren().addAll(
            titulo,
            form,
            stack
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
        VBox contenedor = new VBox(25);
        contenedor.setPadding(new Insets(30, 25, 30, 25));
        contenedor.setAlignment(Pos.TOP_CENTER);
        return contenedor;
    }

    /**
     * Metodo crearNavegacionInferior que crea una barra de navegación inferior con botones
     * @return La barra de navegación inferior HBox
     */
    private HBox crearNavegacionInferior() {
        HBox navegacion = new HBox(15);
        navegacion.setPadding(new Insets(15));
        navegacion.setAlignment(Pos.CENTER);
        navegacion.getStyleClass().add("nav-inferior");

        Button btnInicio = new Button("🏠");
        btnInicio.getStyleClass().add("boton-nav");
        btnInicio.setTooltip(new Tooltip("Inicio"));
        btnInicio.setOnAction(event -> mostrarInicio());

        Button btnActividades = new Button("🎯");
        btnActividades.getStyleClass().add("boton-nav");
        btnActividades.setTooltip(new Tooltip("Actividades"));
        btnActividades.setOnAction(event -> mostrarActividades());

        Button btnReservas = new Button("📅");
        btnReservas.getStyleClass().add("boton-nav");
        btnReservas.setTooltip(new Tooltip("Reservas"));
        btnReservas.setOnAction(event -> mostrarReservas());

        Button btnIncidencias = new Button("⚠️");
        btnIncidencias.getStyleClass().add("boton-nav");
        btnIncidencias.setTooltip(new Tooltip("Incidencias"));
        btnIncidencias.setOnAction(event -> mostrarIncidencias());

        navegacion.getChildren().addAll(btnInicio, btnActividades, btnReservas, btnIncidencias);
        return navegacion;
    }

    private void actualizarNavActivo(int index) {
        if (navBar == null) return;
        for (int i = 0; i < navBar.getChildren().size(); i++) {
            Button btn = (Button) navBar.getChildren().get(i);
            btn.getStyleClass().remove("boton-nav-activo");
            if (i == index) {
                btn.getStyleClass().add("boton-nav-activo");
            }
        }
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