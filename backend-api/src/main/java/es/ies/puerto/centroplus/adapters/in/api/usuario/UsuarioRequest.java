package es.ies.puerto.centroplus.adapters.in.api.usuario;
/**
 * Clase para representar una solicitud de usuario 
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
public class UsuarioRequest {
    private String nombre;
    private String dni;
    private String email;
    private String telefono;
    private String tipoUsuario;
    /**
     * Constructor por defecto
     */
    public UsuarioRequest() {}
    /**
     * Getter para el nombre del usuario
     * @return El nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Getter para el dni del usuario
     * @return El dni del usuario
     */
    public String getDni() {
        return dni;
    }
    /**
     * Getter para el email del usuario
     * @return El email del usuario
     */
    public String getEmail() {
        return email;
    }
    /**
     * Getter para el telefono del usuario
     * @return El telefono del usuario
     */
    public String getTelefono() {
        return telefono;
    }
    /**
     * Getter para el tipo de usuario
     * @return El tipo de usuario
     */
    public String getTipoUsuario() {
        return tipoUsuario;
    }
    /**
     * Setter para el nombre del usuario
     * @param nombre El nombre del usuario
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Setter para el dni del usuario
     * @param dni El dni del usuario
     */
    public void setDni(String dni) {
        this.dni = dni;
    }
    /**
     * Setter para el email del usuario
     * @param email El email del usuario
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Setter para el telefono del usuario
     * @param telefono El telefono del usuario
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    /**
     * Setter para el tipo de usuario
     * @param tipoUsuario El tipo de usuario
     */
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}