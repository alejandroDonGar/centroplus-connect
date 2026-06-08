package es.ies.puerto.centroplus.adapters.out.persistencia.reserva;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repositorio JPA para la entidad ReservaJpaEntity
 * 
 * @author Ismael Perez
 * @version 1.0
 */
public interface ReservaJpaRepository extends JpaRepository<ReservaJpaEntity, Long> {}