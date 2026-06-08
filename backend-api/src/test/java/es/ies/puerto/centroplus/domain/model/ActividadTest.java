package es.ies.puerto.centroplus.domain.model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Clase de prueba para el modelo de dominio Actividad
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
class ActividadTest {
    private Actividad actividad;
    @BeforeEach
    void setUp() {
        actividad = new Actividad(1L, "Yoga", "DEPORTIVA", 60, 25.50, 15, 8);
    }
    /**
     * Prueba para los métodos getter de Actividad
     */
    @Test
    void testGetters() {
        assertEquals(1L, actividad.getId());
        assertEquals("Yoga", actividad.getNombre());
        assertEquals("DEPORTIVA", actividad.getTipoActividad());
        assertEquals(60, actividad.getDuracion());
        assertEquals(25.50, actividad.getPrecio());
        assertEquals(15, actividad.getPlazasMaximas());
        assertEquals(8, actividad.getPlazasOcupadas());
    }
    /**
     * Prueba para los métodos setter de Actividad
     */
    @Test
    void testSetters() {
        actividad.setId(2L);
        actividad.setNombre("Pilates");
        actividad.setTipoActividad("BIENESTAR");
        actividad.setDuracion(45);
        actividad.setPrecio(20.00);
        actividad.setPlazasMaximas(20);
        actividad.setPlazasOcupadas(10);
        assertEquals(2L, actividad.getId());
        assertEquals("Pilates", actividad.getNombre());
        assertEquals("BIENESTAR", actividad.getTipoActividad());
        assertEquals(45, actividad.getDuracion());
        assertEquals(20.00, actividad.getPrecio());
        assertEquals(20, actividad.getPlazasMaximas());
        assertEquals(10, actividad.getPlazasOcupadas());
    }
    /**
     * Prueba para el método getPlazasDisponibles
     */
    @Test
    void testGetPlazasDisponibles() {
        assertEquals(7, actividad.getPlazasDisponibles());
    }
}