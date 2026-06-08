package es.ies.puerto.centroplus.adapters.in.api.incidencia;
/**
 * Clase de solicitud para crear una incidencia.
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
public class IncidenciaRequest {
    private Long idUsuario;
    private String asunto;
    private String descripcion;
    private String fecha;
    private String estado;
    /**
     * Constructor por defecto para la clase IncidenciaRequest
     */
    public IncidenciaRequest() {}
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
     * Setter para el ID del usuario
     * @param id El ID del usuario
     */
    public void setIdUsuario(Long id) {
        this.idUsuario = id;
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