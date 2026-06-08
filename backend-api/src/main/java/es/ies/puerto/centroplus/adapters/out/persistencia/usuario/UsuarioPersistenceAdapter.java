package es.ies.puerto.centroplus.adapters.out.persistencia.usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import es.ies.puerto.centroplus.adapters.mapper.UsuarioMapper;
import es.ies.puerto.centroplus.domain.model.Usuario;
/**
 * Clase para adaptar la persistencia de usuarios en la base de datos
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
@Component
public class UsuarioPersistenceAdapter {
    private final UsuarioJpaRepository usuarioJpaRepository;
    private final UsuarioMapper usuarioMapper;
    /**
     * Constructor de la clase
     * @param usuarioJpaRepository El repositorio JPA para usuarios
     * @param usuarioMapper El mapeador de usuarios
     */
    public UsuarioPersistenceAdapter(UsuarioJpaRepository usuarioJpaRepository, UsuarioMapper usuarioMapper) {
        this.usuarioJpaRepository = usuarioJpaRepository;
        this.usuarioMapper = usuarioMapper;
    }
    /**
     * Método para obtener todos los usuarios
     * @return List<Usuario> Una lista de todos los usuarios
     */
    public List<Usuario> findAll() {
        return usuarioJpaRepository.findAll()
                .stream() // Convertir a flujo de objetos de la clase UsuarioJpaEntity
                .map(usuarioMapper::toDomain) // Mapear a objetos de la clase Usuario lo que significa que se convierte la entidad en un modelo de dominio
                .toList();
    }
    /**
     * Método para obtener un usuario por su ID
     * @param id El ID del usuario a obtener
     * @return Optional<Usuario> El usuario con el ID especificado, si existe
     */
    public Optional<Usuario> findById(Long id) {
        return usuarioJpaRepository.findById(id)
                .map(usuario -> usuarioMapper.toDomain(usuario)); // Mapear a objetos de la clase Usuario lo que significa que se convierte la entidad en un modelo de dominio
    }
    /**
     * Método para guardar un usuario
     * @param usuario El usuario a guardar
     * @return El El usuario guardado
     */
    public Usuario save(Usuario usuario) {
        UsuarioJpaEntity entity = usuarioMapper.toEntity(usuario);
        UsuarioJpaEntity savedEntity = usuarioJpaRepository.save(entity);
        return usuarioMapper.toDomain(savedEntity);
    }
    /**
     * Método para verificar si un usuario existe por su ID
     * @param id El ID del usuario a verificar
     * @return boolean Si el usuario existe
     */
    public boolean existsById(Long id) {
        return usuarioJpaRepository.existsById(id);
    }
    /**
     * Método para eliminar un usuario por su ID
     * @param id El ID del usuario a eliminar
     */
    public void delete(Long id) {
        usuarioJpaRepository.deleteById(id);
    }
}