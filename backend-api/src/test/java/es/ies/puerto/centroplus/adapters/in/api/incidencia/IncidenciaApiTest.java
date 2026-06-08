package es.ies.puerto.centroplus.adapters.in.api.incidencia;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Clase de prueba para los DTOs de Incidencia (Request y Response)
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
class IncidenciaApiTest {
    private IncidenciaRequest request;
    private IncidenciaResponse response;
    /**
     * Configuración inicial para cada prueba
     */
    @BeforeEach
    void setUp() {
        request = new IncidenciaRequest();
        response = new IncidenciaResponse(1L, 1L, "Asunto", "Descripcion", "2024-06-08", "ABIERTA");
    }
    /**
     * Prueba para los métodos getter y setter de IncidenciaRequest
     */
    @Test
    void testRequestGettersSetters() {
        request.setIdUsuario(2L);
        request.setAsunto("Nuevo Asunto");
        request.setDescripcion("Nueva Descripcion");
        request.setFecha("2024-06-09");
        request.setEstado("CERRADA");
        assertEquals(2L, request.getIdUsuario());
        assertEquals("Nuevo Asunto", request.getAsunto());
        assertEquals("Nueva Descripcion", request.getDescripcion());
        assertEquals("2024-06-09", request.getFecha());
        assertEquals("CERRADA", request.getEstado());
    }
    /**
     * Prueba para los métodos getter y setter de IncidenciaResponse
     */
    @Test
    void testResponseGettersSetters() {
        response.setId(2L);
        response.setIdUsuario(2L);
        response.setAsunto("Nuevo Asunto");
        response.setDescripcion("Nueva Descripcion");
        response.setFecha("2024-06-09");
        response.setEstado("CERRADA");
        assertEquals(2L, response.getId());
        assertEquals(2L, response.getIdUsuario());
        assertEquals("Nuevo Asunto", response.getAsunto());
        assertEquals("Nueva Descripcion", response.getDescripcion());
        assertEquals("2024-06-09", response.getFecha());
        assertEquals("CERRADA", response.getEstado());
    }
}