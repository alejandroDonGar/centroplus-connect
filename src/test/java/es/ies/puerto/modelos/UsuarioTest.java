package es.ies.puerto.modelos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author AlejandroDonGar y JavierReyPer
 * @version 1.0.0
 * 
 * Clase UsuarioTest
 */
public class UsuarioTest {

    @Test
    public void constructorTest() {
        Usuarios usuario = new Usuarios(1, "Nombre", "12345678A", "email@email.com", "666555444", "ALUMNO");
        Assertions.assertEquals(1, usuario.getId());
        Assertions.assertEquals("Nombre", usuario.getNombre());
    }
}
