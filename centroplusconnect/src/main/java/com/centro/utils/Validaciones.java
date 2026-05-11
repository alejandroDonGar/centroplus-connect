package com.centro.utils;
import java.util.Date;
import java.util.regex.Pattern;
import com.centro.modelos.Actividades;
import com.centro.modelos.Incidencias;
import com.centro.modelos.Reservas;
import com.centro.modelos.Usuarios;
/**
 * @author AlejandroDonGar
 * @version 1.0.0
 * 
 * Clase Validaciones
 */
public class Validaciones {
    public boolean esCadenaValida(String value) {
        return value!=null && !value.isEmpty();
    }
    public boolean esIntegerValido(Integer value) {
        return value!=null && value>=1; 
    }
    public boolean esDoubleValido(Double value) {
        return value!=null && value>0; 
    }
    public boolean esDniValido(String value) {
        return value!=null && Pattern.matches("^[0-9]{8}[A-Z]$", value);
    }
    public boolean esEmailValido(String value) {
        return value!=null && Pattern.matches("^[A-ZÑÁÉÍÓÚa-zñáéíóú0-9-+_]+@[A-ZÑÁÉÍÓÚa-zñáéíóú0-9-+_]+\\.[A-Za-z]{2,}$", value);
    }
    public boolean esTelefonoValido(String value) {
        return value!=null && Pattern.matches("^[+34]?[0-9]{9}$", value);
    }
    public boolean esTipoUsuarioValido(String value) {
        return value!=null && Pattern.matches("^[ALUMNO]|[SOCIO]|[AMBOS]$", value);
    }
    public boolean esTipoActividadValida(String value) {
        return value!=null && Pattern.matches("^[ACADEMICA]|[DEPORTIVA]$", value);
    }
    public boolean esPlazasOcupadas(Integer value) {
        Actividades actividad = new Actividades();
        if(value==null || value>actividad.getPlazasMaximas()) {
            return false;
        }
        return true;
    }
    public boolean esFechaValida(Date value) {
        String fecha = value.toString();
        return fecha!=null && Pattern.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$", fecha);
    }
    public boolean esEstadoReservaValido(String value) {
        return value!=null && Pattern.matches("^[ACTIVA]|[CANCELADA]$", value);
    }
    public boolean esEstadoIncidenciaValido(String value) {
        return value!=null && Pattern.matches("^[ABIERTA]|[EN_PROCESO]|[CERRADA]$", value);
    }
    public boolean esUsuarioValido(Usuarios usuario) {
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
    public boolean esActividadValida(Actividades actividad) {
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
    public boolean esReservaValida(Reservas reserva) {
        if(reserva==null
            || !esIntegerValido(reserva.getId())
            || !esIntegerValido(reserva.getIdUsuario())
            || !esIntegerValido(reserva.getIdActividad())
            || !esFechaValida(reserva.getFecha())
            || !esEstadoReservaValido(reserva.isEstado())) {
                return false;
            }
        return true;
    }
    public boolean esIncidenciaValida(Incidencias incidencia) {
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