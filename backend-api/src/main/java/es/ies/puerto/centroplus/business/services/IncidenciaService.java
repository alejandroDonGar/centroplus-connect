package es.ies.puerto.centroplus.business.services;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import es.ies.puerto.centroplus.adapters.out.persistencia.incidencia.IncidenciaPersistenceAdapter;
import es.ies.puerto.centroplus.business.ports.IncidenciaServicePort;
import es.ies.puerto.centroplus.domain.model.Incidencia;
/**
 * Servicio de negocio para la entidad Incidencia
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
@Service
public class IncidenciaService implements IncidenciaServicePort {
    private final IncidenciaPersistenceAdapter incidenciaPersistenceAdapter;
    /**
     * Constructor de la clase IncidenciaService
     * @param incidenciaPersistenceAdapter El adaptador de persistencia Incidencia
     */
    public IncidenciaService(IncidenciaPersistenceAdapter incidenciaPersistenceAdapter) {
        this.incidenciaPersistenceAdapter = incidenciaPersistenceAdapter;
    }
    @Override
    public List<Incidencia> findAll() {
        return incidenciaPersistenceAdapter.findAll();
    }
    @Override
    public Optional<Incidencia> findById(Long id) {
        return incidenciaPersistenceAdapter.findById(id);
    }
    @Override
    public Incidencia save(Incidencia incidencia) {
        return incidenciaPersistenceAdapter.save(incidencia);
    }
    @Override
    public Optional<Incidencia> update(Long id, Incidencia incidencia) {
        Optional<Incidencia> incidenciaEncontrada = incidenciaPersistenceAdapter.findById(id);
        if (incidenciaEncontrada.isEmpty()) {
            return Optional.empty();
        }
        incidencia.setId(id); // Se setea para actualizar la incidencia en la base de datos
        Incidencia incidenciaActualizada = incidenciaPersistenceAdapter.save(incidencia);
        return Optional.of(incidenciaActualizada);
    }
    @Override
    public boolean delete(Long id) {
        if (!incidenciaPersistenceAdapter.existsById(id)) {
            return false;
        }
        incidenciaPersistenceAdapter.deleteById(id);
        return true;
    }
}