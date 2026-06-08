package es.ies.puerto.centroplus.adapters.in.controlador;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import es.ies.puerto.centroplus.adapters.in.api.incidencia.IncidenciaRequest;
import es.ies.puerto.centroplus.adapters.in.api.incidencia.IncidenciaResponse;
import es.ies.puerto.centroplus.business.ports.IncidenciaServicePort;
import es.ies.puerto.centroplus.domain.model.Incidencia;
/**
 * Clase de prueba para el controlador de incidencias
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
class IncidenciaControllerTest {
    private IncidenciaServicePort incidenciaServicePort;
    private IncidenciaController incidenciaController;
    /**
     * Configuración inicial para cada prueba
     */
    @BeforeEach
    void setUp() {
        incidenciaServicePort = mock(IncidenciaServicePort.class);
        incidenciaController = new IncidenciaController(incidenciaServicePort);
    }
    /**
     * Prueba para el método obtenerIncidencias
     */
    @Test
    void obtenerIncidenciasDebeDevolverLista() {
        Incidencia incidencia = new Incidencia(1L, 1L, "Gotera", "Hay una gotera", "2024-06-08", "ABIERTA");
        when(incidenciaServicePort.findAll()).thenReturn(List.of(incidencia));
        List<IncidenciaResponse> resultado = incidenciaController.obtenerIncidencias();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Gotera", resultado.get(0).getAsunto());
        verify(incidenciaServicePort, times(1)).findAll();
    }
    /**
     * Prueba para el método obtenerIncidencia
     */
    @Test
    void obtenerIncidenciaDebeDevolverIncidenciaSiExiste() {
        Incidencia incidencia = new Incidencia(1L, 1L, "Gotera", "Hay una gotera", "2024-06-08", "ABIERTA");
        when(incidenciaServicePort.findById(1L)).thenReturn(Optional.of(incidencia));
        ResponseEntity<IncidenciaResponse> resultado = incidenciaController.obtenerIncidencia(1L);
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertNotNull(resultado.getBody());
        assertEquals("Gotera", resultado.getBody().getAsunto());
        verify(incidenciaServicePort, times(1)).findById(1L);
    }
    /**
     * Prueba para el método save
     */
    @Test
    void saveDebeGuardarIncidencia() {
        IncidenciaRequest request = new IncidenciaRequest();
        request.setAsunto("Gotera");
        Incidencia incidenciaGuardada = new Incidencia(1L, 1L, "Gotera", "Hay una gotera", "2024-06-08", "ABIERTA");
        when(incidenciaServicePort.save(any(Incidencia.class))).thenReturn(incidenciaGuardada);
        ResponseEntity<IncidenciaResponse> resultado = incidenciaController.save(request);
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertNotNull(resultado.getBody());
        assertEquals("Gotera", resultado.getBody().getAsunto());
        verify(incidenciaServicePort, times(1)).save(any(Incidencia.class));
    }
    /**
     * Prueba para el método update
     */
    @Test
    void updateDebeActualizarIncidenciaSiExiste() {
        IncidenciaRequest request = new IncidenciaRequest();
        request.setAsunto("Gotera Actualizada");
        Incidencia incidenciaActualizada = new Incidencia(1L, 1L, "Gotera Actualizada", "Hay una gotera", "2024-06-08", "ABIERTA");
        when(incidenciaServicePort.update(eq(1L), any(Incidencia.class))).thenReturn(Optional.of(incidenciaActualizada));
        ResponseEntity<IncidenciaResponse> resultado = incidenciaController.update(1L, request);
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertNotNull(resultado.getBody());
        assertEquals("Gotera Actualizada", resultado.getBody().getAsunto());
        verify(incidenciaServicePort, times(1)).update(eq(1L), any(Incidencia.class));
    }
    /**
     * Prueba para el método delete
     */
    @Test
    void deleteDebeEliminarIncidenciaSiExiste() {
        when(incidenciaServicePort.delete(1L)).thenReturn(true);
        ResponseEntity<Void> resultado = incidenciaController.delete(1L);
        assertEquals(HttpStatus.NO_CONTENT, resultado.getStatusCode());
        verify(incidenciaServicePort, times(1)).delete(1L);
    }
}