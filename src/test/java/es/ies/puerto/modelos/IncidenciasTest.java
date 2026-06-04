package es.ies.puerto.modelos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Date;

/**
 * @author AlejandroDonGar y JavierReyPer
 * @version 1.0.0
 * 
 * Clase IncidenciasTest
 */
public class IncidenciasTest {

    @Test
    public void constructorTest() {
        Date fecha = new Date();
        Incidencias incidencia = new Incidencias(1, 1, "Asunto", "Descripcion", fecha, "ABIERTA");
        Assertions.assertEquals(1, incidencia.getId());
        Assertions.assertEquals("Asunto", incidencia.getAsunto());
    }
}
