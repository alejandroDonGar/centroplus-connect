package es.ies.puerto.centroplus.domain.model;
/**
 * Entidad para representar una reserva en la base de datos utilizando JPA
 * 
 * @author AlejandroDonate y JavierRey
 * @version 1.0
 */
public class Reserva {
    private Long id;
    private Long idUsuario;
    private Long idActividad;
    private String fecha;
    private String estado;
    /**
     * Metodo constructor para inicializar la entidad de reserva utilizando JPA
     */
    public Reserva() {}
    /**
     * Metodo constructor para inicializar la entidad de reserva utilizando JPA
     * @param id El ID de la reserva en la base de datos
     * @param idUsuario El ID del usuario en la base de datos
     * @param idActividad El ID de la actividad en la base de datos
     * @param fecha La fecha de la reserva
     * @param estado El estado de la reserva
     */
    public Reserva(Long id, Long idUsuario, Long idActividad, String fecha, String estado) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idActividad = idActividad;
        this.fecha = fecha;
        this.estado = estado;
    }
    /**
     * Getter para obtener el ID de la reserva en la base de datos
     * @return El ID de la reserva
     */
    public Long getId() {
        return id;
    }
    /**
     * Getter para obtener el ID del usuario en la base de datos
     * @return El ID del usuario
     */
    public Long getIdUsuario() {
        return idUsuario;
    }
    /**
     * Getter para obtener el ID de la actividad en la base de datos
     * @return El ID de la actividad
     */
    public Long getIdActividad() {
        return idActividad;
    }
    /**
     * Getter para obtener la fecha de la reserva
     * @return La fecha de la reserva
     */
    public String getFecha() {
        return fecha;
    }
    /**
     * Getter para obtener el estado de la reserva
     * @return El estado de la reserva
     */
    public String getEstado() {
        return estado;
    }
    /**
     * Setter para establecer el ID de la reserva en la base de datos
     * @param id El ID de la reserva
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Setter para establecer el ID del usuario en la base de datos
     * @param idUsuario El ID del usuario
     */
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    /**
     * Setter para establecer el ID de la actividad en la base de datos
     * @param idActividad El ID de la actividad
     */
    public void setIdActividad(Long idActividad) {
        this.idActividad = idActividad;
    }
    /**
     * Setter para establecer la fecha de la reserva
     * @param fecha La fecha de la reserva
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    /**
     * Setter para establecer el estado de la reserva
     * @param estado El estado de la reserva
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
}