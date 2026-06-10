package es.ies.puerto.centroplus.business.services;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.ies.puerto.centroplus.adapters.out.persistencia.actividad.ActividadPersistenceAdapter;
import es.ies.puerto.centroplus.adapters.out.persistencia.reserva.ReservaPersistenceAdapter;
import es.ies.puerto.centroplus.domain.model.Actividad;
import es.ies.puerto.centroplus.domain.model.Reserva;
/**
 * Clase de prueba para el servicio de reservas
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
class ReservaServiceTest {
    private ReservaPersistenceAdapter reservaPersistenceAdapter;
    private ActividadPersistenceAdapter actividadPersistenceAdapter;
    private ReservaService reservaService;
    /**
     * Configuración inicial para cada prueba
     */
    @BeforeEach
    void setUp() {
        reservaPersistenceAdapter = mock(ReservaPersistenceAdapter.class);
        actividadPersistenceAdapter = mock(ActividadPersistenceAdapter.class);
        reservaService = new ReservaService(reservaPersistenceAdapter, actividadPersistenceAdapter);
    }
    /**
     * Prueba para el método findAll del servicio de reservas
     */
    @Test
    void findAllDebeDevolverReservas() {
        Reserva reserva = new Reserva(1L, 1L, 1L, "2024-06-08", "CONFIRMADA");
        when(reservaPersistenceAdapter.findAll()).thenReturn(List.of(reserva));
        List<Reserva> resultado = reservaService.findAll();
        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getIdUsuario());
        verify(reservaPersistenceAdapter, times(1)).findAll();
    }
    /**
     * Prueba para el método findById del servicio de reservas
     */
    @Test
    void findByIdDebeDevolverReservaSiExiste() {
        Reserva reserva = new Reserva(1L, 1L, 1L, "2024-06-08", "CONFIRMADA");
        when(reservaPersistenceAdapter.findById(1L)).thenReturn(Optional.of(reserva));
        Optional<Reserva> resultado = reservaService.findById(1L);
        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getIdUsuario());
        verify(reservaPersistenceAdapter, times(1)).findById(1L);
    }
    /**
     * Prueba para el método save del servicio de reservas
     */
    @Test
    void saveDebeGuardarReserva() {
        Reserva reserva = new Reserva(null, 1L, 1L, "2024-06-08", "CONFIRMADA");
        Reserva reservaGuardada = new Reserva(3L, 1L, 1L, "2024-06-08", "CONFIRMADA");
        Actividad actividad = new Actividad( 1L, "Yoga", "DEPORTIVA", 60, 25.50, 15, 8);
        when(actividadPersistenceAdapter.findById(1L)).thenReturn(Optional.of(actividad));
        when(actividadPersistenceAdapter.save(any(Actividad.class))).thenReturn(actividad);
        when(reservaPersistenceAdapter.save(reserva)).thenReturn(reservaGuardada);
        Reserva resultado = reservaService.save(reserva);
        assertNotNull(resultado);
        assertEquals(3L, resultado.getId());
        assertEquals(1L, resultado.getIdUsuario());
        assertEquals(9, actividad.getPlazasOcupadas());
        verify(actividadPersistenceAdapter, times(1)).findById(1L);
        verify(actividadPersistenceAdapter, times(1)).save(actividad);
        verify(reservaPersistenceAdapter, times(1)).save(reserva);
    }
    /**
     * Prueba para el método update del servicio de reservas
     */
    @Test
    void updateDebeActualizarReservaSiExiste() {
        Reserva reserva = new Reserva(1L, 1L, 1L, "2024-06-08", "CONFIRMADA");
        when(reservaPersistenceAdapter.findById(1L)).thenReturn(Optional.of(reserva));
        when(reservaPersistenceAdapter.save(reserva)).thenReturn(reserva);
        Optional<Reserva> resultado = reservaService.update(1L, reserva);
        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getIdUsuario());
        verify(reservaPersistenceAdapter, times(1)).findById(1L);
        verify(reservaPersistenceAdapter, times(1)).save(reserva);
    }
    /**
     * Prueba para el método delete del servicio de reservas
     */
    @Test
    void deleteDebeDevolverTrueSiExiste() {
        Reserva reserva = new Reserva(1L, 1L, 1L, "2024-06-08", "ACTIVA");
        Actividad actividad = new Actividad( 1L, "Yoga", "DEPORTIVA", 60, 25.50, 15, 8);
        when(reservaPersistenceAdapter.findById(1L)).thenReturn(Optional.of(reserva));
        when(actividadPersistenceAdapter.findById(1L)).thenReturn(Optional.of(actividad));
        when(actividadPersistenceAdapter.save(any(Actividad.class))).thenReturn(actividad);
        boolean resultado = reservaService.delete(1L);
        assertTrue(resultado);
        assertEquals(7, actividad.getPlazasOcupadas());
        verify(reservaPersistenceAdapter, times(1)).findById(1L);
        verify(actividadPersistenceAdapter, times(1)).findById(1L);
        verify(actividadPersistenceAdapter, times(1)).save(actividad);
        verify(reservaPersistenceAdapter, times(1)).deleteById(1L);
    }
    /**
     * Prueba para el método delete del servicio de reservas
     */
    @Test
    void deleteDebeDevolverFalseSiNoExiste() {
        when(reservaPersistenceAdapter.findById(99L))
                .thenReturn(Optional.empty());
        boolean resultado = reservaService.delete(99L);
        assertFalse(resultado);
        verify(reservaPersistenceAdapter, times(1)).findById(99L);
        verify(reservaPersistenceAdapter, never()).deleteById(99L);
        verify(actividadPersistenceAdapter, never()).save(any(Actividad.class));
    }
}