package es.ies.puerto.centroplus.adapters.out.persistencia.incidencia;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.ies.puerto.centroplus.adapters.mapper.IncidenciaMapper;
import es.ies.puerto.centroplus.domain.model.Incidencia;
/**
 * Clase de prueba para el adaptador de persistencia de incidencias
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
class IncidenciaPersistenceAdapterTest {
    private IncidenciaJpaRepository incidenciaJpaRepository;
    private IncidenciaMapper incidenciaMapper;
    private IncidenciaPersistenceAdapter incidenciaPersistenceAdapter;
    /**
     * Configuración inicial para cada prueba
     */
    @BeforeEach
    void setUp() {
        incidenciaJpaRepository = mock(IncidenciaJpaRepository.class);
        incidenciaMapper = mock(IncidenciaMapper.class);
        incidenciaPersistenceAdapter = new IncidenciaPersistenceAdapter(incidenciaJpaRepository, incidenciaMapper);
    }
    /**
     * Prueba para el método findAll del adaptador de persistencia
     */
    @Test
    void findAllDebeDevolverIncidencias() {
        IncidenciaJpaEntity entity = new IncidenciaJpaEntity();
        Incidencia domain = new Incidencia();
        when(incidenciaJpaRepository.findAll()).thenReturn(List.of(entity));
        when(incidenciaMapper.toDomain(entity)).thenReturn(domain);
        List<Incidencia> resultado = incidenciaPersistenceAdapter.findAll();
        assertEquals(1, resultado.size());
        verify(incidenciaJpaRepository, times(1)).findAll();
        verify(incidenciaMapper, times(1)).toDomain(entity);
    }
    /**
     * Prueba para el método findById del adaptador de persistencia
     */
    @Test
    void findByIdDebeDevolverIncidenciaSiExiste() {
        IncidenciaJpaEntity entity = new IncidenciaJpaEntity();
        Incidencia domain = new Incidencia();
        when(incidenciaJpaRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(incidenciaMapper.toDomain(entity)).thenReturn(domain);
        Optional<Incidencia> resultado = incidenciaPersistenceAdapter.findById(1L);
        assertTrue(resultado.isPresent());
        verify(incidenciaJpaRepository, times(1)).findById(1L);
        verify(incidenciaMapper, times(1)).toDomain(entity);
    }
    /**
     * Prueba para el método save del adaptador de persistencia
     */
    @Test
    void saveDebeGuardarIncidencia() {
        Incidencia domain = new Incidencia();
        IncidenciaJpaEntity entity = new IncidenciaJpaEntity();
        when(incidenciaMapper.toEntity(domain)).thenReturn(entity);
        when(incidenciaJpaRepository.save(entity)).thenReturn(entity);
        when(incidenciaMapper.toDomain(entity)).thenReturn(domain);
        Incidencia resultado = incidenciaPersistenceAdapter.save(domain);
        assertNotNull(resultado);
        verify(incidenciaJpaRepository, times(1)).save(entity);
        verify(incidenciaMapper, times(1)).toEntity(domain);
        verify(incidenciaMapper, times(1)).toDomain(entity);
    }
    /**
     * Prueba para el método existsById del adaptador de persistencia
     */
    @Test
    void existsByIdDebeDevolverTrueSiExiste() {
        when(incidenciaJpaRepository.existsById(1L)).thenReturn(true);
        assertTrue(incidenciaPersistenceAdapter.existsById(1L));
        verify(incidenciaJpaRepository, times(1)).existsById(1L);
    }
    /**
     * Prueba para el método deleteById del adaptador de persistencia
     */
    @Test
    void deleteByIdDebeLlamarAlRepositorio() {
        incidenciaPersistenceAdapter.deleteById(1L);
        verify(incidenciaJpaRepository, times(1)).deleteById(1L);
    }
}