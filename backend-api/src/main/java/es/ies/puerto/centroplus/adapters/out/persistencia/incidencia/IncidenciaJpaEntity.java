package es.ies.puerto.centroplus.adapters.out.persistencia.incidencia;
import jakarta.persistence.*;
/**
 * Entidad JPA para la incidencia.
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
@Entity
@Table(name = "incidencias") // El nombre de la tabla en la base de datos
public class IncidenciaJpaEntity {
    /**
     * Se usa '@Column' para identificar la columna en la base de datos
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_usuario")
    private Long idUsuario;
    @Column(name = "asunto")
    private String asunto;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fecha")
    private String fecha;
    @Column(name = "estado")
    private String estado;
    /**
     * Constructor por defecto
     */
    public IncidenciaJpaEntity() {}
    /**
     * Getter para el campo id
     * @return El valor del campo id
     */
    public Long getId() {
        return id;
    }
    /**
     * Getter para el campo idUsuario
     * @return El valor del campo idUsuario
     */
    public Long getIdUsuario() {
        return idUsuario;
    }
    /**
     * Getter para el campo asunto
     * @return El valor del campo asunto
     */
    public String getAsunto() {
        return asunto;
    }
    /**
     * Getter para el campo descripcion
     * @return El valor del campo descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * Getter para el campo fecha
     * @return El valor del campo fecha
     */
    public String getFecha() {
        return fecha;
    }
    /**
     * Getter para el campo estado
     * @return El valor del campo estado
     */
    public String getEstado() {
        return estado;
    }
    /**
     * Setter para el campo id
     * @param id El nuevo valor del campo id
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Setter para el campo idUsuario
     * @param idUsuario El nuevo valor del campo idUsuario
     */
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    /**
     * Setter para el campo asunto
     * @param asunto El nuevo valor del campo asunto
     */
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }
    /**
     * Setter para el campo descripcion
     * @param descripcion El nuevo valor del campo descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /**
     * Setter para el campo fecha
     * @param fecha El nuevo valor del campo fecha
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    /**
     * Setter para el campo estado
     * @param estado El nuevo valor del campo estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
}