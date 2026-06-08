package es.ies.puerto.centroplus.adapters.in.api.reserva;
/**
 * Clase de solicitud para crear una Reserva
 * 
 * @author Ismael Perez
 * @version 1.0
 */
public class ReservaRequest {
    private Long idUsuario;
    private Long idActividad;
    private String fecha;
    private String estado;
    /**
     * Constructor de la clase ReservaRequest
     */
    public ReservaRequest() {}
    /**
     * Getter para el ID del usuario asociado a la Reserva
     * @return El ID del usuario
     */
    public Long getIdUsuario() {
        return idUsuario;
    }
    /**
     * Getter para el ID de la actividad asociada a la Reserva
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
     * Setter para el ID del usuario asociado a la Reserva
     * @param idUsuario El ID del usuario
     */
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    /**
     * Setter para el ID de la actividad asociada a la Reserva
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