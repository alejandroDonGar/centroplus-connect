package es.ies.puerto.repositories;

import es.ies.puerto.modelos.Reservas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author AlejandroDonGar
 * @version 1.0.0
 * 
 * Clase ReservaRepositoryTest donde implementamos los metodos de test de ReservaRepository
 */
public class ReservaRepositoryTest {

    private ReservaRepository reservaRepository;

    @BeforeEach
    public void setUp() {
        reservaRepository = new ReservaRepository();
        Sqlite3Manager.setDatabasePath("src/main/resources/es/ies/puerto/database/centroplus.db");
    }

    /**
     * Test findAll()
     * Comprueba que el método findAll devuelve una lista
     */
    @Test
    public void findAllTestOk() {
        List<Reservas> resultado = reservaRepository.findAll();
        Assertions.assertNotNull(resultado);
    }

    /**
     * Test findByID(Integer) con un ID inexistente devuelve null
     */
    @Test
    public void findByIDTestNotFound() {
        Reservas resultado = reservaRepository.findByID(-1);
        Assertions.assertNull(resultado);
    }

    /**
     * Test save(Reservas) con datos inválidos
     * Comprueba que intentar guardar una reserva nula devuelve false
     */
    @Test
    public void saveTestFail() {
        boolean resultado = reservaRepository.save(new Reservas(-1, null, null, null, null));
        Assertions.assertFalse(resultado);
    }

    /**
     * Test update(Reservas) con datos inexistentes
     * Comprueba que intentar actualizar una reserva inexistente devuelve false
     */
    @Test
    public void updateTestFail() {
        boolean resultado = reservaRepository.update(new Reservas(-1, 1, 1, new java.util.Date(), "X"));
        Assertions.assertFalse(resultado);
    }

    /**
     * Test delete(Integer) con un ID inexistente
     * Comprueba que intentar borrar una reserva inexistente devuelve false
     */
    @Test
    public void deleteTestFail() {
        boolean resultado = reservaRepository.delete(-1);
        Assertions.assertFalse(resultado);
    }

    /**
     * Test save(Reservas) éxito
     * Comprueba que se puede guardar una reserva correctamente
     */
    @Test
    public void saveTestOk() {
        Reservas reserva = new Reservas(999, 1, 1, new java.util.Date(), "ACTIVA");
        boolean resultado = reservaRepository.save(reserva);
        Assertions.assertTrue(resultado);
        reservaRepository.delete(999);
    }

    /**
     * Test update(Reservas) éxito
     * Comprueba que se puede actualizar una reserva correctamente
     */
    @Test
    public void updateTestOk() {
        Reservas reserva = new Reservas(998, 1, 1, new java.util.Date(), "ACTIVA");
        reservaRepository.save(reserva);
        reserva.setEstado("CANCELADA");
        boolean resultado = reservaRepository.update(reserva);
        Assertions.assertTrue(resultado);
        reservaRepository.delete(998);
    }

    /**
     * Test delete(Integer) éxito
     * Comprueba que se puede eliminar una reserva correctamente
     */
    @Test
    public void deleteTestOk() {
        Reservas reserva = new Reservas(997, 1, 1, new java.util.Date(), "ACTIVA");
        reservaRepository.save(reserva);
        boolean resultado = reservaRepository.delete(997);
        Assertions.assertTrue(resultado);
    }

    /**
     * Test findByID(Integer) éxito
     * Comprueba que se puede encontrar una reserva por su ID
     */
    @Test
    public void findByIDTestOk() {
        Reservas reserva = new Reservas(996, 1, 1, new java.util.Date(), "ACTIVA");
        reservaRepository.save(reserva);
        Reservas resultado = reservaRepository.findByID(996);
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(996, resultado.getId());
        reservaRepository.delete(996);
    }

    /**
     * Test de excepciones (bloques catch)
     * Provoca fallos de conexión para cubrir los bloques catch del repositorio
     */
    @Test
    public void testExcepciones() {
        String originalPath = Sqlite3Manager.getDatabasePath();
        Sqlite3Manager.setDatabasePath("/invalid/path/database.db");
        
        Assertions.assertTrue(reservaRepository.findAll().isEmpty());
        Assertions.assertNull(reservaRepository.findByID(1));
        Assertions.assertFalse(reservaRepository.save(new Reservas(1, 1, 1, new java.util.Date(), "X")));
        Assertions.assertFalse(reservaRepository.update(new Reservas(1, 1, 1, new java.util.Date(), "X")));
        Assertions.assertFalse(reservaRepository.delete(1));
        Assertions.assertEquals(-1, reservaRepository.numeroDePlazasDisponibles(1));
        
        Sqlite3Manager.setDatabasePath(originalPath);
    }
}