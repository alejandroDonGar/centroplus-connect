package es.ies.puerto.repositories;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * @author AlejandroDonGar y JavierReyPer
 * @version 1.0.0
 * 
 * Clase Sqlite3Manager que maneja la conexión con la base de datos
 */
public class Sqlite3Manager {

    private static String databasePath = "mobile-app/src/main/resources/es/ies/puerto/database/centroplus.db";
    
    public static void setDatabasePath(String path) { 
        databasePath = path; 
    }
    public static String getDatabasePath() { 
        return databasePath; 
    }
    public static Connection getConnection() throws SQLException { 
        Connection c = DriverManager.getConnection("jdbc:sqlite:"+databasePath); 
        try(Statement st = c.createStatement()) { 
            st.execute("PRAGMA foreign_keys = ON"); 
        } 
        return c; 
    }
}
