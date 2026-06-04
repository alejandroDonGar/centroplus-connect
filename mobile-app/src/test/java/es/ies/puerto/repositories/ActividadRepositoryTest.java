package es.ies.puerto.repositories;
import es.ies.puerto.modelos.Actividades;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

/**
 * @author AlejandroDonGar
 * @version 1.0.0
 * 
 * Clase ActividadRepositoryTest donde implementamos los metodos de test de ActividadRepository
 */
public class ActividadRepositoryTest {

    private ActividadRepository actividadRepository;

    @BeforeEach
    public void setUp() {
        actividadRepository = new ActividadRepository();
        // Usamos la base de datos de test o la configurada por defecto
        Sqlite3Manager.setDatabasePath("src/main/resources/es/ies/puerto/database/centroplus.db");
    }

    /**
     * Test findAll()
     * Comprueba que el método findAll devuelve una lista (vacía o con datos)
     */
    @Test
    public void findAllTestOk() {
        List<Actividades> resultado = actividadRepository.findAll();
        Assertions.assertNotNull(resultado);
    }

    /**
     * Test findByID(Integer)
     * Comprueba que el método findByID con un ID inexistente devuelve null
     */
    @Test
    public void findByIDTestNotFound() {
        Actividades resultado = actividadRepository.findByID(-1);
        Assertions.assertNull(resultado);
    }

    /**
     * Test save(Actividades) con datos nulos
     * Comprueba que intentar guardar una actividad nula lanza excepción o devuelve false
     */
    @Test
    public void saveTestNull() {
        try {
            boolean resultado = actividadRepository.save(null);
            Assertions.assertFalse(resultado);
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }
    }
}
