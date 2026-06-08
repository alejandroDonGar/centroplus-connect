package es.ies.puerto.centroplus.adapters.out.persistencia.actividad;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.ies.puerto.centroplus.adapters.mapper.ActividadMapper;
import es.ies.puerto.centroplus.domain.model.Actividad;
/**
 * Clase de prueba para el adaptador de persistencia de actividades
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
class ActividadPersistenceAdapterTest {
    private ActividadJpaRepository actividadJpaRepository;
    private ActividadMapper actividadMapper;
    private ActividadPersistenceAdapter actividadPersistenceAdapter;
    /**
     * Configuración inicial para cada prueba
     */
    @BeforeEach
    void setUp() {
        actividadJpaRepository = mock(ActividadJpaRepository.class);
        actividadMapper = mock(ActividadMapper.class);
        actividadPersistenceAdapter = new ActividadPersistenceAdapter(actividadJpaRepository, actividadMapper);
    }
    /**
     * Prueba para el método findAll del adaptador de persistencia
     */
    @Test
    void findAllDebeDevolverActividades() {
        ActividadJpaEntity entity = new ActividadJpaEntity();
        Actividad domain = new Actividad();
        when(actividadJpaRepository.findAll()).thenReturn(List.of(entity));
        when(actividadMapper.toDomain(entity)).thenReturn(domain);
        List<Actividad> resultado = actividadPersistenceAdapter.findAll();
        assertEquals(1, resultado.size());
        verify(actividadJpaRepository, times(1)).findAll();
        verify(actividadMapper, times(1)).toDomain(entity);
    }
    /**
     * Prueba para el método findById del adaptador de persistencia
     */
    @Test
    void findByIdDebeDevolverActividadSiExiste() {
        ActividadJpaEntity entity = new ActividadJpaEntity();
        Actividad domain = new Actividad();
        when(actividadJpaRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(actividadMapper.toDomain(entity)).thenReturn(domain);
        Optional<Actividad> resultado = actividadPersistenceAdapter.findById(1L);
        assertTrue(resultado.isPresent());
        verify(actividadJpaRepository, times(1)).findById(1L);
    }
    /**
     * Prueba para el método save del adaptador de persistencia
     */
    @Test
    void saveDebeGuardarActividad() {
        Actividad domain = new Actividad();
        ActividadJpaEntity entity = new ActividadJpaEntity();
        when(actividadMapper.toEntity(domain)).thenReturn(entity);
        when(actividadJpaRepository.save(entity)).thenReturn(entity);
        when(actividadMapper.toDomain(entity)).thenReturn(domain);
        Actividad resultado = actividadPersistenceAdapter.save(domain);
        assertNotNull(resultado);
        verify(actividadJpaRepository, times(1)).save(entity);
    }
    /**
     * Prueba para el método existsById del adaptador de persistencia
     */
    @Test
    void existsByIdDebeDevolverTrueSiExiste() {
        when(actividadJpaRepository.existsById(1L)).thenReturn(true);
        assertTrue(actividadPersistenceAdapter.existsById(1L));
    }
    /**
     * Prueba para el método deleteById del adaptador de persistencia
     */
    @Test
    void deleteByIdDebeLlamarAlRepositorio() {
        actividadPersistenceAdapter.deleteById(1L);
        verify(actividadJpaRepository, times(1)).deleteById(1L);
    }
}