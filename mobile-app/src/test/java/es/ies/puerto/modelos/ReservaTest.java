package es.ies.puerto.modelos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Date;

/**
 * @author AlejandroDonGar
 * @version 1.0.0
 * 
 * Clase ReservaTest
 */
public class ReservaTest {

    // * ---- Constructor ----
    /**
     * Test constructor Reservas(Integer, Integer, Integer, Date, String)
     * Comprueba que el constructor con parámetros devuelve los valores correctos
     */
    @Test
    public void constructorTestOk() {
        Date fecha = new Date();
        Reservas reserva = new Reservas(1, 1, 1, fecha, "ACTIVA");
        Assertions.assertEquals(1, reserva.getId());
        Assertions.assertEquals(1, reserva.getIdUsuario());
        Assertions.assertEquals(1, reserva.getIdActividad());
        Assertions.assertEquals(fecha, reserva.getFecha());
        Assertions.assertEquals("ACTIVA", reserva.getEstado());
    }
    /**
     * Test constructor Reservas()
     * Comprueba que el constructor sin parámetros devuelve valores nulos
     */
    @Test
    public void constructorTestNull() {
        Reservas reserva = new Reservas();
        Assertions.assertNull(reserva.getId());
        Assertions.assertNull(reserva.getIdUsuario());
        Assertions.assertNull(reserva.getIdActividad());
        Assertions.assertNull(reserva.getFecha());
        Assertions.assertNull(reserva.getEstado());
    }
    // * ---- ID ----
    /**
     * Test getId()
     * Comprueba que el método getId devuelve el valor correcto
     */
    @Test
    public void getIdTestOk() {
        Reservas reserva = new Reservas();
        reserva.setId(1);
        Assertions.assertEquals(1, reserva.getId());
    }
    /**
     * Test setId(Integer)
     * Comprueba que el método setId establece el valor correcto
     */
    @Test
    public void setIdTestOk() {
        Reservas reserva = new Reservas();
        reserva.setId(1);
        Assertions.assertEquals(1, reserva.getId());
    }
    // * ---- ID Usuario ----
    /**
     * Test getIdUsuario()
     * Comprueba que el método getIdUsuario devuelve el valor correcto
     */
    @Test
    public void getIdUsuarioTestOk() {
        Reservas reserva = new Reservas();
        reserva.setIdUsuario(1);
        Assertions.assertEquals(1, reserva.getIdUsuario());
    }
    /**
     * Test setIdUsuario(Integer)
     * Comprueba que el método setIdUsuario establece el valor correcto
     */
    @Test
    public void setIdUsuarioTestOk() {
        Reservas reserva = new Reservas();
        reserva.setIdUsuario(1);
        Assertions.assertEquals(1, reserva.getIdUsuario());
    }
    // * ---- ID Actividad ----
    /**
     * Test getIdActividad()
     * Comprueba que el método getIdActividad devuelve el valor correcto
     */
    @Test
    public void getIdActividadTestOk() {
        Reservas reserva = new Reservas();
        reserva.setIdActividad(1);
        Assertions.assertEquals(1, reserva.getIdActividad());
    }
    /**
     * Test setIdActividad(Integer)
     * Comprueba que el método setIdActividad establece el valor correcto
     */
    @Test
    public void setIdActividadTestOk() {
        Reservas reserva = new Reservas();
        reserva.setIdActividad(1);
        Assertions.assertEquals(1, reserva.getIdActividad());
    }
    // * ---- Fecha ----
    /**
     * Test getFecha()
     * Comprueba que el método getFecha devuelve el valor correcto
     */
    @Test
    public void getFechaTestOk() {
        Reservas reserva = new Reservas();
        Date fecha = new Date();
        reserva.setFecha(fecha);
        Assertions.assertEquals(fecha, reserva.getFecha());
    }
    /**
     * Test setFecha(Date)
     * Comprueba que el método setFecha establece el valor correcto
     */
    @Test
    public void setFechaTestOk() {
        Reservas reserva = new Reservas();
        Date fecha = new Date();
        reserva.setFecha(fecha);
        Assertions.assertEquals(fecha, reserva.getFecha());
    }
    // * ---- Estado ----
    /**
     * Test getEstado()
     * Comprueba que el método getEstado devuelve el valor correcto
     */
    @Test
    public void getEstadoTestOk() {
        Reservas reserva = new Reservas();
        reserva.setEstado("CANCELADA");
        Assertions.assertEquals("CANCELADA", reserva.getEstado());
    }
    /**
     * Test setEstado(String)
     * Comprueba que el método setEstado establece el valor correcto
     */
    @Test
    public void setEstadoTestOk() {
        Reservas reserva = new Reservas();
        reserva.setEstado("ACTIVA");
        Assertions.assertEquals("ACTIVA", reserva.getEstado());
    }

    /**
     * Test constructor identificador de la reserva
     * Comprueba que el constructor con ID devuelve el valor correcto
     */
    @Test
    public void constructorIdTest() {
        Reservas reserva = new Reservas(1);
        Assertions.assertEquals(1, reserva.getId());
    }

    /**
     * Test equals(Object)
     * Comprueba que el método equals funciona correctamente comparando por ID
     */
    @Test
    public void equalsTest() {
        Reservas r1 = new Reservas(1);
        Reservas r2 = new Reservas(1);
        Reservas r3 = new Reservas(2);
        
        Assertions.assertEquals(r1, r1);
        Assertions.assertEquals(r1, r2);
        Assertions.assertNotEquals(r1, r3);
        Assertions.assertNotEquals(r1, null);
    }

    /**
     * Test hashCode()
     * Comprueba que el método hashCode devuelve el mismo valor para objetos iguales
     */
    @Test
    public void hashCodeTest() {
        Reservas r1 = new Reservas(1);
        Assertions.assertEquals(r1.hashCode(), new Reservas(1).hashCode());
        Assertions.assertEquals(0, new Reservas().hashCode());
    }
}
