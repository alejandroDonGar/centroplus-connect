package es.ies.puerto.repositories;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import es.ies.puerto.modelos.Actividades;
import es.ies.puerto.modelos.Reservas;
import es.ies.puerto.repositories.interfaces.IActividadRepository;
/**
 * @author AlejandroDonGar
 * @version 1.0.0
 * 
 * Clase ActividadRepository donde implementamos los metodos de IActividadRepository
 */
public class ActividadRepository implements IActividadRepository {
    public ActividadRepository() {
        super();
    }
    @Override
    public List<Actividades> findAll() {
        List<Actividades> actividadesEncontradas = new ArrayList<>();
        try (Connection connection = Sqlite3Manager.getConnection();
            PreparedStatement sentencia = connection.prepareStatement("SELECT * FROM actividades")) {
                ResultSet resultado = sentencia.executeQuery();
                while(resultado.next()) {
                    Integer id = resultado.getInt("id");
                    String nombre = resultado.getString("nombre");
                    String tipoActividad = resultado.getString("tipo_actividad");
                    Integer duracion = resultado.getInt("duracion");
                    Double precio = resultado.getDouble("precio");
                    Integer plazasMaximas = resultado.getInt("plazas_maximas");
                    Integer plazasOcupadas = resultado.getInt("plazas_ocupadas");
                    Actividades actividad = new Actividades(id, nombre, tipoActividad, duracion, precio, plazasMaximas, plazasOcupadas);
                    actividadesEncontradas.add(actividad);
                }
        } catch (Exception e) {
            System.err.println("No se han encontrado los resultados");
            e.printStackTrace();
            return new ArrayList<>();
        }
        return actividadesEncontradas;
    }
    @Override
    public Actividades findByID(Integer id) {
        Actividades actividadEncontrada = null;
        try (Connection connection = Sqlite3Manager.getConnection();
            PreparedStatement sentencia = connection.prepareStatement("SELECT * FROM actividades WHERE id=?")) {
                sentencia.setInt(1, id);
                ResultSet resultado = sentencia.executeQuery();
                while(resultado.next()) {
                    String nombre = resultado.getString("nombre");
                    String tipoActividad = resultado.getString("tipo_actividad");
                    Integer duracion = resultado.getInt("duracion");
                    Double precio = resultado.getDouble("precio");
                    Integer plazasMaximas = resultado.getInt("plazas_maximas");
                    Integer plazasOcupadas = resultado.getInt("plazas_ocupadas");
                    actividadEncontrada = new Actividades(id, nombre, tipoActividad, duracion, precio, plazasMaximas, plazasOcupadas);
                    return actividadEncontrada;
                }
        } catch (Exception e) {
            System.err.println("No se han encontrado los resultados");
            e.printStackTrace();
            return null;
        }
        return actividadEncontrada;
    }
    @Override
    public boolean save(Actividades actividad) {
        try (Connection connection = Sqlite3Manager.getConnection();
            PreparedStatement sentencia = connection.prepareStatement("INSERT INTO actividades VALUES(?,?,?,?,?,?,?)")) {
                sentencia.setInt(1, actividad.getId());
                sentencia.setString(2, actividad.getNombre());
                sentencia.setString(3, actividad.getTipoActividad());
                sentencia.setInt(4, actividad.getDuracion());
                sentencia.setDouble(5, actividad.getPrecio());
                sentencia.setInt(6, actividad.getPlazasMaximas());
                sentencia.setInt(7, actividad.getPlazasOcupadas());
                return sentencia.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("No se ha podido guardar la actividad"); 
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean update(Actividades actividad) {
        try (Connection connection = Sqlite3Manager.getConnection();
            PreparedStatement sentencia = connection.prepareStatement("UPDATE actividades SET nombre=?, tipo_actividad=?, duracion=?, precio=?, plazas_maximas=?, plazas_ocupadas=? WHERE id=?")) {
                sentencia.setString(1, actividad.getNombre());
                sentencia.setString(2, actividad.getTipoActividad());
                sentencia.setInt(3, actividad.getDuracion());
                sentencia.setDouble(4, actividad.getPrecio());
                sentencia.setInt(5, actividad.getPlazasMaximas());
                sentencia.setInt(6, actividad.getPlazasOcupadas());
                sentencia.setInt(7, actividad.getId());
                return sentencia.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("No se ha podido actualizar la actividad"); 
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean delete(Actividades actividad) {
        try (Connection connection = Sqlite3Manager.getConnection();
            PreparedStatement sentencia = connection.prepareStatement("DELETE FROM actividades WHERE id=?")) {
                sentencia.setInt(1, actividad.getId());
                return sentencia.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("No se ha podido borrar la actividad"); 
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public Reservas reservarPlaza(Integer idCliente, Integer idActividad) {
        try (Connection connection = Sqlite3Manager.getConnection();
            PreparedStatement sentencia = connection.prepareStatement("UPDATE actividades SET plazas_ocupadas = plazas_ocupadas + 1 WHERE id=? AND plazas_ocupadas < plazas_maximas")) {
                sentencia.setInt(1, idActividad);
                if (sentencia.executeUpdate() > 0) {
                    return new Reservas(null, idCliente, idActividad, new java.util.Date(), "ACTIVA");
                }
        } catch (Exception e) {
            System.err.println("No se ha podido reservar la plaza"); 
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public boolean cancelarPlaza(Integer idActividad) {
        try (Connection connection = Sqlite3Manager.getConnection();
            PreparedStatement sentencia = connection.prepareStatement("UPDATE actividades SET plazas_ocupadas = plazas_ocupadas - 1 WHERE id=? AND plazas_ocupadas > 0")) {
                sentencia.setInt(1, idActividad);
                return sentencia.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("No se ha podido actualizar la actividad"); 
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public List<Actividades> findCompletas() {
        List<Actividades> actividadesCompletas = new ArrayList<>();
        try (Connection connection = Sqlite3Manager.getConnection();
            PreparedStatement sentencia = connection.prepareStatement("SELECT * FROM actividades WHERE plazas_ocupadas=plazas_maximas")) {
                ResultSet resultado = sentencia.executeQuery();
                while(resultado.next()) {
                    Integer id = resultado.getInt("id");
                    String nombre = resultado.getString("nombre");
                    String tipoActividad = resultado.getString("tipo_actividad");
                    Integer duracion = resultado.getInt("duracion");
                    Double precio = resultado.getDouble("precio");
                    Integer plazasMaximas = resultado.getInt("plazas_maximas");
                    Integer plazasOcupadas = resultado.getInt("plazas_ocupadas");
                    Actividades actividad = new Actividades(id, nombre, tipoActividad, duracion, precio, plazasMaximas, plazasOcupadas);
                    actividadesCompletas.add(actividad);
                }
        } catch (Exception e) {
            System.err.println("No se han encontrado los resultados"); 
            e.printStackTrace();
            return new ArrayList<>();
        }
        return actividadesCompletas;
    }
    @Override
    public Double calcularIngresosTotales(Integer plazasOcupadas, Double precio) {
        Double ingresosTotales = plazasOcupadas * precio;
        return ingresosTotales;
    }
}
