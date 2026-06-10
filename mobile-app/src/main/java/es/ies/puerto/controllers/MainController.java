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
import java.text.SimpleDateFormat;
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
        this(new ActividadService(), new ReservaService(), new IncidenciaService());
    }

    /**
     * Constructor con inyección de dependencias para testing
     */
    public MainController(ActividadService actividadService, ReservaService reservaService, IncidenciaService incidenciaService) {
        this.actividadService = actividadService;
        this.reservaService = reservaService;
        this.incidenciaService = incidenciaService;

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

        Double ingresosTotales = 0.0;
        for (Actividades a : actividadService.findAll()) {
            ingresosTotales += a.getPrecio() * a.getPlazasOcupadas();
        }
        Label lblIngresos = new Label("Ingresos Totales: " + ingresosTotales + " €");
        lblIngresos.getStyleClass().add("card-subtitulo");
        tarjeta.getChildren().add(lblIngresos);

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

        // Barra de búsqueda
        HBox busquedaBox = new HBox(10);
        TextField campoBusqueda = new TextField();
        campoBusqueda.setPromptText("Buscar por ID o 'completas'");
        campoBusqueda.getStyleClass().add("campo-texto");
        campoBusqueda.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(campoBusqueda, Priority.ALWAYS);
        Button btnBuscar = new Button("🔍");
        btnBuscar.getStyleClass().add("boton-secundario");

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

        btnBuscar.setOnAction(event -> {
            String texto = campoBusqueda.getText().trim().toLowerCase();
            if (texto.equals("completas")) {
                listaActividades.setItems(FXCollections.observableArrayList(actividadService.findCompletas()));
            } else {
                try {
                    int id = Integer.parseInt(texto);
                    Actividades a = actividadService.findByID(id);
                    if (a != null) {
                        listaActividades.setItems(FXCollections.observableArrayList(a));
                    } else {
                        listaActividades.setItems(FXCollections.emptyObservableList());
                    }
                } catch (NumberFormatException e) {
                    listaActividades.setItems(FXCollections.observableArrayList(actividadService.findAll()));
                }
            }
        });

        busquedaBox.getChildren().addAll(campoBusqueda, btnBuscar);

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
        
        contenido.getChildren().addAll(titulo, busquedaBox, stack);
        root.setCenter(contenido);
    }

    /**
     * Metodo mostrarDetalleActividad que muestra el detalle de una actividad del escenario
     * @param actividad La actividad a mostrar
     */
    private void mostrarDetalleActividad(Actividades actividad) {
        VBox contenido = crearContenedorPantalla();

        Label titulo = new Label(actividad.getNombre());
        titulo.getStyleClass().add("titulo");
        titulo.setWrapText(true);

        int plazasDisponibles = actividad.getPlazasMaximas() - actividad.getPlazasOcupadas();

        Label tipo = new Label("Tipo: " + actividad.getTipoActividad());
        tipo.getStyleClass().add("texto");

        Label duracion = new Label("Duración: " + actividad.getDuracion() + " minutos");
        duracion.getStyleClass().add("texto");

        Label precio = new Label("Precio: " + actividad.getPrecio() + "€");
        precio.getStyleClass().add("texto");

        Label plazas = new Label("Plazas: " + plazasDisponibles);
        plazas.getStyleClass().add("texto");

        Button btnReservar = new Button("Reservar plaza");
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

        Button btnEditar = new Button("Editar Actividad");
        btnEditar.getStyleClass().add("boton-secundario");
        btnEditar.setOnAction(event -> mostrarFormularioEditarActividad(actividad));

        Button btnVolver = new Button("Volver a actividades");
        btnVolver.getStyleClass().add("boton-secundario");
        btnVolver.setOnAction(event -> mostrarActividades());

        contenido.getChildren().addAll(titulo, tipo, duracion, precio, plazas, btnReservar, btnEditar, btnVolver);

        root.setCenter(contenido);
    }

    /**
     * Metodo mostrarFormularioEditarActividad que muestra el formulario de edición de una actividad del escenario
     * @param actividad La actividad a editar
     */
    private void mostrarFormularioEditarActividad(Actividades actividad) {
        VBox contenido = crearContenedorPantalla();

        Label titulo = new Label("Editar Actividad");
        titulo.getStyleClass().add("titulo");

        TextField campoNombre = new TextField(actividad.getNombre());
        campoNombre.setPromptText("Nombre de la actividad");
        campoNombre.getStyleClass().add("campo-texto");

        TextField campoTipo = new TextField(actividad.getTipoActividad());
        campoTipo.setPromptText("Tipo de actividad");
        campoTipo.getStyleClass().add("campo-texto");

        TextField campoDuracion = new TextField(String.valueOf(actividad.getDuracion()));
        campoDuracion.setPromptText("Duración en minutos");
        campoDuracion.getStyleClass().add("campo-texto");

        TextField campoPrecio = new TextField(String.valueOf(actividad.getPrecio()));
        campoPrecio.setPromptText("Precio");
        campoPrecio.getStyleClass().add("campo-texto");

        TextField campoPlazasMax = new TextField(String.valueOf(actividad.getPlazasMaximas()));
        campoPlazasMax.setPromptText("Plazas máximas");
        campoPlazasMax.getStyleClass().add("campo-texto");

        TextField campoPlazasOcup = new TextField(String.valueOf(actividad.getPlazasOcupadas()));
        campoPlazasOcup.setPromptText("Plazas ocupadas");
        campoPlazasOcup.getStyleClass().add("campo-texto");

        Button btnGuardar = new Button("Guardar Cambios");
        btnGuardar.getStyleClass().add("boton-principal");
        btnGuardar.setOnAction(event -> {
            try {
                actividad.setNombre(campoNombre.getText());
                actividad.setTipoActividad(campoTipo.getText());
                actividad.setDuracion(Integer.parseInt(campoDuracion.getText()));
                actividad.setPrecio(Double.parseDouble(campoPrecio.getText()));
                actividad.setPlazasMaximas(Integer.parseInt(campoPlazasMax.getText()));
                actividad.setPlazasOcupadas(Integer.parseInt(campoPlazasOcup.getText()));

                boolean actualizado = actividadService.update(actividad);
                if (actualizado) {
                    mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Actividad actualizada correctamente.");
                    mostrarActividades();
                } else {
                    mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se ha podido actualizar la actividad.");
                }
            } catch (NumberFormatException e) {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "Por favor, introduce valores numéricos válidos.");
            }
        });

        Button btnVolver = new Button("Volver");
        btnVolver.getStyleClass().add("boton-secundario");
        btnVolver.setOnAction(event -> mostrarDetalleActividad(actividad));

        contenido.getChildren().addAll(titulo, campoNombre, campoTipo, campoDuracion, campoPrecio, campoPlazasMax, campoPlazasOcup, btnGuardar, btnVolver);
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

        // Barra de búsqueda por ID
        HBox busquedaBox = new HBox(10);
        TextField campoBusqueda = new TextField();
        campoBusqueda.setPromptText("Buscar por ID de reserva");
        campoBusqueda.getStyleClass().add("campo-texto");
        campoBusqueda.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(campoBusqueda, Priority.ALWAYS);
        Button btnBuscar = new Button("🔍");
        btnBuscar.getStyleClass().add("boton-secundario");

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

        btnBuscar.setOnAction(event -> {
            String texto = campoBusqueda.getText().trim();
            try {
                int id = Integer.parseInt(texto);
                Reservas r = reservaService.findByID(id);
                if (r != null) {
                    listaReservas.setItems(FXCollections.observableArrayList(r));
                } else {
                    listaReservas.setItems(FXCollections.emptyObservableList());
                }
            } catch (NumberFormatException e) {
                listaReservas.setItems(FXCollections.observableArrayList(reservaService.findAll()));
            }
        });

        busquedaBox.getChildren().addAll(campoBusqueda, btnBuscar);

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

        Button btnEditar = new Button("Editar Reserva");
        btnEditar.getStyleClass().add("boton-secundario");
        btnEditar.setDisable(true);
        listaReservas.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> btnEditar.setDisable(newVal == null));
        btnEditar.setOnAction(event -> {
            Reservas reservaSeleccionada = listaReservas.getSelectionModel().getSelectedItem();
            if (reservaSeleccionada != null) {
                mostrarFormularioEditarReserva(reservaSeleccionada);
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

        contenido.getChildren().addAll(titulo, busquedaBox, stack, btnCancelar, btnEditar);
        root.setCenter(contenido);
    }
    
    /**
     * Metodo mostrarFormularioEditarReserva que muestra el formulario de edición de una reserva del escenario
     * @param reserva La reserva a editar
     */
    private void mostrarFormularioEditarReserva(Reservas reserva) {
        VBox contenido = crearContenedorPantalla();

        Label titulo = new Label("Editar Reserva");
        titulo.getStyleClass().add("titulo");

        TextField campoIdUsuario = new TextField(String.valueOf(reserva.getIdUsuario()));
        campoIdUsuario.setPromptText("ID de usuario");
        campoIdUsuario.getStyleClass().add("campo-texto");

        TextField campoIdActividad = new TextField(String.valueOf(reserva.getIdActividad()));
        campoIdActividad.setPromptText("ID de actividad");
        campoIdActividad.getStyleClass().add("campo-texto");

        TextField campoEstado = new TextField(reserva.getEstado());
        campoEstado.setPromptText("Estado");
        campoEstado.getStyleClass().add("campo-texto");

        Button btnGuardar = new Button("Guardar Cambios");
        btnGuardar.getStyleClass().add("boton-principal");
        btnGuardar.setOnAction(event -> {
            try {
                reserva.setIdUsuario(Integer.parseInt(campoIdUsuario.getText()));
                reserva.setIdActividad(Integer.parseInt(campoIdActividad.getText()));
                reserva.setEstado(campoEstado.getText());

                boolean actualizado = reservaService.update(reserva);
                if (actualizado) {
                    mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Reserva actualizada correctamente.");
                    mostrarReservas();
                } else {
                    mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se ha podido actualizar la reserva.");
                }
            } catch (NumberFormatException e) {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "Por favor, introduce valores numéricos válidos.");
            }
        });

        Button btnVolver = new Button("Volver");
        btnVolver.getStyleClass().add("boton-secundario");
        btnVolver.setOnAction(event -> mostrarReservas());

        contenido.getChildren().addAll(titulo, campoIdUsuario, campoIdActividad, campoEstado, btnGuardar, btnVolver);
        root.setCenter(contenido);
    }

    /**
     * Metodo generarIdReserva que genera un nuevo ID de reserva buscando el maximo actual
     * @return El nuevo ID de reserva
     */
    private Integer generarIdReserva() {
        return reservaService.findAll().stream()
                .mapToInt(r -> r.getId())
                .max()
                .orElse(0) + 1;
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

        // Barra de búsqueda por ID o ID de usuario
        HBox busquedaBox = new HBox(10);
        TextField campoBusqueda = new TextField();
        campoBusqueda.setPromptText("Buscar por ID de incidencia o ID de usuario");
        campoBusqueda.getStyleClass().add("campo-texto");
        campoBusqueda.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(campoBusqueda, Priority.ALWAYS);
        Button btnBuscar = new Button("🔍");
        btnBuscar.getStyleClass().add("boton-secundario");

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
                    
                    Label idUsuario = new Label("ID Usuario: " + incidencia.getIdUsuario());
                    idUsuario.getStyleClass().add("card-info");
                    
                    card.getChildren().addAll(asunto, estado, idUsuario, desc);
                    setGraphic(card);
                }
            }
        });

        btnBuscar.setOnAction(event -> {
            String texto = campoBusqueda.getText().trim();
            try {
                int id = Integer.parseInt(texto);
                Incidencias i = incidenciaService.findByID(id);
                if (i != null) {
                    listaIncidencias.setItems(FXCollections.observableArrayList(i));
                } else {
                    listaIncidencias.setItems(FXCollections.observableArrayList(incidenciaService.incidenciasPorIdUsuario(id)));
                }
            } catch (NumberFormatException e) {
                listaIncidencias.setItems(FXCollections.observableArrayList(incidenciaService.findAll()));
            }
        });

        busquedaBox.getChildren().addAll(campoBusqueda, btnBuscar);

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

        Button btnEditar = new Button("Editar Incidencia");
        btnEditar.getStyleClass().add("boton-secundario");
        btnEditar.setDisable(true);
        listaIncidencias.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> btnEditar.setDisable(newVal == null));
        btnEditar.setOnAction(event -> {
            Incidencias incidenciaSeleccionada = listaIncidencias.getSelectionModel().getSelectedItem();
            if (incidenciaSeleccionada != null) {
                mostrarFormularioEditarIncidencia(incidenciaSeleccionada);
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
            busquedaBox,
            stack,
            btnEditar
        );
        root.setCenter(contenido);
    }
    
    /**
     * Metodo mostrarFormularioEditarIncidencia que muestra el formulario de edición de una incidencia del escenario
     * @param incidencia La incidencia a editar
     */
    private void mostrarFormularioEditarIncidencia(Incidencias incidencia) {
        VBox contenido = crearContenedorPantalla();

        Label titulo = new Label("Editar Incidencia");
        titulo.getStyleClass().add("titulo");

        TextField campoIdUsuario = new TextField(String.valueOf(incidencia.getIdUsuario()));
        campoIdUsuario.setPromptText("ID de usuario");
        campoIdUsuario.getStyleClass().add("campo-texto");

        TextField campoAsunto = new TextField(incidencia.getAsunto());
        campoAsunto.setPromptText("Asunto");
        campoAsunto.getStyleClass().add("campo-texto");

        TextArea campoDescripcion = new TextArea(incidencia.getDescripcion());
        campoDescripcion.setPromptText("Descripción");
        campoDescripcion.setWrapText(true);
        campoDescripcion.setPrefRowCount(3);
        campoDescripcion.getStyleClass().add("campo-texto");

        TextField campoEstado = new TextField(incidencia.getEstado());
        campoEstado.setPromptText("Estado");
        campoEstado.getStyleClass().add("campo-texto");

        Button btnGuardar = new Button("Guardar Cambios");
        btnGuardar.getStyleClass().add("boton-principal");
        btnGuardar.setOnAction(event -> {
            try {
                incidencia.setIdUsuario(Integer.parseInt(campoIdUsuario.getText()));
                incidencia.setAsunto(campoAsunto.getText());
                incidencia.setDescripcion(campoDescripcion.getText());
                incidencia.setEstado(campoEstado.getText());

                boolean actualizado = incidenciaService.update(incidencia);
                if (actualizado) {
                    mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Incidencia actualizada correctamente.");
                    mostrarIncidencias();
                } else {
                    mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se ha podido actualizar la incidencia.");
                }
            } catch (NumberFormatException e) {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "Por favor, introduce valores numéricos válidos.");
            }
        });

        Button btnVolver = new Button("Volver");
        btnVolver.getStyleClass().add("boton-secundario");
        btnVolver.setOnAction(event -> mostrarIncidencias());

        contenido.getChildren().addAll(titulo, campoIdUsuario, campoAsunto, campoDescripcion, campoEstado, btnGuardar, btnVolver);
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
     * Metodo generarIdIncidencia que genera un nuevo ID de incidencia buscando el maximo actual
     * @return El nuevo ID de incidencia
     */
    private Integer generarIdIncidencia() {
        return incidenciaService.findAll().stream()
                .mapToInt(i -> i.getId())
                .max()
                .orElse(0) + 1;
    }

    /**
     * Metodo crearContenedorPantalla que crea un contenedor VBox con un espacio entre los elementos
     * @return El contenedor VBox
     */
    private VBox crearContenedorPantalla() {
        VBox contenedor = new VBox(20);
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
}
