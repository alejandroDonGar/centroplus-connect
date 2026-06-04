package es.ies.puerto.services;
import java.util.List;
import es.ies.puerto.modelos.Usuarios;
import es.ies.puerto.repositories.interfaces.IUsuarioRepository;
import es.ies.puerto.services.interfaces.IUsuarioService;
import es.ies.puerto.utils.Validaciones;
/**
 * @author AlejandroDonGar y JavierReyPer
 * @version 1.0.0
 * 
 * Clase UsuarioService donde implementamos los metodos de IUsuarioService
 */
public class UsuarioService implements IUsuarioService{

    IUsuarioRepository usuarioRepository;

    public UsuarioService() {
        this.usuarioRepository = new es.ies.puerto.repositories.UsuarioRepository();
    }

    public UsuarioService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    public List<Usuarios> findAll() {
        return usuarioRepository.findAll();
    }
    @Override
    public Usuarios findByID(Integer id) {
        if(!Validaciones.esIntegerValido(id)) {
            return null;
        }
        return usuarioRepository.findByID(id);
    }
    @Override
    public boolean save(Usuarios usuario) {
        if(!Validaciones.esUsuarioValido(usuario)) {
            return false;
        }
        return usuarioRepository.save(usuario);
    }
    @Override
    public boolean update(Usuarios usuario) {
        if(!Validaciones.esUsuarioValido(usuario)) {
            return false;
        }
        return usuarioRepository.update(usuario);
    }
    @Override
    public boolean delete(Usuarios usuario) {
        if(!Validaciones.esUsuarioValido(usuario)) {
            return false;
        }
        return usuarioRepository.delete(usuario);
    }
}
