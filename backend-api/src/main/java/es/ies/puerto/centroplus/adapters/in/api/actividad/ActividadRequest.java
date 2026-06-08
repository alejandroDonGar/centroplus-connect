package es.ies.puerto.centroplus.adapters.in.api.actividad;
/**
 * Clase para representar una solicitud de actividad
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
public class ActividadRequest {
    private String nombre;
    private String tipoActividad;
    private Integer duracion;
    private Double precio;
    private Integer plazasMaximas;
    private Integer plazasOcupadas;
    /**
     * Constructor por defecto
     */
    public ActividadRequest() {}
    /**
     * Getter para el nombre de la actividad
     * @return El nombre de la actividad
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Getter para el tipo de la actividad
     * @return El tipo de la actividad
     */
    public String getTipoActividad() {
        return tipoActividad;
    }
    /**
     * Getter para la duración de la actividad
     * @return La duración de la actividad
     */
    public Integer getDuracion() {
        return duracion;
    }
    /**
     * Getter para el precio de la actividad
     * @return El precio de la actividad
     */
    public Double getPrecio() {
        return precio;
    }
    /**
     * Getter para el número de plazas máximas de la actividad
     * @return El número de plazas máximas de la actividad
     */
    public Integer getPlazasMaximas() {
        return plazasMaximas;
    }
    /**
     * Getter para el número de plazas ocupadas de la actividad
     * @return El número de plazas ocupadas de la actividad
     */
    public Integer getPlazasOcupadas() {
        return plazasOcupadas;
    }
    /**
     * Setter para el nombre de la actividad
     * @param nombre El nombre de la actividad
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Setter para el tipo de la actividad
     * @param tipoActividad El tipo de la actividad
     */
    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
    }
    /**
     * Setter para la duración de la actividad
     * @param duracion La duración de la actividad
     */
    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }
    /**
     * Setter para el precio de la actividad
     * @param precio El precio de la actividad
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    /**
     * Setter para el número de plazas máximas de la actividad
     * @param plazasMaximas El número de plazas máximas de la actividad
     */
    public void setPlazasMaximas(Integer plazasMaximas) {
        this.plazasMaximas = plazasMaximas;
    }
    /**
     * Setter para el número de plazas ocupadas de la actividad
     * @param plazasOcupadas El número de plazas ocupadas de la actividad
     */
    public void setPlazasOcupadas(Integer plazasOcupadas) {
        this.plazasOcupadas = plazasOcupadas;
    }
}
