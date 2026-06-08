package es.ies.puerto.centroplus.business.services;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.ies.puerto.centroplus.adapters.out.persistencia.incidencia.IncidenciaPersistenceAdapter;
import es.ies.puerto.centroplus.domain.model.Incidencia;
/**
 * Clase de prueba para el servicio de incidencias
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
class IncidenciaServiceTest {
    private IncidenciaPersistenceAdapter incidenciaPersistenceAdapter;
    private IncidenciaService incidenciaService;
    /**
     * Configuración inicial para cada prueba
     */
    @BeforeEach
    void setUp() {
        incidenciaPersistenceAdapter = mock(IncidenciaPersistenceAdapter.class);
        incidenciaService = new IncidenciaService(incidenciaPersistenceAdapter);
    }
    /**
     * Prueba para el método findAll del servicio de incidencias
     */
    @Test
    void findAllDebeDevolverIncidencias() {
        Incidencia incidencia = new Incidencia(1L, 1L, "Gotera", "Hay una gotera en el techo", "2024-06-08", "ABIERTA");
        when(incidenciaPersistenceAdapter.findAll()).thenReturn(List.of(incidencia));
        List<Incidencia> resultado = incidenciaService.findAll();
        assertEquals(1, resultado.size());
        assertEquals("Gotera", resultado.get(0).getAsunto());
        verify(incidenciaPersistenceAdapter, times(1)).findAll();
    }
    /**
     * Prueba para el método findById del servicio de incidencias
     */
    @Test
    void findByIdDebeDevolverIncidenciaSiExiste() {
        Incidencia incidencia = new Incidencia(1L, 1L, "Gotera", "Hay una gotera en el techo", "2024-06-08", "ABIERTA");
        when(incidenciaPersistenceAdapter.findById(1L)).thenReturn(Optional.of(incidencia));
        Optional<Incidencia> resultado = incidenciaService.findById(1L);
        assertTrue(resultado.isPresent());
        assertEquals("Gotera", resultado.get().getAsunto());
        verify(incidenciaPersistenceAdapter, times(1)).findById(1L);
    }
    /**
     * Prueba para el método save del servicio de incidencias
     */
    @Test
    void saveDebeGuardarIncidencia() {
        Incidencia incidencia = new Incidencia(1L, 1L, "Gotera", "Hay una gotera en el techo", "2024-06-08", "ABIERTA");
        when(incidenciaPersistenceAdapter.save(incidencia)).thenReturn(incidencia);
        Incidencia resultado = incidenciaService.save(incidencia);
        assertNotNull(resultado);
        assertEquals("Gotera", resultado.getAsunto());
        verify(incidenciaPersistenceAdapter, times(1)).save(incidencia);
    }
    /**
     * Prueba para el método update del servicio de incidencias
     */
    @Test
    void updateDebeActualizarIncidenciaSiExiste() {
        Incidencia incidencia = new Incidencia(1L, 1L, "Gotera", "Hay una gotera en el techo", "2024-06-08", "ABIERTA");
        when(incidenciaPersistenceAdapter.findById(1L)).thenReturn(Optional.of(incidencia));
        when(incidenciaPersistenceAdapter.save(incidencia)).thenReturn(incidencia);
        Optional<Incidencia> resultado = incidenciaService.update(1L, incidencia);
        assertTrue(resultado.isPresent());
        assertEquals("Gotera", resultado.get().getAsunto());
        verify(incidenciaPersistenceAdapter, times(1)).findById(1L);
        verify(incidenciaPersistenceAdapter, times(1)).save(incidencia);
    }
    /**
     * Prueba para el método delete del servicio de incidencias
     */
    @Test
    void deleteDebeDevolverTrueSiExiste() {
        when(incidenciaPersistenceAdapter.existsById(1L)).thenReturn(true);
        boolean resultado = incidenciaService.delete(1L);
        assertTrue(resultado);
        verify(incidenciaPersistenceAdapter, times(1)).existsById(1L);
        verify(incidenciaPersistenceAdapter, times(1)).deleteById(1L);
    }
    /**
     * Prueba para el método delete del servicio de incidencias
     */
    @Test
    void deleteDebeDevolverFalseSiNoExiste() {
        when(incidenciaPersistenceAdapter.existsById(99L)).thenReturn(false);
        boolean resultado = incidenciaService.delete(99L);
        assertFalse(resultado);
        verify(incidenciaPersistenceAdapter, times(1)).existsById(99L);
        verify(incidenciaPersistenceAdapter, never()).deleteById(99L);
    }
}