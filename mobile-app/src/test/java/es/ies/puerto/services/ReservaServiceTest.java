package es.ies.puerto.services;
import es.ies.puerto.modelos.Reservas;
import es.ies.puerto.repositories.interfaces.IActividadRepository;
import es.ies.puerto.repositories.interfaces.IReservaRepository;
import es.ies.puerto.repositories.interfaces.IUsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.mockito.Mockito.*;

/**
 * @author AlejandroDonGar
 * @version 1.0.0
 * 
 * Clase ReservaServiceTest
 */
public class ReservaServiceTest {

    private ReservaService reservaService;

    @Mock
    private IReservaRepository reservaRepository;
    @Mock
    private IUsuarioRepository usuarioRepository;
    @Mock
    private IActividadRepository actividadRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        reservaService = new ReservaService(reservaRepository, usuarioRepository, actividadRepository);
    }

    /**
     * Test findAll()
     * Comprueba que el método findAll llama al repositorio y devuelve la lista
     */
    @Test
    public void findAllTestOk() {
        List<Reservas> lista = new ArrayList<>();
        lista.add(new Reservas(1, 1, 1, new Date(), "ACTIVA"));
        when(reservaRepository.findAll()).thenReturn(lista);

        List<Reservas> resultado = reservaService.findAll();
        Assertions.assertEquals(1, resultado.size());
        verify(reservaRepository, times(1)).findAll();
    }

    /**
     * Test findByID(Integer)
     * Comprueba que el método findByID llama al repositorio con el ID correcto
     */
    @Test
    public void findByIDTestOk() {
        Reservas reserva = new Reservas(1, 1, 1, new Date(), "ACTIVA");
        when(reservaRepository.findByID(1)).thenReturn(reserva);

        Reservas resultado = reservaService.findByID(1);
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(1, resultado.getId());
        verify(reservaRepository, times(1)).findByID(1);
    }

    /**
     * Test update(Reservas)
     * Comprueba que el método update llama al repositorio si la reserva es válida
     */
    @Test
    public void updateTestOk() {
        Reservas reserva = new Reservas(1, 1, 1, new Date(), "ACTIVA");
        when(reservaRepository.update(reserva)).thenReturn(true);

        boolean resultado = reservaService.update(reserva);
        Assertions.assertTrue(resultado);
        verify(reservaRepository, times(1)).update(reserva);
    }

    /**
     * Test delete(Integer)
     * Comprueba que el método delete llama al repositorio con el ID correcto
     */
    @Test
    public void deleteTestOk() {
        Reservas reserva = new Reservas(1, 1, 1, new Date(), "ACTIVA");
        when(reservaRepository.findByID(1)).thenReturn(reserva);
        when(reservaRepository.delete(1)).thenReturn(true);
        when(actividadRepository.cancelarPlaza(1)).thenReturn(true);

        boolean resultado = reservaService.delete(1);
        Assertions.assertTrue(resultado);
        verify(reservaRepository, times(1)).delete(1);
    }
}
