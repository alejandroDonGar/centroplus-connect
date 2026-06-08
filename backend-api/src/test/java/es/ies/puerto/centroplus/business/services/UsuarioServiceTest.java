package es.ies.puerto.centroplus.business.services;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.ies.puerto.centroplus.adapters.out.persistencia.usuario.UsuarioPersistenceAdapter;
import es.ies.puerto.centroplus.domain.model.Usuario;
/**
 * Clase de prueba para el servicio de usuarios
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
class UsuarioServiceTest {
    private UsuarioPersistenceAdapter usuarioPersistenceAdapter;
    private UsuarioService usuarioService;
    /**
     * Configuración inicial para cada prueba
     */
    @BeforeEach
    void setUp() {
        usuarioPersistenceAdapter = mock(UsuarioPersistenceAdapter.class);
        usuarioService = new UsuarioService(usuarioPersistenceAdapter);
    }
    /**
     * Prueba para el método findAll del servicio de usuarios
     */
    @Test
    void findAllDebeDevolverUsuarios() {
        Usuario usuario = new Usuario(1L, "Juan Pérez", "12345678A", "juan@gmail.com", "666555444", "USER");
        when(usuarioPersistenceAdapter.findAll()).thenReturn(List.of(usuario));
        List<Usuario> resultado = usuarioService.findAll();
        assertEquals(1, resultado.size());
        assertEquals("Juan Pérez", resultado.get(0).getNombre());
        verify(usuarioPersistenceAdapter, times(1)).findAll();
    }
    /**
     * Prueba para el método findById del servicio de usuarios
     */
    @Test
    void findByIdDebeDevolverUsuarioSiExiste() {
        Usuario usuario = new Usuario(1L, "Juan Pérez", "12345678A", "juan@gmail.com", "666555444", "USER");
        when(usuarioPersistenceAdapter.findById(1L)).thenReturn(Optional.of(usuario));
        Optional<Usuario> resultado = usuarioService.findById(1L);
        assertTrue(resultado.isPresent());
        assertEquals("Juan Pérez", resultado.get().getNombre());
        verify(usuarioPersistenceAdapter, times(1)).findById(1L);
    }
    /**
     * Prueba para el método save del servicio de usuarios
     */
    @Test
    void saveDebeGuardarUsuario() {
        Usuario usuario = new Usuario(1L, "Juan Pérez", "12345678A", "juan@gmail.com", "666555444", "USER");
        when(usuarioPersistenceAdapter.save(usuario)).thenReturn(usuario);
        Usuario resultado = usuarioService.save(usuario);
        assertNotNull(resultado);
        assertEquals("Juan Pérez", resultado.getNombre());
        verify(usuarioPersistenceAdapter, times(1)).save(usuario);
    }
    /**
     * Prueba para el método update del servicio de usuarios
     */
    @Test
    void updateDebeActualizarUsuarioSiExiste() {
        Usuario usuario = new Usuario(1L, "Juan Pérez", "12345678A", "juan@gmail.com", "666555444", "USER");
        when(usuarioPersistenceAdapter.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioPersistenceAdapter.save(usuario)).thenReturn(usuario);
        Optional<Usuario> resultado = usuarioService.update(1L, usuario);
        assertTrue(resultado.isPresent());
        assertEquals("Juan Pérez", resultado.get().getNombre());
        verify(usuarioPersistenceAdapter, times(1)).findById(1L);
        verify(usuarioPersistenceAdapter, times(1)).save(usuario);
    }
    /**
     * Prueba para el método delete del servicio de usuarios
     */
    @Test
    void deleteDebeDevolverTrueSiExiste() {
        when(usuarioPersistenceAdapter.existsById(1L)).thenReturn(true);
        boolean resultado = usuarioService.delete(1L);
        assertTrue(resultado);
        verify(usuarioPersistenceAdapter, times(1)).existsById(1L);
        verify(usuarioPersistenceAdapter, times(1)).delete(1L);
    }
    /**
     * Prueba para el método delete del servicio de usuarios
     */
    @Test
    void deleteDebeDevolverFalseSiNoExiste() {
        when(usuarioPersistenceAdapter.existsById(99L)).thenReturn(false);
        boolean resultado = usuarioService.delete(99L);
        assertFalse(resultado);
        verify(usuarioPersistenceAdapter, times(1)).existsById(99L);
        verify(usuarioPersistenceAdapter, never()).delete(99L);
    }
}