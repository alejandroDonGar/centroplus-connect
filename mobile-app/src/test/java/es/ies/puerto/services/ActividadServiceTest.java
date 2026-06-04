package es.ies.puerto.services;
import es.ies.puerto.modelos.Actividades;
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
}
