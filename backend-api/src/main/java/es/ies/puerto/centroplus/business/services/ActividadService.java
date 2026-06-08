package es.ies.puerto.centroplus.business.services;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import es.ies.puerto.centroplus.adapters.out.persistencia.actividad.ActividadPersistenceAdapter;
import es.ies.puerto.centroplus.business.ports.ActividadServicePort;
import es.ies.puerto.centroplus.domain.model.Actividad;
/**
 * Clase para implementar el servicio de actividades utilizando JPA
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
@Service // Indica que esta clase es un servicio de Spring
public class ActividadService implements ActividadServicePort {
    private final ActividadPersistenceAdapter actividadPersistenceAdapter;
    /**
     * Metodo constructor para inicializar el servicio de actividades utilizando JPA
     * @param actividadPersistenceAdapter El adaptador de persistencia de actividades utilizando JPA
     */
    public ActividadService(ActividadPersistenceAdapter actividadPersistenceAdapter) {
        this.actividadPersistenceAdapter = actividadPersistenceAdapter;
    }
    @Override
    public List<Actividad> findAll() {
        return actividadPersistenceAdapter.findAll();
    }
    @Override
    public Optional<Actividad> findById(Long id) {
        return actividadPersistenceAdapter.findById(id);
    }
    @Override
    public Actividad save(Actividad actividad) {
        return actividadPersistenceAdapter.save(actividad);
    }
    @Override
    public Optional<Actividad> update(Long id, Actividad actividad) {
        return actividadPersistenceAdapter.findById(id).map(actividadExistente -> { // el '.map' utiliza una función lambda para transformar el objeto existente en uno nuevo
            actividad.setId(id); // Se setea para actualizar la actividad en la base de datos
            return actividadPersistenceAdapter.save(actividad);
        });
    }
    @Override
    public boolean delete(Long id) {
        if(!actividadPersistenceAdapter.existsById(id)) {
            return false;
        }
        actividadPersistenceAdapter.deleteById(id);
        return true;
    }
}