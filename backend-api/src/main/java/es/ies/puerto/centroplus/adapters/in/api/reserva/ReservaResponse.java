package es.ies.puerto.centroplus.adapters.in.api.reserva;
/**
 * Clase para representar una Reserva en la API de entrada
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
public class ReservaResponse {
    private Long id;
    private Long idUsuario;
    private Long idActividad;
    private String fecha;
    private String estado;
    /**
     * Constructor por defecto
     */
    public ReservaResponse() {}
    /**
     * Constructor con parámetros
     * @param id El ID de la Reserva
     * @param idUsuario El ID del usuario
     * @param idActividad El ID de la actividad
     * @param fecha La fecha de la Reserva
     * @param estado El estado de la Reserva
     */
    public ReservaResponse(Long id, Long idUsuario, Long idActividad, String fecha, String estado) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idActividad = idActividad;
        this.fecha = fecha;
        this.estado = estado;
    }
    /**
     * Getter para el ID de la Reserva
     * @return El ID de la Reserva
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
     * Getter para el ID de la actividad
     * @return El ID de la actividad
     */
    public Long getIdActividad() {
        return idActividad;
    }
    /**
     * Getter para la fecha de la Reserva
     * @return La fecha de la Reserva
     */
    public String getFecha() {
        return fecha;
    }
    /**
     * Getter para el estado de la Reserva
     * @return El estado de la Reserva
     */
    public String getEstado() {
        return estado;
    }
    /**
     * Setter para el ID de la Reserva
     * @param id El ID de la Reserva
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
     * Setter para el ID de la actividad
     * @param idActividad El ID de la actividad
     */ 
    public void setIdActividad(Long idActividad) {
        this.idActividad = idActividad;
    }
    /**
     * Setter para la fecha de la Reserva
     * @param fecha La fecha de la Reserva
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    /**
     * Setter para el estado de la Reserva
     * @param estado El estado de la Reserva
     */ 
    public void setEstado(String estado) {
        this.estado = estado;
    }
}