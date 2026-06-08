package es.ies.puerto.centroplus.adapters.out.persistencia.actividad;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Clase para representar una actividad en la base de datos utilizando JPA
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
public interface ActividadJpaRepository extends JpaRepository<ActividadJpaEntity, Long> {}
