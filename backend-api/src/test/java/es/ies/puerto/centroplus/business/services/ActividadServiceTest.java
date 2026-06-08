package es.ies.puerto.centroplus.business.services;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.ies.puerto.centroplus.adapters.out.persistencia.actividad.ActividadPersistenceAdapter;
import es.ies.puerto.centroplus.domain.model.Actividad;
/**
 * Clase de prueba para el servicio de actividades
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
class ActividadServiceTest {
    private ActividadPersistenceAdapter actividadPersistenceAdapter;
    private ActividadService actividadService;
    /**
     * Configuración inicial para cada prueba
     */
    @BeforeEach
    void setUp() {
        actividadPersistenceAdapter = mock(ActividadPersistenceAdapter.class);
        actividadService = new ActividadService(actividadPersistenceAdapter);
    }
    /**
     * Prueba para el método findAll del servicio de actividades
     */
    @Test
    void findAllDebeDevolverActividades() {
        Actividad actividad = new Actividad(1L, "Yoga", "DEPORTIVA", 60, 25.50, 15, 8);
        when(actividadPersistenceAdapter.findAll()).thenReturn(List.of(actividad));
        List<Actividad> resultado = actividadService.findAll();
        assertEquals(1, resultado.size());
        assertEquals("Yoga", resultado.get(0).getNombre());
        verify(actividadPersistenceAdapter, times(1)).findAll();
    }
    /**
     * Prueba para el método findById del servicio de actividades
     */
    @Test
    void findByIdDebeDevolverActividadSiExiste() {
        Actividad actividad = new Actividad(1L, "Yoga", "DEPORTIVA", 60, 25.50, 15, 8);
        when(actividadPersistenceAdapter.findById(1L)).thenReturn(Optional.of(actividad));
        Optional<Actividad> resultado = actividadService.findById(1L);
        assertTrue(resultado.isPresent());
        assertEquals("Yoga", resultado.get().getNombre());
        verify(actividadPersistenceAdapter, times(1)).findById(1L);
    }
    /**
     * Prueba para el método delete del servicio de actividades
     */
    @Test
    void deleteDebeDevolverTrueSiExiste() {
        when(actividadPersistenceAdapter.existsById(1L)).thenReturn(true);
        boolean resultado = actividadService.delete(1L);
        assertTrue(resultado);
        verify(actividadPersistenceAdapter, times(1)).existsById(1L);
        verify(actividadPersistenceAdapter, times(1)).deleteById(1L);
    }
    /**
     * Prueba para el método delete del servicio de actividades
     */
    @Test
    void deleteDebeDevolverFalseSiNoExiste() {
        when(actividadPersistenceAdapter.existsById(99L)).thenReturn(false);
        boolean resultado = actividadService.delete(99L);
        assertFalse(resultado);
        verify(actividadPersistenceAdapter, times(1)).existsById(99L);
        verify(actividadPersistenceAdapter, never()).deleteById(99L);
    }
}