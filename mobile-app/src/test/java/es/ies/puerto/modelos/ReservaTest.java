package es.ies.puerto.modelos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Date;

/**
 * @author AlejandroDonGar y JavierReyPer
 * @version 1.0.0
 * 
 * Clase ReservaTest
 */
public class ReservaTest {

    @Test
    public void constructorTest() {
        Date fecha = new Date();
        Reservas reserva = new Reservas(1, 1, 1, fecha, "ACTIVA");
        Assertions.assertEquals(1, reserva.getId());
        Assertions.assertEquals(fecha, reserva.getFecha());
    }
}
