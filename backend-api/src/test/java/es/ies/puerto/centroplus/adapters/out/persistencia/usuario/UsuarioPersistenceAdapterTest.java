package es.ies.puerto.centroplus.adapters.out.persistencia.usuario;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.ies.puerto.centroplus.adapters.mapper.UsuarioMapper;
import es.ies.puerto.centroplus.domain.model.Usuario;
/**
 * Clase de prueba para el adaptador de persistencia de usuarios
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
class UsuarioPersistenceAdapterTest {
    private UsuarioJpaRepository usuarioJpaRepository;
    private UsuarioMapper usuarioMapper;
    private UsuarioPersistenceAdapter usuarioPersistenceAdapter;
    /**
     * Configuración inicial para cada prueba
     */
    @BeforeEach
    void setUp() {
        usuarioJpaRepository = mock(UsuarioJpaRepository.class);
        usuarioMapper = mock(UsuarioMapper.class);
        usuarioPersistenceAdapter = new UsuarioPersistenceAdapter(usuarioJpaRepository, usuarioMapper);
    }
    /**
     * Prueba para el método findAll del adaptador de persistencia
     */
    @Test
    void findAllDebeDevolverUsuarios() {
        UsuarioJpaEntity entity = new UsuarioJpaEntity();
        Usuario domain = new Usuario();
        when(usuarioJpaRepository.findAll()).thenReturn(List.of(entity));
        when(usuarioMapper.toDomain(entity)).thenReturn(domain);
        List<Usuario> resultado = usuarioPersistenceAdapter.findAll();
        assertEquals(1, resultado.size());
        verify(usuarioJpaRepository, times(1)).findAll();
        verify(usuarioMapper, times(1)).toDomain(entity);
    }
    /**
     * Prueba para el método findById del adaptador de persistencia
     */
    @Test
    void findByIdDebeDevolverUsuarioSiExiste() {
        UsuarioJpaEntity entity = new UsuarioJpaEntity();
        Usuario domain = new Usuario();
        when(usuarioJpaRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(usuarioMapper.toDomain(entity)).thenReturn(domain);
        Optional<Usuario> resultado = usuarioPersistenceAdapter.findById(1L);
        assertTrue(resultado.isPresent());
        verify(usuarioJpaRepository, times(1)).findById(1L);
        verify(usuarioMapper, times(1)).toDomain(entity);
    }
    /**
     * Prueba para el método save del adaptador de persistencia
     */
    @Test
    void saveDebeGuardarUsuario() {
        Usuario domain = new Usuario();
        UsuarioJpaEntity entity = new UsuarioJpaEntity();
        when(usuarioMapper.toEntity(domain)).thenReturn(entity);
        when(usuarioJpaRepository.save(entity)).thenReturn(entity);
        when(usuarioMapper.toDomain(entity)).thenReturn(domain);
        Usuario resultado = usuarioPersistenceAdapter.save(domain);
        assertNotNull(resultado);
        verify(usuarioJpaRepository, times(1)).save(entity);
        verify(usuarioMapper, times(1)).toEntity(domain);
        verify(usuarioMapper, times(1)).toDomain(entity);
    }
    /**
     * Prueba para el método existsById del adaptador de persistencia
     */
    @Test
    void existsByIdDebeDevolverTrueSiExiste() {
        when(usuarioJpaRepository.existsById(1L)).thenReturn(true);
        assertTrue(usuarioPersistenceAdapter.existsById(1L));
        verify(usuarioJpaRepository, times(1)).existsById(1L);
    }
    /**
     * Prueba para el método delete del adaptador de persistencia
     */
    @Test
    void deleteDebeLlamarAlRepositorio() {
        usuarioPersistenceAdapter.delete(1L);
        verify(usuarioJpaRepository, times(1)).deleteById(1L);
    }
}