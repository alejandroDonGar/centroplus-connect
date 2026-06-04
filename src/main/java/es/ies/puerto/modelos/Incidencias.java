package es.ies.puerto.modelos;
import java.util.Date;
/**
 * @author AlejandroDonGar y JavierReyPer
 * @version 1.0.0
 * 
 * Clase Incidencias
 */
public class Incidencias {
    private Integer id;
    private Integer idUsuario;
    private String asunto;
    private String descripcion;
    private Date fecha;
    private String estado;
    /**
     * Constructor vacio de la clase Incidencias
     */
    public Incidencias(){}
    /**
     * Constructor identificador de la incidencia
     * @param id identificador de la incidencia
     */
    public Incidencias(Integer id) {
        this.id = id;
    }
    /**
     * Constructor completo de la clase Incidencias
     * @param id identificador de la incidencia
     * @param idUsuario identificador del usuario vinculado con la incidencia
     * @param asunto asunto del que se trata la incidencia
     * @param descipcion descripcion de la incidencia
     * @param fecha fecha de la incidencia
     * @param estado estado de la incidencia
     */
    public Incidencias(Integer id, Integer idUsuario, String asunto, String descripcion, Date fecha, String estado) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.asunto = asunto;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
    }
    /**
     * Getter del parametro identificador
     * @return Devuelve el valor del identificador de la incidencia
     */
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * Getter del parametro idUsuario
     * @return Devuelve el valor del id del usuario asociado a la incidencia
     */
    public Integer getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    /**
     * Getter del parametro asunto
     * @return Devuelve el valor del asunto de la incidencia
     */
    public String getAsunto() {
        return asunto;
    }
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }
    /**
     * Getter del parametro descripcion
     * @return Devuelve el valor de la descripcion de la incidencia
     */
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /**
     * Getter del parametro fecha
     * @return devuelve el valor de la fecha de la incidencia
     */
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    /**
     * Getter del parametro estado
     * @return Devuelve el valor del estado de la incidencia.
     */
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Incidencias that = (Incidencias) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
