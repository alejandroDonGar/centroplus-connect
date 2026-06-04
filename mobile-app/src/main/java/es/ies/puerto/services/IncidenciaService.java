package es.ies.puerto.services;
import java.util.ArrayList;
import java.util.List;

import es.ies.puerto.modelos.Incidencias;
import es.ies.puerto.repositories.interfaces.IIncidenciaRepository;
import es.ies.puerto.services.interfaces.IIncidenciaService;
import es.ies.puerto.utils.Validaciones;
/**
 * @author AlejandroDonGar y JavierReyPer
 * @version 1.0.0
 * 
 * Clase IncidenciaService donde implementamos los metodos de IIncidenciaService
 */
public class IncidenciaService implements IIncidenciaService{
    
    IIncidenciaRepository incidenciaRepository;

    public IncidenciaService() {
        this.incidenciaRepository = new es.ies.puerto.repositories.IncidenciaRepository();
    }

    public IncidenciaService(IIncidenciaRepository incidenciaRepository) {
        this.incidenciaRepository = incidenciaRepository;
    }
    public List<Incidencias> findAll() {
        return incidenciaRepository.findAll();
    }
    @Override
    public Incidencias findByID(Integer id) {
        if(!Validaciones.esIntegerValido(id)) {
            return null;
        }
        return incidenciaRepository.findByID(id);
    }
    @Override
    public boolean save(Incidencias incidencia) {
        if(!Validaciones.esIncidenciaValida(incidencia)) {
            return false;
        }
        return incidenciaRepository.save(incidencia);
    }
    @Override
    public boolean update(Incidencias incidencia) {
        if(!Validaciones.esIncidenciaValida(incidencia)) {
            return false;
        }
        return incidenciaRepository.update(incidencia);
    }
    @Override
    public boolean delete(Integer id) {
        if(!Validaciones.esIntegerValido(id)) {
            return false;
        }
        return incidenciaRepository.delete(id);
    }
    @Override
    public List<Incidencias> incidenciasPorIdUsuario(Integer idUsuario) {
        if(!Validaciones.esIntegerValido(idUsuario)) {
            return new ArrayList<>();
        }
        return incidenciaRepository.incidenciasPorIdUsuario(idUsuario);
    }

}
