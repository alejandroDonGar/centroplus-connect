package es.ies.puerto.services.interfaces;
import java.util.List;
import es.ies.puerto.modelos.Usuarios;
/**
 * @author AlejandroDonGar
 * @version 1.0.0
 * 
 * Interfaz IUsuarioService que realiza la logica del usuario
 */
public interface IUsuarioService {
    /**
     * Lista todos los usuarios
     * @return Devuelve una lista de todos los usuarios
     */
    public List<Usuarios> findAll();
    /**
     * Busca un usuario por su id
     * @param id identificador del usuario a buscar
     * @return Devuelve un usuario
     */
    public Usuarios findByID(Integer id);
    /**
     * Guarda un usuario en la base de datos
     * @param usuario usuario a guardar
     * @return Devuelve true o false
     */
    public boolean save(Usuarios usuario);
    /**
     * Actualiza un usuario
     * @param usuario usuario a actualizar
     * @return Devuelve true o false
     */
    public boolean update(Usuarios usuario);
    /**
     * Borra un usuario
     * @param usuario usuario a borrar
     * @return Devuelve true o false
     */
    public boolean delete(Usuarios usuario);
}
