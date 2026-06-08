package es.ies.puerto.centroplus.business.ports;
import java.util.List;
import java.util.Optional;
import es.ies.puerto.centroplus.domain.model.Actividad;
/**
 * Interface para representar el servicio de actividades
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
public interface ActividadServicePort {
    /**
     * Metodo para obtener todas las actividades existentes
     * @return Una lista de todas las actividades existentes
     */
    List<Actividad> findAll();
    /**
     * Metodo para obtener una actividad por su identificador
     * @param id El identificador de la actividad
     * @return El objeto Actividad correspondiente al identificador
     */
    Optional<Actividad> findById(Long id);
    /**
     * Metodo para guardar una actividad
     * @param actividad El objeto Actividad a guardar
     * @return El objeto Actividad guardado
     */
    /**
     * Metodo para guardar una actividad
     * @param actividad El objeto Actividad a guardar
     * @return El objeto Actividad guardado
     */
    Actividad save(Actividad actividad);
    /**
     * Metodo para actualizar una actividad existente
     * @param id El identificador de la actividad a actualizar
     * @param actividad El objeto Actividad con los nuevos datos
     * @return El objeto Actividad actualizado
     */
    Optional<Actividad> update(Long id, Actividad actividad);
    /**
     * Metodo para eliminar una actividad por su identificador
     * @param id El identificador de la actividad a eliminar
     * @return true si la actividad fue eliminada, false en caso contrario
     */
    boolean delete(Long id);
}