package es.ies.puerto.services;
import es.ies.puerto.modelos.Incidencias;
import es.ies.puerto.repositories.interfaces.IIncidenciaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.mockito.Mockito.*;

/**
 * @author AlejandroDonGar
 * @version 1.0.0
 * 
 * Clase IncidenciasServiceTest
 */
public class IncidenciasServiceTest {

    private IncidenciaService incidenciaService;

    @Mock
    private IIncidenciaRepository incidenciaRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        incidenciaService = new IncidenciaService(incidenciaRepository);
    }

    /**
     * Test constructor IncidenciaService()
     * Comprueba que el constructor sin parámetros inicializa el repositorio
     */
    @Test
    public void constructorDefaultTest() {
        IncidenciaService service = new IncidenciaService();
        Assertions.assertNotNull(service);
    }

    /**
     * Test findAll()
     * Comprueba que el método findAll llama al repositorio y devuelve la lista
     */
    @Test
    public void findAllTestOk() {
        List<Incidencias> lista = new ArrayList<>();
        lista.add(new Incidencias(1, 1, "Asunto", "Descripcion", new Date(), "ABIERTA"));
        when(incidenciaRepository.findAll()).thenReturn(lista);

        List<Incidencias> resultado = incidenciaService.findAll();
        Assertions.assertEquals(1, resultado.size());
        verify(incidenciaRepository, times(1)).findAll();
    }

    /**
     * Test findByID(Integer) con ID inválido
     * Comprueba que devuelve null si el ID es nulo o negativo
     */
    @Test
    public void findByIDTestInvalid() {
        Assertions.assertNull(incidenciaService.findByID(null));
        Assertions.assertNull(incidenciaService.findByID(-1));
    }

    /**
     * Test findByID(Integer)
     * Comprueba que el método findByID llama al repositorio con el ID correcto
     */
    @Test
    public void findByIDTestOk() {
        Incidencias incidencia = new Incidencias(1, 1, "Asunto", "Descripcion", new Date(), "ABIERTA");
        when(incidenciaRepository.findByID(1)).thenReturn(incidencia);

        Incidencias resultado = incidenciaService.findByID(1);
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals("Asunto", resultado.getAsunto());
        verify(incidenciaRepository, times(1)).findByID(1);
    }

    /**
     * Test save(Incidencias) con incidencia inválida
     * Comprueba que devuelve false si la incidencia es nula
     */
    @Test
    public void saveTestInvalid() {
        Assertions.assertFalse(incidenciaService.save(null));
    }

    /**
     * Test save(Incidencias)
     * Comprueba que el método save llama al repositorio si la incidencia es válida
     */
    @Test
    public void saveTestOk() {
        Incidencias incidencia = new Incidencias(1, 1, "Asunto", "Descripcion", new Date(), "ABIERTA");
        when(incidenciaRepository.save(incidencia)).thenReturn(true);

        boolean resultado = incidenciaService.save(incidencia);
        Assertions.assertTrue(resultado);
        verify(incidenciaRepository, times(1)).save(incidencia);
    }

    /**
     * Test update(Incidencias) con incidencia inválida
     * Comprueba que devuelve false si la incidencia es nula
     */
    @Test
    public void updateTestInvalid() {
        Assertions.assertFalse(incidenciaService.update(null));
    }

    /**
     * Test update(Incidencias)
     * Comprueba que el método update llama al repositorio si la incidencia es válida
     */
    @Test
    public void updateTestOk() {
        Incidencias incidencia = new Incidencias(1, 1, "Asunto", "Descripcion", new Date(), "ABIERTA");
        when(incidenciaRepository.update(incidencia)).thenReturn(true);

        boolean resultado = incidenciaService.update(incidencia);
        Assertions.assertTrue(resultado);
        verify(incidenciaRepository, times(1)).update(incidencia);
    }

    /**
     * Test delete(Integer) con ID inválido
     * Comprueba que devuelve false si el ID es nulo
     */
    @Test
    public void deleteTestInvalid() {
        Assertions.assertFalse(incidenciaService.delete(null));
    }

    /**
     * Test delete(Integer)
     * Comprueba que el método delete llama al repositorio con el ID correcto
     */
    @Test
    public void deleteTestOk() {
        when(incidenciaRepository.delete(1)).thenReturn(true);

        boolean resultado = incidenciaService.delete(1);
        Assertions.assertTrue(resultado);
        verify(incidenciaRepository, times(1)).delete(1);
    }

    /**
     * Test incidenciasPorIdUsuario(Integer) con ID inválido
     * Comprueba que devuelve una lista vacía si el ID es inválido
     */
    @Test
    public void incidenciasPorIdUsuarioTestInvalid() {
        List<Incidencias> resultado = incidenciaService.incidenciasPorIdUsuario(null);
        Assertions.assertTrue(resultado.isEmpty());
    }

    /**
     * Test incidenciasPorIdUsuario(Integer)
     * Comprueba que el método incidenciasPorIdUsuario llama al repositorio y devuelve una lista
     */
    @Test
    public void incidenciasPorIdUsuarioTest() {
        when(incidenciaRepository.incidenciasPorIdUsuario(1)).thenReturn(new ArrayList<>());
        List<Incidencias> resultado = incidenciaService.incidenciasPorIdUsuario(1);
        Assertions.assertNotNull(resultado);
    }
}
