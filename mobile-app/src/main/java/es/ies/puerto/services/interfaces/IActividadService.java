package es.ies.puerto.services.interfaces;
import java.util.List;
import es.ies.puerto.modelos.Actividades;
import es.ies.puerto.modelos.Reservas;
/**
 * @author AlejandroDonGar
 * @version 1.0.0
 * 
 * Interfaz IActividadService que realiza la logica de la actividad
 */
public interface IActividadService {
    /**
     * Lista todas las actividades
     * @return Devuelve una lista de todos los usuarios
     */
    public List<Actividades> findAll();
    /**
     * Busca una actividad por su id
     * @param id identificador del usuario a buscar
     * @return Devuelve un usuario
     */
    public Actividades findByID(Integer id);
    /**
     * Guarda una actividad en la base de datos
     * @param actividad actividad a guardar
     * @return Devuelve true o false
     */
    public boolean save(Actividades actividad);
    /**
     * Actualiza una actividad
     * @param actividad actividad a actualizar
     * @return Devuelve true o false
     */
    public boolean update(Actividades actividad);
    /**
     * Borra una actividad
     * @param actividad actividad a borrar
     * @return Devuelve true o false
     */
    public boolean delete(Actividades actividad);
    /**
     * Crea una nueva actividad
     * @param idCliente cliente que realiza la reserva
     * @param idActividad actividad de la reserva
     * @return Devuelve true o false
     */
    public Reservas reservarPlaza(Integer idCliente, Integer idActividad);
    /**
     * Cancela una reserva
     * @param idActividad identificador de la reserva 
     * @return Devuelve true o false
     */
    public boolean cancelarPlaza(Integer idActividad);
    /**
     * Lista las actividades con su aforo completo
     * @return Devuelve una lista de actividades completas
     */
    public List<Actividades> findCompletas();
    /**
     * Calcula los ingresos totales por actividad
     * @param plazasOcupadas plazas totales ocupadas
     * @param precio precio por entrada
     * @return Devuelve el total de ingresos de la actividad
     */
    public Double calcularIngresosTotales(Integer plazasOcupadas, Double precio);
}
