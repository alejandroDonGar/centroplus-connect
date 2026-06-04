package es.ies.puerto.controllers;
import es.ies.puerto.modelos.Actividades;
import es.ies.puerto.modelos.Reservas;
import es.ies.puerto.services.ActividadService;
import es.ies.puerto.services.IncidenciaService;
import es.ies.puerto.services.ReservaService;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;
import static org.mockito.Mockito.*;

/**
 * @author AlejandroDonGar
 * @version 1.0.0
 * 
 * Clase MainControllerTest para alcanzar el 100% de cobertura
 */
public class MainControllerTest extends ApplicationTest {

    private MainController mainController;

    @Mock
    private ActividadService actividadService;
    @Mock
    private ReservaService reservaService;
    @Mock
    private IncidenciaService incidenciaService;

    @Override
    public void start(Stage stage) {
        MockitoAnnotations.openMocks(this);
        
        // Comportamiento por defecto de los mocks para evitar NullPointerException
        when(actividadService.findAll()).thenReturn(new ArrayList<>());
        when(reservaService.findAll()).thenReturn(new ArrayList<>());
        when(incidenciaService.findAll()).thenReturn(new ArrayList<>());

        mainController = new MainController(actividadService, reservaService, incidenciaService);
        Scene scene = new Scene(mainController.getView(), 390, 760);
        stage.setScene(scene);
        stage.show();
    }

    @AfterEach
    public void tearDown() throws TimeoutException {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    /**
     * Test constructor por defecto
     */
    @Test
    public void testConstructorDefault() {
        interact(() -> {
            MainController controller = new MainController();
            Assertions.assertNotNull(controller);
        });
    }

    /**
     * Test de navegación entre pantallas
     */
    @Test
    public void testNavigation() {
        interact(() -> clickOn("🎯"));
        interact(() -> clickOn("📅"));
        interact(() -> clickOn("⚠️"));
        interact(() -> clickOn("🏠"));
        
        Assertions.assertNotNull(mainController.getView());
    }

    /**
     * Test mostrar actividades y ver detalle
     */
    @Test
    public void testMostrarActividadesYDetalle() {
        List<Actividades> actividades = new ArrayList<>();
        actividades.add(new Actividades(1, "Yoga", "DEPORTIVA", 60, 20.0, 20, 10));
        when(actividadService.findAll()).thenReturn(actividades);

        clickOn("🎯");
        
        // Buscamos el nodo que contiene el texto "Yoga"
        Node yogaNode = lookup("Yoga").query();
        Assertions.assertNotNull(yogaNode);
        clickOn(yogaNode);
        
        // Verificar que estamos en detalle (buscando el título en la vista de detalle)
        Assertions.assertTrue(lookup("Yoga").queryAll().size() >= 1);
        
        clickOn("Volver a actividades");
    }

    /**
     * Test reservar plaza con éxito
     */
    @Test
    public void testReservarPlazaExito() {
        Actividades actividad = new Actividades(1, "Yoga", "DEPORTIVA", 60, 20.0, 20, 10);
        List<Actividades> actividades = new ArrayList<>();
        actividades.add(actividad);
        
        when(actividadService.findAll()).thenReturn(actividades);
        when(reservaService.save(any())).thenReturn(true);

        clickOn("🎯");
        clickOn("Yoga");
        
        // Clic en reservar
        clickOn("Reservar plaza");
        
        // Dismiss alert con ENTER
        type(KeyCode.ENTER);
        
        verify(reservaService).save(any());
    }

    /**
     * Test enviar incidencia con éxito y validaciones
     */
    @Test
    public void testIncidenciasFlow() {
        when(incidenciaService.save(any())).thenReturn(true);
        
        clickOn("⚠️");
        
        // 1. Validar campos vacíos (disparar alerta y cerrarla)
        clickOn("Enviar Incidencia");
        type(KeyCode.ENTER);

        // 2. Rellenar campos y enviar
        // Usamos lookups por tipo para evitar ClassCastException por la misma clase CSS
        TextField tfAsunto = lookup(n -> n instanceof TextField).queryAs(TextField.class);
        clickOn(tfAsunto).write("Asunto Test");
        
        TextArea taDesc = lookup(n -> n instanceof TextArea).queryAs(TextArea.class);
        clickOn(taDesc).write("Descripcion Test");
        
        clickOn("Enviar Incidencia");
        type(KeyCode.ENTER);
        
        verify(incidenciaService).save(any());
    }

    /**
     * Test mostrar reservas y cancelar una
     */
    @Test
    public void testReservasFlow() {
        List<Reservas> reservas = new ArrayList<>();
        Reservas r = new Reservas(1, 1, 1, new Date(), "ACTIVA");
        reservas.add(r);
        
        when(reservaService.findAll()).thenReturn(reservas);
        // El controlador llama a findByID para mostrar el nombre de la actividad en la lista de reservas
        when(actividadService.findByID(1)).thenReturn(new Actividades(1, "Yoga", "DEPORTIVA", 60, 20.0, 20, 10));
        when(reservaService.delete(1)).thenReturn(true);

        clickOn("📅");
        clickOn("Yoga"); // Seleccionamos la reserva de Yoga
        
        clickOn("Cancelar reserva");
        
        // Dos ENTER: uno para confirmación, otro para éxito
        type(KeyCode.ENTER);
        sleep(500);
        type(KeyCode.ENTER);
        
        verify(reservaService).delete(1);
    }

    /**
     * Test para cubrir ramas de error en reserva e incidencia
     */
    @Test
    public void testErrorBranches() {
        Actividades actividad = new Actividades(1, "Yoga", "DEPORTIVA", 60, 20.0, 20, 10);
        List<Actividades> actividades = new ArrayList<>();
        actividades.add(actividad);
        
        when(actividadService.findAll()).thenReturn(actividades);
        when(reservaService.save(any())).thenReturn(false);
        when(incidenciaService.save(any())).thenReturn(false);
        when(reservaService.delete(any())).thenReturn(false);

        // Error en reserva
        clickOn("🎯");
        clickOn("Yoga");
        clickOn("Reservar plaza");
        type(KeyCode.ENTER); // Cerrar error

        // Error en incidencia
        clickOn("⚠️");
        TextField tfAsunto = lookup(n -> n instanceof TextField).queryAs(TextField.class);
        clickOn(tfAsunto).write("Error");
        TextArea taDesc = lookup(n -> n instanceof TextArea).queryAs(TextArea.class);
        clickOn(taDesc).write("Error");
        clickOn("Enviar Incidencia");
        type(KeyCode.ENTER); // Cerrar error

        // Error en cancelación (sin selección)
        clickOn("📅");
        clickOn("Cancelar reserva");
        type(KeyCode.ENTER); // Cerrar aviso "Reserva no seleccionada"
    }

    /**
     * Test para cubrir el inicio y botón de explorar
     */
    @Test
    public void testInicioYDetalleBoton() {
        clickOn("Explorar Actividades");
        Assertions.assertNotNull(mainController.getView());
    }
}
