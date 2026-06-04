package es.ies.puerto.services;
import java.util.List;
import es.ies.puerto.modelos.Actividades;
import es.ies.puerto.modelos.Reservas;
import es.ies.puerto.repositories.interfaces.IActividadRepository;
import es.ies.puerto.services.interfaces.IActividadService;
import es.ies.puerto.utils.Validaciones;
/**
 * @author AlejandroDonGar
 * @version 1.0.0
 * 
 * Clase ActividadService donde implementamos los metodos de IActividadService
 */
public class ActividadService implements IActividadService {

    IActividadRepository actividadRepository;

    public ActividadService() {
        this.actividadRepository = new es.ies.puerto.repositories.ActividadRepository();
    }

    public ActividadService(IActividadRepository actividadRepository) {
        this.actividadRepository = actividadRepository;
    }
    public List<Actividades> findAll() {
        return actividadRepository.findAll();
    }
    @Override
    public Actividades findByID(Integer id) {
        if(!Validaciones.esIntegerValido(id)) {
            return null;
        }
        return actividadRepository.findByID(id);
    }
    @Override
    public boolean save(Actividades actividad) {
        if(!Validaciones.esActividadValida(actividad)) {
            return false;
        }
        return actividadRepository.save(actividad);
    }
    @Override
    public boolean update(Actividades actividad) {
        if(!Validaciones.esActividadValida(actividad)) {
            return false;
        }
        return actividadRepository.update(actividad);
    }
    @Override
    public boolean delete(Actividades actividad) {
        if(!Validaciones.esActividadValida(actividad)) {
            return false;
        }
        return actividadRepository.delete(actividad);
    }
    @Override
    public Reservas reservarPlaza(Integer idCliente, Integer idActividad) {
        if(!Validaciones.esIntegerValido(idCliente) || !Validaciones.esIntegerValido(idActividad)) {
            return null;
        }
        return actividadRepository.reservarPlaza(idCliente, idActividad);
    }
    @Override
    public boolean cancelarPlaza(Integer idActividad) {
        if(!Validaciones.esIntegerValido(idActividad)) {
            return false;
        }
        return actividadRepository.cancelarPlaza(idActividad);
    }
    @Override
    public List<Actividades> findCompletas() {
        return actividadRepository.findCompletas();
    }
    @Override
    public Double calcularIngresosTotales(Integer plazasOcupadas, Double precio) {
        if(!Validaciones.esPlazasOcupadas(plazasOcupadas)||!Validaciones.esDoubleValido(precio)) {
            return 0.0;
        }
        return actividadRepository.calcularIngresosTotales(plazasOcupadas, precio);
    }
}
