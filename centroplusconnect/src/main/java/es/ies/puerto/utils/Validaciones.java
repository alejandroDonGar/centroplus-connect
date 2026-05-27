package es.ies.puerto.utils;
import java.util.Date;
import java.util.regex.Pattern;
import es.ies.puerto.modelos.Actividades;
import es.ies.puerto.modelos.Incidencias;
import es.ies.puerto.modelos.Reservas;
import es.ies.puerto.modelos.Usuarios;
/**
 * @author AlejandroDonGar y JavierReyPer
 * @version 1.0.0
 * 
 * Clase Validaciones
 */
public class Validaciones {
    public static boolean esCadenaValida(String value) {
        return value!=null && !value.isEmpty();
    }
    public static boolean esIntegerValido(Integer value) {
        return value!=null && value>=1; 
    }
    public static boolean esDoubleValido(Double value) {
        return value!=null && value>0; 
    }
    public static boolean esDniValido(String value) {
        return value!=null && Pattern.matches("^[0-9]{8}[A-Z]$", value);
    }
    public static boolean esEmailValido(String value) {
        return value!=null && Pattern.matches("^[A-ZÑÁÉÍÓÚa-zñáéíóú0-9-+_]+@[A-ZÑÁÉÍÓÚa-zñáéíóú0-9-+_]+\\.[A-Za-z]{2,}$", value);
    }
    public static boolean esTelefonoValido(String value) {
        return value!=null && Pattern.matches("^[+34]?[0-9]{9}$", value);
    }
    public static boolean esTipoUsuarioValido(String value) {
        return value!=null && Pattern.matches("^(ALUMNO|SOCIO|AMBOS)$", value);
    }
    public static boolean esTipoActividadValida(String value) {
        return value!=null && Pattern.matches("^(ACADEMICA|DEPORTIVA)$", value);
    }
    public static boolean esPlazasOcupadas(Integer value) {
        return value!=null && value >= 0;
    }
    public static boolean esFechaValida(Date value) {
        return value!=null;
    }
    public static boolean esEstadoReservaValido(String value) {
        return value!=null && Pattern.matches("^(ACTIVA|CANCELADA)$", value);
    }
    public static boolean esEstadoIncidenciaValido(String value) {
        return value!=null && Pattern.matches("^(ABIERTA|EN_PROCESO|CERRADA)$", value);
    }
    public static boolean esUsuarioValido(Usuarios usuario) {
        if(usuario==null 
            || !esIntegerValido(usuario.getId())
            || !esCadenaValida(usuario.getNombre())
            || !esDniValido(usuario.getDni())
            || !esEmailValido(usuario.getEmail())
            || !esTelefonoValido(usuario.getTelefono())
            || !esTipoUsuarioValido(usuario.getTipoUsuario())) {
                return false;
        }
        return true;
    }
    public static boolean esActividadValida(Actividades actividad) {
        if(actividad == null
            || !esIntegerValido(actividad.getId())
            || !esCadenaValida(actividad.getNombre())
            || !esTipoActividadValida(actividad.getTipoActividad())
            || !esIntegerValido(actividad.getDuracion())
            || !esDoubleValido(actividad.getPrecio())
            || !esIntegerValido(actividad.getPlazasMaximas())
            || !esPlazasOcupadas(actividad.getPlazasOcupadas())) {
                return false;
        }
        return true;
    }
    public static boolean esReservaValida(Reservas reserva) {
        if(reserva==null
            || !esIntegerValido(reserva.getId())
            || !esIntegerValido(reserva.getIdUsuario())
            || !esIntegerValido(reserva.getIdActividad())
            || !esFechaValida(reserva.getFecha())
            || !esEstadoReservaValido(reserva.getEstado())) {
                return false;
            }
        return true;
    }
    public static boolean esIncidenciaValida(Incidencias incidencia) {
        if(incidencia==null
            || !esIntegerValido(incidencia.getId())
            || !esIntegerValido(incidencia.getIdUsuario())
            || !esCadenaValida(incidencia.getAsunto())
            || !esCadenaValida(incidencia.getDescripcion())
            || !esFechaValida(incidencia.getFecha())
            || !esEstadoIncidenciaValido(incidencia.getEstado())) {
                return false;
            }
            return true;
    }
}