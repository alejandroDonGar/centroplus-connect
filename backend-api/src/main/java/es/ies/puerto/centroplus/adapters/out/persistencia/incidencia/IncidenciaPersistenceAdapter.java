package es.ies.puerto.centroplus.adapters.out.persistencia.incidencia;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import es.ies.puerto.centroplus.adapters.mapper.IncidenciaMapper;
import es.ies.puerto.centroplus.domain.model.Incidencia;
/**
 * Adaptador de persistencia para la entidad IncidenciaJpaEntity
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
@Component
public class IncidenciaPersistenceAdapter {
    private final IncidenciaJpaRepository incidenciaJpaRepository;
    private final IncidenciaMapper incidenciaMapper;
    /**
     * Constructor de la clase IncidenciaPersistenceAdapter
     * @param incidenciaJpaRepository El repositorio JPA para la entidad IncidenciaJpaEntity
     * @param incidenciaMapper El mapper para la entidad IncidenciaJpaEntity
     */
    public IncidenciaPersistenceAdapter(IncidenciaJpaRepository incidenciaJpaRepository, IncidenciaMapper incidenciaMapper) {
        this.incidenciaJpaRepository = incidenciaJpaRepository;
        this.incidenciaMapper = incidenciaMapper;
    }
    /**
     * Busca todas las Incidencias en la base de datos
     * @return Una lista de Incidencias
     */
    public List<Incidencia> findAll() {
        return incidenciaJpaRepository.findAll()
                .stream() // Convierte la lista a un flujo
                .map(incidencia -> incidenciaMapper.toDomain(incidencia)) // Mapea cada IncidenciaJpaEntity a Incidencia
                .toList();
    }
    /**
     * Busca una Incidencia por su ID en la base de datos
     * @param id El ID de la Incidencia
     * @return El objeto Incidencia correspondiente, o Optional.empty() si no se encuentra
     */
    public Optional<Incidencia> findById(Long id) {
        return incidenciaJpaRepository.findById(id)
                .map(incidencia -> incidenciaMapper.toDomain(incidencia)); // Mapea la IncidenciaJpaEntity a Incidencia
    }
    /**
     * Guarda una Incidencia en la base de datos
     * @param incidencia El objeto Incidencia a guardar
     * @return El objeto Incidencia guardado
     */
    public Incidencia save(Incidencia incidencia) {
        IncidenciaJpaEntity entity = incidenciaMapper.toEntity(incidencia);
        IncidenciaJpaEntity savedEntity = incidenciaJpaRepository.save(entity);
        return incidenciaMapper.toDomain(savedEntity);
    }
    /**
     * Comprueba si existe una Incidencia con el ID dado en la base de datos
     * @param id El ID de la Incidencia a comprobar
     * @return true si existe, false si no
     */
    public boolean existsById(Long id) {
        return incidenciaJpaRepository.existsById(id);
    }
    /**
     * Elimina una Incidencia con el ID dado de la base de datos
     * @param id El ID de la Incidencia a eliminar
     */
    public void deleteById(Long id) {
        incidenciaJpaRepository.deleteById(id);
    }
}