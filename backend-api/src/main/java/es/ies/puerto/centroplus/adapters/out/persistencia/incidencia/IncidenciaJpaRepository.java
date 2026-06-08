package es.ies.puerto.centroplus.adapters.out.persistencia.incidencia;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repositorio JPA para la entidad IncidenciaJpaEntity
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
public interface IncidenciaJpaRepository extends JpaRepository<IncidenciaJpaEntity, Long> {}