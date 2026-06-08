package es.ies.puerto.centroplus.business.ports;
import es.ies.puerto.centroplus.domain.model.Usuario;
import java.util.List;
import java.util.Optional;
/**
 * Interfaz para definir los métodos de servicio de usuarios
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
public interface UsuarioServicePort {
    /**
     * Método para obtener todos los usuarios
     * @return List<Usuario> Una lista de todos los usuarios
     */
    List<Usuario> findAll();
    /**
     * Método para obtener un usuario por su ID
     * @param id El ID del usuario a obtener
     * @return Optional<Usuario> El usuario con el ID especificado, si existe
     */
    Optional<Usuario> findById(Long id);
    /**
     * Método para guardar un usuario
     * @param usuario El usuario a guardar
     * @return El El usuario guardado
     */
    Usuario save(Usuario usuario);
    /**
     * Método para actualizar un usuario
     * @param id El ID del usuario a actualizar
     * @param usuario El usuario actualizado
     * @return Optional<Usuario> El usuario actualizado, si existe
     */
    Optional<Usuario> update(Long id, Usuario usuario);
    /**
     * Método para eliminar un usuario por su ID
     * @param id El ID del usuario a eliminar
     * @return boolean Si el usuario se eliminó con éxito
     */
    boolean delete(Long id);
}