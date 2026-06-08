package es.ies.puerto.centroplus.business.services;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import es.ies.puerto.centroplus.adapters.out.persistencia.usuario.UsuarioPersistenceAdapter;
import es.ies.puerto.centroplus.business.ports.UsuarioServicePort;
import es.ies.puerto.centroplus.domain.model.Usuario;
/**
 * Clase para implementar el servicio de usuarios utilizando JPA
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
@Service // Indica que esta clase es un servicio de Spring
public class UsuarioService implements UsuarioServicePort {
    private final UsuarioPersistenceAdapter usuarioPersistenceAdapter;
    /**
     * Constructor de la clase
     * @param usuarioPersistenceAdapter El adaptador de persistencia de usuarios
     */
    public UsuarioService(UsuarioPersistenceAdapter usuarioPersistenceAdapter) {
        this.usuarioPersistenceAdapter = usuarioPersistenceAdapter;
    }
    @Override
    public List<Usuario> findAll() {
        return usuarioPersistenceAdapter.findAll();
    }
    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioPersistenceAdapter.findById(id);
    }
    @Override
    public Usuario save(Usuario usuario) {
        return usuarioPersistenceAdapter.save(usuario);
    }
    @Override
    public Optional<Usuario> update(Long id, Usuario usuario) {
        Optional<Usuario> usuarioEncontrado = usuarioPersistenceAdapter.findById(id);
        if (usuarioEncontrado.isEmpty()) {
            return Optional.empty();
        }
        usuario.setId(id);
        Usuario usuarioActualizado = usuarioPersistenceAdapter.save(usuario);
        return Optional.of(usuarioActualizado);
    }
    @Override
    public boolean delete(Long id) {
        if (!usuarioPersistenceAdapter.existsById(id)) {
            return false;
        }
        usuarioPersistenceAdapter.delete(id);
        return true;
    }
}