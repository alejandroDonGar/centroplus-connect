package es.ies.puerto.centroplus.adapters.in.api.incidencia;
/**
 * Clase de respuesta para obtener información sobre una incidencia.
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
public class IncidenciaResponse {
    private Long id;
    private Long idUsuario;
    private String asunto;
    private String descripcion;
    private String fecha;
    private String estado;
    /**
     * Constructor por defecto para la clase IncidenciaResponse
     */
    public IncidenciaResponse() {}
    /**
     * Constructor para la clase IncidenciaResponse
     * @param id El ID de la incidencia
     * @param idUsuario El ID del usuario
     * @param asunto El asunto de la incidencia
     * @param descripcion La descripción de la incidencia
     * @param fecha La fecha de la incidencia
     * @param estado El estado de la incidencia
     */
    public IncidenciaResponse(Long id, Long idUsuario, String asunto, String descripcion, String fecha, String estado) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.asunto = asunto;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
    }
    /**
     * Getter para el ID de la incidencia
     * @return El ID de la incidencia
     */
    public Long getId() {
        return id;
    }
    /**
     * Getter para el ID del usuario
     * @return El ID del usuario
     */
    public Long getIdUsuario() {
        return idUsuario;
    }
    /**
     * Getter para el asunto de la incidencia
     * @return El asunto de la incidencia
     */
    public String getAsunto() {
        return asunto;
    }
    /**
     * Getter para la descripción de la incidencia
     * @return La descripción de la incidencia
     */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * Getter para la fecha de la incidencia
     * @return La fecha de la incidencia
     */
    public String getFecha() {
        return fecha;
    }
    /**
     * Getter para el estado de la incidencia
     * @return El estado de la incidencia
     */
    public String getEstado() {
        return estado;
    }
    /**
     * Setter para el ID de la incidencia
     * @param id El ID de la incidencia
     */
    public void setId(Long id) {
        this.id = id;
    }   
    /**
     * Setter para el ID del usuario
     * @param idUsuario El ID del usuario
     */
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    /**
     * Setter para el asunto de la incidencia
     * @param asunto El asunto de la incidencia
     */
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }
    /**
     * Setter para la descripción de la incidencia
     * @param descripcion La descripción de la incidencia
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /**
     * Setter para la fecha de la incidencia
     * @param fecha La fecha de la incidencia
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    /**
     * Setter para el estado de la incidencia
     * @param estado El estado de la incidencia
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
}