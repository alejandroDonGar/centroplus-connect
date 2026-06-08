package es.ies.puerto.centroplus.adapters.out.persistencia.actividad;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import es.ies.puerto.centroplus.adapters.mapper.ActividadMapper;
import es.ies.puerto.centroplus.domain.model.Actividad;
/**
 * Clase para persistir una actividad en la base de datos utilizando JPA
 * 
 * @author AlejandroDonGar  
 * @version 1.0
 */
@Component
public class ActividadPersistenceAdapter {
    private final ActividadJpaRepository actividadJpaRepository;
    private final ActividadMapper actividadMapper;
    /**
     * Constructor con parametros de la clase ActividadPersistenceAdapter
     * @param actividadJpaRepository El repositorio JPA de la actividad
     * @param actividadMapper El mapeador de la actividad
     */
    public ActividadPersistenceAdapter(ActividadJpaRepository actividadJpaRepository, ActividadMapper actividadMapper) {
        this.actividadJpaRepository = actividadJpaRepository;
        this.actividadMapper = actividadMapper;
    }
    /**
     * Metodo para obtener todas las actividades en la base de datos
     * @return Una lista de todas las actividades en la base de datos
     */
    public List<Actividad> findAll() {
        return actividadJpaRepository.findAll()
                .stream() // Convertir a flujo
                .map(actividadMapper::toDomain).toList(); // Mapear a objetos de dominio
    }
    /**
     * Metodo para obtener una actividad en la base de datos por su ID
     * @param id El ID de la actividad
     * @return El modelo de dominio de la actividad
     */
    public Optional<Actividad> findById(Long id) {
        return actividadJpaRepository.findById(id).map(actividadMapper::toDomain);
    }
    /**
     * Metodo para guardar una actividad en la base de datos
     * @param actividad El modelo de dominio de la actividad
     * @return El modelo de dominio de la actividad guardada
     */
    public Actividad save(Actividad actividad) {
        ActividadJpaEntity entity = actividadMapper.toEntity(actividad);
        ActividadJpaEntity savedEntity = actividadJpaRepository.save(entity);
        return actividadMapper.toDomain(savedEntity);
    }
    /**
     * Metodo para verificar si una actividad existe en la base de datos por su ID
     * @param id El ID de la actividad
     * @return Si la actividad existe
     */
    public boolean existsById(Long id) {
        return actividadJpaRepository.existsById(id);
    }
    /**
     * Metodo para eliminar una actividad en la base de datos por su ID
     * @param id El ID de la actividad
     */ 
    public void deleteById(Long id) {
        actividadJpaRepository.deleteById(id);
    }
}
