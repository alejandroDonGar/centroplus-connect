package es.ies.puerto.modelos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Date;

/**
 * @author AlejandroDonGar
 * @version 1.0.0
 * 
 * Clase IncidenciasTest
 */
public class IncidenciasTest {

    // * ---- Constructor ----
    /**
     * Test constructor Incidencias(Integer, Integer, String, String, Date, String)
     * Comprueba que el constructor con parámetros devuelve los valores correctos
     */
    @Test
    public void constructorTestOk() {
        Date fecha = new Date();
        Incidencias incidencia = new Incidencias(1, 1, "Asunto", "Descripcion", fecha, "ABIERTA");
        Assertions.assertEquals(1, incidencia.getId());
        Assertions.assertEquals(1, incidencia.getIdUsuario());
        Assertions.assertEquals("Asunto", incidencia.getAsunto());
        Assertions.assertEquals("Descripcion", incidencia.getDescripcion());
        Assertions.assertEquals(fecha, incidencia.getFecha());
        Assertions.assertEquals("ABIERTA", incidencia.getEstado());
    }
    /**
     * Test constructor Incidencias()
     * Comprueba que el constructor sin parámetros devuelve valores nulos
     */
    @Test
    public void constructorTestNull() {
        Incidencias incidencia = new Incidencias();
        Assertions.assertNull(incidencia.getId());
        Assertions.assertNull(incidencia.getIdUsuario());
        Assertions.assertNull(incidencia.getAsunto());
        Assertions.assertNull(incidencia.getDescripcion());
        Assertions.assertNull(incidencia.getFecha());
        Assertions.assertNull(incidencia.getEstado());
    }
    // * ---- ID ----
    /**
     * Test getId()
     * Comprueba que el método getId devuelve el valor correcto
     */
    @Test
    public void getIdTestOk() {
        Incidencias incidencia = new Incidencias();
        incidencia.setId(1);
        Assertions.assertEquals(1, incidencia.getId());
    }
    /**
     * Test setId(Integer)
     * Comprueba que el método setId establece el valor correcto
     */
    @Test
    public void setIdTestOk() {
        Incidencias incidencia = new Incidencias();
        incidencia.setId(1);
        Assertions.assertEquals(1, incidencia.getId());
    }
    // * ---- ID Usuario ----
    /**
     * Test getIdUsuario()
     * Comprueba que el método getIdUsuario devuelve el valor correcto
     */
    @Test
    public void getIdUsuarioTestOk() {
        Incidencias incidencia = new Incidencias();
        incidencia.setIdUsuario(1);
        Assertions.assertEquals(1, incidencia.getIdUsuario());
    }
    /**
     * Test setIdUsuario(Integer)
     * Comprueba que el método setIdUsuario establece el valor correcto
     */
    @Test
    public void setIdUsuarioTestOk() {
        Incidencias incidencia = new Incidencias();
        incidencia.setIdUsuario(1);
        Assertions.assertEquals(1, incidencia.getIdUsuario());
    }
    // * ---- Asunto ----
    /**
     * Test getAsunto()
     * Comprueba que el método getAsunto devuelve el valor correcto
     */
    @Test
    public void getAsuntoTestOk() {
        Incidencias incidencia = new Incidencias();
        incidencia.setAsunto("Asunto");
        Assertions.assertEquals("Asunto", incidencia.getAsunto());
    }
    /**
     * Test setAsunto(String)
     * Comprueba que el método setAsunto establece el valor correcto
     */
    @Test
    public void setAsuntoTestOk() {
        Incidencias incidencia = new Incidencias();
        incidencia.setAsunto("Asunto");
        Assertions.assertEquals("Asunto", incidencia.getAsunto());
    }
    // * ---- Descripcion ----
    /**
     * Test getDescripcion()
     * Comprueba que el método getDescripcion devuelve el valor correcto
     */
    @Test
    public void getDescripcionTestOk() {
        Incidencias incidencia = new Incidencias();
        incidencia.setDescripcion("Descripcion");
        Assertions.assertEquals("Descripcion", incidencia.getDescripcion());
    }
    /**
     * Test setDescripcion(String)
     * Comprueba que el método setDescripcion establece el valor correcto
     */
    @Test
    public void setDescripcionTestOk() {
        Incidencias incidencia = new Incidencias();
        incidencia.setDescripcion("Descripcion");
        Assertions.assertEquals("Descripcion", incidencia.getDescripcion());
    }
    // * ---- Fecha ----
    /**
     * Test getFecha()
     * Comprueba que el método getFecha devuelve el valor correcto
     */
    @Test
    public void getFechaTestOk() {
        Incidencias incidencia = new Incidencias();
        Date fecha = new Date();
        incidencia.setFecha(fecha);
        Assertions.assertEquals(fecha, incidencia.getFecha());
    }
    /**
     * Test setFecha(Date)
     * Comprueba que el método setFecha establece el valor correcto
     */
    @Test
    public void setFechaTestOk() {
        Incidencias incidencia = new Incidencias();
        Date fecha = new Date();
        incidencia.setFecha(fecha);
        Assertions.assertEquals(fecha, incidencia.getFecha());
    }
    // * ---- Estado ----
    /**
     * Test getEstado()
     * Comprueba que el método getEstado devuelve el valor correcto
     */
    @Test
    public void getEstadoTestOk() {
        Incidencias incidencia = new Incidencias();
        incidencia.setEstado("CERRADA");
        Assertions.assertEquals("CERRADA", incidencia.getEstado());
    }
    /**
     * Test setEstado(String)
     * Comprueba que el método setEstado establece el valor correcto
     */
    @Test
    public void setEstadoTestOk() {
        Incidencias incidencia = new Incidencias();
        incidencia.setEstado("ABIERTA");
        Assertions.assertEquals("ABIERTA", incidencia.getEstado());
    }

    /**
     * Test constructor identificador de la incidencia
     * Comprueba que el constructor con ID devuelve el valor correcto
     */
    @Test
    public void constructorIdTest() {
        Incidencias incidencia = new Incidencias(1);
        Assertions.assertEquals(1, incidencia.getId());
    }

    /**
     * Test equals(Object)
     * Comprueba que el método equals funciona correctamente comparando por ID
     */
    @Test
    public void equalsTest() {
        Incidencias i1 = new Incidencias(1);
        Incidencias i2 = new Incidencias(1);
        Incidencias i3 = new Incidencias(2);
        
        Assertions.assertEquals(i1, i1);
        Assertions.assertEquals(i1, i2);
        Assertions.assertNotEquals(i1, i3);
        Assertions.assertNotEquals(i1, null);
        Assertions.assertNotEquals(i1, new Object());
    }

    /**
     * Test hashCode()
     * Comprueba que el método hashCode devuelve el mismo valor para objetos iguales
     */
    @Test
    public void hashCodeTest() {
        Incidencias i1 = new Incidencias(1);
        Assertions.assertEquals(i1.hashCode(), new Incidencias(1).hashCode());
        Assertions.assertEquals(0, new Incidencias().hashCode());
    }
}
