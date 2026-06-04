package es.ies.puerto.repositories;
import es.ies.puerto.modelos.Usuarios;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

/**
 * @author AlejandroDonGar
 * @version 1.0.0
 * 
 * Clase UsuarioRepositoryTest donde implementamos los metodos de test de UsuarioRepository
 */
public class UsuarioRepositoryTest {

    private UsuarioRepository usuarioRepository;

    @BeforeEach
    public void setUp() {
        usuarioRepository = new UsuarioRepository();
        Sqlite3Manager.setDatabasePath("src/main/resources/es/ies/puerto/database/centroplus.db");
    }

    /**
     * Test findAll()
     * Comprueba que el método findAll devuelve una lista
     */
    @Test
    public void findAllTestOk() {
        List<Usuarios> resultado = usuarioRepository.findAll();
        Assertions.assertNotNull(resultado);
    }

    /**
     * Test findByID(Integer) con un ID inexistente devuelve null
     */
    @Test
    public void findByIDTestNotFound() {
        Usuarios resultado = usuarioRepository.findByID(-1);
        Assertions.assertNull(resultado);
    }

    /**
     * Test save(Usuarios) con datos inválidos
     * Comprueba que intentar guardar un usuario nulo devuelve false
     */
    @Test
    public void saveTestFail() {
        boolean resultado = usuarioRepository.save(new Usuarios(-1, null, null, null, null, null));
        Assertions.assertFalse(resultado);
    }

    /**
     * Test update(Usuarios) con datos inexistentes
     * Comprueba que intentar actualizar un usuario inexistente devuelve false
     */
    @Test
    public void updateTestFail() {
        boolean resultado = usuarioRepository.update(new Usuarios(-1, "X", "Y", "Z", "W", "V"));
        Assertions.assertFalse(resultado);
    }

    /**
     * Test save(Usuarios) éxito
     * Comprueba que se puede guardar un usuario correctamente
     */
    @Test
    public void saveTestOk() {
        Usuarios usuario = new Usuarios(999, "Test", "12345678A", "test@test.com", "123456789", "CLIENTE");
        boolean resultado = usuarioRepository.save(usuario);
        Assertions.assertTrue(resultado);
        usuarioRepository.delete(usuario);
    }

    /**
     * Test update(Usuarios) éxito
     * Comprueba que se puede actualizar un usuario correctamente
     */
    @Test
    public void updateTestOk() {
        Usuarios usuario = new Usuarios(998, "Test", "12345678B", "test@test.com", "123456789", "CLIENTE");
        usuarioRepository.save(usuario);
        usuario.setNombre("Test Updated");
        boolean resultado = usuarioRepository.update(usuario);
        Assertions.assertTrue(resultado);
        usuarioRepository.delete(usuario);
    }

    /**
     * Test delete(Usuarios) éxito
     * Comprueba que se puede eliminar un usuario correctamente
     */
    @Test
    public void deleteTestOk() {
        Usuarios usuario = new Usuarios(997, "Test", "12345678C", "test@test.com", "123456789", "CLIENTE");
        usuarioRepository.save(usuario);
        boolean resultado = usuarioRepository.delete(usuario);
        Assertions.assertTrue(resultado);
    }

    /**
     * Test findByID(Integer) éxito
     * Comprueba que se puede encontrar un usuario por su ID
     */
    @Test
    public void findByIDTestOk() {
        Usuarios usuario = new Usuarios(996, "Test", "12345678D", "test@test.com", "123456789", "CLIENTE");
        usuarioRepository.save(usuario);
        Usuarios resultado = usuarioRepository.findByID(996);
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(996, resultado.getId());
        usuarioRepository.delete(usuario);
    }

    /**
     * Test de excepciones (bloques catch)
     * Provoca fallos de conexión para cubrir los bloques catch del repositorio
     */
    @Test
    public void testExcepciones() {
        String originalPath = Sqlite3Manager.getDatabasePath();
        Sqlite3Manager.setDatabasePath("/invalid/path/database.db");
        
        Assertions.assertTrue(usuarioRepository.findAll().isEmpty());
        Assertions.assertNull(usuarioRepository.findByID(1));
        Assertions.assertFalse(usuarioRepository.save(new Usuarios(1, "X", "X", "X", "X", "X")));
        Assertions.assertFalse(usuarioRepository.update(new Usuarios(1, "X", "X", "X", "X", "X")));
        Assertions.assertFalse(usuarioRepository.delete(new Usuarios(1, "X", "X", "X", "X", "X")));
        
        Sqlite3Manager.setDatabasePath(originalPath);
    }
}
