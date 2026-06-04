package es.ies.puerto.repositories;
import es.ies.puerto.modelos.Actividades;
import es.ies.puerto.modelos.Reservas;
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

    /**
     * Test update(Actividades) con datos inexistentes
     * Comprueba que intentar actualizar una actividad inexistente devuelve false
     */
    @Test
    public void updateTestFail() {
        boolean resultado = actividadRepository.update(new Actividades(-1, "X", "Y", 0, 0.0, 0, 0));
        Assertions.assertFalse(resultado);
    }

    /**
     * Test delete(Actividades) con datos inexistentes
     * Comprueba que intentar borrar una actividad inexistente devuelve false
     */
    @Test
    public void deleteTestFail() {
        boolean resultado = actividadRepository.delete(new Actividades(-1, null, null, null, null, null, null));
        Assertions.assertFalse(resultado);
    }

    /**
     * Test reservarPlaza(int, int) con datos inexistentes
     * Comprueba que intentar reservar plaza con IDs inexistentes devuelve null
     */
    @Test
    public void reservarPlazaFail() {
        Reservas reserva = actividadRepository.reservarPlaza(-1, -1);
        Assertions.assertNull(reserva);
    }

    /**
     * Test cancelarPlaza(int) con ID inexistente
     * Comprueba que intentar cancelar una plaza inexistente devuelve false
     */
    @Test
    public void cancelarPlazaFail() {
        boolean resultado = actividadRepository.cancelarPlaza(-1);
        Assertions.assertFalse(resultado);
    }

    /**
     * Test findCompletas()
     * Comprueba que el método devuelve una lista de actividades completas
     */
    @Test
    public void findCompletasTest() {
        List<Actividades> completas = actividadRepository.findCompletas();
        Assertions.assertNotNull(completas);
    }

    /**
     * Test save(Actividades) éxito
     * Comprueba que se puede guardar una actividad correctamente
     */
    @Test
    public void saveTestOk() {
        Actividades actividad = new Actividades(999, "Test", "TEST", 10, 10.0, 10, 0);
        boolean resultado = actividadRepository.save(actividad);
        Assertions.assertTrue(resultado);
        // Limpieza
        actividadRepository.delete(actividad);
    }

    /**
     * Test findByID(Integer) éxito
     * Comprueba que el método findByID devuelve la actividad correcta
     */
    @Test
    public void findByIDTestOk() {
        Actividades actividad = new Actividades(998, "Test", "TEST", 10, 10.0, 10, 0);
        actividadRepository.save(actividad);
        Actividades resultado = actividadRepository.findByID(998);
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(998, resultado.getId());
        // Limpieza
        actividadRepository.delete(actividad);
    }

    /**
     * Test update(Actividades) éxito
     * Comprueba que se puede actualizar una actividad correctamente
     */
    @Test
    public void updateTestOk() {
        Actividades actividad = new Actividades(997, "Test", "TEST", 10, 10.0, 10, 0);
        actividadRepository.save(actividad);
        actividad.setNombre("Test Updated");
        boolean resultado = actividadRepository.update(actividad);
        Assertions.assertTrue(resultado);
        // Limpieza
        actividadRepository.delete(actividad);
    }

    /**
     * Test delete(Actividades) éxito
     * Comprueba que se puede borrar una actividad correctamente
     */
    @Test
    public void deleteTestOk() {
        Actividades actividad = new Actividades(996, "Test", "TEST", 10, 10.0, 10, 0);
        actividadRepository.save(actividad);
        boolean resultado = actividadRepository.delete(actividad);
        Assertions.assertTrue(resultado);
    }

    /**
     * Test reservarPlaza(int, int) éxito
     * Comprueba que se puede reservar una plaza correctamente
     */
    @Test
    public void reservarPlazaOk() {
        Actividades actividad = new Actividades(995, "Test", "TEST", 10, 10.0, 10, 0);
        actividadRepository.save(actividad);
        Reservas reserva = actividadRepository.reservarPlaza(1, 995);
        Assertions.assertNotNull(reserva);
        Assertions.assertEquals(995, reserva.getIdActividad());
        // Limpieza
        actividadRepository.delete(actividad);
    }

    /**
     * Test cancelarPlaza(int) éxito
     * Comprueba que se puede cancelar una plaza correctamente
     */
    @Test
    public void cancelarPlazaOk() {
        Actividades actividad = new Actividades(994, "Test", "TEST", 10, 10.0, 10, 1);
        actividadRepository.save(actividad);
        boolean resultado = actividadRepository.cancelarPlaza(994);
        Assertions.assertTrue(resultado);
        // Limpieza
        actividadRepository.delete(actividad);
    }

    /**
     * Test de excepciones (bloques catch)
     * Provoca excepciones cambiando el path de la base de datos a uno inválido
     */
    @Test
    public void testExcepciones() {
        String originalPath = Sqlite3Manager.getDatabasePath();
        Sqlite3Manager.setDatabasePath("/invalid/path/database.db");
        
        Assertions.assertTrue(actividadRepository.findAll().isEmpty());
        Assertions.assertNull(actividadRepository.findByID(1));
        Assertions.assertFalse(actividadRepository.save(new Actividades(1, "X", "X", 1, 1.0, 1, 0)));
        Assertions.assertFalse(actividadRepository.update(new Actividades(1, "X", "X", 1, 1.0, 1, 0)));
        Assertions.assertFalse(actividadRepository.delete(new Actividades(1, "X", "X", 1, 1.0, 1, 0)));
        Assertions.assertNull(actividadRepository.reservarPlaza(1, 1));
        Assertions.assertFalse(actividadRepository.cancelarPlaza(1));
        Assertions.assertTrue(actividadRepository.findCompletas().isEmpty());
        
        Sqlite3Manager.setDatabasePath(originalPath);
     }

    /**
     * Test calcularIngresosTotales(int, double)
     * Comprueba que el cálculo de ingresos es correcto
     */
    @Test
    public void calcularIngresosTotalesTest() {
        Double ingresos = actividadRepository.calcularIngresosTotales(10, 20.0);
        Assertions.assertEquals(200.0, ingresos);
    }
}
