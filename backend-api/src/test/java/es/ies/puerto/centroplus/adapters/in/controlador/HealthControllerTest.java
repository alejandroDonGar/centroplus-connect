package es.ies.puerto.centroplus.adapters.in.controlador;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.ies.puerto.centroplus.adapters.in.api.HealthResponse;
/**
 * Clase de prueba para el controlador de salud
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
class HealthControllerTest {
    private HealthController healthController;
    /**
     * Configuración inicial para cada prueba
     */
    @BeforeEach
    void setUp() {
        healthController = new HealthController();
    }
    /**
     * Prueba para el método checkHealth
     */
    @Test
    void checkHealthDebeDevolverStatusOk() {
        HealthResponse resultado = healthController.health();
        assertNotNull(resultado);
        assertEquals("UP", resultado.getStatus());
    }
}