package es.ies.puerto.models;
import org.junit.jupiter.api.Test;
import es.ies.puerto.modelos.Actividades;
import org.junit.jupiter.api.Assertions;
/**
 * @author AlejandroDonGar y JavierReyPer
 * @version 1.0.0
 * 
 *          Clase ActividadTest
 */
public class ActividadTest {

    // * ---- Constructor ----
    /**
     * Test constructor Actividades(int, String, String, int, double, int, int)
     * Comprueba que el constructor con parámetros devuelve los valores correctos
     */
    @Test
    public void constructorTestOk() {
        Actividades actividad = new Actividades(
                1,
                "Yoga",
                "DEPORTIVA",
                60,
                25.5,
                20,
                10);
        Assertions.assertEquals(1, actividad.getId());
        Assertions.assertEquals("Yoga", actividad.getNombre());
        Assertions.assertEquals("DEPORTIVA", actividad.getTipoActividad());
        Assertions.assertEquals(60, actividad.getDuracion());
        Assertions.assertEquals(25.5, actividad.getPrecio());
        Assertions.assertEquals(20, actividad.getPlazasMaximas());
        Assertions.assertEquals(10, actividad.getPlazasOcupadas());
    }
    /**
     * Test constructor Actividades()
     * Comprueba que el constructor sin parámetros devuelve valores nulos
     */
    @Test
    public void constructorTestNull() {
        Actividades actividad = new Actividades();
        Assertions.assertNull(actividad.getId());
        Assertions.assertNull(actividad.getNombre());
        Assertions.assertNull(actividad.getTipoActividad());
        Assertions.assertNull(actividad.getDuracion());
        Assertions.assertNull(actividad.getPrecio());
        Assertions.assertNull(actividad.getPlazasMaximas());
        Assertions.assertNull(actividad.getPlazasOcupadas());
    }
    // * ---- ID ----
    /**
     * Test getId()
     * Comprueba que el método getId devuelve el valor correcto
     */
    @Test
    public void getIdTestOk() {
        Actividades actividad = new Actividades();
        actividad.setId(1);
        Assertions.assertEquals(1, actividad.getId());
    }
    /**
     * Test setId(int)
     * Comprueba que el método setId establece el valor correcto
     */
    @Test
    public void setIdTestOk() {
        Actividades actividad = new Actividades();
        actividad.setId(1);
        Assertions.assertEquals(1, actividad.getId());
    }
    // * ---- Nombre ----
    /**
     * Test getNombre()
     * Comprueba que el método getNombre devuelve el valor correcto
     */
    @Test
    public void getNombreTestOk() {
        Actividades actividad = new Actividades();
        actividad.setNombre("Yoga");
        Assertions.assertEquals("Yoga", actividad.getNombre());
    }
    /**
     * Test setNombre(String)
     * Comprueba que el método setNombre establece el valor correcto
     */
    @Test
    public void setNombreTestOk() {
        Actividades actividad = new Actividades();
        actividad.setNombre("Pilates");
        Assertions.assertEquals("Pilates", actividad.getNombre());
    }
    // * ---- Tipo Actividad ----
    /**
     * Test getTipoActividad()
     * Comprueba que el método getTipoActividad devuelve el valor correcto
     */
    @Test
    public void getTipoActividadTestOk() {
        Actividades actividad = new Actividades();
        actividad.setTipoActividad("DEPORTIVA");
        Assertions.assertEquals("DEPORTIVA", actividad.getTipoActividad());
    }
    /**
     * Test setTipoActividad(String)
     * Comprueba que el método setTipoActividad establece el valor correcto
     */
    @Test
    public void setTipoActividadTestOk() {
        Actividades actividad = new Actividades();
        actividad.setTipoActividad("PILATES");
        Assertions.assertEquals("PILATES", actividad.getTipoActividad());
    }
    // * ---- Duracion ----
    /**
     * Test getDuracion()
     * Comprueba que el método getDuracion devuelve el valor correcto
     */
    @Test
    public void getDuracionTestOk() {
        Actividades actividad = new Actividades();
        actividad.setDuracion(60);
        Assertions.assertEquals(60, actividad.getDuracion());
    }
    /**
     * Test setDuracion(int)
     * Comprueba que el método setDuracion establece el valor correcto
     */
    @Test
    public void setDuracionTestOk() {
        Actividades actividad = new Actividades();
        actividad.setDuracion(90);
        Assertions.assertEquals(90, actividad.getDuracion());
    }
    // * ---- Precio ----
    /**
     * Test getPrecio()
     * Comprueba que el método getPrecio devuelve el valor correcto
     */
    @Test
    public void getPrecioTestOk() {
        Actividades actividad = new Actividades();
        actividad.setPrecio(25.5);
        Assertions.assertEquals(25.5, actividad.getPrecio());
    }
    /**
     * Test setPrecio(double)
     * Comprueba que el método setPrecio establece el valor correcto
     */
    @Test
    public void setPrecioTestOk() {
        Actividades actividad = new Actividades();
        actividad.setPrecio(30.0);
        Assertions.assertEquals(30.0, actividad.getPrecio());
    }
    // * ---- Plazas Maximas ----
    /**
     * Test getPlazasMaximas()
     * Comprueba que el método getPlazasMaximas devuelve el valor correcto
     */
    @Test
    public void getPlazasMaximasTestOk() {
        Actividades actividad = new Actividades();
        actividad.setPlazasMaximas(10);
        Assertions.assertEquals(10, actividad.getPlazasMaximas());
    }
    /**
     * Test setPlazasMaximas(int)
     * Comprueba que el método setPlazasMaximas establece el valor correcto
     */
    @Test
    public void setPlazasMaximasTestOk() {
        Actividades actividad = new Actividades();
        actividad.setPlazasMaximas(20);
        Assertions.assertEquals(20, actividad.getPlazasMaximas());
    }
    // * ---- Plazas Ocupadas ----
    /**
     * Test getPlazasOcupadas()
     * Comprueba que el método getPlazasOcupadas devuelve el valor correcto
     */
    @Test
    public void getPlazasOcupadasTestOk() {
        Actividades actividad = new Actividades();
        actividad.setPlazasOcupadas(5);
        Assertions.assertEquals(5, actividad.getPlazasOcupadas());
    }
    /**
     * Test setPlazasOcupadas(int)
     * Comprueba que el método setPlazasOcupadas establece el valor correcto
     */
    @Test
    public void setPlazasOcupadasTestOk() {
        Actividades actividad = new Actividades();
        actividad.setPlazasOcupadas(10);
        Assertions.assertEquals(10, actividad.getPlazasOcupadas());
    }
    // * ---- Equals ----
    /**
     * Test equals(Object)
     * Comprueba que el método equals devuelve false cuando los objetos no son iguales
     */
    @Test
    public void equalsTestFalse() {
        Actividades actividad1 = new Actividades();
        Actividades actividad2 = new Actividades();
        Assertions.assertFalse(actividad1.equals(actividad2));
    }
    // * ---- HashCode ----
    /**
     * Test hashCode()
     * Comprueba que el método hashCode devuelve un valor no nulo
     */
    @Test
    public void hashCodeTestOk() {
        Actividades actividad = new Actividades();
        Assertions.assertNotNull(actividad.hashCode());
    }
}