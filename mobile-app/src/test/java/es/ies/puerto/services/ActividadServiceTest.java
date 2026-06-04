package es.ies.puerto.services;
import es.ies.puerto.modelos.Actividades;
import es.ies.puerto.modelos.Reservas;
import es.ies.puerto.repositories.interfaces.IActividadRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

/**
 * @author AlejandroDonGar
 * @version 1.0.0
 * 
 * Clase ActividadServiceTest
 */
public class ActividadServiceTest {

    private ActividadService actividadService;

    @Mock
    private IActividadRepository actividadRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        actividadService = new ActividadService(actividadRepository);
    }

    /**
     * Test constructor ActividadService()
     * Comprueba que el constructor sin parámetros inicializa el repositorio
     */
    @Test
    public void constructorDefaultTest() {
        ActividadService service = new ActividadService();
        Assertions.assertNotNull(service);
    }

    /**
     * Test findAll()
     * Comprueba que el método findAll llama al repositorio y devuelve la lista
     */
    @Test
    public void findAllTestOk() {
        List<Actividades> lista = new ArrayList<>();
        lista.add(new Actividades(1, "Yoga", "DEPORTIVA", 60, 20.0, 20, 10));
        when(actividadRepository.findAll()).thenReturn(lista);

        List<Actividades> resultado = actividadService.findAll();
        Assertions.assertEquals(1, resultado.size());
        verify(actividadRepository, times(1)).findAll();
    }

    /**
     * Test findByID(Integer) con ID inválido
     * Comprueba que devuelve null si el ID es nulo o negativo
     */
    @Test
    public void findByIDTestInvalid() {
        Assertions.assertNull(actividadService.findByID(null));
        Assertions.assertNull(actividadService.findByID(-1));
    }

    /**
     * Test findByID(Integer)
     * Comprueba que el método findByID llama al repositorio con el ID correcto
     */
    @Test
    public void findByIDTestOk() {
        Actividades actividad = new Actividades(1, "Yoga", "DEPORTIVA", 60, 20.0, 20, 10);
        when(actividadRepository.findByID(1)).thenReturn(actividad);

        Actividades resultado = actividadService.findByID(1);
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals("Yoga", resultado.getNombre());
        verify(actividadRepository, times(1)).findByID(1);
    }

    /**
     * Test save(Actividades) con actividad inválida
     * Comprueba que devuelve false si la actividad es nula
     */
    @Test
    public void saveTestInvalid() {
        Assertions.assertFalse(actividadService.save(null));
    }

    /**
     * Test save(Actividades)
     * Comprueba que el método save llama al repositorio si la actividad es válida
     */
    @Test
    public void saveTestOk() {
        Actividades actividad = new Actividades(1, "Yoga", "DEPORTIVA", 60, 20.0, 20, 10);
        when(actividadRepository.save(actividad)).thenReturn(true);

        boolean resultado = actividadService.save(actividad);
        Assertions.assertTrue(resultado);
        verify(actividadRepository, times(1)).save(actividad);
    }

    /**
     * Test update(Actividades) con actividad inválida
     * Comprueba que devuelve false si la actividad es nula
     */
    @Test
    public void updateTestInvalid() {
        Assertions.assertFalse(actividadService.update(null));
    }

    /**
     * Test update(Actividades)
     * Comprueba que el método update llama al repositorio si la actividad es válida
     */
    @Test
    public void updateTestOk() {
        Actividades actividad = new Actividades(1, "Yoga", "DEPORTIVA", 60, 20.0, 20, 10);
        when(actividadRepository.update(actividad)).thenReturn(true);

        boolean resultado = actividadService.update(actividad);
        Assertions.assertTrue(resultado);
        verify(actividadRepository, times(1)).update(actividad);
    }

    /**
     * Test delete(Actividades) con actividad inválida
     * Comprueba que devuelve false si la actividad es nula
     */
    @Test
    public void deleteTestInvalid() {
        Assertions.assertFalse(actividadService.delete(null));
    }

    /**
     * Test delete(Actividades)
     * Comprueba que el método delete llama al repositorio si la actividad es válida
     */
    @Test
    public void deleteTestOk() {
        Actividades actividad = new Actividades(1, "Yoga", "DEPORTIVA", 60, 20.0, 20, 10);
        when(actividadRepository.delete(actividad)).thenReturn(true);

        boolean resultado = actividadService.delete(actividad);
        Assertions.assertTrue(resultado);
        verify(actividadRepository, times(1)).delete(actividad);
    }

    /**
     * Test reservarPlaza(int, int) con parámetros inválidos
     * Comprueba que devuelve null si algún ID es inválido
     */
    @Test
    public void reservarPlazaTestInvalid() {
        Assertions.assertNull(actividadService.reservarPlaza(null, 1));
        Assertions.assertNull(actividadService.reservarPlaza(1, null));
    }

    /**
     * Test reservarPlaza(int, int)
     * Comprueba que el método reservarPlaza llama al repositorio y devuelve una reserva
     */
    @Test
    public void reservarPlazaTest() {
        when(actividadRepository.reservarPlaza(1, 1)).thenReturn(new Reservas());
        Reservas resultado = actividadService.reservarPlaza(1, 1);
        Assertions.assertNotNull(resultado);
    }

    /**
     * Test cancelarPlaza(int) con ID inválido
     * Comprueba que devuelve false si el ID es nulo
     */
    @Test
    public void cancelarPlazaTestInvalid() {
        Assertions.assertFalse(actividadService.cancelarPlaza(null));
    }

    /**
     * Test cancelarPlaza(int)
     * Comprueba que el método cancelarPlaza llama al repositorio y devuelve true
     */
    @Test
    public void cancelarPlazaTest() {
        when(actividadRepository.cancelarPlaza(1)).thenReturn(true);
        boolean resultado = actividadService.cancelarPlaza(1);
        Assertions.assertTrue(resultado);
    }

    /**
     * Test findCompletas()
     * Comprueba que el método findCompletas llama al repositorio y devuelve una lista
     */
    @Test
    public void findCompletasTest() {
        when(actividadRepository.findCompletas()).thenReturn(new ArrayList<>());
        List<Actividades> resultado = actividadService.findCompletas();
        Assertions.assertNotNull(resultado);
    }

    /**
     * Test calcularIngresosTotales(int, double) con parámetros inválidos
     * Comprueba que devuelve 0.0 si los parámetros son inválidos
     */
    @Test
    public void calcularIngresosTotalesTestInvalid() {
        Assertions.assertEquals(0.0, actividadService.calcularIngresosTotales(-1, 20.0));
        Assertions.assertEquals(0.0, actividadService.calcularIngresosTotales(10, null));
    }

    /**
     * Test calcularIngresosTotales(int, double)
     * Comprueba que el método calcularIngresosTotales llama al repositorio y devuelve el resultado
     */
    @Test
    public void calcularIngresosTotalesTest() {
        when(actividadRepository.calcularIngresosTotales(10, 20.0)).thenReturn(200.0);
        Double resultado = actividadService.calcularIngresosTotales(10, 20.0);
        Assertions.assertEquals(200.0, resultado);
    }
}
