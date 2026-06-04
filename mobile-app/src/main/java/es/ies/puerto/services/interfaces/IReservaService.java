package es.ies.puerto.services.interfaces;
import java.util.List;
import es.ies.puerto.modelos.Reservas;
/**
 * @author AlejandroDonGar y JavierReyPer
 * @version 1.0.0
 * 
 * Interfaz IReservaService que realiza la logica de la reserva
 */
public interface IReservaService {
    /**
     * Lista todos los reserva
     * @return Devuelve una lista de todos los usuarios
     */
    public List<Reservas> findAll();
    /**
     * Busca un reserva por su id
     * @param id identificador del usuario a buscar
     * @return Devuelve un usuario
     */
    public Reservas findByID(Integer id);
    /**
     * Guarda un reserva en la base de datos
     * @param reserva reserva a guardar
     * @return Devuelve true o false
     */
    public boolean save(Reservas reserva);
    /**
     * Actualiza un reserva
     * @param reserva reserva a actualizar
     * @return Devuelve true o false
     */
    public boolean update(Reservas reserva);
    /**
     * Borra un reserva
     * @param id identificador de la reserva a borrar
     * @return Devuelve true o false
     */
    public boolean delete(Integer id);
    /**
     * Cuenta el numero de plazas dispobibles en una actividad
     * @param idActividad identificador de la actividad a analizar
     * @return Devuelve el numero de plazas disponibles en una actividad
     */
    public Integer numeroDePlazasDisponibles(Integer idActividad);
}
