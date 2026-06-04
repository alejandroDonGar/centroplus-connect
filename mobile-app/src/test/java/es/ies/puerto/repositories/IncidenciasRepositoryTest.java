package es.ies.puerto.repositories;
import es.ies.puerto.modelos.Incidencias;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

/**
 * @author AlejandroDonGar
 * @version 1.0.0
 * 
 * Clase IncidenciasRepositoryTest donde implementamos los metodos de test de IncidenciaRepository
 */
public class IncidenciasRepositoryTest {

    private IncidenciaRepository incidenciaRepository;

    @BeforeEach
    public void setUp() {
        incidenciaRepository = new IncidenciaRepository();
        Sqlite3Manager.setDatabasePath("src/main/resources/es/ies/puerto/database/centroplus.db");
    }

    /**
     * Test findAll()
     * Comprueba que el método findAll devuelve una lista
     */
    @Test
    public void findAllTestOk() {
        List<Incidencias> resultado = incidenciaRepository.findAll();
        Assertions.assertNotNull(resultado);
    }

    /**
     * Test findByID(Integer) con un ID inexistente devuelve null
     */
    @Test
    public void findByIDTestNotFound() {
        Incidencias resultado = incidenciaRepository.findByID(-1);
        Assertions.assertNull(resultado);
    }

    /**
     * Test save(Incidencias) con datos inválidos
     * Comprueba que intentar guardar una incidencia nula devuelve false
     */
    @Test
    public void saveTestFail() {
        boolean resultado = incidenciaRepository.save(new Incidencias(-1, null, null, null, null, null));
        Assertions.assertFalse(resultado);
    }

    /**
     * Test update(Incidencias) con datos inexistentes
     * Comprueba que intentar actualizar una incidencia inexistente devuelve false
     */
    @Test
    public void updateTestFail() {
        boolean resultado = incidenciaRepository.update(new Incidencias(-1, 1, "X", "Y", new java.util.Date(), "Z"));
        Assertions.assertFalse(resultado);
    }

    /**
     * Test delete(Integer) con un ID inexistente
     * Comprueba que intentar borrar una incidencia inexistente devuelve false
     */
    @Test
    public void deleteTestFail() {
        boolean resultado = incidenciaRepository.delete(-1);
        Assertions.assertFalse(resultado);
    }

    /**
     * Test save(Incidencias) éxito
     * Comprueba que se puede guardar una incidencia correctamente
     */
    @Test
    public void saveTestOk() {
        Incidencias incidencia = new Incidencias(999, 1, "Test", "Test", new java.util.Date(), "ABIERTA");
        boolean resultado = incidenciaRepository.save(incidencia);
        Assertions.assertTrue(resultado);
        incidenciaRepository.delete(999);
    }

    /**
     * Test update(Incidencias) éxito
     * Comprueba que se puede actualizar una incidencia correctamente
     */
    @Test
    public void updateTestOk() {
        Incidencias incidencia = new Incidencias(998, 1, "Test", "Test", new java.util.Date(), "ABIERTA");
        incidenciaRepository.save(incidencia);
        incidencia.setAsunto("Test Updated");
        boolean resultado = incidenciaRepository.update(incidencia);
        Assertions.assertTrue(resultado);
        incidenciaRepository.delete(998);
    }

    /**
     * Test delete(Integer) éxito
     * Comprueba que se puede eliminar una incidencia correctamente
     */
    @Test
    public void deleteTestOk() {
        Incidencias incidencia = new Incidencias(997, 1, "Test", "Test", new java.util.Date(), "ABIERTA");
        incidenciaRepository.save(incidencia);
        boolean resultado = incidenciaRepository.delete(997);
        Assertions.assertTrue(resultado);
    }

    /**
     * Test findByID(Integer) éxito
     * Comprueba que se puede encontrar una incidencia por su ID
     */
    @Test
    public void findByIDTestOk() {
        Incidencias incidencia = new Incidencias(996, 1, "Test", "Test", new java.util.Date(), "ABIERTA");
        incidenciaRepository.save(incidencia);
        Incidencias resultado = incidenciaRepository.findByID(996);
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(996, resultado.getId());
        incidenciaRepository.delete(996);
    }

    /**
     * Test de excepciones (bloques catch)
     * Provoca fallos de conexión para cubrir los bloques catch del repositorio
     */
    @Test
    public void testExcepciones() {
        String originalPath = Sqlite3Manager.getDatabasePath();
        Sqlite3Manager.setDatabasePath("/invalid/path/database.db");
        
        Assertions.assertTrue(incidenciaRepository.findAll().isEmpty());
        Assertions.assertNull(incidenciaRepository.findByID(1));
        Assertions.assertFalse(incidenciaRepository.save(new Incidencias(1, 1, "X", "X", new java.util.Date(), "X")));
        Assertions.assertFalse(incidenciaRepository.update(new Incidencias(1, 1, "X", "X", new java.util.Date(), "X")));
        Assertions.assertFalse(incidenciaRepository.delete(1));
        Assertions.assertTrue(incidenciaRepository.incidenciasPorIdUsuario(1).isEmpty());
        
        Sqlite3Manager.setDatabasePath(originalPath);
    }
}