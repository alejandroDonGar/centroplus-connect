package es.ies.puerto.services;
import java.util.List;
import es.ies.puerto.modelos.Actividades;
import es.ies.puerto.modelos.Reservas;
import es.ies.puerto.modelos.Usuarios;
import es.ies.puerto.services.interfaces.IActividadService;
/**
 * @author AlejandroDonGar
 * @version 1.0.0
 * 
 * Clase ActividadService donde implementamos los metodos de IActividadService
 */
public class ActividadService implements IActividadService {

    @Override
    public List<Usuarios> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
    @Override
    public Usuarios findByID(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByID'");
    }
    @Override
    public boolean save(Usuarios usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }
    @Override
    public boolean update(Usuarios usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    @Override
    public boolean delete(Usuarios usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    @Override
    public Reservas reservarPlaza(Integer idCliente, Integer idActividad) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reservarPlaza'");
    }
    @Override
    public boolean cancelarPlaza(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancelarPlaza'");
    }
    @Override
    public List<Actividades> findCompletas(Integer plazasOcupadas) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findCompletas'");
    }
    @Override
    public Double calcularIngresosTotales(Integer plazasOcupadas, Double precio) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calcularIngresosTotales'");
    }
}
