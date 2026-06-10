package es.ies.puerto.repositories;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import es.ies.puerto.modelos.Reservas;
import es.ies.puerto.repositories.interfaces.IReservaRepository;
import java.text.SimpleDateFormat;
/**
 * @author AlejandroDonGar
 * @version 1.0.0
 * 
 * Clase ReservaRepository donde implementamos los metodos de IReservaRepository
 */
public class ReservaRepository implements IReservaRepository{

    @Override
    public List<Reservas> findAll() {
        List<Reservas> reservasEncontradas = new ArrayList<>();
        try (Connection connection = Sqlite3Manager.getConnection();
            PreparedStatement sentencia = connection.prepareStatement("SELECT * FROM reservas")) {
                ResultSet resultado = sentencia.executeQuery();
                while (resultado.next()) {
                    Integer id = resultado.getInt("id");
                    Integer idUsuario = resultado.getInt("id_usuario");
                    Integer idActividad = resultado.getInt("id_actividad");
                    Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(resultado.getString("fecha"));
                    String estado = resultado.getString("estado");
                    Reservas reserva = new Reservas(id, idUsuario, idActividad, fecha, estado);
                    reservasEncontradas.add(reserva);
                }
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return reservasEncontradas;
    }
    @Override
    public Reservas findByID(Integer id) {
        Reservas reservaEncontrada = null;
        try (Connection connection = Sqlite3Manager.getConnection();
            PreparedStatement sentencia = connection.prepareStatement("SELECT * FROM reservas WHERE id=?")) {
                sentencia.setInt(1, id);
                ResultSet resultado = sentencia.executeQuery();
                while (resultado.next()) {
                    Integer idUsuario = resultado.getInt("id_usuario");
                    Integer idActividad = resultado.getInt("id_actividad");
                    Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(resultado.getString("fecha"));
                    String estado = resultado.getString("estado");
                    reservaEncontrada = new Reservas(id, idUsuario, idActividad, fecha, estado);
                    return reservaEncontrada;
                }
        } catch (Exception e) {
            return null;
        }
        return reservaEncontrada;
    }
    @Override
    public boolean save(Reservas reserva) {
        try (Connection connection = Sqlite3Manager.getConnection();
            PreparedStatement sentencia = connection.prepareStatement("INSERT INTO reservas VALUES (?,?,?,?,?)")) {
                sentencia.setInt(1, reserva.getId());
                sentencia.setInt(2, reserva.getIdUsuario());
                sentencia.setInt(3, reserva.getIdActividad());
                sentencia.setString(4, new SimpleDateFormat("yyyy-MM-dd").format(reserva.getFecha()));
                sentencia.setString(5, reserva.getEstado());
                return sentencia.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public boolean update(Reservas reserva) {
        try (Connection connection = Sqlite3Manager.getConnection();
            PreparedStatement sentencia = connection.prepareStatement("UPDATE reservas SET id_usuario=?, id_actividad=?, fecha=?, estado=? WHERE id=?")) {
                sentencia.setInt(1, reserva.getIdUsuario());
                sentencia.setInt(2, reserva.getIdActividad());
                sentencia.setString(3, new SimpleDateFormat("yyyy-MM-dd").format(reserva.getFecha()));
                sentencia.setString(4, reserva.getEstado());
                sentencia.setInt(5, reserva.getId());
                return sentencia.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public boolean delete(Integer id) {
        try (Connection connection = Sqlite3Manager.getConnection();
            PreparedStatement sentencia = connection.prepareStatement("DELETE FROM reservas WHERE id=?")) {
                sentencia.setInt(1, id);
                return sentencia.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public Integer numeroDePlazasDisponibles(Integer idActividad) {
        Integer plazasDisponibles = 0;
        try (Connection connection = Sqlite3Manager.getConnection();
            PreparedStatement sentencia = connection.prepareStatement("SELECT plazas_maximas, plazas_ocupadas FROM actividades WHERE id=?")) {
                sentencia.setInt(1, idActividad);
                ResultSet resultado = sentencia.executeQuery();
                if (resultado.next()) {
                    Integer plazasMaximas = resultado.getInt("plazas_maximas");
                    Integer plazasOcupadas = resultado.getInt("plazas_ocupadas");
                    plazasDisponibles = plazasMaximas - plazasOcupadas;
                }
        } catch (Exception e) {
            return -1;
        }
        return plazasDisponibles;
    }
}
