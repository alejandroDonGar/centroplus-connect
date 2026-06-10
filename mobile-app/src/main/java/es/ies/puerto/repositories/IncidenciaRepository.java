package es.ies.puerto.repositories;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import es.ies.puerto.modelos.Incidencias;
import es.ies.puerto.repositories.interfaces.IIncidenciaRepository;
import java.text.SimpleDateFormat;
/**
 * @author AlejandroDonGar
 * @version 1.0.0
 * 
 * Clase IncidenciaRepository donde implementamos los metodos de IIncidenciaRepository
 */
public class IncidenciaRepository implements IIncidenciaRepository{

    @Override
    public List<Incidencias> findAll() {
        List<Incidencias> incidenciasEncontradas = new ArrayList<>();
        try (Connection connection = Sqlite3Manager.getConnection();
            PreparedStatement sentencia = connection.prepareStatement("SELECT * FROM incidencias")) {
                ResultSet resultado = sentencia.executeQuery();
                while (resultado.next()) {
                    Integer id = resultado.getInt("id");
                    Integer idUsuario = resultado.getInt("id_usuario");
                    String asunto = resultado.getString("asunto");
                    String descripcion = resultado.getString("descripcion");
                    Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(resultado.getString("fecha"));
                    String estado = resultado.getString("estado");
                    Incidencias incidencia = new Incidencias(id, idUsuario, asunto, descripcion, fecha, estado);
                    incidenciasEncontradas.add(incidencia);
                }
            
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return incidenciasEncontradas;
    }
    @Override
    public Incidencias findByID(Integer id) {
        Incidencias incidenciaEncontrada = null;
        try (Connection connection = Sqlite3Manager.getConnection();
            PreparedStatement sentencia = connection.prepareStatement("SELECT * FROM incidencias WHERE id=?")) {
                sentencia.setInt(1, id);
                ResultSet resultado = sentencia.executeQuery();
                while (resultado.next()) {
                    Integer idUsuario = resultado.getInt("id_usuario");
                    String asunto = resultado.getString("asunto");
                    String description = resultado.getString("descripcion");
                    Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(resultado.getString("fecha"));
                    String estado = resultado.getString("estado");
                    incidenciaEncontrada = new Incidencias(id, idUsuario, asunto, description, fecha, estado);
                    return incidenciaEncontrada;
                }
            
        } catch (Exception e) {
            return null;
        }
        return incidenciaEncontrada;
    }
    @Override
    public boolean save(Incidencias incidencia) {
        try (Connection connection = Sqlite3Manager.getConnection();
            PreparedStatement sentencia = connection.prepareStatement("INSERT INTO incidencias VALUES (?,?,?,?,?,?)")) {
                sentencia.setInt(1, incidencia.getId());
                sentencia.setInt(2, incidencia.getIdUsuario());
                sentencia.setString(3, incidencia.getAsunto());
                sentencia.setString(4, incidencia.getDescripcion());
                sentencia.setString(5, new SimpleDateFormat("yyyy-MM-dd").format(incidencia.getFecha()));
                sentencia.setString(6, incidencia.getEstado());
                return sentencia.executeUpdate() > 0;
            
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public boolean update(Incidencias incidencia) {
        try (Connection connection = Sqlite3Manager.getConnection();
            PreparedStatement sentencia = connection.prepareStatement("UPDATE incidencias SET id_usuario=?, asunto=?, descripcion=?, fecha=?, estado=? WHERE id=?")) {
                sentencia.setInt(1, incidencia.getIdUsuario());
                sentencia.setString(2, incidencia.getAsunto());
                sentencia.setString(3, incidencia.getDescripcion());
                sentencia.setString(4, new SimpleDateFormat("yyyy-MM-dd").format(incidencia.getFecha()));
                sentencia.setString(5, incidencia.getEstado());
                sentencia.setInt(6, incidencia.getId());
                return sentencia.executeUpdate() > 0;
            
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public boolean delete(Integer id) {
        try (Connection connection = Sqlite3Manager.getConnection();
            PreparedStatement sentencia = connection.prepareStatement("DELETE FROM incidencias WHERE id=?")) {
                sentencia.setInt(1, id);
                return sentencia.executeUpdate() > 0;
            
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public List<Incidencias> incidenciasPorIdUsuario(Integer idUsuario) {
        List<Incidencias> incidenciasPorUsuarioEncontradas = new ArrayList<>();
        try (Connection connection = Sqlite3Manager.getConnection();
            PreparedStatement sentencia = connection.prepareStatement("SELECT * FROM incidencias WHERE id_usuario=?")) {
                sentencia.setInt(1, idUsuario);
                ResultSet resultado = sentencia.executeQuery();
                while (resultado.next()) {
                    Integer id = resultado.getInt("id");
                    String asunto = resultado.getString("asunto");
                    String descripcion = resultado.getString("descripcion");
                    Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(resultado.getString("fecha"));
                    String estado = resultado.getString("estado");
                    Incidencias incidencia = new Incidencias(id, idUsuario, asunto, descripcion, fecha, estado);
                    incidenciasPorUsuarioEncontradas.add(incidencia);
                }
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return incidenciasPorUsuarioEncontradas;
    }
}
