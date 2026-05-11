package es.ies.puerto.repositories.interfaces;
import java.util.List;
import es.ies.puerto.modelos.Actividades;
import es.ies.puerto.modelos.Reservas;
/**
 * 
 */
public interface IActividadRepository {
    /**
     * Lista todos los usuarios
     * @return Devuelve una lista de todos los usuarios
     */
    public List<Actividades> findAll();
    /**
     * Busca un usuario por su id
     * @param id identificador del usuario a buscar
     * @return Devuelve un usuario
     */
    public Actividades findByID(Integer id);
    /**
     * Guarda un usuario en la base de datos
     * @param usuario usuario a guardar
     * @return Devuelve true o false
     */
    public boolean save(Actividades actividad);
    /**
     * Actualiza un usuario
     * @param usuario usuario a actualizar
     * @return Devuelve true o false
     */
    public boolean update(Actividades actividad);
    /**
     * Borra un usuario
     * @param usuario usuario a borrar
     * @return Devuelve true o false
     */
    public boolean delete(Actividades actividad);
    /**
     * Crea una nueva reserva
     * @param idCliente cliente que realiza la reserva
     * @param idActividad actividad de la reserva
     * @return Devuelve true o false
     */
    public Reservas reservarPlaza(Integer idCliente, Integer idActividad);
    /**
     * Cancela una reserva
     * @param id identificador de la reserva 
     * @return Devuelve true o false
     */
    public boolean cancelarPlaza(Integer id);
    /**
     * Lista las actividades con su aforo completo
     * @param plazasOcupadas plazas ocupadas en la acividad
     * @return Devuelve una lista de actividades completas
     */
    public List<Actividades> findCompletas(Integer plazasOcupadas);
    /**
     * Calcula los ingresos totales por actividad
     * @param plazasOcupadas plazas totales ocupadas
     * @param precio precio por entrada
     * @return Devuelve el total de ingresos de la actividad
     */
    public Double calcularIngresosTotales(Integer plazasOcupadas, Double precio);
}
