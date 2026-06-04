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
     * Test findByID(Integer)
     * Comprueba que el método findByID con un ID inexistente devuelve null
     */
    @Test
    public void findByIDTestNotFound() {
        Usuarios resultado = usuarioRepository.findByID(-1);
        Assertions.assertNull(resultado);
    }
}
