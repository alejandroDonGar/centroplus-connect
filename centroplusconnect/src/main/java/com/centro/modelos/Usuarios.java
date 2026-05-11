package com.centro.modelos;
/**
 * @author AlejandroDonGar
 * @version 1.0.0
 * 
 * Clase Usuarios
 */
public class Usuarios {
    private Integer id;
    private String nombre;
    private String dni;
    private String email;
    private String telefono;
    private String tipo_usuario;
    /**
     * Constructor vacio de la clase
     */
    public Usuarios(){}
    /**
     * Constructor de la clase usuarios
     * @param id parametro identificador del usuario
     * @param nombre parametro nombre del usuario
     * @param dni parametro dni del usuario
     * @param email parametro emial del usuario
     * @param telefono parametro telefono del usuario
     * @param tipo_usuario parametro tipo_usuario del usuario
     */
    public Usuarios(int id, String nombre, String dni, String email, String telefono, String tipo_usuario) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
        this.tipo_usuario = tipo_usuario;
    }
    /**
     * Getter del parametro id
     * @return Devuelve el valor del identificador
     */
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Getter del parametro nombre
     * @return Devuelve el valor del nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Getter del parametro dni
     * @return Devuelve el valor del dni del usuario
     */
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    /**
     * Getter del parametro email
     * @return Devuelve el valor del email de usuario
     */
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Getter del parametro telefono
     * @return Devuelve el valor del telefono del usuario
     */
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    /**
     * Getter del parametro tipo_usuario
     * @return Devuelve el valor de tipo_usuario del usuario
     */
    public String getTipo_usuario() {
        return tipo_usuario;
    }
    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }
}
