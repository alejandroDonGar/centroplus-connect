package es.ies.puerto.centroplus.business.ports;
import es.ies.puerto.centroplus.domain.model.Reserva;
import java.util.List;
import java.util.Optional;
/**
 * Interfaz para implementar el servicio de reservas utilizando JPA
 * 
 * @author AlejandroDonate
 * @version 1.0
 */
public interface ReservaServicePort {
    /**
     * Metodo para obtener todas las reservas en la base de datos
     * @return Una lista de todas las reservas en la base de datos
     */
    List<Reserva> findAll();
    /**
     * Metodo para obtener una reserva por su ID en la base de datos
     * @param id El ID de la reserva
     * @return El objeto Reserva correspondiente al ID
     */
    Optional<Reserva> findById(Long id);
    /**
     * Metodo para guardar una reserva en la base de datos
     * @param reserva El objeto Reserva a guardar
     * @return El objeto Reserva guardado
     */
    Reserva save(Reserva reserva);
    /**
     * Metodo para actualizar una reserva en la base de datos
     * @param id El ID de la reserva a actualizar
     * @param reserva El objeto Reserva con los nuevos valores
     * @return El objeto Reserva actualizado
     */
    Optional<Reserva> update(Long id, Reserva reserva);
    /**
     * Metodo para eliminar una reserva en la base de datos
     * @param id El ID de la reserva a eliminar
     * @return True si la operación es exitosa, False en caso contrario
     */
    boolean delete(Long id);
}