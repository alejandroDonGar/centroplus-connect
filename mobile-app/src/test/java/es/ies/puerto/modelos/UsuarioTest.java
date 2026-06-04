package es.ies.puerto.modelos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author AlejandroDonGar
 * @version 1.0.0
 * 
 * Clase UsuarioTest
 */
public class UsuarioTest {

    // * ---- Constructor ----
    /**
     * Test constructor Usuarios(Integer, String, String, String, String, String)
     * Comprueba que el constructor con parámetros devuelve los valores correctos
     */
    @Test
    public void constructorTestOk() {
        Usuarios usuario = new Usuarios(1, "Nombre", "12345678A", "email@email.com", "666555444", "ALUMNO");
        Assertions.assertEquals(1, usuario.getId());
        Assertions.assertEquals("Nombre", usuario.getNombre());
        Assertions.assertEquals("12345678A", usuario.getDni());
        Assertions.assertEquals("email@email.com", usuario.getEmail());
        Assertions.assertEquals("666555444", usuario.getTelefono());
        Assertions.assertEquals("ALUMNO", usuario.getTipoUsuario());
    }
    /**
     * Test constructor Usuarios()
     * Comprueba que el constructor sin parámetros devuelve valores nulos
     */
    @Test
    public void constructorTestNull() {
        Usuarios usuario = new Usuarios();
        Assertions.assertNull(usuario.getId());
        Assertions.assertNull(usuario.getNombre());
        Assertions.assertNull(usuario.getDni());
        Assertions.assertNull(usuario.getEmail());
        Assertions.assertNull(usuario.getTelefono());
        Assertions.assertNull(usuario.getTipoUsuario());
    }
    // * ---- ID ----
    /**
     * Test getId()
     * Comprueba que el método getId devuelve el valor correcto
     */
    @Test
    public void getIdTestOk() {
        Usuarios usuario = new Usuarios();
        usuario.setId(1);
        Assertions.assertEquals(1, usuario.getId());
    }
    /**
     * Test setId(Integer)
     * Comprueba que el método setId establece el valor correcto
     */
    @Test
    public void setIdTestOk() {
        Usuarios usuario = new Usuarios();
        usuario.setId(1);
        Assertions.assertEquals(1, usuario.getId());
    }
    // * ---- Nombre ----
    /**
     * Test getNombre()
     * Comprueba que el método getNombre devuelve el valor correcto
     */
    @Test
    public void getNombreTestOk() {
        Usuarios usuario = new Usuarios();
        usuario.setNombre("Nombre");
        Assertions.assertEquals("Nombre", usuario.getNombre());
    }
    /**
     * Test setNombre(String)
     * Comprueba que el método setNombre establece el valor correcto
     */
    @Test
    public void setNombreTestOk() {
        Usuarios usuario = new Usuarios();
        usuario.setNombre("Nombre");
        Assertions.assertEquals("Nombre", usuario.getNombre());
    }
    // * ---- DNI ----
    /**
     * Test getDni()
     * Comprueba que el método getDni devuelve el valor correcto
     */
    @Test
    public void getDniTestOk() {
        Usuarios usuario = new Usuarios();
        usuario.setDni("12345678A");
        Assertions.assertEquals("12345678A", usuario.getDni());
    }
    /**
     * Test setDni(String)
     * Comprueba que el método setDni establece el valor correcto
     */
    @Test
    public void setDniTestOk() {
        Usuarios usuario = new Usuarios();
        usuario.setDni("12345678A");
        Assertions.assertEquals("12345678A", usuario.getDni());
    }
    // * ---- Email ----
    /**
     * Test getEmail()
     * Comprueba que el método getEmail devuelve el valor correcto
     */
    @Test
    public void getEmailTestOk() {
        Usuarios usuario = new Usuarios();
        usuario.setEmail("email@email.com");
        Assertions.assertEquals("email@email.com", usuario.getEmail());
    }
    /**
     * Test setEmail(String)
     * Comprueba que el método setEmail establece el valor correcto
     */
    @Test
    public void setEmailTestOk() {
        Usuarios usuario = new Usuarios();
        usuario.setEmail("email@email.com");
        Assertions.assertEquals("email@email.com", usuario.getEmail());
    }
    // * ---- Telefono ----
    /**
     * Test getTelefono()
     * Comprueba que el método getTelefono devuelve el valor correcto
     */
    @Test
    public void getTelefonoTestOk() {
        Usuarios usuario = new Usuarios();
        usuario.setTelefono("666555444");
        Assertions.assertEquals("666555444", usuario.getTelefono());
    }
    /**
     * Test setTelefono(String)
     * Comprueba que el método setTelefono establece el valor correcto
     */
    @Test
    public void setTelefonoTestOk() {
        Usuarios usuario = new Usuarios();
        usuario.setTelefono("666555444");
        Assertions.assertEquals("666555444", usuario.getTelefono());
    }
    // * ---- Tipo Usuario ----
    /**
     * Test getTipoUsuario()
     * Comprueba que el método getTipoUsuario devuelve el valor correcto
     */
    @Test
    public void getTipoUsuarioTestOk() {
        Usuarios usuario = new Usuarios();
        usuario.setTipoUsuario("ALUMNO");
        Assertions.assertEquals("ALUMNO", usuario.getTipoUsuario());
    }
    /**
     * Test setTipoUsuario(String)
     * Comprueba que el método setTipoUsuario establece el valor correcto
     */
    @Test
    public void setTipoUsuarioTestOk() {
        Usuarios usuario = new Usuarios();
        usuario.setTipoUsuario("PROFESOR");
        Assertions.assertEquals("PROFESOR", usuario.getTipoUsuario());
    }
}
