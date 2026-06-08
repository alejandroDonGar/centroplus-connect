package es.ies.puerto.centroplus.domain.model;
/**
 * Clase para representar una actividad
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
public class Actividad {
    private Long id;
    private String nombre;
    private String tipoActividad;
    private Integer duracion;
    private Double precio;
    private Integer plazasMaximas;
    private Integer plazasOcupadas;
    /**
     * Constructor vacio de la clase Actividad
     */
    public Actividad() {}
    /**
     * Constructor con parametros de la clase Actividad
     * @param id El identificador de la actividad
     * @param nombre El nombre de la actividad
     * @param tipoActividad El tipo de la actividad
     * @param duracion La duración de la actividad
     * @param precio El precio de la actividad
     * @param plazasMaximas El número máximo de plazas
     * @param plazasOcupadas El número de plazas ocupadas
     */
    public Actividad(Long id, String nombre, String tipoActividad, Integer duracion, Double precio, Integer plazasMaximas, Integer plazasOcupadas) {
        this.id = id;
        this.nombre = nombre;
        this.tipoActividad = tipoActividad;
        this.duracion = duracion;
        this.precio = precio;
        this.plazasMaximas = plazasMaximas;
        this.plazasOcupadas = plazasOcupadas;
    }
    /**
     * Metodo para obtener el número de plazas disponibles
     * @return El número de plazas disponibles
     */
    public Integer getPlazasDisponibles() {
        return plazasMaximas - plazasOcupadas;
    }
    /**
     * Metodo para verificar si la actividad está completa
     * @return true si la actividad está completa, false en caso contrario
     */
    public boolean estaCompleta() {
        return getPlazasDisponibles() <= 0;
    }
    /**
     * Metodo para obtener el identificador de la actividad
     * @return El identificador de la actividad
     */
    public Long getId() {
        return id;
    }
    /**
     * Metodo para obtener el nombre de la actividad
     * @return El nombre de la actividad
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Metodo para obtener el tipo de la actividad
     * @return El tipo de la actividad
     */
    public String getTipoActividad() {
        return tipoActividad;
    }
    /**
     * Metodo para obtener la duración de la actividad
     * @return La duración de la actividad
     */ 
    public Integer getDuracion() {
        return duracion;
    }
    /**
     * Metodo para obtener el precio de la actividad
     * @return El precio de la actividad
     */
    public Double getPrecio() {
        return precio;
    }
    /**
     * Metodo para obtener el número máximo de plazas
     * @return El número máximo de plazas
     */
    public Integer getPlazasMaximas() {
        return plazasMaximas;
    }
    /**
     * Metodo para obtener el número de plazas ocupadas
     * @return El número de plazas ocupadas
     */
    public Integer getPlazasOcupadas() {
        return plazasOcupadas;
    }
    /**
     * Metodo para establecer el identificador de la actividad
     * @param id El identificador de la actividad
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Metodo para establecer el nombre de la actividad
     * @param nombre El nombre de la actividad
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Metodo para establecer el tipo de la actividad
     * @param tipoActividad El tipo de la actividad
     */
    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
    }
    /**
     * Metodo para establecer la duración de la actividad
     * @param duracion La duración de la actividad
     */
    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }
    /**
     * Metodo para establecer el precio de la actividad
     * @param precio El precio de la actividad
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    /**
     * Metodo para establecer el número máximo de plazas
     * @param plazasMaximas El número máximo de plazas
     */
    public void setPlazasMaximas(Integer plazasMaximas) {
        this.plazasMaximas = plazasMaximas;
    }
    /**
     * Metodo para establecer el número de plazas ocupadas
     * @param plazasOcupadas El número de plazas ocupadas
     */
    public void setPlazasOcupadas(Integer plazasOcupadas) {
        this.plazasOcupadas = plazasOcupadas;
    }
}