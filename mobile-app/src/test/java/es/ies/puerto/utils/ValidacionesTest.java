package es.ies.puerto.utils;

import es.ies.puerto.modelos.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Date;

/**
 * @author AlejandroDonGar
 * @version 1.0.0
 * 
 * Clase ValidacionesTest
 */
public class ValidacionesTest {

    /**
     * Test esCadenaValida(String)
     * Comprueba que las cadenas no sean nulas ni vacías
     */
    @Test
    public void esCadenaValidaTest() {
        Assertions.assertTrue(Validaciones.esCadenaValida("texto"));
        Assertions.assertFalse(Validaciones.esCadenaValida(null));
        Assertions.assertFalse(Validaciones.esCadenaValida(""));
    }

    /**
     * Test esIntegerValido(Integer)
     * Comprueba que los enteros sean positivos y no nulos
     */
    @Test
    public void esIntegerValidoTest() {
        Assertions.assertTrue(Validaciones.esIntegerValido(1));
        Assertions.assertFalse(Validaciones.esIntegerValido(null));
        Assertions.assertFalse(Validaciones.esIntegerValido(0));
    }

    /**
     * Test esDoubleValido(Double)
     * Comprueba que los dobles sean positivos y no nulos
     */
    @Test
    public void esDoubleValidoTest() {
        Assertions.assertTrue(Validaciones.esDoubleValido(1.0));
        Assertions.assertFalse(Validaciones.esDoubleValido(null));
        Assertions.assertFalse(Validaciones.esDoubleValido(0.0));
    }

    /**
     * Test esDniValido(String)
     * Comprueba que el formato del DNI sea correcto
     */
    @Test
    public void esDniValidoTest() {
        Assertions.assertTrue(Validaciones.esDniValido("12345678A"));
        Assertions.assertFalse(Validaciones.esDniValido(null));
        Assertions.assertFalse(Validaciones.esDniValido("123"));
    }

    /**
     * Test esEmailValido(String)
     * Comprueba que el formato del email sea correcto
     */
    @Test
    public void esEmailValidoTest() {
        Assertions.assertTrue(Validaciones.esEmailValido("test@test.com"));
        Assertions.assertFalse(Validaciones.esEmailValido(null));
        Assertions.assertFalse(Validaciones.esEmailValido("test"));
    }

    /**
     * Test esTelefonoValido(String)
     * Comprueba que el formato del teléfono sea correcto
     */
    @Test
    public void esTelefonoValidoTest() {
        Assertions.assertTrue(Validaciones.esTelefonoValido("666555444"));
        Assertions.assertFalse(Validaciones.esTelefonoValido(null));
        Assertions.assertFalse(Validaciones.esTelefonoValido("123"));
    }

    /**
     * Test esTipoUsuarioValido(String)
     * Comprueba que el tipo de usuario sea uno de los permitidos
     */
    @Test
    public void esTipoUsuarioValidoTest() {
        Assertions.assertTrue(Validaciones.esTipoUsuarioValido("ALUMNO"));
        Assertions.assertTrue(Validaciones.esTipoUsuarioValido("SOCIO"));
        Assertions.assertTrue(Validaciones.esTipoUsuarioValido("AMBOS"));
        Assertions.assertFalse(Validaciones.esTipoUsuarioValido(null));
        Assertions.assertFalse(Validaciones.esTipoUsuarioValido("OTRO"));
    }

    /**
     * Test esTipoActividadValida(String)
     * Comprueba que el tipo de actividad sea uno de los permitidos
     */
    @Test
    public void esTipoActividadValidaTest() {
        Assertions.assertTrue(Validaciones.esTipoActividadValida("ACADEMICA"));
        Assertions.assertTrue(Validaciones.esTipoActividadValida("DEPORTIVA"));
        Assertions.assertFalse(Validaciones.esTipoActividadValida(null));
        Assertions.assertFalse(Validaciones.esTipoActividadValida("OTRA"));
    }

    /**
     * Test esPlazasOcupadas(Integer)
     * Comprueba que las plazas ocupadas sean un número válido (>= 0)
     */
    @Test
    public void esPlazasOcupadasTest() {
        Assertions.assertTrue(Validaciones.esPlazasOcupadas(0));
        Assertions.assertTrue(Validaciones.esPlazasOcupadas(1));
        Assertions.assertFalse(Validaciones.esPlazasOcupadas(null));
        Assertions.assertFalse(Validaciones.esPlazasOcupadas(-1));
    }

    /**
     * Test esFechaValida(Date)
     * Comprueba que la fecha no sea nula
     */
    @Test
    public void esFechaValidaTest() {
        Assertions.assertTrue(Validaciones.esFechaValida(new Date()));
        Assertions.assertFalse(Validaciones.esFechaValida(null));
    }

    /**
     * Test esEstadoReservaValido(String)
     * Comprueba que el estado de la reserva sea uno de los permitidos
     */
    @Test
    public void esEstadoReservaValidoTest() {
        Assertions.assertTrue(Validaciones.esEstadoReservaValido("ACTIVA"));
        Assertions.assertTrue(Validaciones.esEstadoReservaValido("CANCELADA"));
        Assertions.assertFalse(Validaciones.esEstadoReservaValido(null));
        Assertions.assertFalse(Validaciones.esEstadoReservaValido("OTRO"));
    }

    /**
     * Test esEstadoIncidenciaValido(String)
     * Comprueba que el estado de la incidencia sea uno de los permitidos
     */
    @Test
    public void esEstadoIncidenciaValidoTest() {
        Assertions.assertTrue(Validaciones.esEstadoIncidenciaValido("ABIERTA"));
        Assertions.assertTrue(Validaciones.esEstadoIncidenciaValido("EN_PROCESO"));
        Assertions.assertTrue(Validaciones.esEstadoIncidenciaValido("CERRADA"));
        Assertions.assertFalse(Validaciones.esEstadoIncidenciaValido(null));
        Assertions.assertFalse(Validaciones.esEstadoIncidenciaValido("OTRO"));
    }

    /**
     * Test esUsuarioValido(Usuarios)
     * Comprueba que todos los campos de un usuario sean válidos
     */
    @Test
    public void esUsuarioValidoTest() {
        Usuarios u = new Usuarios(1, "N", "12345678A", "e@e.com", "666555444", "ALUMNO");
        Assertions.assertTrue(Validaciones.esUsuarioValido(u));
        Assertions.assertFalse(Validaciones.esUsuarioValido(null));
        
        // Test each field invalidation
        Assertions.assertFalse(Validaciones.esUsuarioValido(new Usuarios(null, "N", "12345678A", "e@e.com", "666555444", "ALUMNO")));
        Assertions.assertFalse(Validaciones.esUsuarioValido(new Usuarios(1, null, "12345678A", "e@e.com", "666555444", "ALUMNO")));
        Assertions.assertFalse(Validaciones.esUsuarioValido(new Usuarios(1, "N", null, "e@e.com", "666555444", "ALUMNO")));
        Assertions.assertFalse(Validaciones.esUsuarioValido(new Usuarios(1, "N", "12345678A", null, "666555444", "ALUMNO")));
        Assertions.assertFalse(Validaciones.esUsuarioValido(new Usuarios(1, "N", "12345678A", "e@e.com", null, "ALUMNO")));
        Assertions.assertFalse(Validaciones.esUsuarioValido(new Usuarios(1, "N", "12345678A", "e@e.com", "666555444", null)));
    }

    /**
     * Test esActividadValida(Actividades)
     * Comprueba que todos los campos de una actividad sean válidos
     */
    @Test
    public void esActividadValidaTest() {
        Actividades a = new Actividades(1, "N", "DEPORTIVA", 60, 20.0, 20, 10);
        Assertions.assertTrue(Validaciones.esActividadValida(a));
        Assertions.assertFalse(Validaciones.esActividadValida(null));
        
        // Test each field invalidation
        Assertions.assertFalse(Validaciones.esActividadValida(new Actividades(null, "N", "DEPORTIVA", 60, 20.0, 20, 10)));
        Assertions.assertFalse(Validaciones.esActividadValida(new Actividades(1, null, "DEPORTIVA", 60, 20.0, 20, 10)));
        Assertions.assertFalse(Validaciones.esActividadValida(new Actividades(1, "N", null, 60, 20.0, 20, 10)));
        Assertions.assertFalse(Validaciones.esActividadValida(new Actividades(1, "N", "DEPORTIVA", null, 20.0, 20, 10)));
        Assertions.assertFalse(Validaciones.esActividadValida(new Actividades(1, "N", "DEPORTIVA", 60, null, 20, 10)));
        Assertions.assertFalse(Validaciones.esActividadValida(new Actividades(1, "N", "DEPORTIVA", 60, 20.0, null, 10)));
        Assertions.assertFalse(Validaciones.esActividadValida(new Actividades(1, "N", "DEPORTIVA", 60, 20.0, 20, null)));
    }

    /**
     * Test esReservaValida(Reservas)
     * Comprueba que todos los campos de una reserva sean válidos
     */
    @Test
    public void esReservaValidaTest() {
        Reservas r = new Reservas(1, 1, 1, new Date(), "ACTIVA");
        Assertions.assertTrue(Validaciones.esReservaValida(r));
        Assertions.assertFalse(Validaciones.esReservaValida(null));
        
        // Test each field invalidation
        Assertions.assertFalse(Validaciones.esReservaValida(new Reservas(null, 1, 1, new Date(), "ACTIVA")));
        Assertions.assertFalse(Validaciones.esReservaValida(new Reservas(1, null, 1, new Date(), "ACTIVA")));
        Assertions.assertFalse(Validaciones.esReservaValida(new Reservas(1, 1, null, new Date(), "ACTIVA")));
        Assertions.assertFalse(Validaciones.esReservaValida(new Reservas(1, 1, 1, null, "ACTIVA")));
        Assertions.assertFalse(Validaciones.esReservaValida(new Reservas(1, 1, 1, new Date(), null)));
    }

    /**
     * Test esIncidenciaValida(Incidencias)
     * Comprueba que todos los campos de una incidencia sean válidos
     */
    @Test
    public void esIncidenciaValidaTest() {
        Incidencias i = new Incidencias(1, 1, "A", "D", new Date(), "ABIERTA");
        Assertions.assertTrue(Validaciones.esIncidenciaValida(i));
        Assertions.assertFalse(Validaciones.esIncidenciaValida(null));
        
        // Test each field invalidation
        Assertions.assertFalse(Validaciones.esIncidenciaValida(new Incidencias(null, 1, "A", "D", new Date(), "ABIERTA")));
        Assertions.assertFalse(Validaciones.esIncidenciaValida(new Incidencias(1, null, "A", "D", new Date(), "ABIERTA")));
        Assertions.assertFalse(Validaciones.esIncidenciaValida(new Incidencias(1, 1, null, "D", new Date(), "ABIERTA")));
        Assertions.assertFalse(Validaciones.esIncidenciaValida(new Incidencias(1, 1, "A", null, new Date(), "ABIERTA")));
        Assertions.assertFalse(Validaciones.esIncidenciaValida(new Incidencias(1, 1, "A", "D", null, "ABIERTA")));
        Assertions.assertFalse(Validaciones.esIncidenciaValida(new Incidencias(1, 1, "A", "D", new Date(), null)));
    }
}