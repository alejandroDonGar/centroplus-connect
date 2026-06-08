package es.ies.puerto.centroplus.adapters.in.controlador;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import es.ies.puerto.centroplus.adapters.in.api.usuario.UsuarioRequest;
import es.ies.puerto.centroplus.adapters.in.api.usuario.UsuarioResponse;
import es.ies.puerto.centroplus.business.ports.UsuarioServicePort;
import es.ies.puerto.centroplus.domain.model.Usuario;
/**
 * Clase de prueba para el controlador de usuarios
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
class UsuarioControllerTest {
    private UsuarioServicePort usuarioServicePort;
    private UsuarioController usuarioController;
    /**
     * Configuración inicial para cada prueba
     */
    @BeforeEach
    void setUp() {
        usuarioServicePort = mock(UsuarioServicePort.class);
        usuarioController = new UsuarioController(usuarioServicePort);
    }
    /**
     * Prueba para el método obtenerUsuarios
     */
    @Test
    void obtenerUsuariosDebeDevolverLista() {
        Usuario usuario = new Usuario(1L, "Juan Pérez", "12345678A", "juan@gmail.com", "666555444", "USER");
        when(usuarioServicePort.findAll()).thenReturn(List.of(usuario));
        List<UsuarioResponse> resultado = usuarioController.obtenerUsuarios();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Juan Pérez", resultado.get(0).getNombre());
        verify(usuarioServicePort, times(1)).findAll();
    }
    /**
     * Prueba para el método obtenerUsuario
     */
    @Test
    void obtenerUsuarioDebeDevolverUsuarioSiExiste() {
        Usuario usuario = new Usuario(1L, "Juan Pérez", "12345678A", "juan@gmail.com", "666555444", "USER");
        when(usuarioServicePort.findById(1L)).thenReturn(Optional.of(usuario));
        ResponseEntity<UsuarioResponse> resultado = usuarioController.obtenerUsuario(1L);
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertNotNull(resultado.getBody());
        assertEquals("Juan Pérez", resultado.getBody().getNombre());
        verify(usuarioServicePort, times(1)).findById(1L);
    }
    /**
     * Prueba para el método save
     */
    @Test
    void saveDebeGuardarUsuario() {
        UsuarioRequest request = new UsuarioRequest();
        request.setNombre("Juan Pérez");
        Usuario usuarioGuardada = new Usuario(1L, "Juan Pérez", "12345678A", "juan@gmail.com", "666555444", "USER");
        when(usuarioServicePort.save(any(Usuario.class))).thenReturn(usuarioGuardada);
        ResponseEntity<UsuarioResponse> resultado = usuarioController.save(request);
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertNotNull(resultado.getBody());
        assertEquals("Juan Pérez", resultado.getBody().getNombre());
        verify(usuarioServicePort, times(1)).save(any(Usuario.class));
    }
    /**
     * Prueba para el método delete
     */
    @Test
    void deleteDebeEliminarUsuarioSiExiste() {
        when(usuarioServicePort.delete(1L)).thenReturn(true);
        ResponseEntity<Void> resultado = usuarioController.delete(1L);
        assertEquals(HttpStatus.NO_CONTENT, resultado.getStatusCode());
        verify(usuarioServicePort, times(1)).delete(1L);
    }
}