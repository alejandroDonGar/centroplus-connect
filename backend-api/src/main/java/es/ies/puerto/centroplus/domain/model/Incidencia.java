package es.ies.puerto.centroplus.domain.model;
/** 
 * Clase que representa una incidencia en el sistema.
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
public class Incidencia {
    private Long id;
    private Long idUsuario;
    private String asunto;
    private String descripcion;
    private String fecha;
    private String estado;
    /**
     * Constructor por defecto.
     */
    public Incidencia() {}
    /**
     * Constructor con parámetros
     * @param id Identificador de la incidencia.
     * @param idUsuario Identificador del usuario que ha creado la incidencia.
     * @param asunto Asunto de la incidencia.
     * @param descripcion Descripción de la incidencia.
     * @param fecha Fecha de creación de la incidencia.
     * @param estado Estado de la incidencia.
     */
    public Incidencia(Long id, Long idUsuario, String asunto, String descripcion, String fecha, String estado) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.asunto = asunto;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
    }
    /**
     * Getter para el identificador de la incidencia.
     * @return El identificador de la incidencia.
     */
    public Long getId() {
        return id;
    }
    /**
     * Getter para el identificador del usuario que ha creado la incidencia.
     * @return El identificador del usuario que ha creado la incidencia.
     */
    public Long getIdUsuario() {
        return idUsuario;
    }
    /**
     * Getter para el asunto de la incidencia.
     * @return El asunto de la incidencia.
     */
    public String getAsunto() {
        return asunto;
    }
    /**
     * Getter para la descripción de la incidencia.
     * @return La descripción de la incidencia.
     */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * Getter para la fecha de creación de la incidencia.
     * @return La fecha de creación de la incidencia.
     */
    public String getFecha() {
        return fecha;
    }
    /**
     * Getter para el estado de la incidencia.
     * @return El estado de la incidencia.
     */
    public String getEstado() {
        return estado;
    }
    /**
     * Setter para el identificador de la incidencia.
     * @param id El identificador de la incidencia.
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Setter para el identificador del usuario que ha creado la incidencia.
     * @param idUsuario El identificador del usuario que ha creado la incidencia.
     */
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    /**
     * Setter para el asunto de la incidencia.
     * @param asunto El asunto de la incidencia.
     */
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }
    /**
     * Setter para la descripción de la incidencia.
     * @param descripcion La descripción de la incidencia.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /**
     * Setter para la fecha de creación de la incidencia.
     * @param fecha La fecha de creación de la incidencia.
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    /**
     * Setter para el estado de la incidencia.
     * @param estado El estado de la incidencia.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
}