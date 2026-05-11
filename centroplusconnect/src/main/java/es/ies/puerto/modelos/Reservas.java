package es.ies.puerto.modelos;
import java.util.Date;
/**
 * @author AlejandroDonGar
 * @version 1.0.0
 * 
 * Clase Reservas
 */
public class Reservas {
    private Integer id;
    private Integer idUsuario;
    private Integer idActividad;
    private Date fecha;
    private String estado;
    /**
     * Constructor vacio de la clase Reservas
     */
    public Reservas(){}
    /**
     * Constructor completo de la clase Reservas
     * @param id identificador de la reseva
     * @param idUsuario identificador del usuario que realiza la reserva
     * @param idActividad identificador de la actividad de la reserva
     * @param fecha fecha de la reserva
     * @param estado estado de la reserva
     */
    public Reservas(Integer id, Integer idUsuario, Integer idActividad, Date fecha, String estado){
        this.id = id;
        this.idUsuario = idUsuario;
        this.idActividad = idActividad;
        this.fecha = fecha;
        this.estado = estado;
    }
    /**
     * Getter del parametro identificador
     * @return Devuelve el valor del identificador de la reserva
     */
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * GEtter del parametro identificador del usuario
     * @return Devuelve el valor del identificador del usuario
     */
    public Integer getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    /**
     * Getter del parametro identificador de la actividad
     * @return Devuelve el valor del identificador de la actividad
     */
    public Integer getIdActividad() {
        return idActividad;
    }
    public void setIdActividad(Integer idActividad) {
        this.idActividad = idActividad;
    }
    /**
     * Getter del parametro fecha de la reserva
     * @return Devuelve el valor de la fecha de la reserva
     */
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    /**
     * Getter del parametro estado
     * @return Devuelve el valor del estado de la reserva
     */
    public String isEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
