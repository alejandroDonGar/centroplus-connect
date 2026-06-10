package es.ies.puerto.centroplus.adapters.out.persistencia.actividad;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
/**
 * Clase para representar una actividad en la base de datos utilizando JPA
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
@Entity
@Table(name = "actividades")
public class ActividadJpaEntity {
    /**
     * Se usa '@Column' para especificar el nombre de la columna en la base de datos
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // El nombre de la columna en la base de datos
    private Long id;
    @Column(name = "nombre") // El nombre de la columna en la base de datos
    private String nombre;
    @Column(name = "tipo_actividad") // El nombre de la columna en la base de datos
    private String tipoActividad;
    @Column(name = "duracion") // El nombre de la columna en la base de datos
    private Integer duracion;
    @Column(name = "precio") // El nombre de la columna en la base de datos
    private Double precio;
    @Column(name = "plazas_maximas") // El nombre de la columna en la base de datos
    private Integer plazasMaximas;
    @Column(name = "plazas_ocupadas") // El nombre de la columna en la base de datos
    private Integer plazasOcupadas;
    /**
     * Metodo constructor por defecto
     */
    public ActividadJpaEntity() {}
    /**
     * Getter para obtener el id de la actividad
     * @return El id de la actividad
     */
    public Long getId() {
        return id;
    }
    /**
     * Getter para obtener el nombre de la actividad
     * @return El nombre de la actividad
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Getter para obtener el tipo de la actividad
     * @return El tipo de la actividad
     */
    public String getTipoActividad() {
        return tipoActividad;
    }
    /**
     * Getter para obtener la duración de la actividad
     * @return La duración de la actividad
     */
    public Integer getDuracion() {
        return duracion;
    }
    /**
     * Getter para obtener el precio de la actividad
     * @return El precio de la actividad
     */
    public Double getPrecio() {
        return precio;
    }
    /**
     * Getter para obtener el número de plazas máximas de la actividad
     * @return El número de plazas máximas de la actividad
     */
    public Integer getPlazasMaximas() {
        return plazasMaximas;
    }
    /**
     * Getter para obtener el número de plazas ocupadas de la actividad
     * @return El número de plazas ocupadas de la actividad
     */
    public Integer getPlazasOcupadas() {
        return plazasOcupadas;
    }
    /**
     * Setter para establecer el id de la actividad
     * @param id El id de la actividad
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Setter para establecer el nombre de la actividad
     * @param nombre El nombre de la actividad
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Setter para establecer el tipo de la actividad
     * @param tipoActividad El tipo de la actividad
     */
    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
    }
    /**
     * Setter para establecer la duración de la actividad
     * @param duracion La duración de la actividad
     */
    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }
    /**
     * Setter para establecer el precio de la actividad
     * @param precio El precio de la actividad
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    /**
     * Setter para establecer el número de plazas máximas de la actividad
     * @param plazasMaximas El número de plazas máximas de la actividad
     */
    public void setPlazasMaximas(Integer plazasMaximas) {
        this.plazasMaximas = plazasMaximas;
    }
    /**
     * Setter para establecer el número de plazas ocupadas de la actividad
     * @param plazasOcupadas El número de plazas ocupadas de la actividad
     */
    public void setPlazasOcupadas(Integer plazasOcupadas) {
        this.plazasOcupadas = plazasOcupadas;
    }
}
