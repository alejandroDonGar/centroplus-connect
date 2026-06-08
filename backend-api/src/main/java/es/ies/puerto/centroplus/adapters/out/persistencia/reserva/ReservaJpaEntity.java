package es.ies.puerto.centroplus.adapters.out.persistencia.reserva;
import jakarta.persistence.*;
/**
 * Entidad JPA para representar una reserva en la base de datos
 * 
 * @author AlejandroDonate
 * @version 1.0
 */
@Entity
@Table(name = "reservas")
public class ReservaJpaEntity {
    /**
     * Se usa '@Column' para mapear las columnas en la base de datos
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_usuario")
    private Long idUsuario;
    @Column(name = "id_actividad")
    private Long idActividad;
    @Column(name = "fecha")
    private String fecha;
    @Column(name = "estado")
    private String estado;
    /**
     * Constructor por defecto
     */
    public ReservaJpaEntity() {}
    /**
     * Getter para el campo 'id'
     * @return El valor del campo 'id'
     */
    public Long getId() {
        return id;
    }
    /**
     * Getter para el campo 'idUsuario'
     * @return El valor del campo 'idUsuario'
     */
    public Long getIdUsuario() {
        return idUsuario;
    }
    /**
     * Getter para el campo 'idActividad'
     * @return El valor del campo 'idActividad' 
     */
    public Long getIdActividad() {
        return idActividad;
    }
    /**
     * Getter para el campo 'fecha'
     * @return El valor del campo 'fecha'
     */
    public String getFecha() {
        return fecha;
    }
    /**
     * Getter para el campo 'estado'
     * @return El valor del campo 'estado'
     */
    public String getEstado() {
        return estado;
    }
    /**
     * Setter para el campo 'id'
     * @param id El nuevo valor para el campo 'id'
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Setter para el campo 'idUsuario'
     * @param idUsuario El nuevo valor para el campo 'idUsuario'
     */
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    /**
     * Setter para el campo 'idActividad'
     * @param idActividad El nuevo valor para el campo 'idActividad'
     */
    public void setIdActividad(Long idActividad) {
        this.idActividad = idActividad;
    }
    /**
     * Setter para el campo 'fecha'
     * @param fecha El nuevo valor para el campo 'fecha'
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    /**
     * Setter para el campo 'estado'
     * @param estado El nuevo valor para el campo 'estado'
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
}