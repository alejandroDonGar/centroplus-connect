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
     * Test constructor ReservaService()
     * Comprueba que el constructor sin parámetros inicializa los repositorios
     */
    @Test
    public void constructorDefaultTest() {
        ReservaService service = new ReservaService();
        Assertions.assertNotNull(service);
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
     * Test findByID(Integer) con ID inválido
     * Comprueba que devuelve null si el ID es nulo o negativo
     */
    @Test
    public void findByIDTestInvalid() {
        Assertions.assertNull(reservaService.findByID(null));
        Assertions.assertNull(reservaService.findByID(-1));
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
     * Test save(Reservas) cubriendo todas las ramas de validación
     */
    @Test
    public void saveTestAllBranches() {
        // Caso reserva nula
        Assertions.assertFalse(reservaService.save(null));

        Reservas reserva = new Reservas(1, 1, 1, new Date(), "ACTIVA");

        // Caso usuario no existe
        when(usuarioRepository.findByID(1)).thenReturn(null);
        Assertions.assertFalse(reservaService.save(reserva));

        // Caso actividad no existe
        when(usuarioRepository.findByID(1)).thenReturn(new es.ies.puerto.modelos.Usuarios());
        when(actividadRepository.findByID(1)).thenReturn(null);
        Assertions.assertFalse(reservaService.save(reserva));

        // Caso sin plazas disponibles
        when(actividadRepository.findByID(1)).thenReturn(new es.ies.puerto.modelos.Actividades());
        when(reservaRepository.numeroDePlazasDisponibles(1)).thenReturn(0);
        Assertions.assertFalse(reservaService.save(reserva));

        // Caso reserva ya existente activa
        when(reservaRepository.numeroDePlazasDisponibles(1)).thenReturn(5);
        List<Reservas> todas = new ArrayList<>();
        todas.add(new Reservas(2, 1, 1, new Date(), "ACTIVA"));
        when(reservaRepository.findAll()).thenReturn(todas);
        Assertions.assertFalse(reservaService.save(reserva));

        // Caso error al reservar plaza (actividadRepository.reservarPlaza retorna null)
        when(reservaRepository.findAll()).thenReturn(new ArrayList<>());
        when(actividadRepository.reservarPlaza(1, 1)).thenReturn(null);
        Assertions.assertFalse(reservaService.save(reserva));

        // Caso éxito
        when(actividadRepository.reservarPlaza(1, 1)).thenReturn(new Reservas());
        when(reservaRepository.save(reserva)).thenReturn(true);
        Assertions.assertTrue(reservaService.save(reserva));
    }

    /**
     * Test update(Reservas) con reserva inválida
     * Comprueba que devuelve false si la reserva es nula
     */
    @Test
    public void updateTestInvalid() {
        Assertions.assertFalse(reservaService.update(null));
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
     * Test delete(Integer) con ID inválido o inexistente
     */
    @Test
    public void deleteTestInvalid() {
        Assertions.assertFalse(reservaService.delete(null));
        when(reservaRepository.findByID(1)).thenReturn(null);
        Assertions.assertFalse(reservaService.delete(1));
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

    /**
     * Test numeroDePlazasDisponibles(Integer) con ID inválido
     */
    @Test
    public void numeroDePlazasDisponiblesTestInvalid() {
        Assertions.assertEquals(-1, reservaService.numeroDePlazasDisponibles(null));
    }

    /**
     * Test numeroDePlazasDisponibles(Integer)
     * Comprueba que el método numeroDePlazasDisponibles llama al repositorio y devuelve el valor
     */
    @Test
    public void numeroDePlazasDisponiblesTest() {
        when(reservaRepository.numeroDePlazasDisponibles(1)).thenReturn(10);
        Integer resultado = reservaService.numeroDePlazasDisponibles(1);
        Assertions.assertEquals(10, resultado);
    }
}
