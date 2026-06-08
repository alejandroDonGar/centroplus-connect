package es.ies.puerto.centroplus.domain.model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Clase de prueba para el modelo de dominio Usuario
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
class UsuarioTest {
    private Usuario usuario;
    @BeforeEach
    void setUp() {
        usuario = new Usuario(1L, "Juan Pérez", "12345678A", "juan@gmail.com", "666555444", "USER");
    }
    /**
     * Prueba para los métodos getter de Usuario
     */
    @Test
    void testGetters() {
        assertEquals(1L, usuario.getId());
        assertEquals("Juan Pérez", usuario.getNombre());
        assertEquals("12345678A", usuario.getDni());
        assertEquals("juan@gmail.com", usuario.getEmail());
        assertEquals("666555444", usuario.getTelefono());
        assertEquals("USER", usuario.getTipoUsuario());
    }
    /**
     * Prueba para los métodos setter de Usuario
     */
    @Test
    void testSetters() {
        usuario.setId(2L);
        usuario.setNombre("Ana López");
        usuario.setDni("87654321B");
        usuario.setEmail("ana@gmail.com");
        usuario.setTelefono("777888999");
        usuario.setTipoUsuario("ADMIN");
        assertEquals(2L, usuario.getId());
        assertEquals("Ana López", usuario.getNombre());
        assertEquals("87654321B", usuario.getDni());
        assertEquals("ana@gmail.com", usuario.getEmail());
        assertEquals("777888999", usuario.getTelefono());
        assertEquals("ADMIN", usuario.getTipoUsuario());
    }
}