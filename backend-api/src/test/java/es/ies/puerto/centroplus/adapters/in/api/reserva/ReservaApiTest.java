package es.ies.puerto.centroplus.adapters.in.api.reserva;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Clase de prueba para los DTOs de Reserva (Request y Response)
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
class ReservaApiTest {
    private ReservaRequest request;
    private ReservaResponse response;
    /**
     * Configuración inicial para cada prueba
     */
    @BeforeEach
    void setUp() {
        request = new ReservaRequest();
        response = new ReservaResponse(1L, 1L, 1L, "2024-06-08", "CONFIRMADA");
    }
    /**
     * Prueba para los métodos getter y setter de ReservaRequest
     */
    @Test
    void testRequestGettersSetters() {
        request.setIdUsuario(2L);
        request.setIdActividad(2L);
        request.setFecha("2024-06-09");
        request.setEstado("CANCELADA");
        assertEquals(2L, request.getIdUsuario());
        assertEquals(2L, request.getIdActividad());
        assertEquals("2024-06-09", request.getFecha());
        assertEquals("CANCELADA", request.getEstado());
    }
    /**
     * Prueba para los métodos getter y setter de ReservaResponse
     */
    @Test
    void testResponseGettersSetters() {
        response.setId(2L);
        response.setIdUsuario(2L);
        response.setIdActividad(2L);
        response.setFecha("2024-06-09");
        response.setEstado("CANCELADA");
        assertEquals(2L, response.getId());
        assertEquals(2L, response.getIdUsuario());
        assertEquals(2L, response.getIdActividad());
        assertEquals("2024-06-09", response.getFecha());
        assertEquals("CANCELADA", response.getEstado());
    }
}