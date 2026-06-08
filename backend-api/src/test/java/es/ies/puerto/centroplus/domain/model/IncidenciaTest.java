package es.ies.puerto.centroplus.domain.model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Clase de prueba para el modelo de dominio Incidencia
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
class IncidenciaTest {
    private Incidencia incidencia;
    @BeforeEach
    void setUp() {
        incidencia = new Incidencia(1L, 1L, "Asunto", "Descripcion", "2024-06-08", "ABIERTA");
    }
    /**
     * Prueba para los métodos getter de Incidencia
     */
    @Test
    void testGetters() {
        assertEquals(1L, incidencia.getId());
        assertEquals(1L, incidencia.getIdUsuario());
        assertEquals("Asunto", incidencia.getAsunto());
        assertEquals("Descripcion", incidencia.getDescripcion());
        assertEquals("2024-06-08", incidencia.getFecha());
        assertEquals("ABIERTA", incidencia.getEstado());
    }
    /**
     * Prueba para los métodos setter de Incidencia
     */
    @Test
    void testSetters() {
        incidencia.setId(2L);
        incidencia.setIdUsuario(2L);
        incidencia.setAsunto("Nuevo Asunto");
        incidencia.setDescripcion("Nueva Descripcion");
        incidencia.setFecha("2024-06-09");
        incidencia.setEstado("CERRADA");
        assertEquals(2L, incidencia.getId());
        assertEquals(2L, incidencia.getIdUsuario());
        assertEquals("Nuevo Asunto", incidencia.getAsunto());
        assertEquals("Nueva Descripcion", incidencia.getDescripcion());
        assertEquals("2024-06-09", incidencia.getFecha());
        assertEquals("CERRADA", incidencia.getEstado());
    }
}