package es.ies.puerto.centroplus.domain.model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Clase de prueba para el modelo de dominio Reserva
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
class ReservaTest {
    private Reserva reserva;
    @BeforeEach
    void setUp() {
        reserva = new Reserva(1L, 1L, 1L, "2024-06-08", "CONFIRMADA");
    }
    /**
     * Prueba para los métodos getter de Reserva
     */
    @Test
    void testGetters() {
        assertEquals(1L, reserva.getId());
        assertEquals(1L, reserva.getIdUsuario());
        assertEquals(1L, reserva.getIdActividad());
        assertEquals("2024-06-08", reserva.getFecha());
        assertEquals("CONFIRMADA", reserva.getEstado());
    }
    /**
     * Prueba para los métodos setter de Reserva
     */
    @Test
    void testSetters() {
        reserva.setId(2L);
        reserva.setIdUsuario(2L);
        reserva.setIdActividad(2L);
        reserva.setFecha("2024-06-09");
        reserva.setEstado("CANCELADA");
        assertEquals(2L, reserva.getId());
        assertEquals(2L, reserva.getIdUsuario());
        assertEquals(2L, reserva.getIdActividad());
        assertEquals("2024-06-09", reserva.getFecha());
        assertEquals("CANCELADA", reserva.getEstado());
    }
}