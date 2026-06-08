package es.ies.puerto.centroplus.adapters.out.persistencia.usuario;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Interfaz para implementar el repositorio de usuarios utilizando JPA
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
public interface UsuarioJpaRepository extends JpaRepository<UsuarioJpaEntity, Long> {}
