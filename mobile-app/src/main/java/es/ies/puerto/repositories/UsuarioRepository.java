package es.ies.puerto.repositories;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import es.ies.puerto.modelos.Usuarios;
import es.ies.puerto.repositories.interfaces.IUsuarioRepository;
/**
 * @author AlejandroDonGar
 * @version 1.0.0
 * 
 * Clase UsuarioRepository donde implementamos los metodos de IUsuarioRepository
 */
public class UsuarioRepository implements IUsuarioRepository{

    @Override
    public List<Usuarios> findAll() {
        List<Usuarios> usuariosEncontrados = new ArrayList<>();
        try (Connection connection = Sqlite3Manager.getConnection();
            PreparedStatement sentencia = connection.prepareStatement("SELECT * FROM usuarios")) {
                ResultSet resultado = sentencia.executeQuery();
                while(resultado.next()) {
                    Integer id = resultado.getInt("id");
                    String nombre = resultado.getString("nombre");
                    String dni = resultado.getString("dni");
                    String email = resultado.getString("email");
                    String telefono = resultado.getString("telefono");
                    String tipoUsuario = resultado.getString("tipo_usuario");
                    Usuarios usuario = new Usuarios(id, nombre, dni, email, telefono, tipoUsuario);
                    usuariosEncontrados.add(usuario);
                }
        } catch (Exception e) {
            System.err.println("No se han encontrado los resultados"); 
            e.printStackTrace();
            return new ArrayList<>();
        }
        return usuariosEncontrados;
    }
    @Override
    public Usuarios findByID(Integer id) {
        Usuarios usuario = null;
        try (Connection connection = Sqlite3Manager.getConnection();
            PreparedStatement sentencia = connection.prepareStatement("SELECT * FROM usuarios WHERE id=?")) {
                sentencia.setInt(1, id);
                ResultSet resultado = sentencia.executeQuery();
                while(resultado.next()) {
                    String nombre = resultado.getString("nombre");
                    String dni = resultado.getString("dni");
                    String email = resultado.getString("email");
                    String telefono = resultado.getString("telefono");
                    String tipoUsuario = resultado.getString("tipo_usuario");
                    usuario = new Usuarios(id, nombre, dni, email, telefono, tipoUsuario);
                    return usuario;
                }
        } catch (Exception e) {
            System.err.println("No se ha encontrado el usuario"); 
            e.printStackTrace();
            return null;
        }
        return usuario;
    }
    @Override
    public boolean save(Usuarios usuario) {
        try (Connection connection = Sqlite3Manager.getConnection();
            PreparedStatement sentencia = connection.prepareStatement("INSERT INTO usuarios VALUES (?,?,?,?,?,?)")) {
                sentencia.setInt(1, usuario.getId());
                sentencia.setString(2, usuario.getNombre());
                sentencia.setString(3, usuario.getDni());
                sentencia.setString(4, usuario.getEmail());
                sentencia.setString(5, usuario.getTelefono());
                sentencia.setString(6, usuario.getTipoUsuario());
                return sentencia.executeUpdate() > 0;
            
        } catch (Exception e) {
            System.err.println("No se ha podido guardar el usuario"); 
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean update(Usuarios usuario) {
        try (Connection connection = Sqlite3Manager.getConnection();
            PreparedStatement sentencia = connection.prepareStatement("UPDATE usuarios SET nombre=?, dni=?, email=?, telefono=?, tipo_usuario=? WHERE id=?")) {
                sentencia.setString(1, usuario.getNombre());
                sentencia.setString(2, usuario.getDni());
                sentencia.setString(3, usuario.getEmail());
                sentencia.setString(4, usuario.getTelefono());
                sentencia.setString(5, usuario.getTipoUsuario());
                sentencia.setInt(6, usuario.getId());
                return sentencia.executeUpdate() > 0;
            
        } catch (Exception e) {
            System.err.println("No se ha podido actualizar el usuario"); 
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean delete(Usuarios usuario) {
        try (Connection connection = Sqlite3Manager.getConnection();
            PreparedStatement sentencia = connection.prepareStatement("DELETE FROM usuarios WHERE id=?")) {
                sentencia.setInt(1, usuario.getId());
                return sentencia.executeUpdate() > 0;
            
        } catch (Exception e) {
            System.err.println("No se ha podido eliminar el usuario"); 
            e.printStackTrace();
            return false;
        }
    }
}
