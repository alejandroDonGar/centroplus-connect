package es.ies.puerto.centroplus.adapters.in.controlador;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import es.ies.puerto.centroplus.adapters.in.api.actividad.ActividadRequest;
import es.ies.puerto.centroplus.adapters.in.api.actividad.ActividadResponse;
import es.ies.puerto.centroplus.business.ports.ActividadServicePort;
import es.ies.puerto.centroplus.domain.model.Actividad;
/**
 * Clase de prueba para el controlador de actividades
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
class ActividadControllerTest {
    private ActividadServicePort actividadServicePort;
    private ActividadController actividadController;
    /**
     * Configuración inicial para cada prueba
     */
    @BeforeEach
    void setUp() {
        actividadServicePort = mock(ActividadServicePort.class);
        actividadController = new ActividadController(actividadServicePort);
    }
    /**
     * Prueba para el método obtenerActividades
     */
    @Test
    void obtenerActividadesDebeDevolverLista() {
        Actividad actividad = new Actividad(1L, "Yoga", "DEPORTIVA", 60, 25.50, 15, 8);
        when(actividadServicePort.findAll()).thenReturn(List.of(actividad));
        List<ActividadResponse> resultado = actividadController.obtenerActividades();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Yoga", resultado.get(0).getNombre());
        verify(actividadServicePort, times(1)).findAll();
    }
    /**
     * Prueba para el método obtenerActividad
     */
    @Test
    void obtenerActividadDebeDevolverActividadSiExiste() {
        Actividad actividad = new Actividad(1L, "Yoga", "DEPORTIVA", 60, 25.50, 15, 8);
        when(actividadServicePort.findById(1L)).thenReturn(Optional.of(actividad));
        ResponseEntity<ActividadResponse> resultado = actividadController.obtenerActividad(1L);
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertNotNull(resultado.getBody());
        assertEquals("Yoga", resultado.getBody().getNombre());
        verify(actividadServicePort, times(1)).findById(1L);
    }
    /**
     * Prueba para el método obtenerActividad cuando no existe
     */
    @Test
    void obtenerActividadDebeDevolverNotFoundSiNoExiste() {
        when(actividadServicePort.findById(99L)).thenReturn(Optional.empty());
        ResponseEntity<ActividadResponse> resultado = actividadController.obtenerActividad(99L);
        assertEquals(HttpStatus.NOT_FOUND, resultado.getStatusCode());
        verify(actividadServicePort, times(1)).findById(99L);
    }
    /**
     * Prueba para el método save
     */
    @Test
    void saveDebeGuardarActividad() {
        ActividadRequest request = new ActividadRequest();
        request.setNombre("Yoga");
        request.setTipoActividad("DEPORTIVA");
        request.setDuracion(60);
        request.setPrecio(25.50);
        request.setPlazasMaximas(15);
        request.setPlazasOcupadas(8);
        Actividad actividadGuardada = new Actividad(1L, "Yoga", "DEPORTIVA", 60, 25.50, 15, 8);
        when(actividadServicePort.save(any(Actividad.class))).thenReturn(actividadGuardada);
        ResponseEntity<ActividadResponse> resultado = actividadController.save(request);
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertNotNull(resultado.getBody());
        assertEquals("Yoga", resultado.getBody().getNombre());
        verify(actividadServicePort, times(1)).save(any(Actividad.class));
    }
    /**
     * Prueba para el método update
     */
    @Test
    void updateDebeActualizarActividadSiExiste() {
        ActividadRequest request = new ActividadRequest();
        request.setNombre("Yoga");
        Actividad actividadActualizada = new Actividad(1L, "Yoga", "DEPORTIVA", 60, 25.50, 15, 8);
        when(actividadServicePort.update(eq(1L), any(Actividad.class))).thenReturn(Optional.of(actividadActualizada));
        ResponseEntity<ActividadResponse> resultado = actividadController.update(1L, request);
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertNotNull(resultado.getBody());
        assertEquals("Yoga", resultado.getBody().getNombre());
        verify(actividadServicePort, times(1)).update(eq(1L), any(Actividad.class));
    }
    /**
     * Prueba para el método delete
     */
    @Test
    void deleteDebeEliminarActividadSiExiste() {
        when(actividadServicePort.delete(1L)).thenReturn(true);
        ResponseEntity<Void> resultado = actividadController.delete(1L);
        assertEquals(HttpStatus.NO_CONTENT, resultado.getStatusCode());
        verify(actividadServicePort, times(1)).delete(1L);
    }
    /**
     * Prueba para el método delete cuando no existe
     */
    @Test
    void deleteDebeDevolverNotFoundSiNoExiste() {
        when(actividadServicePort.delete(99L)).thenReturn(false);
        ResponseEntity<Void> resultado = actividadController.delete(99L);
        assertEquals(HttpStatus.NOT_FOUND, resultado.getStatusCode());
        verify(actividadServicePort, times(1)).delete(99L);
    }
}