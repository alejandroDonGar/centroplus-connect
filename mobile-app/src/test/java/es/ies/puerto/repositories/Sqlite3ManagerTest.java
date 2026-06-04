package es.ies.puerto.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author AlejandroDonGar
 * @version 1.0.0
 * 
 * Clase Sqlite3ManagerTest
 */
public class Sqlite3ManagerTest {

    /**
     * Test setDatabasePath(String) y getDatabasePath()
     * Comprueba que se puede establecer y obtener la ruta de la base de datos
     */
    @Test
    public void setAndGetDatabasePathTest() {
        String path = "test.db";
        Sqlite3Manager.setDatabasePath(path);
        Assertions.assertEquals(path, Sqlite3Manager.getDatabasePath());
        // Reset to original
        Sqlite3Manager.setDatabasePath("src/main/resources/es/ies/puerto/database/centroplus.db");
    }

    /**
     * Test getConnection()
     * Comprueba que se puede obtener una conexión a la base de datos
     */
    @Test
    public void getConnectionTest() throws SQLException {
        Connection connection = Sqlite3Manager.getConnection();
        Assertions.assertNotNull(connection);
        connection.close();
    }

    /**
     * Test constructor Sqlite3Manager()
     * Comprueba que se puede instanciar la clase (para cobertura)
     */
    @Test
    public void constructorTest() {
        Sqlite3Manager manager = new Sqlite3Manager();
        Assertions.assertNotNull(manager);
    }
}
