package es.ies.puerto.services;
import es.ies.puerto.modelos.Usuarios;
import es.ies.puerto.repositories.interfaces.IUsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

/**
 * @author AlejandroDonGar
 * @version 1.0.0
 * 
 * Clase UsuarioServiceTest
 */
public class UsuarioServiceTest {

    private UsuarioService usuarioService;

    @Mock
    private IUsuarioRepository usuarioRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        usuarioService = new UsuarioService(usuarioRepository);
    }

    /**
     * Test constructor UsuarioService()
     * Comprueba que el constructor sin parámetros inicializa el repositorio
     */
    @Test
    public void constructorDefaultTest() {
        UsuarioService service = new UsuarioService();
        Assertions.assertNotNull(service);
    }

    /**
     * Test findAll()
     * Comprueba que el método findAll llama al repositorio y devuelve la lista
     */
    @Test
    public void findAllTestOk() {
        List<Usuarios> lista = new ArrayList<>();
        lista.add(new Usuarios(1, "Nombre", "12345678A", "email@email.com", "666555444", "ALUMNO"));
        when(usuarioRepository.findAll()).thenReturn(lista);

        List<Usuarios> resultado = usuarioService.findAll();
        Assertions.assertEquals(1, resultado.size());
        verify(usuarioRepository, times(1)).findAll();
    }

    /**
     * Test findByID(Integer) con ID inválido
     * Comprueba que devuelve null si el ID es nulo o negativo
     */
    @Test
    public void findByIDTestInvalid() {
        Assertions.assertNull(usuarioService.findByID(null));
        Assertions.assertNull(usuarioService.findByID(-1));
    }

    /**
     * Test findByID(Integer)
     * Comprueba que el método findByID llama al repositorio con el ID correcto
     */
    @Test
    public void findByIDTestOk() {
        Usuarios usuario = new Usuarios(1, "Nombre", "12345678A", "email@email.com", "666555444", "ALUMNO");
        when(usuarioRepository.findByID(1)).thenReturn(usuario);

        Usuarios resultado = usuarioService.findByID(1);
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals("Nombre", resultado.getNombre());
        verify(usuarioRepository, times(1)).findByID(1);
    }

    /**
     * Test save(Usuarios) con usuario inválido
     * Comprueba que devuelve false si el usuario es nulo
     */
    @Test
    public void saveTestInvalid() {
        Assertions.assertFalse(usuarioService.save(null));
    }

    /**
     * Test save(Usuarios)
     * Comprueba que el método save llama al repositorio si el usuario es válido
     */
    @Test
    public void saveTestOk() {
        Usuarios usuario = new Usuarios(1, "Nombre", "12345678A", "email@email.com", "666555444", "ALUMNO");
        when(usuarioRepository.save(usuario)).thenReturn(true);

        boolean resultado = usuarioService.save(usuario);
        Assertions.assertTrue(resultado);
        verify(usuarioRepository, times(1)).save(usuario);
    }

    /**
     * Test update(Usuarios) con usuario inválido
     * Comprueba que devuelve false si el usuario es nulo
     */
    @Test
    public void updateTestInvalid() {
        Assertions.assertFalse(usuarioService.update(null));
    }

    /**
     * Test update(Usuarios)
     * Comprueba que el método update llama al repositorio si el usuario es válido
     */
    @Test
    public void updateTestOk() {
        Usuarios usuario = new Usuarios(1, "Nombre", "12345678A", "email@email.com", "666555444", "ALUMNO");
        when(usuarioRepository.update(usuario)).thenReturn(true);

        boolean resultado = usuarioService.update(usuario);
        Assertions.assertTrue(resultado);
        verify(usuarioRepository, times(1)).update(usuario);
    }

    /**
     * Test delete(Usuarios) con usuario inválido
     * Comprueba que devuelve false si el usuario es nulo
     */
    @Test
    public void deleteTestInvalid() {
        Assertions.assertFalse(usuarioService.delete(null));
    }

    /**
     * Test delete(Usuarios)
     * Comprueba que el método delete llama al repositorio si el usuario es válido
     */
    @Test
    public void deleteTestOk() {
        Usuarios usuario = new Usuarios(1, "Nombre", "12345678A", "email@email.com", "666555444", "ALUMNO");
        when(usuarioRepository.delete(usuario)).thenReturn(true);

        boolean resultado = usuarioService.delete(usuario);
        Assertions.assertTrue(resultado);
        verify(usuarioRepository, times(1)).delete(usuario);
    }
}
