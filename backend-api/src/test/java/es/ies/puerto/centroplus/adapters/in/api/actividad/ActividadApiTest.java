package es.ies.puerto.centroplus.adapters.in.api.actividad;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Clase de prueba para los DTOs de Actividad (Request y Response)
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
class ActividadApiTest {
    private ActividadRequest request;
    private ActividadResponse response;
    /**
     * Configuración inicial para cada prueba
     */
    @BeforeEach
    void setUp() {
        request = new ActividadRequest();
        response = new ActividadResponse(1L, "Yoga", "DEPORTIVA", 60, 25.50, 15, 8, 7);
    }
    /**
     * Prueba para los métodos getter y setter de ActividadRequest
     */
    @Test
    void testRequestGettersSetters() {
        request.setNombre("Pilates");
        request.setTipoActividad("BIENESTAR");
        request.setDuracion(45);
        request.setPrecio(20.00);
        request.setPlazasMaximas(20);
        request.setPlazasOcupadas(10);
        assertEquals("Pilates", request.getNombre());
        assertEquals("BIENESTAR", request.getTipoActividad());
        assertEquals(45, request.getDuracion());
        assertEquals(20.00, request.getPrecio());
        assertEquals(20, request.getPlazasMaximas());
        assertEquals(10, request.getPlazasOcupadas());
    }
    /**
     * Prueba para los métodos getter y setter de ActividadResponse
     */
    @Test
    void testResponseGettersSetters() {
        response.setId(2L);
        response.setNombre("Pilates");
        response.setTipoActividad("BIENESTAR");
        response.setDuracion(45);
        response.setPrecio(20.00);
        response.setPlazasMaximas(20);
        response.setPlazasOcupadas(10);
        response.setPlazasDisponibles(10);
        assertEquals(2L, response.getId());
        assertEquals("Pilates", response.getNombre());
        assertEquals("BIENESTAR", response.getTipoActividad());
        assertEquals(45, response.getDuracion());
        assertEquals(20.00, response.getPrecio());
        assertEquals(20, response.getPlazasMaximas());
        assertEquals(10, response.getPlazasOcupadas());
        assertEquals(10, response.getPlazasDisponibles());
    }
}