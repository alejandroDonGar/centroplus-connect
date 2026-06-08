package es.ies.puerto.centroplus.adapters.out.persistencia.usuario;
import jakarta.persistence.*;
/**
 * Entidad para representar un usuario en la base de datos utilizando JPA
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
@Entity
@Table(name = "usuarios")
public class UsuarioJpaEntity {
    /**
     * Se usa '@Column' para especificar el nombre de la columna en la base de datos
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre") // El nombre de la columna en la base de datos
    private String nombre;
    @Column(name = "dni") // El nombre de la columna en la base de datos
    private String dni;
    @Column(name = "email") // El nombre de la columna en la base de datos
    private String email;
    @Column(name = "telefono") // El nombre de la columna en la base de datos
    private String telefono;
    @Column(name = "tipo_usuario") // El nombre de la columna en la base de datos
    private String tipoUsuario;
    /**
     * Metodo constructor para inicializar la entidad de usuario utilizando JPA
     */
    public UsuarioJpaEntity() {}
    /**
     * Getter para obtener el ID del usuario en la base de datos
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
     * Getter para obtener el DNI del usuario
     * @return El DNI del usuario
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
     * Getter para obtener el teléfono del usuario
     * @return El teléfono del usuario
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
     * Setter para establecer el ID del usuario en la base de datos
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
     * Setter para establecer el DNI del usuario
     * @param dni El DNI del usuario
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
     * Setter para establecer el teléfono del usuario
     * @param telefono El teléfono del usuario
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