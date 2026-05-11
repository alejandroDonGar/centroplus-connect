package com.centro.utils;
import java.util.regex.Pattern;
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
        return Pattern.matches("^[0-9]{8}[A-Z]$", value);
    }
    public boolean esEmailValido(String email) {
        return Pattern.matches("^[A-ZÑÁÉÍÓÚa-zñáéíóú0-9-+_]+@[A-ZÑÁÉÍÓÚa-zñáéíóú0-9-+_]+\\.[A-Za-z]{2,}$", email);
    }
    public boolean esTelefonoValido(String value) {
        return false;
    }
    public boolean esUsuarioValido(Usuarios usuario) {
        if(usuario==null 
            || !esIntegerValido(usuario.getId())
            || !esCadenaValida(usuario.getNombre())
            || !esDniValido(usuario.getDni())
            || !esEmailValido(usuario.getEmail()))
            {
            return false;
        }
        return true;
    }

}