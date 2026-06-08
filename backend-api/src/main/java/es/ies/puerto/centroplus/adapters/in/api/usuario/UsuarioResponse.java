package es.ies.puerto.centroplus.adapters.in.api.usuario;
/**
 * Clase para representar una respuesta de usuario
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
public class UsuarioResponse {
    private Long id;
    private String nombre;
    private String dni;
    private String email;
    private String telefono;
    private String tipoUsuario;
    /**
     * Constructor por defecto para la clase
     */
    public UsuarioResponse() {}
    /**
     * Constructor para crear una respuesta de usuario con los datos proporcionados
     * 
     * @param id El ID del usuario
     * @param nombre El nombre del usuario
     * @param dni El dni del usuario
     * @param email El email del usuario
     * @param telefono El telefono del usuario
     * @param tipoUsuario El tipo de usuario
     */
    public UsuarioResponse(Long id, String nombre, String dni, String email, String telefono, String tipoUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
        this.tipoUsuario = tipoUsuario;
    }
    /**
     * Getter para obtener el ID del usuario
     * @return El ID del usuario
     */
    public Long getId() {
        return id;
    }
    /**
     * Getter para obtener el nombre del usuario
     * @return El nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Getter para obtener el dni del usuario
     * @return El dni del usuario
     */
    public String getDni() {
        return dni;
    }
    /**
     * Getter para obtener el email del usuario
     * @return El email del usuario
     */
    public String getEmail() {
        return email;
    }
    /**
     * Getter para obtener el telefono del usuario
     * @return El telefono del usuario
     */
    public String getTelefono() {
        return telefono;
    }
    /**
     * Getter para obtener el tipo de usuario
     * @return El tipo de usuario
     */
    public String getTipoUsuario() {
        return tipoUsuario;
    }
    /**
     * Setter para establecer el ID del usuario
     * @param id El ID del usuario
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Setter para establecer el nombre del usuario
     * @param nombre El nombre del usuario
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Setter para establecer el dni del usuario
     * @param dni El dni del usuario
     */
    public void setDni(String dni) {
        this.dni = dni;
    }
    /**
     * Setter para establecer el email del usuario
     * @param email El email del usuario
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Setter para establecer el telefono del usuario
     * @param telefono El telefono del usuario
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    /**
     * Setter para establecer el tipo de usuario
     * @param tipoUsuario El tipo de usuario
     */
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}