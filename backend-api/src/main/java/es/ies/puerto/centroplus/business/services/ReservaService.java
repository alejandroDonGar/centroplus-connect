package es.ies.puerto.centroplus.business.services;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import es.ies.puerto.centroplus.adapters.out.persistencia.actividad.ActividadPersistenceAdapter;
import es.ies.puerto.centroplus.adapters.out.persistencia.reserva.ReservaPersistenceAdapter;
import es.ies.puerto.centroplus.business.ports.ReservaServicePort;
import es.ies.puerto.centroplus.domain.model.Actividad;
import es.ies.puerto.centroplus.domain.model.Reserva;
/**
 * Servicio de Reservas
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
@Service
public class ReservaService implements ReservaServicePort {
    private final ReservaPersistenceAdapter reservaPersistenceAdapter;
    private final ActividadPersistenceAdapter actividadPersistenceAdapter;
    /**
     * Constructor de la clase ReservaService
     * 
     * @param reservaPersistenceAdapter El adapter de persistencia de Reservas
     * @param actividadPersistenceAdapter El adapter de persistencia de Actividades
     */
    public ReservaService(ReservaPersistenceAdapter reservaPersistenceAdapter, ActividadPersistenceAdapter actividadPersistenceAdapter) {
        this.reservaPersistenceAdapter = reservaPersistenceAdapter;
        this.actividadPersistenceAdapter = actividadPersistenceAdapter;
    }
    /**
     * Devuelve todas las Reservas en la base de datos
     * 
     * @return Una lista de todas las Reservas en la base de datos
     */
    @Override
    public List<Reserva> findAll() {
        return reservaPersistenceAdapter.findAll();
    }
    /**
     * Devuelve una Reserva existente en la base de datos por su ID
     * 
     * @param id El ID de la Reserva a buscar
     * @return El objeto Reserva correspondiente
     */
    @Override
    public Optional<Reserva> findById(Long id) {
        return reservaPersistenceAdapter.findById(id);
    }
    /**
     * Guarda una Reserva en la base de datos
     * 
     * @param reserva El objeto Reserva a guardar
     * @return El objeto Reserva guardado
     */
    @Override
    public Reserva save(Reserva reserva) {
        Actividad actividad = actividadPersistenceAdapter.findById(reserva.getIdActividad())
                .orElseThrow(() -> new IllegalArgumentException("La actividad no existe"));
        if (actividad.getPlazasDisponibles() <= 0) {
            throw new IllegalStateException("No hay plazas disponibles para esta actividad");
        }
        actividad.setPlazasOcupadas(actividad.getPlazasOcupadas() + 1);
        actividadPersistenceAdapter.save(actividad);
        if (reserva.getEstado() == null || reserva.getEstado().isBlank()) {
            reserva.setEstado("ACTIVA");
        }
        return reservaPersistenceAdapter.save(reserva);
    }
    /**
     * Actualiza una Reserva existente en la base de datos
     * 
     * @param id El ID de la Reserva a actualizar
     * @param reserva El objeto Reserva actualizado
     * @return El objeto Reserva actualizado
     */
    @Override
    public Optional<Reserva> update(Long id, Reserva reserva) {
        Optional<Reserva> reservaEncontrada = reservaPersistenceAdapter.findById(id);
        if (reservaEncontrada.isEmpty()) {
            return Optional.empty();
        }
        reserva.setId(id);
        Reserva reservaActualizada = reservaPersistenceAdapter.save(reserva);
        return Optional.of(reservaActualizada);
    }
    /**
     * Elimina una Reserva existente en la base de datos por su ID
     * 
     * @param id El ID de la Reserva a eliminar
     * @return true si se pudo eliminar, false si no
     */
    @Override
    public boolean delete(Long id) {
        Optional<Reserva> reservaEncontrada = reservaPersistenceAdapter.findById(id);
        if (reservaEncontrada.isEmpty()) {
            return false;
        }
        Reserva reserva = reservaEncontrada.get();
        actividadPersistenceAdapter.findById(reserva.getIdActividad())
                .ifPresent(actividad -> {
                    if (actividad.getPlazasOcupadas() > 0) {
                        actividad.setPlazasOcupadas(actividad.getPlazasOcupadas() - 1);
                        actividadPersistenceAdapter.save(actividad);
                    }
                });
        reservaPersistenceAdapter.deleteById(id);
        return true;
    }
}