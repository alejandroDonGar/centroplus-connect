package es.ies.puerto.centroplus.adapters.out.persistencia.reserva;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import es.ies.puerto.centroplus.adapters.mapper.ReservaMapper;
import es.ies.puerto.centroplus.domain.model.Reserva;
/**
 * Adapter para la persistencia de Reservas
 * 
 * @author Ismael Perez
 * @version 1.0
 */
@Component
public class ReservaPersistenceAdapter {
    private final ReservaJpaRepository reservaJpaRepository;
    private final ReservaMapper reservaMapper;
    /**
     * Constructor de la clase ReservaPersistenceAdapter
     * 
     * @param reservaJpaRepository El repositorio JPA para Reservas
     * @param reservaMapper El mapper para Reservas
     */
    public ReservaPersistenceAdapter(ReservaJpaRepository reservaJpaRepository, ReservaMapper reservaMapper) {
        this.reservaJpaRepository = reservaJpaRepository;
        this.reservaMapper = reservaMapper;
    }
    /**
     * Devuelve todas las Reservas existentes en la base de datos
     * @return Una lista de Reservas
     */
    public List<Reserva> findAll() {
        return reservaJpaRepository.findAll()
                .stream() // Convierte la lista a un flujo de objetos
                .map(reserva -> reservaMapper.toDomain(reserva)) // Mapea cada objeto ReservaJpaEntity a un objeto Reserva
                .toList();
    }
    /**
     * Devuelve una Reserva existente en la base de datos por su ID
     * @param id El ID de la Reserva a buscar
     * @return El objeto Reserva correspondiente
     */
    public Optional<Reserva> findById(Long id) {
        return reservaJpaRepository.findById(id)
                .map(reserva -> reservaMapper.toDomain(reserva)); // Mapea el objeto ReservaJpaEntity a un objeto Reserva
    }
    /**
     * Guarda una Reserva en la base de datos
     * @param reserva El objeto Reserva a guardar
     * @return El objeto Reserva guardado
     */
    public Reserva save(Reserva reserva) {
        ReservaJpaEntity entity = reservaMapper.toEntity(reserva);
        ReservaJpaEntity savedEntity = reservaJpaRepository.save(entity);
        return reservaMapper.toDomain(savedEntity);
    }
    /**
     * Comprueba si existe una Reserva con el ID dado en la base de datos
     * @param id El ID de la Reserva a comprobar
     * @return true si existe, false si no
     */
    public boolean existsById(Long id) {
        return reservaJpaRepository.existsById(id);
    }
    /**
     * Elimina una Reserva con el ID dado de la base de datos
     * @param id El ID de la Reserva a eliminar
     */
    public void deleteById(Long id) {
        reservaJpaRepository.deleteById(id);
    }
}