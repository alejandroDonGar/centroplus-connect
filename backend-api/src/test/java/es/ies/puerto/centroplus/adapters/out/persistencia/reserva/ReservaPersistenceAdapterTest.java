package es.ies.puerto.centroplus.adapters.out.persistencia.reserva;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.ies.puerto.centroplus.adapters.mapper.ReservaMapper;
import es.ies.puerto.centroplus.domain.model.Reserva;
/**
 * Clase de prueba para el adaptador de persistencia de reservas
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
class ReservaPersistenceAdapterTest {
    private ReservaJpaRepository reservaJpaRepository;
    private ReservaMapper reservaMapper;
    private ReservaPersistenceAdapter reservaPersistenceAdapter;
    /**
     * Configuración inicial para cada prueba
     */
    @BeforeEach
    void setUp() {
        reservaJpaRepository = mock(ReservaJpaRepository.class);
        reservaMapper = mock(ReservaMapper.class);
        reservaPersistenceAdapter = new ReservaPersistenceAdapter(reservaJpaRepository, reservaMapper);
    }
    /**
     * Prueba para el método findAll del adaptador de persistencia
     */
    @Test
    void findAllDebeDevolverReservas() {
        ReservaJpaEntity entity = new ReservaJpaEntity();
        Reserva domain = new Reserva();
        when(reservaJpaRepository.findAll()).thenReturn(List.of(entity));
        when(reservaMapper.toDomain(entity)).thenReturn(domain);
        List<Reserva> resultado = reservaPersistenceAdapter.findAll();
        assertEquals(1, resultado.size());
        verify(reservaJpaRepository, times(1)).findAll();
        verify(reservaMapper, times(1)).toDomain(entity);
    }
    /**
     * Prueba para el método findById del adaptador de persistencia
     */
    @Test
    void findByIdDebeDevolverReservaSiExiste() {
        ReservaJpaEntity entity = new ReservaJpaEntity();
        Reserva domain = new Reserva();
        when(reservaJpaRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(reservaMapper.toDomain(entity)).thenReturn(domain);
        Optional<Reserva> resultado = reservaPersistenceAdapter.findById(1L);
        assertTrue(resultado.isPresent());
        verify(reservaJpaRepository, times(1)).findById(1L);
        verify(reservaMapper, times(1)).toDomain(entity);
    }
    /**
     * Prueba para el método save del adaptador de persistencia
     */
    @Test
    void saveDebeGuardarReserva() {
        Reserva domain = new Reserva();
        ReservaJpaEntity entity = new ReservaJpaEntity();
        when(reservaMapper.toEntity(domain)).thenReturn(entity);
        when(reservaJpaRepository.save(entity)).thenReturn(entity);
        when(reservaMapper.toDomain(entity)).thenReturn(domain);
        Reserva resultado = reservaPersistenceAdapter.save(domain);
        assertNotNull(resultado);
        verify(reservaJpaRepository, times(1)).save(entity);
        verify(reservaMapper, times(1)).toEntity(domain);
        verify(reservaMapper, times(1)).toDomain(entity);
    }
    /**
     * Prueba para el método existsById del adaptador de persistencia
     */
    @Test
    void existsByIdDebeDevolverTrueSiExiste() {
        when(reservaJpaRepository.existsById(1L)).thenReturn(true);
        assertTrue(reservaPersistenceAdapter.existsById(1L));
        verify(reservaJpaRepository, times(1)).existsById(1L);
    }
    /**
     * Prueba para el método deleteById del adaptador de persistencia
     */
    @Test
    void deleteByIdDebeLlamarAlRepositorio() {
        reservaPersistenceAdapter.deleteById(1L);
        verify(reservaJpaRepository, times(1)).deleteById(1L);
    }
}