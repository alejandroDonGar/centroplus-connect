package es.ies.puerto.centroplus.adapters.in.api.usuario;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Clase de prueba para los DTOs de Usuario (Request y Response)
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
class UsuarioApiTest {
    private UsuarioRequest request;
    private UsuarioResponse response;
    /**
     * Configuración inicial para cada prueba
     */
    @BeforeEach
    void setUp() {
        request = new UsuarioRequest();
        response = new UsuarioResponse(1L, "Juan Pérez", "12345678A", "juan@gmail.com", "666555444", "USER");
    }
    /**
     * Prueba para los métodos getter y setter de UsuarioRequest
     */
    @Test
    void testRequestGettersSetters() {
        request.setNombre("Ana López");
        request.setDni("87654321B");
        request.setEmail("ana@gmail.com");
        request.setTelefono("777888999");
        request.setTipoUsuario("ADMIN");
        assertEquals("Ana López", request.getNombre());
        assertEquals("87654321B", request.getDni());
        assertEquals("ana@gmail.com", request.getEmail());
        assertEquals("777888999", request.getTelefono());
        assertEquals("ADMIN", request.getTipoUsuario());
    }
    /**
     * Prueba para los métodos getter y setter de UsuarioResponse
     */
    @Test
    void testResponseGettersSetters() {
        response.setId(2L);
        response.setNombre("Ana López");
        response.setDni("87654321B");
        response.setEmail("ana@gmail.com");
        response.setTelefono("777888999");
        response.setTipoUsuario("ADMIN");
        assertEquals(2L, response.getId());
        assertEquals("Ana López", response.getNombre());
        assertEquals("87654321B", response.getDni());
        assertEquals("ana@gmail.com", response.getEmail());
        assertEquals("777888999", response.getTelefono());
        assertEquals("ADMIN", response.getTipoUsuario());
    }
}