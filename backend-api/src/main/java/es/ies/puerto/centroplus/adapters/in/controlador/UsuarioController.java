package es.ies.puerto.centroplus.adapters.in.controlador;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import es.ies.puerto.centroplus.adapters.in.api.usuario.UsuarioRequest;
import es.ies.puerto.centroplus.adapters.in.api.usuario.UsuarioResponse;
import es.ies.puerto.centroplus.business.ports.UsuarioServicePort;
import es.ies.puerto.centroplus.domain.model.Usuario;
/**
 * Controlador para manejar las operaciones de usuarios
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
    private final UsuarioServicePort usuarioServicePort;
    /**
     * Constructor para inicializar el controlador con el servicio de usuarios
     * @param usuarioServicePort El servicio de usuarios para manejar las operaciones de negocio
     */
    public UsuarioController(UsuarioServicePort usuarioServicePort) {
        this.usuarioServicePort = usuarioServicePort;
    }
    /**
     * Obtener todos los usuarios
     * @return Una lista de objetos UsuarioResponse
     */
    @GetMapping
    public List<UsuarioResponse> obtenerUsuarios() {
        return usuarioServicePort.findAll()
                .stream() // Utilizar flujo de datos para transformar los usuarios a objetos UsuarioResponse
                .map(usuario -> toResponse(usuario)) // Mapear cada usuario a un objeto UsuarioResponse
                .toList();
    }
    /**
     * Obtener un usuario por su ID
     * @param id El ID del usuario a obtener
     * @return El objeto UsuarioResponse que contiene la información del usuario
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> obtenerUsuario(@PathVariable Long id) {
        return usuarioServicePort.findById(id)
                .map(usuario -> ResponseEntity.ok(toResponse(usuario))) // Mapear el usuario a un objeto UsuarioResponse
                .orElse(ResponseEntity.notFound().build()); // Si no se encuentra el usuario, devolver una respuesta 404
    }
    /**
     * Crear un nuevo usuario
     * @param request El objeto UsuarioRequest que contiene los información del usuario a crear
     * @return El objeto UsuarioResponse que contiene la información del usuario creado
     */
    @PostMapping
    public ResponseEntity<UsuarioResponse> save(@RequestBody UsuarioRequest request) {
        Usuario usuario = toDomain(request);
        Usuario usuarioGuardado = usuarioServicePort.save(usuario);
        return ResponseEntity.ok(toResponse(usuarioGuardado));
    }
    /**
     * Actualizar un usuario existente
     * @param id El ID del usuario a actualizar
     * @param request El objeto UsuarioRequest que contiene los información del usuario a actualizar
     * @return El objeto UsuarioResponse que contiene la información del usuario actualizado
     */
    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioResponse> update(@PathVariable Long id, @RequestBody UsuarioRequest request) {
        Usuario usuario = toDomain(request);
        return usuarioServicePort.update(id, usuario)
                .map(usuarioActualizado -> ResponseEntity.ok(toResponse(usuarioActualizado))) // Mapear el usuario actualizado a un objeto UsuarioResponse
                .orElse(ResponseEntity.notFound().build()); // Si no se encuentra el usuario, devolver una respuesta 404
    }
    /**
     * Eliminar un usuario existente
     * @param id El ID del usuario a eliminar
     * @return El objeto Void que representa la eliminación exitosa
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean eliminado = usuarioServicePort.delete(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build(); // Si no se encuentra el usuario, devolver una respuesta 404
        }
        return ResponseEntity.noContent().build(); // Si se elimina el usuario, devolver una respuesta 204
    }
    /**
     * Mapear un objeto Usuario domain a un objeto Usuario domain
     * @param request El objeto UsuarioRequest a mapear
     * @return El objeto Usuario domain que contiene la información del usuario
     */
    private Usuario toDomain(UsuarioRequest request) {
        return new Usuario(
                null,
                request.getNombre(),
                request.getDni(),
                request.getEmail(),
                request.getTelefono(),
                request.getTipoUsuario()
        );
    }
    /**
     * Mapear un objeto Usuario domain a un objeto Usuario domain
     * @param request El objeto UsuarioRequest a mapear
     * @return El objeto UsuarioResponse que contiene la información del usuario
     */
    private UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getDni(),
                usuario.getEmail(),
                usuario.getTelefono(),
                usuario.getTipoUsuario()
        );
    }
}