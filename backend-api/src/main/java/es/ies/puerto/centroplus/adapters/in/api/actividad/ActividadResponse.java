package es.ies.puerto.centroplus.adapters.in.api.actividad;
/**
 * Clase para representar una respuesta de actividad
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
public class ActividadResponse {
    private Long id;
    private String nombre;
    private String tipoActividad;
    private Integer duracion;
    private Double precio;
    private Integer plazasMaximas;
    private Integer plazasOcupadas;
    private Integer plazasDisponibles;
    /**
     * Constructor vacio de la clase ActividadResponse
     */
    public ActividadResponse() {}
    /**
     * Constructor con parametros de la clase ActividadResponse
     * @param id El identificador de la actividad
     * @param nombre El nombre de la actividad
     * @param tipoActividad El tipo de la actividad
     * @param duracion La duración de la actividad
     * @param precio El precio de la actividad
     * @param plazasMaximas El número máximo de plazas
     * @param plazasOcupadas El número de plazas ocupadas
     * @param plazasDisponibles El número de plazas disponibles
     */
    public ActividadResponse(Long id, String nombre, String tipoActividad, Integer duracion, Double precio, Integer plazasMaximas, Integer plazasOcupadas, Integer plazasDisponibles) {
        this.id = id;
        this.nombre = nombre;
        this.tipoActividad = tipoActividad;
        this.duracion = duracion;
        this.precio = precio;
        this.plazasMaximas = plazasMaximas;
        this.plazasOcupadas = plazasOcupadas;
        this.plazasDisponibles = plazasDisponibles;
    }
    /**
     * Getter para el identificador de la actividad
     * @return El identificador de la actividad
     */
    public Long getId() {
        return id;
    }
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
     * Getter para el número máximo de plazas
     * @return El número máximo de plazas
     */
    public Integer getPlazasMaximas() {
        return plazasMaximas;
    }
    /**
     * Getter para el número de plazas ocupadas
     * @return El número de plazas ocupadas
     */
    public Integer getPlazasOcupadas() {
        return plazasOcupadas;
    }
    /**
     * Getter para el número de plazas disponibles
     * @return El número de plazas disponibles
     */
    public Integer getPlazasDisponibles() {
        return plazasDisponibles;
    }
    /**
     * Setter para el identificador de la actividad
     * @param id El identificador de la actividad
     */
    public void setId(Long id) {
        this.id = id;
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
     * Setter para el número máximo de plazas
     * @param plazasMaximas El número máximo de plazas
     */
    public void setPlazasMaximas(Integer plazasMaximas) {
        this.plazasMaximas = plazasMaximas;
    }
    /**
     * Setter para el número de plazas ocupadas
     * @param plazasOcupadas El número de plazas ocupadas
     */
    public void setPlazasOcupadas(Integer plazasOcupadas) {
        this.plazasOcupadas = plazasOcupadas;
    }
    /**
     * Setter para el número de plazas disponibles
     * @param plazasDisponibles El número de plazas disponibles
     */
    public void setPlazasDisponibles(Integer plazasDisponibles) {
        this.plazasDisponibles = plazasDisponibles;
    }
}
