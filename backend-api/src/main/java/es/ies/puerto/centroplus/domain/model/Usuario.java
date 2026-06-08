package es.ies.puerto.centroplus.domain.model;
/**
 * Clase para representar un usuario en el sistema
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
public class Usuario {
    private Long id;
    private String nombre;
    private String dni;
    private String email;
    private String telefono;
    private String tipoUsuario;
    /**
     * Constructor por defecto de la clase Usuario
     */
    public Usuario() {}
    /**
     * Constructor de la clase Usuario con parámetros
     * @param id El ID del usuario
     * @param nombre El nombre del usuario
     * @param dni El número de identificación personal del usuario
     * @param email El correo electrónico del usuario
     * @param telefono El número de teléfono del usuario
     * @param tipoUsuario El tipo de usuario (admin o usuario)
     */
    public Usuario(Long id, String nombre, String dni, String email, String telefono, String tipoUsuario) {
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
     * Getter para obtener el número de identificación personal del usuario
     * @return El número de identificación personal del usuario
     */
    public String getDni() {
        return dni;
    }
    /**
     * Getter para obtener el correo electrónico del usuario
     * @return El correo electrónico del usuario
     */
    public String getEmail() {
        return email;
    }
    /**
     * Getter para obtener el número de teléfono del usuario
     * @return El número de teléfono del usuario
     */
    public String getTelefono() {
        return telefono;
    }
    /**
     * Getter para obtener el tipo de usuario (admin o usuario)
     * @return El tipo de usuario (admin o usuario)
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Setter para establecer el número de identificación personal del usuario
     * @param dni El número de identificación personal del usuario
     */
    public void setDni(String dni) {
        this.dni = dni;
    }
    /**
     * Setter para establecer el correo electrónico del usuario
     * @param email El correo electrónico del usuario
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Setter para establecer el número de teléfono del usuario
     * @param telefono El número de teléfono del usuario
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    /**
     * Setter para establecer el tipo de usuario (admin o usuario)
     * @param tipoUsuario El tipo de usuario (admin o usuario)
     */
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}