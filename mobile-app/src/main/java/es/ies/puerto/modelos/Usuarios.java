package es.ies.puerto.modelos;
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
    private String tipoUsuario;
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
     * @param tipoUsuario parametro tipo_usuario del usuario
     */
    public Usuarios(Integer id, String nombre, String dni, String email, String telefono, String tipoUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
        this.tipoUsuario = tipoUsuario;
    }
    /**
     * Getter del parametro id
     * @return Devuelve el valor del identificador
     */
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
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
     * Getter del parametro tipoUsuario
     * @return Devuelve el valor de tipo de usuario
     */
    public String getTipoUsuario() {
        return tipoUsuario;
    }
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuarios usuarios = (Usuarios) o;
        return id != null && id.equals(usuarios.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
