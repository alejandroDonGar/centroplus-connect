package es.ies.puerto.centroplus.business.ports;
import java.util.List;
import java.util.Optional;
import es.ies.puerto.centroplus.domain.model.Incidencia;
/**
 * Interfaz de servicio para la gestión de incidencias.
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
public interface IncidenciaServicePort {
    /**
     * Devuelve una lista de todas las incidencias.
     * @return Una lista de todas las incidencias.
     */
    List<Incidencia> findAll();
    /**
     * Devuelve una incidencia por su ID.
     * @param id El ID de la incidencia.
     * @return El objeto Incidencia correspondiente al ID.
     */
    Optional<Incidencia> findById(Long id);
    /**
     * Guarda una nueva incidencia.
     * @param incidencia El objeto Incidencia a guardar.
     * @return El objeto Incidencia guardado.
     */
    Incidencia save(Incidencia incidencia);
    /**
     * Actualiza una incidencia existente.
     * @param id El ID de la incidencia a actualizar.
     * @param incidencia El objeto Incidencia con los nuevos datos.
     * @return El objeto Incidencia actualizado.
     */
    Optional<Incidencia> update(Long id, Incidencia incidencia);
    /**
     * Elimina una incidencia existente.
     * @param id El ID de la incidencia a eliminar.
     * @return true si la operación es exitosa, false en caso contrario.
     */
    boolean delete(Long id);
}