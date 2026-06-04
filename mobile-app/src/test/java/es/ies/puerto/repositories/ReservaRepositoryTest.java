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
     * Test findByID(Integer)
     * Comprueba que el método findByID con un ID inexistente devuelve null
     */
    @Test
    public void findByIDTestNotFound() {
        Reservas resultado = reservaRepository.findByID(-1);
        Assertions.assertNull(resultado);
    }
}
