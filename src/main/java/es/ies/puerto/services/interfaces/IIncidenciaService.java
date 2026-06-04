package es.ies.puerto.services.interfaces;
import java.util.List;
import es.ies.puerto.modelos.Incidencias;
/**
 * @author AlejandroDonGar y JavierReyPer
 * @version 1.0.0
 * 
 * Interfaz IIncidenciaService que realiza la logica de la incidencia
 */
public interface IIncidenciaService {
    /**
     * Lista todos los usuarios
     * @return Devuelve una lista de todos los usuarios
     */
    public List<Incidencias> findAll();
    /**
     * Busca un usuario por su id
     * @param id identificador del usuario a buscar
     * @return Devuelve un usuario
     */
    public Incidencias findByID(Integer id);
    /**
     * Guarda un usuario en la base de datos
     * @param incidencia incidencia a guardar
     * @return Devuelve true o false
     */
    public boolean save(Incidencias incidencia);
    /**
     * Actualiza un usuario
     * @param incidencia incidencia a actualizar
     * @return Devuelve true o false
     */
    public boolean update(Incidencias incidencia);
    /**
     * Borra un usuario
     * @param id identificador de la incidencia a borrar
     * @return Devuelve true o false
     */
    public boolean delete(Integer id);
    /**
     * Busca incidencias por el id del usuario que las realiza
     * @param idUsuario identificador del usuario que realiza la incidencia
     * @return Devuelve una lista de las incidencias del usuario
     */
    public List<Incidencias> incidenciasPorIdUsuario(Integer idUsuario);
}
