package es.ies.puerto.modelos;
/**
 * @author AlejandroDonGar
 * @version 1.0.0
 * 
 * Clase Actividades
 */
public class Actividades {
    private Integer id;
    private String nombre;
    private String tipoActividad;
    private Integer duracion;
    private Double precio;
    private Integer plazasMaximas;
    private Integer plazasOcupadas;
    /**
     * Constructor vacio de la clase Actividades
     */
    public Actividades(){}
    /**
     * Constructor completo de la clase Actividades
     * @param id identificador de la actividad
     * @param nombre nombre de la actividad
     * @param tipoActividad tipo de actividad
     * @param duracion duracion de la actividad
     * @param precio precio de la actividad
     * @param plazasMaximas plazas maximas que tiene la actividad
     * @param plazasOcupadas plazas ocupadas en la actividad
     */
    public Actividades(Integer id, String nombre, String tipoActividad, Integer duracion, Double precio, Integer plazasMaximas, Integer plazasOcupadas){
        this.id = id;
        this.nombre = nombre;
        this.tipoActividad = tipoActividad;
        this.duracion = duracion;
        this.plazasMaximas = plazasMaximas;
        this.plazasOcupadas = plazasOcupadas;
    }
    /**
     * Getter del identificador de la actividad
     * @return Devuelve el valor del identificador
     */
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * Getter del parametro nombre de la actividad
     * @return Devuelve el valor del nombre de la actividad
     */
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Getter del parametro tipoActividad
     * @return Devuelve el valor del tipo de actividad de la actividad
     */
    public String getTipoActividad() {
        return tipoActividad;
    }
    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
    }
    /**
     * Getter del parametro duracion
     * @return Devuelve el valor de la duracion de la actividad
     */
    public Integer getDuracion() {
        return duracion;
    }
    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }
    /**
     * Getter del parametro precio
     * @return Devuelve el valor del precio de la actividad
     */
    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    /**
     * Getter del parametro plazasMaximas
     * @return Devuelve el valor de las plazas maximas de la actividad
     */
    public Integer getPlazasMaximas() {
        return plazasMaximas;
    }
    public void setPlazasMaximas(Integer plazasMaximas) {
        this.plazasMaximas = plazasMaximas;
    }
    /**
     * Getter del parametro plazasOCupadas
     * @return Devuelve el valor de las plazas ocupadas de la actividad
     */
    public Integer getPlazasOcupadas() {
        return plazasOcupadas;
    }
    public void setPlazasOcupadas(Integer plazasOcupadas) {
        this.plazasOcupadas = plazasOcupadas;
    }
}