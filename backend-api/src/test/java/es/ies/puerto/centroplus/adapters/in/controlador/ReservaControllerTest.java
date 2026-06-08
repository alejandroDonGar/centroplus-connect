package es.ies.puerto.centroplus.adapters.in.controlador;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import es.ies.puerto.centroplus.adapters.in.api.reserva.ReservaRequest;
import es.ies.puerto.centroplus.adapters.in.api.reserva.ReservaResponse;
import es.ies.puerto.centroplus.business.ports.ReservaServicePort;
import es.ies.puerto.centroplus.domain.model.Reserva;
/**
 * Clase de prueba para el controlador de reservas
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
class ReservaControllerTest {
    private ReservaServicePort reservaServicePort;
    private ReservaController reservaController;
    /**
     * Configuración inicial para cada prueba
     */
    @BeforeEach
    void setUp() {
        reservaServicePort = mock(ReservaServicePort.class);
        reservaController = new ReservaController(reservaServicePort);
    }
    /**
     * Prueba para el método obtenerReservas
     */
    @Test
    void obtenerReservasDebeDevolverLista() {
        Reserva reserva = new Reserva(1L, 1L, 1L, "2024-06-08", "CONFIRMADA");
        when(reservaServicePort.findAll()).thenReturn(List.of(reserva));
        List<ReservaResponse> resultado = reservaController.obtenerReservas();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getIdUsuario());
        verify(reservaServicePort, times(1)).findAll();
    }
    /**
     * Prueba para el método obtenerReserva
     */
    @Test
    void obtenerReservaDebeDevolverReservaSiExiste() {
        Reserva reserva = new Reserva(1L, 1L, 1L, "2024-06-08", "CONFIRMADA");
        when(reservaServicePort.findById(1L)).thenReturn(Optional.of(reserva));
        ResponseEntity<ReservaResponse> resultado = reservaController.obtenerReserva(1L);
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertNotNull(resultado.getBody());
        assertEquals(1L, resultado.getBody().getIdUsuario());
        verify(reservaServicePort, times(1)).findById(1L);
    }
    /**
     * Prueba para el método save
     */
    @Test
    void saveDebeGuardarReserva() {
        ReservaRequest request = new ReservaRequest();
        request.setIdUsuario(1L);
        Reserva reservaGuardada = new Reserva(1L, 1L, 1L, "2024-06-08", "CONFIRMADA");
        when(reservaServicePort.save(any(Reserva.class))).thenReturn(reservaGuardada);
        ResponseEntity<ReservaResponse> resultado = reservaController.save(request);
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertNotNull(resultado.getBody());
        assertEquals(1L, resultado.getBody().getIdUsuario());
        verify(reservaServicePort, times(1)).save(any(Reserva.class));
    }
    /**
     * Prueba para el método delete
     */
    @Test
    void deleteDebeEliminarReservaSiExiste() {
        when(reservaServicePort.delete(1L)).thenReturn(true);
        ResponseEntity<Void> resultado = reservaController.delete(1L);
        assertEquals(HttpStatus.NO_CONTENT, resultado.getStatusCode());
        verify(reservaServicePort, times(1)).delete(1L);
    }
}